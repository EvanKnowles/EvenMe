package za.co.knonchalant.evenme.image;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Converts a photo to a concentric-ring art style.
 *
 * Rings radiate from a focal point. Each ring is coloured using the original
 * image colour at that position. Thin dark separators sit between rings.
 *
 * The {@code perturbStrength} parameter blends radial distance with local
 * luminance so the rings warp around bright/dark features in the image,
 * producing the organic "topographic circle" look.
 */
public class ConcentricRingConverter {

    /** Pixels between ring centres. Smaller = denser rings. */
    private final int ringInterval;

    /** Width (in distance units) of the dark separator between rings. */
    private final int lineThickness;

    /** Hue rotation applied to every pixel. Negative shifts toward blue/purple. */
    private final float hueShift;

    /** Gaussian blur sigma on the luminance map — smooths ring edges. */
    private final float blurSigma;

    /**
     * How strongly local luminance warps the rings (0 = perfect circles,
     * higher values = rings bend to follow image features).
     */
    private final float perturbStrength;

    /** Focal point as fraction of image width/height (0.5 = centre). */
    private final float centerX;
    private final float centerY;

    public ConcentricRingConverter(int ringInterval, int lineThickness,
                                   float hueShift, float blurSigma,
                                   float perturbStrength,
                                   float centerX, float centerY) {
        this.ringInterval    = ringInterval;
        this.lineThickness   = lineThickness;
        this.hueShift        = hueShift;
        this.blurSigma       = blurSigma;
        this.perturbStrength = perturbStrength;
        this.centerX         = centerX;
        this.centerY         = centerY;
    }

    public static ConcentricRingConverter defaultStyle() {
        return new ConcentricRingConverter(8, 1, -0.12f, 2.0f, 30f, 0.5f, 0.5f);
    }

    public BufferedImage convert(BufferedImage input) {
        int w = input.getWidth();
        int h = input.getHeight();

        float cx = centerX * w;
        float cy = centerY * h;

        float[] lum = computeSmoothedLuminance(input, w, h, blurSigma);

        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                float l   = lum[y * w + x];
                int   rgb = input.getRGB(x, y);

                float dx   = x - cx;
                float dy   = y - cy;
                float dist = (float) Math.sqrt(dx * dx + dy * dy);

                // Warp the radial distance by local luminance so rings
                // bend around bright/dark image features.
                float effectiveDist = dist + (l / 255f) * perturbStrength;

                int posInBand = (int) effectiveDist % ringInterval;
                boolean onSeparator = posInBand < lineThickness;

                output.setRGB(x, y, onSeparator ? renderSeparator(rgb, l)
                                                 : renderBand(rgb, l, posInBand));
            }
        }

        return output;
    }

    // -------------------------------------------------------------------------
    // Pixel colouring
    // -------------------------------------------------------------------------

    private int renderSeparator(int rgb, float lum) {
        float[] hsb = toHsb(rgb);
        float hue = wrapHue(hsb[0] + hueShift);
        float sat = clamp(hsb[1] * 0.8f + 0.1f, 0, 1);
        float bri = clamp(lum / 255f * 0.06f, 0, 1);
        return Color.HSBtoRGB(hue, sat, bri);
    }

    private int renderBand(int rgb, float lum, int posInBand) {
        float[] hsb = toHsb(rgb);

        float hue = wrapHue(hsb[0] + hueShift);
        float sat = clamp(hsb[1] * 1.6f + 0.25f, 0, 1);

        float rawBri = lum / 255f;
        float bri    = 0.15f + rawBri * 0.50f;

        // Subtle brightness oscillation across the band
        bri += ((float) posInBand / ringInterval) * 0.06f;
        bri  = clamp(bri, 0, 1);

        return Color.HSBtoRGB(hue, sat, bri);
    }

    // -------------------------------------------------------------------------
    // Luminance + Gaussian blur
    // -------------------------------------------------------------------------

    private float[] computeSmoothedLuminance(BufferedImage img, int w, int h, float sigma) {
        float[] raw = new float[w * h];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                int rgb = img.getRGB(x, y);
                int r = (rgb >> 16) & 0xFF;
                int g = (rgb >> 8)  & 0xFF;
                int b =  rgb        & 0xFF;
                raw[y * w + x] = 0.299f * r + 0.587f * g + 0.114f * b;
            }
        }
        return sigma > 0 ? gaussianBlur(raw, w, h, sigma) : raw;
    }

    private float[] gaussianBlur(float[] data, int w, int h, float sigma) {
        int radius = Math.max(1, (int) Math.ceil(sigma * 3));
        int kSize  = 2 * radius + 1;
        float[] kernel = new float[kSize];
        float sum = 0;
        for (int i = 0; i < kSize; i++) {
            float x = i - radius;
            kernel[i] = (float) Math.exp(-(x * x) / (2 * sigma * sigma));
            sum += kernel[i];
        }
        for (int i = 0; i < kSize; i++) kernel[i] /= sum;

        float[] tmp = new float[w * h];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                float v = 0;
                for (int k = 0; k < kSize; k++) {
                    int sx = clampIdx(x + k - radius, w);
                    v += data[y * w + sx] * kernel[k];
                }
                tmp[y * w + x] = v;
            }
        }

        float[] out = new float[w * h];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                float v = 0;
                for (int k = 0; k < kSize; k++) {
                    int sy = clampIdx(y + k - radius, h);
                    v += tmp[sy * w + x] * kernel[k];
                }
                out[y * w + x] = v;
            }
        }
        return out;
    }

    // -------------------------------------------------------------------------
    // Helpers
    // -------------------------------------------------------------------------

    private static float[] toHsb(int rgb) {
        return Color.RGBtoHSB((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF, null);
    }

    private static float wrapHue(float h) {
        return ((h % 1.0f) + 1.0f) % 1.0f;
    }

    private static float clamp(float v, float lo, float hi) {
        return v < lo ? lo : (v > hi ? hi : v);
    }

    private static int clampIdx(int i, int max) {
        return i < 0 ? 0 : (i >= max ? max - 1 : i);
    }
}

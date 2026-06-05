package za.co.knonchalant.evenme.image;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Converts a photo to an Archimedean spiral art style.
 *
 * One continuous spiral line expands from a focal point. Each point on the
 * spiral is coloured using the original image colour at that location.
 *
 * Algorithm: for every pixel, compute polar coords (r, θ). The spiral
 * coordinate  s = r − (θ/2π) × armSpacing  is continuous across the ±π
 * angle boundary, so  s mod armSpacing  gives a smooth band position that
 * forms a single unbroken outward spiral rather than closed rings.
 */
public class SpiralConverter {

    /** Pixels between successive spiral arms. Smaller = tighter spiral. */
    private final int armSpacing;

    /** Width (in spiral-distance units) of the dark separator line. */
    private final int lineThickness;

    /** Hue rotation applied to every pixel. Negative shifts toward blue/purple. */
    private final float hueShift;

    /** Gaussian blur sigma on luminance — smooths spiral edge aliasing. */
    private final float blurSigma;

    /**
     * How much local luminance warps the spiral arms (0 = perfect spiral,
     * higher = arms bend around image features).
     */
    private final float perturbStrength;

    /** Focal point as fraction of image dimensions (0.5 = centre). */
    private final float centerX;
    private final float centerY;

    public SpiralConverter(int armSpacing, int lineThickness,
                           float hueShift, float blurSigma,
                           float perturbStrength,
                           float centerX, float centerY) {
        this.armSpacing      = armSpacing;
        this.lineThickness   = lineThickness;
        this.hueShift        = hueShift;
        this.blurSigma       = blurSigma;
        this.perturbStrength = perturbStrength;
        this.centerX         = centerX;
        this.centerY         = centerY;
    }

    public static SpiralConverter defaultStyle() {
        return new SpiralConverter(10, 2, -0.12f, 2.0f, 20f, 0.5f, 0.5f);
    }

    public BufferedImage convert(BufferedImage input) {
        int w = input.getWidth();
        int h = input.getHeight();

        float cx = centerX * w;
        float cy = centerY * h;

        float[] lum = computeSmoothedLuminance(input, w, h, blurSigma);

        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        // Radial advance per radian for an Archimedean spiral
        double b = armSpacing / (2.0 * Math.PI);

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                float  l   = lum[y * w + x];
                int    rgb = input.getRGB(x, y);

                double dx    = x - cx;
                double dy    = y - cy;
                double r     = Math.sqrt(dx * dx + dy * dy);
                double theta = Math.atan2(dy, dx);   // range (−π, π)

                // Spiral coordinate — continuous across the ±π seam
                double spiralCoord = r - b * theta;

                // Warp by luminance so arms deform around image features
                spiralCoord += (l / 255.0) * perturbStrength;

                // Position within the current arm band [0, armSpacing)
                int posInBand = (int) ((spiralCoord % armSpacing + armSpacing) % armSpacing);
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
        float sat = clamp(hsb[1] * 0.6f + 0.1f, 0, 1);
        float bri = clamp(lum / 255f * 0.04f, 0, 1);
        return Color.HSBtoRGB(hue, sat, bri);
    }

    private int renderBand(int rgb, float lum, int posInBand) {
        float[] hsb = toHsb(rgb);

        // Small hue shift across each arm makes adjacent arms visually distinct
        float armPhase = (float) posInBand / armSpacing;
        float hue = wrapHue(hsb[0] + hueShift + armPhase * 0.04f);

        float sat = clamp(hsb[1] * 1.8f + 0.2f, 0, 1);

        // Brightness: darker near separator, brighter toward arm centre
        float rawBri = lum / 255f;
        float bri    = 0.12f + rawBri * 0.55f + armPhase * 0.10f;
        bri = clamp(bri, 0, 1);

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

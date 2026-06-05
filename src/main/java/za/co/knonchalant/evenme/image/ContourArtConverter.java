package za.co.knonchalant.evenme.image;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Converts a photo to a topographic contour-line art style.
 *
 * The algorithm divides the luminance range into bands. Thin dark separators
 * appear at each band boundary; the bands themselves are rendered with the
 * original colour, hue-shifted toward cool tones and heavily stylised to
 * produce the psychedelic engraving look.
 */
public class ContourArtConverter {

    /** Luminance levels per band (smaller = more lines, denser effect). */
    private final int contourInterval;

    /** Luminance levels wide for the dark separator line (usually 1–2). */
    private final int lineThickness;

    /** Amount to rotate hue toward cool (purple/indigo). Range -1..1. */
    private final float hueShift;

    /** Gaussian blur sigma applied to luminance before contouring (smooths lines). */
    private final float blurSigma;

    public ContourArtConverter(int contourInterval, int lineThickness, float hueShift, float blurSigma) {
        this.contourInterval = contourInterval;
        this.lineThickness   = lineThickness;
        this.hueShift        = hueShift;
        this.blurSigma       = blurSigma;
    }

    /** Default settings that approximate the reference style. */
    public static ContourArtConverter defaultStyle() {
        return new ContourArtConverter(8, 1, -0.12f, 2.0f);
    }

    public BufferedImage convert(BufferedImage input) {
        int w = input.getWidth();
        int h = input.getHeight();

        float[] lum = computeSmoothedLuminance(input, w, h, blurSigma);

        BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                float l = lum[y * w + x];
                int rgb = input.getRGB(x, y);

                // Position within a band: 0 at the separator, increases into the band
                int posInBand = (int) l % contourInterval;
                boolean onSeparator = posInBand < lineThickness;

                int outRgb = onSeparator ? renderSeparator(rgb, l) : renderBand(rgb, l, posInBand);
                output.setRGB(x, y, outRgb);
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
        // Very dark: the separator forms the "engraved line"
        float bri = clamp(lum / 255f * 0.08f, 0, 1);
        return Color.HSBtoRGB(hue, sat, bri);
    }

    private int renderBand(int rgb, float lum, int posInBand) {
        float[] hsb = toHsb(rgb);

        // Shift hue toward cool (negative shift moves toward blue/indigo)
        float hue = wrapHue(hsb[0] + hueShift);

        // Boost saturation for vivid, painterly look
        float sat = clamp(hsb[1] * 1.6f + 0.25f, 0, 1);

        // Brightness: keep image readable but stay in the 0.15–0.65 range
        // so the overall image stays dark as in the reference
        float rawBri = lum / 255f;
        float bri = 0.15f + rawBri * 0.50f;

        // Add a gentle brightness oscillation within the band so adjacent
        // bands are subtly distinguishable even when colour is similar
        float phase = (float) posInBand / contourInterval;
        bri += phase * 0.06f;

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

        // Horizontal pass
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

        // Vertical pass
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

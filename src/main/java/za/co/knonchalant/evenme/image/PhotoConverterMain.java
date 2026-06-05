package za.co.knonchalant.evenme.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * CLI entry point for the photo-to-art converters.
 *
 * Usage (Archimedean spiral — default):
 *   java PhotoConverterMain <input> <output> [armSpacing] [lineThickness] [hueShift] [blurSigma] [perturbStrength] [centerX] [centerY]
 *
 * Usage (concentric rings):
 *   java PhotoConverterMain <input> <output> --rings [interval] [lineThickness] [hueShift] [blurSigma] [perturbStrength] [centerX] [centerY]
 *
 * Usage (luminance contours):
 *   java PhotoConverterMain <input> <output> --contour [interval] [lineThickness] [hueShift] [blurSigma]
 *
 * Spiral defaults:
 *   armSpacing      = 10     pixels between spiral arms
 *   lineThickness   = 2      separator width in pixels
 *   hueShift        = -0.12  negative → blue/purple
 *   blurSigma       = 2.0    Gaussian smoothing on luminance
 *   perturbStrength = 20     how strongly arms warp around image features
 *   centerX/Y       = 0.5    focal point (0..1 fraction of image size)
 */
public class PhotoConverterMain {

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            printUsage();
            System.exit(1);
        }

        String inputPath  = args[0];
        String outputPath = args[1];

        BufferedImage input = ImageIO.read(new File(inputPath));
        if (input == null) {
            System.err.println("Could not read image: " + inputPath);
            System.exit(1);
        }

        BufferedImage output;

        if (args.length > 2 && args[2].equals("--contour")) {
            int   interval  = args.length > 3 ? Integer.parseInt(args[3]) : 8;
            int   lineThick = args.length > 4 ? Integer.parseInt(args[4]) : 1;
            float hueShift  = args.length > 5 ? Float.parseFloat(args[5]) : -0.12f;
            float blurSigma = args.length > 6 ? Float.parseFloat(args[6]) : 2.0f;

            System.out.printf("Converting %s (%dx%d) → %s  [contour mode]%n",
                    inputPath, input.getWidth(), input.getHeight(), outputPath);
            System.out.printf("  interval=%d  lineThickness=%d  hueShift=%.2f  blurSigma=%.1f%n",
                    interval, lineThick, hueShift, blurSigma);

            output = new ContourArtConverter(interval, lineThick, hueShift, blurSigma).convert(input);

        } else if (args.length > 2 && args[2].equals("--rings")) {
            int   interval  = args.length > 3 ? Integer.parseInt(args[3]) : 8;
            int   lineThick = args.length > 4 ? Integer.parseInt(args[4]) : 1;
            float hueShift  = args.length > 5 ? Float.parseFloat(args[5]) : -0.12f;
            float blurSigma = args.length > 6 ? Float.parseFloat(args[6]) : 2.0f;
            float perturb   = args.length > 7 ? Float.parseFloat(args[7]) : 30f;
            float cx        = args.length > 8 ? Float.parseFloat(args[8]) : 0.5f;
            float cy        = args.length > 9 ? Float.parseFloat(args[9]) : 0.5f;

            System.out.printf("Converting %s (%dx%d) → %s  [concentric rings]%n",
                    inputPath, input.getWidth(), input.getHeight(), outputPath);
            System.out.printf("  interval=%d  lineThickness=%d  hueShift=%.2f  blurSigma=%.1f  perturb=%.1f  center=(%.2f,%.2f)%n",
                    interval, lineThick, hueShift, blurSigma, perturb, cx, cy);

            output = new ConcentricRingConverter(interval, lineThick, hueShift, blurSigma, perturb, cx, cy).convert(input);

        } else {
            int   spacing   = args.length > 2 ? Integer.parseInt(args[2]) : 18;
            int   lineThick = args.length > 3 ? Integer.parseInt(args[3]) : 2;
            float hueShift  = args.length > 4 ? Float.parseFloat(args[4]) : 0f;
            float blurSigma = args.length > 5 ? Float.parseFloat(args[5]) : 2.0f;
            float perturb   = args.length > 6 ? Float.parseFloat(args[6]) : 20f;
            float cx        = args.length > 7 ? Float.parseFloat(args[7]) : 0.5f;
            float cy        = args.length > 8 ? Float.parseFloat(args[8]) : 0.5f;

            System.out.printf("Converting %s (%dx%d) → %s  [spiral]%n",
                    inputPath, input.getWidth(), input.getHeight(), outputPath);
            System.out.printf("  armSpacing=%d  lineThickness=%d  hueShift=%.2f  blurSigma=%.1f  perturb=%.1f  center=(%.2f,%.2f)%n",
                    spacing, lineThick, hueShift, blurSigma, perturb, cx, cy);

            output = new SpiralConverter(spacing, lineThick, hueShift, blurSigma, perturb, cx, cy).convert(input);
        }

        String format = outputPath.toLowerCase().endsWith(".png") ? "PNG" : "JPEG";
        ImageIO.write(output, format, new File(outputPath));
        System.out.println("Done: " + outputPath);
    }

    private static void printUsage() {
        System.err.println("Usage: PhotoConverterMain <input> <output> [armSpacing] [lineThickness] [hueShift] [blurSigma] [perturbStrength] [centerX] [centerY]");
        System.err.println("       PhotoConverterMain <input> <output> --rings   [interval] [lineThickness] [hueShift] [blurSigma] [perturbStrength] [centerX] [centerY]");
        System.err.println("       PhotoConverterMain <input> <output> --contour [interval] [lineThickness] [hueShift] [blurSigma]");
    }
}

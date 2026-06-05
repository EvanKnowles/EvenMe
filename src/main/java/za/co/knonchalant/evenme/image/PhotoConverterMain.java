package za.co.knonchalant.evenme.image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * CLI entry point for the contour-art photo converter.
 *
 * Usage:
 *   java PhotoConverterMain <input> <output> [interval] [lineThickness] [hueShift] [blurSigma]
 *
 * Defaults produce the dense psychedelic engraving style from the reference image:
 *   interval      = 8      (luminance levels per band; smaller = more lines)
 *   lineThickness = 1      (separator width in luminance levels)
 *   hueShift      = -0.12  (negative shifts toward blue/purple)
 *   blurSigma     = 2.0    (Gaussian smoothing on luminance; makes contours smooth)
 *
 * Examples:
 *   java PhotoConverterMain portrait.jpg out.png
 *   java PhotoConverterMain portrait.jpg out.png 6 1 -0.20 3.0
 */
public class PhotoConverterMain {

    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("Usage: PhotoConverterMain <input> <output> [interval] [lineThickness] [hueShift] [blurSigma]");
            System.exit(1);
        }

        String inputPath  = args[0];
        String outputPath = args[1];
        int    interval   = args.length > 2 ? Integer.parseInt(args[2])   : 8;
        int    lineThick  = args.length > 3 ? Integer.parseInt(args[3])   : 1;
        float  hueShift   = args.length > 4 ? Float.parseFloat(args[4])   : -0.12f;
        float  blurSigma  = args.length > 5 ? Float.parseFloat(args[5])   : 2.0f;

        BufferedImage input = ImageIO.read(new File(inputPath));
        if (input == null) {
            System.err.println("Could not read image: " + inputPath);
            System.exit(1);
        }

        System.out.printf("Converting %s (%dx%d) → %s%n",
                inputPath, input.getWidth(), input.getHeight(), outputPath);
        System.out.printf("  interval=%d  lineThickness=%d  hueShift=%.2f  blurSigma=%.1f%n",
                interval, lineThick, hueShift, blurSigma);

        ContourArtConverter converter = new ContourArtConverter(interval, lineThick, hueShift, blurSigma);
        BufferedImage output = converter.convert(input);

        String format = outputPath.toLowerCase().endsWith(".png") ? "PNG" : "JPEG";
        ImageIO.write(output, format, new File(outputPath));
        System.out.println("Done: " + outputPath);
    }
}

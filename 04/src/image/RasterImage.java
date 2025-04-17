package image;

import manipulation.Normalizable;
import manipulation.Rotatable;
import manipulation.RotationDirection;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * This class is a wrapper for the BufferedImage.
 * It implements additional manipulation interfaces: {@link Normalizable}, {@link Rotatable},
 * as well as {@link Savable}.
 */
public class RasterImage implements Savable, Rotatable, Normalizable {

    // contents, do not change
    private BufferedImage image;

    /**
     * Constructs the object by loading a specified PNG image.
     * @param imagePath Path to the PNG image.
     * @throws IllegalArgumentException If the imagePath is null or empty, or the path does not exist, or the path is not an image.
     */
    public RasterImage(String imagePath) {
        // do not change the constructor

        if (imagePath == null || imagePath.isEmpty()) {
            throw new IllegalArgumentException("Image path cannot be null or empty");
        }
        // load the image
        BufferedImage tmpImage;
        try (FileInputStream fis = new FileInputStream(imagePath)) {
            tmpImage = ImageIO.read(fis);
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not load image " + imagePath);
        }
        image = tmpImage;
    }

    /**
     * Get the underlying BufferedImage.
     * @return image
     */
    public BufferedImage getImage() {
        // do not change this method

        return image;
    }

    /**
     * Convert rgn value to an array.
     * @param rgb value to convert
     * @return array with R, G and B values respectively
     */
    private static int[] rgbToArray(int rgb) {
        // do not change this method

        return new int[]{(rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF};
    }

    /**
     * Convert rgb array to a value.
     * Alpha channel is set to 0.
     * Since the alpha channel is ignored, this is intended.
     * @param array an rgb array
     * @return converted value
     * @throws IllegalArgumentException if the array is invalid or contains invalid values.
     */
    private static int arrayToRgb(int[] array) {
        // do not change this method

        if (array == null || array.length != 3 ||
                array[0] < 0 || array[1] < 0 || array[2] < 0 ||
                array[0] > 255 || array[1] > 255 || array[2] > 255)
            throw new IllegalArgumentException("Invalid RGB array!");
        else return (array[0] << 16) | (array[1] << 8) | array[2];
    }

    /**
     * Save the image to the specified path in the PNG format.
     * @param path Path to save the object's content to.
     * @throws IllegalArgumentException if the path is invalid.
     */
    @Override
    public void save(String path) {
        // do not change this method

        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("Image path cannot be null or empty");
        }
        try(FileOutputStream fos = new FileOutputStream(path)) {
            ImageIO.write(image, "png", fos);
        } catch (IOException e) {
            throw new IllegalArgumentException("Could not save image " + path);
        }
    }

    @Override
    public void rotate(RotationDirection direction) {
        if (direction == null) throw new IllegalArgumentException("Rotation direction cannot be null");


        BufferedImage tmpImage = new BufferedImage(image.getHeight(), image.getWidth(), image.getType());

        if (direction==RotationDirection.LEFT) {
            for (int i=0; i<image.getWidth(); i++) {
                for (int p=0; p<image.getHeight(); p++) {
                    tmpImage.setRGB(p, i, image.getRGB(image.getWidth()-1-i, p));
                }
            }

        } else if (direction==RotationDirection.RIGHT) {
            for (int i=0; i<image.getWidth(); i++) {
                for (int p=0; p<image.getHeight(); p++) {
                    tmpImage.setRGB(p, i, image.getRGB(i, image.getHeight()-1-p));
                }
            }
        }

        image = tmpImage;
    }

    @Override
    public void normalize() {
        for (int i=0; i<image.getHeight(); i++) {
            for (int p=0; p<image.getWidth(); p++) {

                int[] rgb = rgbToArray(image.getRGB(p, i));
                int sum = rgb[0] + rgb[1] + rgb[2];

                // delenie nulou
                if (sum > 0) {
                    int R = (rgb[0] * 255) / sum;
                    int G = (rgb[1] * 255) / sum;
                    int B = (rgb[2] * 255) / sum;


                    R = Math.min(255, Math.max(0, R));
                    G = Math.min(255, Math.max(0, G));
                    B = Math.min(255, Math.max(0, B));

                    int[] normalizedRgb = {R, G, B};
                    image.setRGB(p, i, arrayToRgb(normalizedRgb));
                }

                // if pixel is black leave it as it`s
            }
        }
    }
}

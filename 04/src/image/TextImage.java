package image;

import manipulation.Rotatable;
import manipulation.RotationDirection;
import manipulation.Substitutable;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * This class represents a textual image, for instance ascii art.
 * It implements {@link Substitutable}, {@link Rotatable} and {@link Savable}.
 */
public class TextImage implements Savable, Rotatable, Substitutable {
    private char[][] image;
    private int width;
    private int height;

    /**
     * Construct a TextImage from the specified text file.
     * @param imagePath path to the text file to load
     * @throws IllegalArgumentException if the imagePath is invalid or the file is empty.
     */
    public TextImage(String imagePath) {
        if (imagePath == null || imagePath.isEmpty()) throw new IllegalArgumentException();

        try {
            File file = new File(imagePath);
            Scanner inFile = new Scanner(file);

            int w = 0;
            int h = 0;

            while (inFile.hasNextLine()) {
                String line = inFile.nextLine();
                if (line.length()>w) w=line.length();
                h++;
            }
            inFile.close();



            if (h == 0) throw new IllegalArgumentException("File is empty");

            this.width = w;
            this.height = h;
            this.image = new char[height][width];


            inFile = new Scanner(file);
            int i = 0;
            while (inFile.hasNextLine() && i <height) {
                String line = inFile.nextLine();

                for (int p = 0; p<line.length() && p<width; p++) {

                    if (line.charAt(p)>32 && line.charAt(p)<127) {
                        image[i][p] = line.charAt(p);
                    } else {
                        image[i][p] = ' ';
                    }
                }

                i++;
            }
            inFile.close();

        } catch (Exception e) {
            throw new IllegalArgumentException("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Get the image.
     * @return image
     */
    public char[][] getImage() {
        return image;
    }

    /**
     * Get the image's width.
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the image's height.
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get the character at the specified coordinates.
     * @param x the x coordinate
     * @param y the y coordinate
     * @return the symbol at the position (x, y)
     * @throws IllegalArgumentException if the coordinates are out of bounds
     */
    public char getSymbol(int x, int y) {
        if (x<0 || x>=width || y<0 || y>=height) {
            throw new IllegalArgumentException("Coordinates out of bounds");
        }
        return image[y][x];
    }

    @Override
    public void save(String path) {
        if (path == null || path.isEmpty()) throw new IllegalArgumentException();


        try {
            FileWriter file = new FileWriter(path);

            for (int i = 0; i < height; i++) {
                for (int p = 0; p < width; p++) file.write(image[i][p]);
                if (i < height-1) file.write(System.lineSeparator()); // '\0'
            }

            file.close();
        } catch (Exception e) {
            throw new IllegalArgumentException("Error saving file: " + e.getMessage());
        }
    }



    @Override
    public void rotate(RotationDirection direction) {
        if (direction == null) {
            throw new IllegalArgumentException("Rotation direction cannot be null");
        }

        char[][] rotated = new char[width][height];

        if (direction == RotationDirection.LEFT) {
            for (int i= 0; i<width; i++) {
                for (int p = 0; p<height; p++) {
                    rotated[i][p] = image[p][width-1-i];
                }
            }

        } else if (direction == RotationDirection.RIGHT) {
            for (int i= 0; i< width; i++) {
                for (int p = 0; p< height; p++) {
                    rotated[i][p] = image[height-1-p][i];
                }
            }
        }

        int tmp = width;
        width = height;
        height = tmp;

        this.image = rotated;
    }

    @Override
    public void substituteSymbol(char oldSymbol, char newSymbol) {
        for (int i = 0; i < height; i++) {
            for (int p = 0; p < width; p++) {
                if (image[i][p] == oldSymbol) image[i][p] = newSymbol;
            }
        }
    }
}
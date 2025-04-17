import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Main {
    public static void main(String[] args) {
        String pathToAssignment2 = "archiv.zip"; // sem treba doplnit cestu k archivu
                                              // obzvlast zvlastne by bolo, keby tato cesta existovala...
        try (ZipFile zipFile = new ZipFile(pathToAssignment2)) {
            String[] contents = zipFile
                    .stream()
                    .map(ZipEntry::getName)
                    .filter(path -> !path.contains("__MACOSX"))
                    .sorted(Comparator.comparingInt(String::length))
                    .toArray(String[]::new);
            System.out.println("Obsah načítaného archívu: " + Arrays.toString(contents));

        } catch (IOException e) {
            System.err.println("Nebol nájdený súbor s archívom!");
            System.exit(1);
        }

    }
}
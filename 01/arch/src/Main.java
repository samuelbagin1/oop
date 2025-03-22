
public class Main {
    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4, 5};
        int[] b = new int[]{6, -4, 2, -8, 6};
        System.out.println("Skalarny sucin vektorov a a b je: " + VectorUtils.dotProduct(a, b));

    }
}

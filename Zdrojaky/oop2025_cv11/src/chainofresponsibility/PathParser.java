package chainofresponsibility;

import java.util.Arrays;

public class PathParser {
    public static String[] parse(String path) {
        String[] parts = path.split("/");
        return Arrays.stream(parts)
                .filter(text -> ! text.isEmpty())
                .toArray(String[]::new);
    }
}

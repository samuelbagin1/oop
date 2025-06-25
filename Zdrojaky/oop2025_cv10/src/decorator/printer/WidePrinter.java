package decorator.printer;

public class WidePrinter extends PrinterDecorator {
    private String spaces;

    public WidePrinter(Printer wrappedPrinter, int wide) {
        super(wrappedPrinter);
        spaces = " ".repeat(wide);
    }

    @Override
    public void print(String text) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < text.length() - 1; ++ i) {
            builder.append(text.charAt(i));
            builder.append(spaces);
        }
        if (text.length() >= 1) {
            builder.append(text.charAt(text.length() - 1));
        }
        wrappedPrinter.print(builder.toString());
    }

    @Override
    public void printInBrackets(String text) {
        if (text.length() >= 1) {
            StringBuilder builder = new StringBuilder();
            builder.append(spaces);
            for (int i = 0; i < text.length(); ++ i) {
                builder.append(text.charAt(i));
                builder.append(spaces);
            }
            text = builder.toString();
        }
        wrappedPrinter.printInBrackets(text);
    }
}

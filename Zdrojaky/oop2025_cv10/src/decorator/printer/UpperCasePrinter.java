package decorator.printer;

public class UpperCasePrinter extends PrinterDecorator {
    public UpperCasePrinter(Printer wrappedPrinter) {
        super(wrappedPrinter);
    }

    @Override
    public void print(String text) {
        String upper = text.toUpperCase();
        wrappedPrinter.print(upper);
    }

    @Override
    public void printInBrackets(String text) {
        String upper = text.toUpperCase();
        wrappedPrinter.printInBrackets(upper);
    }
}

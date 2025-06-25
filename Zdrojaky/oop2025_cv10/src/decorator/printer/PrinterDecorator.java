package decorator.printer;

public abstract class PrinterDecorator implements Printer {
    protected Printer wrappedPrinter;

    public PrinterDecorator(Printer wrappedPrinter) {
        this.wrappedPrinter = wrappedPrinter;
    }
}

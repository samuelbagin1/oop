package decorator.printer;

public class SimplePrinter implements Printer{

    @Override
    public void print(String text) {
        System.out.println(text);
    }

    @Override
    public void printInBrackets(String text) {
        System.out.println("[" + text + "]");
    }
}

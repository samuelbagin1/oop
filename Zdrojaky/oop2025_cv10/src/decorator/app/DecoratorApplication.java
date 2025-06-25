package decorator.app;

import decorator.printer.Printer;
import decorator.printer.SimplePrinter;
import decorator.printer.UpperCasePrinter;
import decorator.printer.WidePrinter;

public class DecoratorApplication {
    public static void main(String[] args) {
        Printer simple = new SimplePrinter();
        simple.print("tlacim");

        Printer upper = new UpperCasePrinter(simple);
        upper.print("tlacim");

        Printer wide = new WidePrinter(simple, 2);
        wide.print("tlacim");

        Printer combi = new WidePrinter(upper, 2);
        combi.print("tlacim");

        simple.printInBrackets("oop");
        upper.printInBrackets("oop");
        wide.printInBrackets("oop");
        combi.printInBrackets("oop");
    }
}

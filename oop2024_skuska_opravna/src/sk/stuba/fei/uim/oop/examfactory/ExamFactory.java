package sk.stuba.fei.uim.oop.examfactory;

import sk.stuba.fei.uim.oop.figure.CoatDecorator;
import sk.stuba.fei.uim.oop.figure.Figure;
import sk.stuba.fei.uim.oop.figure.ShoesDecorator;
import sk.stuba.fei.uim.oop.figurefactory.BlueFigureFactory;
import sk.stuba.fei.uim.oop.figurefactory.FigureFactory;
import sk.stuba.fei.uim.oop.figurefactory.RedFigureFactory;

// Tovaren na vyrobu objektov pre automaticke testovanie
public class ExamFactory {

    // Vytvori a vrati tovaren na cervene figurky
    public static FigureFactory createRedFigureFactory() {
        return new RedFigureFactory(); // new RedFigureFactory(); TODO upravte
    }

    // Vytvori a vrati tovaren na modre figurky
    public static FigureFactory createBlueFigureFactory() {
        return new BlueFigureFactory(); // new BlueFigureFactory(); TODO upravte
    }

    // Vytvori a vrati zrychlujuci plast
    public static Figure createCoat(Figure figure) {
        return new CoatDecorator(figure); // new CoatDecorator(figure); TODO upravte
    }

    // Vytvori a vrati skakacie topanky
    public static Figure createShoes(Figure figure) {
        return new ShoesDecorator(figure); // new ShoesDecorator(figure); TODO upravte
    }
}

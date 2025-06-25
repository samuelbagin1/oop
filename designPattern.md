# <ins>Design Patterns</ins>

GUI - graphical user interface

## STATE
***stavovy automat, menime stavy podla spravania objektu***

behavorial design pattern that implements a state machine. Each
state is implemented as a derived class of a static interface, and
state transitions are handled by invoking methods defined by the interface.

```java
// all possible states
abstract class State {
    void powerButtonPressed(Phone phone) {
    }
}

// behavior of specific state
class LockedState extends State {
    @Override
    void powerButtonPressed(Phone phone) {
        phone.setState(new WaitingForPasswordState());
    }
}
```

##
## ITERATOR
***iteracia cez kolekciu objektov, bez toho aby sme vedeli ako je kolekcia implementovana***

provides a way to sequentially access elements in a collection without
exposing its internal implementation

```java
interface Collection {
    Iterator createIterator();
}

// concrete collection class
class BookShelf implements Collection {
    private List<String> books = new ArrayList<>();
    
    public void addBook(String book) {
        books.add(book);
    }
    
    @Override
    public Iterator createIterator() {
        return new BookShelfIterator(this);
    }
    
    public List<String> getBooks() {
        return books;
    }
}





interface Iterator {
    boolean hasNext();
    String next();
}

class BookShelfIterator implements Iterator {
    private BookShelf bookShelf;
    private int index;

    public BookShelfIterator(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
        this.index = 0;
    }

    @Override
    public boolean hasNext() {
        return index < bookShelf.getBooks().size();
    }

    @Override
    public String next() {
        if (hasNext()) {
            return bookShelf.getBooks().get(index++);
        }
        
        return null;
    }
}


public class Main {
    public static void main(String[] args) {
        BookShelf bookShelf = new BookShelf();
        bookShelf.addBook("Design Patterns");
        bookShelf.addBook("Effective Java");
        
        Iterator iterator = bookShelf.createIterator();
        
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
```

##
## CHAIN OF RESPONSIBILITY
***reťazec zodpovednosti, spracovanie udalosti cez viacero handlerov, každý handler sa rozhodne či spracuje alebo posunie ďalej
---- viacero kariet linknutych na sluzbu z rozmych bank, hlada ktora to zaplati***

Single Responsibility Principle

chain of objects. A request enters from one end of the chain
and is passed along until it finds a suitable handler.

Behavioral design pattern that decouples the sender and receiver
of a request by connecting multiple handlers in a chain. The
request is then passed along the chain until it is proccessed by one of the handlers.

```java
abstract class Account {
    protected Account successor;
    protected double balance;
    
    public void setNext(Account account) {
        this.successor = account;
    }
    
    public void pay(double amountToPay) {
        if (canPay(amountToPay)) {
            System.out.printf("Paid %s using %s%n", amountToPay, this.getClass().getSimpleName());
        } else if (successor != null) {
            System.out.printf("Cannot pay using %s. Proceeding ...%n", this.getClass().getSimpleName());
            successor.pay(amountToPay);
        } else {
            throw new RuntimeException("None of the accounts have enough balance");
        }
    }
    
    public boolean canPay(double amount) {
        return this.balance >= amount;
    }
}



class Bank extends Account {
    public Bank(double balance) {
        this.balance = balance;
    }
}

class PayPal extends Account {
    public PayPal(double balance) {
        this.balance = balance;
    }
}

class Bitcoin extends Account {
    public Bitcoin(double balance) {
        this.balance = balance;
    }
}



public class Main {
    public static void main(String[] args) {
        Account bank = new Bank(100);
        Account paypal = new PayPal(50);
        Account bitcoin = new Bitcoin(30);
        
        bank.setNext(paypal);
        paypal.setNext(bitcoin);
        
        bank.pay(120); // tries to pay using bank, then paypal, then bitcoin
    }
}
```


##
## PROXY

***zastupca, objekt ktory zastupuje iny objekt, napr. pre lazy loading, alebo ochranu pristupu, checking preconditions***

additional operations when accessing object

The proxy pattern provides a proxy for other objects to control access to them.

Class that functions as an interface to something else. A proxy
is a wrapper or agent object that the client calls to access
the real serving object behind the scenes. Using proxy can simply
forward requests to the actual object or provide additional logic.

```java
interface Door {
    void open(String param);
    void close();
}


class LabDoor implements Door {
    public void open(String param) {
        System.out.println("Opening lab door");
    }
    
    public void close() {
        System.out.println("Closing lab door");
    }
}



class SecuredDoor implements Door {
    private Door door;
    
    public SecuredDoor(Door door) {
        this.door = door;
    }
    
    public void open(String password) {
        if (authenticate(password)) {
            door.open("");
        } else {
            System.out.println("Access denied");
        }
    }
    
    private boolean authenticate(String password) {
        return "secret".equals(password);
    }
    
    public void close() {
        door.close();
    }
}



public class Main {
    public static void main(String[] args) {
        Door door = new SecuredDoor(new LabDoor());
        door.open("wrong_password"); // Access denied
        
        door.open("secret"); // Opening lab door
        door.close();
    }
}
```

##
## FLYWEIGHT

***zdieľanie objektov, napr. pre optimalizáciu pamäte, zdieľanie nejakých dát medzi viacerými objektami***

Reduces memory usage by sharing objects. it uses a factory to manage
these shared objects and ensures that each object is created only once.

Structural design pattern that efficiently supports a large number of
fine-grained objects by sharing them across multiple contexts.

```java
public interface Circle {
    void draw();
    void setPosition(int x, int y);
}

public class ConcreteCircle implements Circle {
    private String color;
    private int x;
    private int y;
    
    public ConcreteCircle(String color) {
        this.color = color;
    }
    
    @Override
    public void draw() {
        System.out.println("Drawing a " + circle + " circle at (" + x + ", " + y + ")");
    }
    
    @Override
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}


public class CircleFactory {
    private static final Map<String, Circle> circleMap = new HashMap<>();
    
    public static Circle getCircle(String color) {
        Circle circle = circleMap.get(color);
        
        if (circle == null) {
            circle = new ConcreteCircle(color);
            circleMap.put(color, circle);
            System.out.println("Creating a " + color + " circle");
        }
        
        return circle;
    }
}


public class Main {
    private static final String[] colors = {"red", "blue", "green", "yellow"};
    
    public static void main(String[] args) {
        for (int i=0; i<20; i++) {
            Circle circle = CircleFactory.getCircle(getRandomColor());
            circle.setPosition(getRandomX(), getRandomY());
            circle.draw();
        }
    }
    
    private static String getRandomColor() {
        return colors[(int) (Math.random() * colors.length)];
    }
    
    private static int getRandomX() {
        return (int) (Math.random() * 100);
    }
    
    private static int getRandomY() {
        return (int) (Math.random() * 100);
    }
}
```

##
## FACADE

***zjednodušenie rozhrania, zjednodušenie prístupu k zložitým subsystémom, napr. API pre prácu s databázou ---
hotel-recepcia, dokaze menezovat resp zavolat upratovanie, servis,...***

Provides a unified interface to a set of interfaces in a subsystem. The
facade defines a higher-level interface that makes the subsytem easier to use.

design pattern that provides a consistent interface to a set of interfaces in a subsystem

```java
public class DVDPlayer {
    public void on() {
        System.out.println("DVD Player is ON");
    }
    
    public void play(String movie) {
        System.out.println("Playing movie: " + movie);
    }
    
    public void off() {
        System.out.println("DVD Player is OFF");
    }
}

public class SoundSystem {
    public void on() {
        System.out.println("Sound System is ON");
    }
    
    public void setVolume(int level) {
        System.out.println("Setting volume to " + level);
    }
    
    public void off() {
        System.out.println("Sound System is OFF");
    }
}

public class Projector {
    public void on() {
        System.out.println("Projector is ON");
    }
    
    public void setInput(String input) {
        System.out.println("Setting projector input to " + input);
    }
    
    public void off() {
        System.out.println("Projector is OFF");
    }
}

public class Screen {
    public void down() {
        System.out.println("Screen is down");
    }
    
    public void up() {
        System.out.println("Screen is up");
    }
}




public class HomeTheaterFacade {
    private DVDPlayer dvdPlayer;
    private Projector projector;
    private SoundSystem soundSystem;
    private Screen screen;
    
    public HomeTheaterFacade(DVDPlayer dvdPlayer, Projector projector, SoundSystem soundSystem, Screen screen) {
        this.dvdPlayer = dvdPlayer;
        this.projector = projector;
        this.soundSystem = soundSystem;
        this.screen = screen;
    }
    
    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        screen.down();
        projector.on();
        projector.setInput("DVD");
        soundSystem.on();
        soundSystem.setVolume(5);
        dvdPlayer.on();
        dvdPlayer.play(movie);
    }
    
    public void endMovie() {
        System.out.println("Shutting down the home theater...");
        dvdPlayer.off();
        soundSystem.off();
        projector.off();
        screen.up();
    }
}


public class FacadePatternDemo {
    public static void main(String[] args) {
        DVDPlayer dvdPlayer = new DVDPlayer();
        Projector projector = new Projector();
        SoundSystem soundSystem = new SoundSystem();
        Screen screen = new Screen();
        
        HomeTheaterFacade homeTheaterFacade = new HomeTheaterFacade(dvdPlayer, projector, soundSystem, screen);
        
        homeTheaterFacade.watchMovie("Inception");
        homeTheaterFacade.endMovie();
    }
}
```

##
## DECORATOR

***pridanie funkcionality objektu dynamicky, bez zmeny jeho triedy, napr. pridanie dekoracie na vianoce, alebo pridanie novych vlastnosti do GUI komponentov***

adding new functionality to an object dynamically, without altering the structure

Allows us to add new functionality to an object dynamically without
modifying the original code. This is achieved by creating
decorator classes that wrap the original object and add new behaviors or attributes.

Structural design pattern that allows behavior to be added to individual objects,
either statically or dynamically, without affecting the behavior of other objects from the same class.

```java
public interface Coffee {
    double getCost();
    String getDescription();
}

public class SimpleCoffee implements Coffee {
    @Override
    public double getCost() {
        return 5.0;
    }
    
    @Override
    public String getDescription() {
        return "Simple Coffee";
    }
}

public class Espresso implements Coffee {
    @Override
    public double getCost() {
        return 10.0;
    }
    
    @Override
    public String getDescription() {
        return "Espresso";
    }
}




public abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;
    
    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }
    
    @Override
    public double getCost() {
        return decoratedCoffee.getCost();
    }
    
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }
}


public class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public double getCost() {
        return super.getCost() + 2.0;
    }
    
    @Override
    public String getDescription() {
        return super.getDescription() + ", Milk";
    }
}


public class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }
    
    @Override
    public double getCost() {
        return super.getCost() + 1.0;
    }
    
    @Override
    public String getDescription() {
        return super.getDescription() + ", Sugar";
    }
}


public class DecoratorPatternDemo {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();

        coffee = new MilkDecorator(coffee); // Simple Coffee, Milk $7.0
        coffee = new SugarDecorator(coffee); // Simple Coffee, Milk, Sugar $8.0
    }
}
```

##
## COMPOSITE

***zložené objekty, zloženie objektov do stromovej štruktúry, napr. súborový systém, GUI komponenty***

Allows you to compose objects into tree structures to represent part-whole
hierarchies. The Composite Pattern makes the client treat individual objects
and compositions of objects uniformly.

Structural design pattern that describes a group of objects that are to be treated
in the same way as a single instance of an object. Implementing the Composite Pattern
allows clients to treat individual objects and compositions uniformly.

```java
public interface File {
    void show();
    void add(File file);
}

// concrete file classes
public class TextFile implements File {
    private String name;
    
    public TextFile(String name) {
        this.name = name;
    }
    
    @Override
    public void show() {
        System.out.println("TextFile: " + name);
    }
    
    @Override
    public void add(File file) {
        throw new UnsupportedOperationException("Cannot add to a TextFile");
    }
}

public class ImageFile implements File {
    private String name;
    
    public ImageFile(String name) {
        this.name = name;
    }
    
    @Override
    public void show() {
        System.out.println("ImageFile: " + name);
    }
    
    @Override
    public void add(File file) {
        throw new UnsupportedOperationException("Cannot add to an ImageFile");
    }
}


public class Folder implements File {
    private String name;
    private List<File> children = new ArrayList<>();
    
    public Folder(String name) {
        this.name = name;
    }
    
    @Override
    public void show() {
        System.out.println("Folder: " + name);
        for (File file : children) { file.show(); }
    }
    
    @Override
    public void add(File file) {
        children.add(file);
    }
}



public class CompositePatternDemo {
    public static void main(String[] args) {
        File file1 = new TextFile("file1.txt");
        File file2 = new ImageFile("file2.jpg");
        
        Folder folder = new Folder("MyFolder");
        folder.add(file1);
        folder.add(file2);
        
        folder.show(); // Folder: MyFolder, TextFile: file1.txt, ImageFile: file2.jpg
    }
}
```

##
## BRIDGE

***oddelenie abstrakcie od implementácie, umožňuje meniť obe nezávisle, napr. GUI framework, kde môžeme mať rôzne implementácie pre rôzne platformy ---
web stranka, menenie Dark a Light temy***

Is about favoring composition over inheritance. It moves implementation details
from one hierarchy to another object with a separate hierarchy.

Structural design pattern that decouples an abstraction from its implementation
so that the two can vary independently.

```java
public interface Color {
    void fill();
}

// concrete color classes
public class Red implements Color {
    @Override
    public void fill() {
        System.out.println("Filling with Red color");
    }
}

public class Green implements Color {
    @Override
    public void fill() {
        System.out.println("Filling with Green color");
    }
}



public abstract class Shape {
    protected Color color;
    
    public Shape(Color color) {
        this.color = color;
    }
    
    abstract void draw();
}

// concrete shape classes
public class Circle extends Shape {
    public Circle(Color color) {
        super(color);
    }
    
    @Override
    void draw() {
        System.out.print("Drawing Circle. ");
        color.fill();
    }
}

public class Square extends Shape {
    public Square(Color color) {
        super(color);
    }
    
    @Override
    void draw() {
        System.out.print("Drawing Square. ");
        color.fill();
    }
}



public class BridgePatternDemo {
    public static void main(String[] args) {
        Shape redCircle = new Circle(new Red());
        Shape greenSquare = new Square(new Green());
        
        redCircle.draw(); // Drawing Circle. Filling with Red color
        greenSquare.draw(); // Drawing Square. Filling with Green color
    }
}
```


##
## ADAPTER

***adaptér, umožňuje nekompatibilným rozhraniam spolupracovať, napr. adaptér pre starý systém, ktorý nekomunikuje s novým systémom***

allows you to wrap an incompatible object in an adapter so that
it becomes compatible with another class

In software engineering, the Adapter Pattern is a structural design pattern that allows
the interface of an existing class to be used as another interface.

```java
public interface MediaPlayer {
    void play(String videoType, String fileName);
}

public interface AdvancedMediaPlayer {
    void playVlc(String fileName);
    void playAvi(String fileName);
}



public class VlcPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        System.out.println("Playing VLC file. Name: " + fileName);
    }
    
    @Override
    public void playAvi(String fileName) {
        // do nothing
    }
}

public class AviPlayer implements AdvancedMediaPlayer {
    @Override
    public void playVlc(String fileName) {
        // do nothing
    }
    
    @Override
    public void playAvi(String fileName) {
        System.out.println("Playing AVI file. Name: " + fileName);
    }
}



public class MediaAdapter implements MediaPlayer {
    AdvancedMediaPlayer vlcMediaPlayer = new VlcPlayer();
    AdvancedMediaPlayer aviMediaPlayer = new AviPlayer();
    
    @Override
    public void play(String videoType, String fileName) {
        if (videoType.equalsIgnoreCase("vlc")) {
            vlcMediaPlayer.playVlc(fileName);
        } else if (videoType.equalsIgnoreCase("avi")) {
            aviMediaPlayer.playAvi(fileName);
        }
    }
}



public class VideoPlayer implements MediaPlayer {
    MediaAdapter mediaAdapter = new MediaAdapter();
    
    @Override
    public void play(String videoType, String fileName) {
        // playing mp4 file with built-in support
        if (videoType.equalsIgnoreCase("mp4")) {
            System.out.println("Playing MP4 file. Name: " + fileName);
            
        // MediaAdapter provides support to play other file formats
        } else if (videoType.equalsIgnoreCase("vlc") || videoType.equalsIgnoreCase("avi")) {
            mediaAdapter.play(videoType, fileName);
        } else {
            System.out.println("Invalid media type: " + videoType + " format not supported");
        }
    }
}
```


##
## SINGLETON

***zabezpečuje, že trieda má len jednu inštanciu a poskytuje globálny prístup k nej, napr. konfigurácia aplikácie, logger***

is a creational design pattern that ensures a class has only one instance
throughout the application and provides a global access point

**benefits:**
- resource saving
- global access
- control instance number

```java
public class Singleton {
    // private static variable to hold the unique instance
    private static Singleton uniqueInstance;
    // private constructor to prevent instantiation from other classes
    private Singleton() {}
    
    public static Singleton getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Singleton();
        }
        
        return uniqueInstance;
    }
    
    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }
}


public class SingletonDemo {
    public static void main(String[] args) {
        // get the only instance of Singleton
        Singleton instance = Singleton.getInstance();
        
        // call the instance method
        instance.showMessage(); // Hello from Singleton!
    }
}



// multithreading safe singleton
public class Singleton {
    private static volatile Singleton uniqueInstance;
    
    private Singleton() {}
    
    public static Singleton getInstance() {
        if (uniqueInstance == null) {
            synchronized (Singleton.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton();
                }
            }
        }
        
        return uniqueInstance;
    }
    
    public void showMessage() {
        System.out.println("Hello from thread-safe Singleton!");
    }
}
```


##
## PROTOTYPE

***vytváranie nových objektov kopírovaním existujúcich objektov, napr. vytvorenie nového používateľa na základe existujúceho***

allows to create new objects by copying existing ones, without
having to recreate every detail of the object

Is a creational design pattern that aims to create new objects by copying existing
instances, thus avoiding the complexity or time-consuming operations
of creating objects from scratch.

**benefits:**
- performance improvement
- simplified object creation
- dynamic typing

```java
public abstract class Shape implements Cloneable {
    private String id;
    protected String type;
    
    abstract void draw();
    
    public String getType() {
        return type;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public Object clone() {
        Object clone = null;
        
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        
        return clone;
    }
}

// concrete shape classes
public class Circle extends Shape {
    public Circle() {
        type = "Circle";
    }
    
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method");
    }
}

public class Square extends Shape {
    public Square() {
        type = "Square";
    }
    
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method");
    }
}



public class ShapeCache {
    private final static Hashtable<String, Shape> shapeMap = new Hashtable<>();
    
    public static Shape getShape(String shapeId) {
        Shape cachedShape = shapeMap.get(shapeId);
        return (Shape) cachedShape.clone();
    }
    
    public static void loadCache() {
        Circle circle = new Circle();
        circle.setId("1");
        shapeMap.put(circle.getId(), circle);
        
        Square square = new Square();
        square.setId("2");
        shapeMap.put(square.getId(), square);
    }
}

public class PrototypePatternDemo {
    public static void main(String[] args) {
        ShapeCache.loadCache();
        
        Shape clonedShape1 = ShapeCache.getShape("1");
        System.out.println("Shape: " + clonedShape1.getType());
        
        Shape clonedShape2 = ShapeCache.getShape("2");
        System.out.println("Shape: " + clonedShape2.getType());
    }
}
```


##
## BUILDER

***vytváranie komplexných objektov pomocou jednoduchých krokov, napr. vytvorenie komplexného objektu s viacerými parametrami, ktoré môžu byť voliteľné***

the builder pattern allows you to create different variants of an object
while avoiding the confusion of constructor parameters

Is a creational design pattern aimed at solving the telescoping constructor anti-pattern.

```java
// telescoping constructor anti-pattern
public class Sandwichh {
    private int size;
    private boolean cheese;
    private boolean pepperoni;
    private boolean tomato;
    private boolean lettuce;
    
    public Sandwich(int size, boolean cheese, boolean pepperoni, boolean tomato, boolean lettuce) {
        this.size = size;
        this.cheese = cheese;
        this.pepperoni = pepperoni;
        this.tomato = tomato;
        this.lettuce = lettuce;
    }
}



// builder pattern
public class Sandwich {
    private int size;
    private boolean cheese;
    private boolean pepperoni;
    private boolean tomato;
    private boolean lettuce;

    public Sandwich(SandwichBuilder builder) {
        this.size = builder.getSize();
        this.cheese = builder.hasCheese();
        this.pepperoni = builder.hasPepperoni();
        this.tomato = builder.hasTomato();
        this.lettuce = builder.hasLettuce();
    }
    
    public String getDescription() {
        return "This is a sandwich of size " + size + " containing: "
                + (cheese ? "cheese ": "")
                + (pepperoni ? "pepperoni " : "")
                + (lettuce ? "lettuce " : "")
                + (tomato ? "tomato " : "");
    }
}


public class SandwichBuilder {
    private int size;
    private boolean cheese = false;
    private boolean pepperoni = false;
    private boolean lettuce = false;
    private boolean tomato = false;
    
    public SandwichBuilder setSize(int size) {
        this.size = size;
        return this;
    }
    
    public SandwichBuilder addCheese() {
        this.cheese = true;
        return this;
    }
    
    public SandwichBuilder addPepperoni() {
        this.pepperoni = true;
        return this;
    }
    
    public SandwichBuilder addLettuce() {
        this.lettuce = true;
        return this;
    }
    
    public SandwichBuilder addTomato() {
        this.tomato = true;
        return this;
    }
    
    public int getSize() {
        return size;
    }
    
    public boolean hasCheese() {
        return cheese;
    }
    
    public boolean hasPepperoni() {
        return pepperoni;
    }
    
    public boolean hasLettuce() {
        return lettuce;
    }
    
    public boolean hasTomato() {
        return tomato;
    }
    
    public Sandwich build() {
        return new Sandwich(this);
    }
}



public class Main {
    public static void main(String[] args) {
        Sandwich sandwich = new SandwichBuilder()
                .setSize(14)
                .addPepperoni()
                .addLettuce()
                .addTomato()
                .build();
        
        System.out.println(sandwich.getDescription());
        // This is a sandwich of size 14 containing: pepperoni lettuce tomato
    }
}
```


##
## ABSTRACT FACTORY

***uml diagram***

an abstract factory is a factory of factories, it provides a way to create
related or dependent objects without specifying their concrete classes

```java
interface Door {
    void getDescription();
}

class WoodenDoor implements Door {
    @Override
    public void getDescription() {
        System.out.println("I am a wooden door");
    }
}

public class IronDoor implements Door {
    @Override
    public void getDescription() {
        System.out.println("I am an iron door");
    }
}



interface DoorFittingExpert {
    void getDescription();
}

class Carpenter implements DoorFittingExpert {
    @Override
    public void getDescription() {
        System.out.println("I am a carpenter, I install wooden doors");
    }
}

class Welder implements DoorFittingExpert {
    @Override
    public void getDescription() {
        System.out.println("I am a welder, I install iron doors");
    }
}


// abstract factory interface
interface DoorFactory {
    Door makeDoor();
    DoorFittingExpert makeFittingExpert();
}

class WoodenDoorFactory implements DoorFactory {
    @Override
    public Door makeDoor() {
        return new WoodenDoor();
    }
    
    @Override
    public DoorFittingExpert makeFittingExpert() {
        return new Carpenter();
    }
}

class IronDoorFactory implements DoorFactory {
    @Override
    public Door makeDoor() {
        return new IronDoor();
    }

    @Override
    public DoorFittingExpert makeFittingExpert() {
        return new Welder();
    }
}



public class Main {
    public static void main(String[] args) {
        DoorFactory woodenFactory = new WoodenDoorFactory();
        Door woodenDoor = woodenFactory.makeDoor();
        DoorFittingExpert woodenExpert = woodenFactory.makeFittingExpert();
        
        woodenDoor.getDescription(); // I am a wooden door
        woodenExpert.getDescription(); // I am a carpenter, I install wooden doors
        
        DoorFactory ironFactory = new IronDoorFactory();
        Door ironDoor = ironFactory.makeDoor();
        DoorFittingExpert ironExpert = ironFactory.makeFittingExpert();
        
        ironDoor.getDescription(); // I am an iron door
        ironExpert.getDescription(); // I am a welder, I install iron doors
    }
}
```


##
## FACTORY METHOD

***manazment***

creational design pattern that defines an interface for creating objects but lets subclasses
decide which class to instantiate

In class-based programming, the Factory Method pattern is a creational design pattern. It uses
factory methods to handle the problem of creating objects without specifying the exact
class of the object that will be created.

**benefits:**
- decoupling the creation process
- enhanced flexibility
- improved code maintainability

```java
interface Interviewer {
    void askQuestions();
}

class Developer implements Interviewer {
    @Override
    public void askQuestions() {
        System.out.println("Ask design pattern related problems! ");
    }
}

class CommunityExecutive implements Interviewer {
    @Override
    public void askQuestions() {
        System.out.println("Ask community building related problems! ");
    }
}



abstract class HiringManager {
    // factory method
    protected abstract Interviewer makeInterviewer();
    
    public void takeInterviewer() {
        Interviewer interviewer = makeInterviewer();
        interviewer.askQuestions();
    }
}

class DevelopmentManager extends HiringManager {
    @Override
    protected Interviewer makeInterviewer() {
        return new Developer();
    }
}

class MarketingManager extends HiringManager {
    @Override
    protected Interviewer makeInterviewer() {
        return new CommunityExecutive();
    }
}



public class Main {
    public static void main(String[] args) {
        HiringManager devManager = new DevelopmentManager();
        devManager.takeInterviewer();
        
        HiringManager marketingManager = new MarketingManager();
        marketingManager.takeInterviewer();
    }
}
```
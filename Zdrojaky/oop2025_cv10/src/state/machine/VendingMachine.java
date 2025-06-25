package state.machine;

public class VendingMachine {
    private Product[] menu; // ponuka vsetkych produktov
    private Product product; // vybrany produkt
    private int count; // pocet kusov vybraneho produktu
    private int credit; // uz zaplatena suma
    private VendingState state; // aktualny stav

    public VendingMachine() {
        menu = new Product[] {
                new Product("mineralka", 10),
                new Product("caj", 15),
                new Product("dzus", 20),
                new Product("kava", 20),
                new Product("pivo", 15)
        };
        credit = 0;
        state = new MenuState(this);
    }

    public void pressNumber(int number) {
        System.out.println("zakaznik: stlacil tlacidlo: " + number);
        state.setNumber(number);
    }

    public void pressBack() {
        System.out.println("zakaznik: stlacil tlacidlo back");
        state.back();
    }

    public void pay(int amount) { // reakcia na vhodenie mince
        System.out.println("zakaznik: vhodil mincu: " + amount);
        state.pay(amount);
    }

    void addCredit(int amount) {
        credit += amount;
    }

    void setMessage(String message) {
        System.out.println("machine:  " + message);
    }

    void setProduct(int productIndex) {
        product = menu[productIndex]; // todo osetrit
    }

    Product getProduct() { // vrati vybrany napoj
        return product;
    }

    void setState(VendingState newState) {
        state = newState;
    }

    void setCount(int productCount) { // nastavi pocet napojov
        count = productCount;
    }

    int getOrderPrice() {
        return count * product.getPrice();
    }

    int getCredit() {
        return credit;
    }

    void deliverProduct() {
        credit -= getOrderPrice();
    }
}

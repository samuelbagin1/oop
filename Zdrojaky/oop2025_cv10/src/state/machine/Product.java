package state.machine;

class Product {
    private String name;
    private int price;

    public Product(String name, int price) {
        this.price = price;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}

//record Product(String name, int price) {}
//getName() -> name(), getPrice() -> price()

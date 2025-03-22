public class Product {
    private int price;
    private String name;
    private int discount;

    public Product(String name, int price) {
        this.price = price;
        this.name = name;
        this.discount = 0;
    }

    public String getName() {
        return this.name;
    }

    public int getDiscountPercentage() {
        return this.discount;
    }

    public int getBasePrice() {
        return this.price;
    }

    public void setName(String s) {
        if (s!=null) this.name = s;
    }

    public void setDiscountPercentage(int percentage) {
        if (percentage<=100 && percentage>=0) this.discount = percentage;
    }

    public void setBasePrice(int pri) {
        if (pri>=0) this.price = pri;
    }

    public int computePrice() {
        return this.price * (100 - this.discount) / 100;
    }
}

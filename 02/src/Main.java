//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Product apple = new Product("Apple", 5);
        Product banana = new Product("Banana", 10);
        Product orange = new Product("Orange", 15);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addProduct(apple);
        shoppingCart.addProduct(banana);
        shoppingCart.addProduct(orange);
        shoppingCart.addProduct(apple);
        // the shopping cart should contain 2 apples, 1 banana and 1 orange
        // and its total price should be 35
        System.out.println(shoppingCart);
    }
}
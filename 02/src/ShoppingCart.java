import java.util.Arrays;

/**
 * This class represents a shopping cart which can hold products and their respective amounts.
 */
public class ShoppingCart {

    /**
     * the default capacity of the arrays for the amounts and the products
     */
    private static final int DEFAULT_CAPACITY = 3;

    /**
     * The array containing the products.
     */
    private Product[] products;

    /**
     * The array containing the counts of the products.
     */
    private int[] amounts;

    /**
     * The pointer to the first available empty slot in the products and amounts arrays.
     */
    private int productsIndex;


    public ShoppingCart() {
        products = new Product[DEFAULT_CAPACITY];
        amounts = new int[DEFAULT_CAPACITY];
        productsIndex = 0;
    }

    /**
     * Add one product to the shopping cart.
     * If the product is already present, increase its amount.
     * Resize arrays as necessary.
     *
     * @param product the product to add, must not be null
     */
    public void addProduct(Product product) {
        if (product == null) {
            return;
        }

        // check whether the cart already contains such a product
        // and if it does, simply increase the amount
        for (int i = 0; i < productsIndex; i++) {
            if (products[i].getName().equals(product.getName())
            && products[i].computePrice() == product.computePrice()
            && products[i].getDiscountPercentage() == product.getDiscountPercentage()
            && products[i].getBasePrice() == product.getBasePrice()) {
                amounts[i]++;
                return;
            }
        }

        // otherwise insert the product
        // (and resize the cart if necessary)
        if (productsIndex == products.length) {
            products = Arrays.copyOf(products, products.length * 2);
            amounts = Arrays.copyOf(amounts, amounts.length * 2);
        }
        products[productsIndex] = product;
        amounts[productsIndex] = 1;
        productsIndex++;
    }

    /**
     * Remove one product from the cart.
     * If it is present in the cart multiple times, only remove one.
     * For instance, if the cart contains two apples,
     * the call to remove the apple will result in the cart containing only one apple.
     * If a product to remove is not present in the cart, do nothing.
     * @param product a product to remove, must not be null
     */
    public void removeProduct(Product product) {
        if (product == null) {
            return;
        }
        for (int i = 0; i < productsIndex; i++) {
            if (products[i].getName().equals(product.getName())
                    && products[i].computePrice() == product.computePrice()
                    && products[i].getDiscountPercentage() == product.getDiscountPercentage()
                    && products[i].getBasePrice() == product.getBasePrice()) {
                if (amounts[i] > 1) {
                    amounts[i]--;
                    return;
                } else if (amounts[i] == 1) {
                    productsIndex--;
                    for (int j = i; j < productsIndex; j++) {
                        products[j] = products[j+1];
                        amounts[j] = amounts[j+1];
                    }
                    products[productsIndex] = null;
                    amounts[productsIndex] = 0;
                    return;
                }
            }
        }
    }

    /**
     * Compute the total price for all items in the cart.
     *
     * @return the computed price
     */
    public int getTotalPrice() {
        int totalPrice = 0;
        for (int i = 0; i < productsIndex; i++) {
            totalPrice += products[i].computePrice()*amounts[i];
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ShoppingCart:\n");
        for (int i = 0; i < productsIndex; i++) {
            sb.append(products[i].getName()).append(" (").append(amounts[i]).append(") at ").append(products[i].computePrice()*amounts[i]).append(" total\n");
        }
        sb.append("Total price for the cart: ").append(getTotalPrice()).append("\n");
        return sb.toString();
    }
}

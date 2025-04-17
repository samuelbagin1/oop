package bookstore;

public class NotInInventoryException extends RuntimeException {
    public NotInInventoryException(Book book) {
        super("Book ISBN " + book.getISBN() + " is not in inventory!");
    }
}

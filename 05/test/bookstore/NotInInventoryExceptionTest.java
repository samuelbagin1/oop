package bookstore;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotInInventoryExceptionTest {

    @Test
    void message() {
        Book b1 = new Book("9992158107", "O syre a plesniach", "Autor Autorský", 1996);
        NotInInventoryException e = new NotInInventoryException(b1);
        assertEquals("Book ISBN " + b1.getISBN() + " is not in inventory!", e.getMessage());
    }

}
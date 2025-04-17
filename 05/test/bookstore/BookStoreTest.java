package bookstore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BookStoreTest {

    BookStore bookStore;
    Book b1;
    Book b2;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        bookStore = new BookStore("Jožkove knihy");
        b1 = new Book("9992158107", "O syre a plesniach", "Autor Autorský", 1996);
        b2 = new Book("080442957X", "Falzifikát či Fejzifikát - filozofická rozprava", "Fejk Fejkovský", 2018);
        Field inventoryField = bookStore.getClass().getDeclaredField("inventory");
        inventoryField.setAccessible(true);
        ((Map<Book, Integer>)inventoryField.get(bookStore)).put(b1, 1);
    }

    @Test
    void addToInventory() {
        assertEquals(1, bookStore.getInventory().get(b1));
        assertEquals(1, bookStore.getInventory().size());
        bookStore.addToInventory(b2);
        assertEquals(1, bookStore.getInventory().get(b2));
        assertEquals(2, bookStore.getInventory().size());
        bookStore.addToInventory(b1);
        assertEquals(2, bookStore.getInventory().get(b1));
        assertEquals(2, bookStore.getInventory().size());
    }

    @Test
    void sell() {
        bookStore.sell(b1);
        assertEquals(0, bookStore.getInventory().get(b1));
        assertThrows(NotInInventoryException.class, () -> bookStore.sell(b2));
        assertThrows(NotInInventoryException.class, () -> bookStore.sell(b1));
    }

    @Test
    void getInventory() {
        Map<Book, Integer> inventory = bookStore.getInventory();
        assertEquals(1, inventory.get(b1));
        assertEquals(1, inventory.size());
    }
}
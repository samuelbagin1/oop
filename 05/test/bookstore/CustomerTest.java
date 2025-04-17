package bookstore;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    Customer c1;
    Customer c2;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        c1 = new Customer("Steve");
        c2 = new Customer("Jack Black");
        Field ownedBooksField = c2.getClass().getDeclaredField("ownedBooks");
        ownedBooksField.setAccessible(true);
        Set<Book> c2OwnedBooks = (Set<Book>) ownedBooksField.get(c2);
        c2OwnedBooks.add(new Book("080442957X", "Falzifikát či Fejzifikát - filozofická rozprava", "Fejk Fejkovský", 2018));
        c2OwnedBooks.add(new Book("9992158107", "O syre a plesniach", "Autor Autorský", 1996));
    }

    @Test
    void getName() {
        assertEquals("Steve", c1.getName());
        assertEquals("Jack Black", c2.getName());
    }

    @Test
    void getOwnedBooks() {
        assertTrue(c1.getOwnedBooks().isEmpty());
        assertEquals(2, c2.getOwnedBooks().size());
        Iterator<Book> it = c2.getOwnedBooks().iterator();
        assertEquals(1996, it.next().getYear());
        assertEquals(2018, it.next().getYear());
    }
}
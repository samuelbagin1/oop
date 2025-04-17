package bookstore;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    Book b1;
    Book b2;
    Book b3;

    @BeforeEach
    void setUp() {
        b1 = new Book("9992158107", "O syre a plesniach", "Autor Autorský", 1996);
        b2 = new Book("080442957X", "Falzifikát či Fejzifikát - filozofická rozprava", "Fejk Fejkovský", 2018);
        b3 = new Book("9780306406157", "Fejka - budúcnosť začína u nás", "Fatima Frfľavá", 2022);
    }

    @org.junit.jupiter.api.Test
    void isISBNValid() {
        assertTrue(Book.isISBNValid("9992158107"));
        assertTrue(Book.isISBNValid("080442957X"));
        assertTrue(Book.isISBNValid("9780306406157"));
        assertFalse(Book.isISBNValid("223456789X"));
        assertFalse(Book.isISBNValid("1234567891234"));
        assertFalse(Book.isISBNValid(null));
        assertFalse(Book.isISBNValid(""));
        assertFalse(Book.isISBNValid(" "));
        assertFalse(Book.isISBNValid("1234efghiX"));
    }

    @org.junit.jupiter.api.Test
    void getISBN() {
        assertEquals("9992158107", b1.getISBN());
        assertEquals("080442957X", b2.getISBN());
        assertEquals("9780306406157", b3.getISBN());
    }

    @org.junit.jupiter.api.Test
    void getTitle() {
        assertEquals("O syre a plesniach", b1.getTitle());
        assertEquals("Falzifikát či Fejzifikát - filozofická rozprava", b2.getTitle());
        assertEquals("Fejka - budúcnosť začína u nás", b3.getTitle());
    }

    @org.junit.jupiter.api.Test
    void getAuthor() {
        assertEquals("Autor Autorský", b1.getAuthor());
        assertEquals("Fejk Fejkovský", b2.getAuthor());
        assertEquals("Fatima Frfľavá", b3.getAuthor());
    }

    @org.junit.jupiter.api.Test
    void getYear() {
        assertEquals(1996, b1.getYear());
        assertEquals(2018, b2.getYear());
        assertEquals(2022, b3.getYear());
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
        Book f1 = new Book("9992158107", "O syre a plesniach", "Autor Autorský", 1996);
        Book f2 = new Book("9992158107", "O syre a plesniach", "Autor Autorský", 1997);
        Book f3 = new Book("9992158107", "O syre a plesniach", "Hektor Hekavý", 1996);
        Book f4 = new Book("9992158107", "O fejke a fejkároch", "Autor Autorský", 1996);
        Book f5 = new Book("080442957X", "O syre a plesniach", "Autor Autorský", 1996);
        assertEquals(b1, f1);
        assertNotEquals(b1, f2);
        assertNotEquals(b1, f3);
        assertNotEquals(b1, f4);
        assertNotEquals(b1, f5);
    }

    @org.junit.jupiter.api.Test
    void compareTo() {
        assertEquals(-1, b1.compareTo(b2));
        assertEquals(0, b1.compareTo(b1));
        assertEquals(1, b2.compareTo(b1));
    }
}
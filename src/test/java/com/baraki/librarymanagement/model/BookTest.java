package com.baraki.librarymanagement.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private Book book;

    @BeforeEach
    void setUp() {
        book = new Book();
        book.setId(1L);
        book.setTitle("Effective Java");
        book.setAuthor("Joshua Bloch");
        book.setPublicationYear(2008);
        book.setIsbn("978-0134685991");
    }

    @Test
    void testGetterAndSetter() {
        assertEquals(1L, book.getId());
        assertEquals("Effective Java", book.getTitle());
        assertEquals("Joshua Bloch", book.getAuthor());
        assertEquals(2008, book.getPublicationYear());
        assertEquals("978-0134685991", book.getIsbn());
    }

    @Test
    void testEquals() {
        Book book2 = new Book();
        book2.setIsbn("978-0134685991");

        assertTrue(book.equals(book2) && book2.equals(book));
        assertEquals(book.hashCode(), book2.hashCode());
    }

    @Test
    void testNotEquals() {
        Book book2 = new Book();
        book2.setIsbn("123-4567890123");

        assertFalse(book.equals(book2));
        assertNotEquals(book.hashCode(), book2.hashCode());
    }

    @Test
    void testToString() {
        String expectedString = "Book{id=1, title='Effective Java', author='Joshua Bloch', publicationYear=2008, isbn='978-0134685991'}";
        assertEquals(expectedString, book.toString());
    }
}

package com.baraki.librarymanagement.repository;

import com.baraki.librarymanagement.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    private Book book;

    @BeforeEach
    public void setUp() {
        // Initialize test book object here
        book = new Book();
        book.setTitle("Test Book Title");
        book.setAuthor("Test Author");
        book.setPublicationYear(2020);
        book.setIsbn("1234567890123");

        // Persist book object
        entityManager.persist(book);
        entityManager.flush();
    }

    @Test
    public void whenFindByAuthor_thenReturnBooks() {
        // Execute the query method
        List<Book> foundBooks = bookRepository.findByAuthor(book.getAuthor());
        assertThat(foundBooks).hasSize(1).extracting(Book::getAuthor).containsOnly(book.getAuthor());
    }

    @Test
    public void whenFindByIsbn_thenReturnBook() {
        // Execute the query method
        Optional<Book> foundBook = bookRepository.findByIsbn(book.getIsbn());
        assertThat(foundBook).isPresent().containsSame(book);
    }

    @Test
    public void whenFindByPublicationYear_thenReturnBooks() {
        // Execute the query method
        List<Book> foundBooks = bookRepository.findByPublicationYear(book.getPublicationYear());
        assertThat(foundBooks).hasSize(1).extracting(Book::getPublicationYear).containsOnly(book.getPublicationYear());
    }
}
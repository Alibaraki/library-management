package com.baraki.librarymanagement.repository;

import com.baraki.librarymanagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for the Book entity.
 *
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Find books by their author.
     *
     * @return A list of books by the specified author.
     */
    List<Book> findByAuthor(String author);

    /**
     * Find a book by its ISBN.
     *
     * @return An Optional containing the book if found, or empty otherwise.
     */
    Optional<Book> findByIsbn(String isbn);

    /**
     * Find books published in a specific year.
     *
     * @return A list of books published in the specified year.
     */
    List<Book> findByPublicationYear(Integer publicationYear);
}
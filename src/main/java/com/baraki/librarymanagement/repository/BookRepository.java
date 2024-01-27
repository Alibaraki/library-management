package com.baraki.librarymanagement.repository;

import com.baraki.librarymanagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for the Book entity.
 * This interface extends JpaRepository, providing CRUD operations and more for Book entities.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Find books by their author.
     * @param author The author of the book.
     * @return A list of books by the specified author.
     */
    List<Book> findByAuthor(String author);

    /**
     * Find a book by its ISBN.
     * @param isbn The ISBN of the book.
     * @return An Optional containing the book if found, or empty otherwise.
     */
    Optional<Book> findByIsbn(String isbn);

    /**
     * Find books published in a specific year.
     * @param publicationYear The publication year.
     * @return A list of books published in the specified year.
     */
    List<Book> findByPublicationYear(Integer publicationYear);
}
package com.baraki.librarymanagement.service;

import com.baraki.librarymanagement.model.Book;
import com.baraki.librarymanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing books.
 */
@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Retrieves all books from the repository.
     * @return a list of all books.
     */
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Retrieves a book by its ID.
     * @param id the ID of the book to retrieve.
     * @return an Optional containing the book if found, or empty otherwise.
     */
    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }

    /**
     * Saves a book to the repository.
     * @param book the book to be saved.
     * @return the saved book.
     */
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    /**
     * Updates a book with new details.
     * @param bookId the ID of the book to update.
     * @param newBookDetails book object containing new details.
     * @return the updated book.
     */
    public Optional<Book> updateBook(Long bookId, Book newBookDetails) {
        return bookRepository.findById(bookId).map(book -> {
            book.setTitle(newBookDetails.getTitle());
            book.setAuthor(newBookDetails.getAuthor());
            book.setPublicationYear(newBookDetails.getPublicationYear());
            book.setIsbn(newBookDetails.getIsbn());
            return bookRepository.save(book);
        });
    }

    /**
     * Deletes a book from the repository.
     * @param id the ID of the book to be deleted.
     */
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    // Additional business logic and methods can be added here as needed
}
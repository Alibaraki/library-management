package com.baraki.librarymanagement.service;

import com.baraki.librarymanagement.model.Book;
import com.baraki.librarymanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baraki.librarymanagement.exception.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;

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
     *
     * @return an Optional containing the book if found, or empty otherwise.
     */
    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }

    /**
     * Saves a book to the repository.
     *
     * @return the saved book.
     */
    @Transactional
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public Book updateBook(Long bookId, Book newBookDetails) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + bookId));

        book.setTitle(newBookDetails.getTitle());
        book.setAuthor(newBookDetails.getAuthor());
        book.setPublicationYear(newBookDetails.getPublicationYear());
        book.setIsbn(newBookDetails.getIsbn());

        return bookRepository.save(book);
    }

    /**
     * Deletes a book from the repository.
     *
     */
    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
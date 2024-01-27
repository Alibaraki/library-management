package com.baraki.librarymanagement.controllers;

import com.baraki.librarymanagement.model.Book;
import com.baraki.librarymanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * REST controller for managing books.
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * GET  /api/books : Get all the books.
     * @return the ResponseEntity with status 200 (OK) and the list of books in body.
     */
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.findAllBooks();
        return ResponseEntity.ok().body(books);
    }

    /**
     * GET  /api/books/{id} : Get the "id" book.
     * @param id the id of the book to retrieve.
     * @return the ResponseEntity with status 200 (OK) and with body the book, or with status 404 (Not Found).
     */
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.findBookById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * POST  /api/books : Create a new book.
     * @param book the book to create.
     * @return the ResponseEntity with status 201 (Created) and with body the new book, or with status 400 (Bad Request) if the book has already an ID.
     */
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        if (book.getId() != null) {
            return ResponseEntity.badRequest().body(null); // Ideally, you should include a message about why it's a bad request
        }
        Book result = bookService.saveBook(book);
        return ResponseEntity.created(URI.create("/api/books/" + result.getId())).body(result);
    }

    /**
     * PUT  /api/books/{id} : Updates an existing book.
     * @param id the id of the book to update.
     * @param book the book to update.
     * @return the ResponseEntity with status 200 (OK) and with body the updated book,
     * or with status 400 (Bad Request) if the book is not valid,
     * or with status 500 (Internal Server Error) if the book couldn't be updated.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        if (book.getId() == null) {
            return ResponseEntity.badRequest().body(null); // ID is required for update
        }
        Book result = bookService.saveBook(book);
        return ResponseEntity.ok().body(result);
    }

    /**
     * DELETE  /api/books/{id} : delete the "id" book.
     * @param id the id of the book to delete.
     * @return the ResponseEntity with status 200 (OK).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }

    // Additional endpoints or utility methods can be added here as needed
}
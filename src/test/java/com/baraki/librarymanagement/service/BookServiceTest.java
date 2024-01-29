package com.baraki.librarymanagement.service;

import com.baraki.librarymanagement.model.Book;
import com.baraki.librarymanagement.repository.BookRepository;
import com.baraki.librarymanagement.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book(1L, "Test Title", "Test Author", 2020, "1234567890");
    }

    @Test
    public void whenFindAllBooks_thenBookListShouldBeReturned() {
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book));

        List<Book> result = bookService.findAllBooks();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(book, result.get(0));
    }

    @Test
    public void whenFindBookById_thenBookShouldBeReturned() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Optional<Book> result = bookService.findBookById(1L);

        assertTrue(result.isPresent());
        assertEquals(book, result.get());
    }

    @Test
    public void whenSaveBook_thenBookShouldBeSaved() {
        when(bookRepository.save(book)).thenReturn(book);

        Book result = bookService.saveBook(book);

        assertNotNull(result);
        assertEquals(book, result);
    }

    @Test
    public void whenUpdateBook_thenBookShouldBeUpdated() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);

        Book updatedBook = new Book(1L, "Updated Title", "Test Author", 2020, "1234567890");
        Book result = bookService.updateBook(1L, updatedBook);

        assertNotNull(result);
        assertEquals(updatedBook.getTitle(), result.getTitle());
    }

    @Test
    public void whenDeleteBook_thenBookShouldBeDeleted() {
        doNothing().when(bookRepository).deleteById(1L);

        bookService.deleteBook(1L);

        verify(bookRepository, times(1)).deleteById(1L);
    }

    @Test
    public void whenUpdateNonExistingBook_thenThrowException() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            bookService.updateBook(1L, new Book());
        });
    }
}
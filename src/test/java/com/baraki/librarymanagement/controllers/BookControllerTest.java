package com.baraki.librarymanagement.controllers;

import com.baraki.librarymanagement.model.Book;
import com.baraki.librarymanagement.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
public class BookControllerTest {

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void getAllBooks_ShouldReturnBooks() throws Exception {
        Book book1 = new Book(); // Set properties as needed
        Book book2 = new Book(); // Set properties as needed
        when(bookService.findAllBooks()).thenReturn(Arrays.asList(book1, book2));

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void getBookById_WhenFound_ShouldReturnBook() throws Exception {
        Book book = new Book(); // Set properties as needed
        when(bookService.findBookById(1L)).thenReturn(Optional.of(book));

        mockMvc.perform(get("/api/books/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(book.getId().intValue())));
    }

    @Test
    public void getBookById_WhenNotFound_ShouldReturnNotFound() throws Exception {
        when(bookService.findBookById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/books/{id}", 1))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createBook_ShouldCreateBookAndReturnCreatedBook() throws Exception {
        Book bookToCreate = new Book(); // Set properties as needed
        Book createdBook = new Book(); // Set properties including ID as needed
        when(bookService.saveBook(ArgumentMatchers.<Book>any())).thenReturn(createdBook);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookToCreate)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(createdBook.getId().intValue())));
    }

    @Test
    public void updateBook_WhenIdMismatch_ShouldReturnBadRequest() throws Exception {
        Book book = new Book();
        book.setId(1L);

        mockMvc.perform(put("/api/books/{id}", 2L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void deleteBook_ShouldDeleteBook() throws Exception {
        doNothing().when(bookService).deleteBook(1L);

        mockMvc.perform(delete("/api/books/{id}", 1))
                .andExpect(status().isOk());

        verify(bookService, times(1)).deleteBook(1L);
    }
}
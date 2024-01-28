package com.baraki.librarymanagement.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.OneToMany;
import java.util.List;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;


@Entity
@Table(name = "books")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    @Size(max = 100, message = "Title must be less than 100 characters")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Author cannot be blank")
    @Size(max = 100, message = "Author must be less than 100 characters")
    @Column(nullable = false)
    private String author;

    @NotNull(message = "Publication year is required")
    @Min(value = 1450, message = "Publication year must be no earlier than 1450") // Assuming books weren't published before this year.
    @Max(value = 2100, message = "Publication year must be no later than 2100")   // Future year constraint.
    @Column(name = "publication_year", nullable = false)
    private Integer publicationYear;

    @NotBlank(message = "ISBN cannot be blank")
    @Size(min = 10, max = 13, message = "ISBN must be between 10 and 13 characters")
    @Column(nullable = false, unique = true, length = 13)
    private String isbn;

    // Default constructor
    public Book() {
    }

    // All-arguments constructor
    public Book(Long id, String title, String author, Integer publicationYear, String isbn) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    // equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    // toString
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publicationYear=" + publicationYear +
                ", isbn='" + isbn + '\'' +
                '}';
    }

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BorrowingRecord> borrowingRecords;

    public List<BorrowingRecord> getBorrowingRecords() {
        return borrowingRecords;
    }

    public void setBorrowingRecords(List<BorrowingRecord> borrowingRecords) {
        this.borrowingRecords = borrowingRecords;
    }
}
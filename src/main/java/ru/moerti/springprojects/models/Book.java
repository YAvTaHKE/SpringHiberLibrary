package ru.moerti.springprojects.models;

import jakarta.validation.constraints.*;

import jakarta.persistence.*;

@Entity
@Table (name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int bookId;

    @Column(name = "title")
    @NotEmpty(message = "Title should not be empty")
    @Size(min = 1, max = 127, message = "Title should between 1 and 50 characters")
    private String title;

    @Column(name = "author")
    @NotEmpty(message = "Author should not be empty")
    @Size(min = 1, max = 127, message = "Author name should between 1 and 127 characters")
    private String author;

    @Column(name = "year")
    @NotNull(message = "Year should not be empty")
    @Min(value = 0, message = "Year should be greater than 0")
    @Max(value = 2100, message = "Year should be less than 2100")
    private int year;

    @ManyToOne
    @JoinColumn (name = "person_id", referencedColumnName = "person_id")
    private Person person;

    public Book(){
        // Пустой конструктор необходим для BeanPropertyRowMapper
    }

    public Book(String title, int year) {
        this.title = title;
        this.year = year;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int id) {
        this.bookId = id;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Person getPerson(){
        return this.person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year + '\'' +
                ", person=" + person +
                '}';
    }
}

package ru.moerti.springprojects.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {

    private int bookId;

    @NotEmpty(message = "Title should not be empty")
    @Size(min = 1, max = 127, message = "Title should between 1 and 127 characters")
    private String title;

    @NotEmpty(message = "Author should not be empty")
    @Size(min = 1, max = 127, message = "Author name should between 1 and 127 characters")
    private String author;


    @Min(value = 1, message = "Year should be greater than 0")
    @Max(value = 2100, message = "Year should be less than 2100")
    private int year;

    public Book(){
        // Пустой конструктор необходим для BeanPropertyRowMapper
    }

    public Book(int bookId, String title, int year) {
        this.bookId = bookId;
        this.title = title;
        this.year = year;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
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

    @Override
    public String toString() {
        return "Book{" +
                "id=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                '}';
    }
}

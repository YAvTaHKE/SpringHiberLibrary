package ru.moerti.springprojects.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class Book {

    private int id;

    @Size(min = 1, max = 50, message = "Title should between 1 and 50 characters")
    private String title;

    @Size(min = 1, max = 127, message = "Author name should between 1 and 127 characters")
    private String author;

    @Min(value = 0, message = "Year should be greater than 0")
    @Max(value = 2100, message = "Year should be less than 2100")
    private int year;

    public Book(int id, String title, int year) {
        this.id = id;
        this.title = title;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                '}';
    }
}

package ru.moerti.springprojects.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private int PersonId;

    @Column(name = "full_name")
    @NotEmpty(message = "Name not should be empty")
    @Size(min = 1, max = 127, message = "Name should between 2 and 30 characters")
    private String fullName;

    @Column(name = "year_of_birth")
    @Min(value = 1, message = "Age should be greater than 0")
    @Max(value = 2100, message = "Age should be less than 2100")
    private int yearOfBirth;

    @OneToMany (mappedBy = "person")

    private List<Book> personBookList;

    public Person(){
        // Пустой конструктор необходим для BeanPropertyRowMapper
    }

    public Person(String fullName, int yearOfBirth) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public int getPersonId() {
        return PersonId;
    }

    public void setPersonId(int personId) {
        this.PersonId = personId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public List<Book> getPersonBookList(){
        return this.personBookList;
    }

    public void setPersonBookList(List<Book> personBookList) {
        this.personBookList = personBookList;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + PersonId +
                ", fullName='" + fullName + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                '}';
    }
}

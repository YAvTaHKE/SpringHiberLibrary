package ru.moerti.springprojects.models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {

    private int PersonId;

    @NotEmpty(message = "Name not should be empty")
    @Size(min = 1, max = 127, message = "Name should between 2 and 30 characters")
    private String fullName;

    @Min(value = 1, message = "Age should be greater than 0")
    @Max(value = 2100, message = "Age should be less than 2100")
    private int yearOfBirth;

    public Person(){
        // Пустой конструктор необходим для BeanPropertyRowMapper
    }

    public Person(int id, String fullName, int yearOfBirth) {
        this.PersonId = id;
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

    @Override
    public String toString() {
        return "Person{" +
                "id=" + PersonId +
                ", fullName='" + fullName + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                '}';
    }
}

package ru.moerti.springprojects.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.moerti.springprojects.dao.PersonDAO;
import ru.moerti.springprojects.models.Person;

import java.util.List;

@Component
public class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;

        if (personDAO.show(person.getPersonId()) != null) {
            // поле, код ошибки, сообщение ошибки
            errors.rejectValue("fullName", "", "This name is already in use");
        }

        // Проверяем, что у человека имя начинается с заглавной буквы
        // Если имя не начинается с заглавной буквы - выдаем ошибку
        /*if (!Character.isUpperCase(person.getFullName().codePointAt(0)))
            errors.rejectValue("name", "", "Name should start with a capital letter");*/

    }
}

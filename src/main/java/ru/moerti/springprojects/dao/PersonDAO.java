package ru.moerti.springprojects.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.moerti.springprojects.models.Person;

import java.util.List;

@Component
@Transactional
public class PersonDAO {

    //private final JdbcTemplate jdbcTemplate;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Person> index() {
        List<Person> people = entityManager
                .createQuery("SELECT p FROM Person p LEFT JOIN FETCH p.personBookList", Person.class)
                .getResultList();

        return people;
    }

    public void save(Person person) {
        //jdbcTemplate.update("INSERT INTO Person(full_name, year_of_birth) VALUES(?, ?)", person.getFullName(), person.getYearOfBirth());
        entityManager.persist(person);
    }

    public Person show(int id) {
    //jdbcTemplate.query("SELECT * FROM Person WHERE person_id=?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
        return entityManager.find(Person.class, id);
    }

    public void update(int id, Person updatedPerson) {
        //jdbcTemplate.update("UPDATE Person SET full_name=?, year_of_birth=? WHERE person_id=?", updatedPerson.getFullName(), updatedPerson.getYearOfBirth(), id);
        Person person = entityManager.find(Person.class, id);
        if (person != null) {
            person.setFullName(updatedPerson.getFullName());
            person.setYearOfBirth(updatedPerson.getYearOfBirth());
            // Не нужно вызывать merge или update - Hibernate сам сохранит изменения
            // благодаря @Transactional и тому, что объект находится в persistence context
        }
    }

    public void delete(int id) {
        //jdbcTemplate.update("DELETE FROM Person WHERE person_id=?", id);
        Person person = entityManager.find(Person.class, id);
        if (person != null) {
            entityManager.remove(person);
        }
    }
}

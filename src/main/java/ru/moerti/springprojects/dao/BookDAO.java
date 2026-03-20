package ru.moerti.springprojects.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.moerti.springprojects.models.Book;
import ru.moerti.springprojects.models.Person;

import java.util.List;

@Component
@Transactional
public class BookDAO {

    private final JdbcTemplate jdbcTemplate;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate, EntityManager entityManager) {
        this.jdbcTemplate = jdbcTemplate;
        this.entityManager = entityManager;
    }

    public List<Book> index() {
        List<Book> bookList  = entityManager
                .createQuery("FROM Book", Book.class)
                .getResultList();
        return bookList;
    }

    public void save(Book book) {
        entityManager.persist(book);
    }

    public Book show(int id) {
        return entityManager.find(Book.class, id);
    }

    public Integer showPersonId(int id) {
            Book book = entityManager.find(Book.class, id);
            if (book != null && book.getPerson() != null) {
                return book.getPerson().getPersonId(); // возвращаем ID владельца
            }
            return null;

    }

    public List<Book> showPersonBooks(int id) {
        return entityManager
                .createQuery("SELECT b FROM Book b WHERE b.person.id = :personId", Book.class)
                .setParameter("personId", id)
                .getResultList();
    }

    public void update(int id, Book updatedBook) {
        Book book = entityManager.find(Book.class, id);
        if (book == null) {
            throw new EntityNotFoundException("Книга с id " + id + " не найдена");
        }

        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setYear(updatedBook.getYear());
    }

    public void delete(int id) {
        Book book = entityManager.find(Book.class, id);
        if (book == null) {
            throw new EntityNotFoundException("Книга с id " + id + " не найдена");
        }
        entityManager.remove(book);
    }
//Назначить книгу
    public void assignBookToPerson(int bookId, int personId) {
        Book book = entityManager.find(Book.class, bookId);
        Person person = entityManager.find(Person.class, personId);

        if (book == null || person == null) {
            throw new EntityNotFoundException("Книга или человек не найдены");
        }

        person.addBook(book); // вспомогательный метод устанавливает связь с обеих сторон
    }

    //Освободить книгу
    public void free(int id) {
        Book book = entityManager.find(Book.class, id);

        if (book == null) {
            throw new EntityNotFoundException("Книга с id " + id + " не найдена");
        }

        Person person = book.getPerson();

        if (person != null) {
            person.removeBook(book); // метод сам удалит связь с обеих сторон
        }
    }

}

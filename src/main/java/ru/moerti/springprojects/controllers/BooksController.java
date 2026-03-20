package ru.moerti.springprojects.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.moerti.springprojects.dao.BookDAO;
import ru.moerti.springprojects.dao.PersonDAO;
import ru.moerti.springprojects.models.Book;

@Controller
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private final BookDAO bookDAO;
    @Autowired
    private PersonDAO personDAO;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public BooksController(BookDAO bookDAO){
        this.bookDAO = bookDAO;
    }

    //Список книг
    @GetMapping
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    //переход на форму новой книги
    @GetMapping("/new")
    public String newBook (Model model){
        model.addAttribute("book", new Book());
        return "books/new";
    }

    //Создать книгу
    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "books/new";

        bookDAO.save(book);
        return "redirect:/books";

    }

    //Отобразить информацию о книге
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.show(id));

        Integer personId = bookDAO.showPersonId(id);

        if (personId != null) {
            model.addAttribute("person", personDAO.show(personId));
        } else {
            model.addAttribute("person", null); // явно указываем null
        }

        model.addAttribute("personList", personDAO.index());
        return "books/show";
    }

    //Переход на страницу редактирования книги
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    //редактировать книгу
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") int id) {
        if (bindingResult.hasErrors())
            return "books/edit";

        bookDAO.update(id, book);
        return "redirect:/books";
    }

    //удалить книгу
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    //Выдать книгу человеку
    @PostMapping("/assign")
    public String assignBook(
            @RequestParam("personId") int personId,  // из видимого select
            @RequestParam("bookId") int bookId       // из скрытого поля
    ) {
        bookDAO.assignBookToPerson(bookId, personId);
        return "redirect:/books";
    }

    //Освободить книгу
    @PatchMapping("/{id}/free")
    public String freeBook (@PathVariable("id") int id){
        bookDAO.free(id);
        return "redirect:/books/"+id;
    }
}

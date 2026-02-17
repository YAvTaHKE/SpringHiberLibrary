package ru.moerti.springprojects.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.moerti.springprojects.dao.BookDAO;
import ru.moerti.springprojects.models.Book;
import ru.moerti.springprojects.models.Person;

@Controller
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private final BookDAO bookDAO;


    public BooksController(BookDAO bookDAO){
        this.bookDAO = bookDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/new")
    public String newBook (@ModelAttribute("person") Book book){
        return "books/new";
    }
}

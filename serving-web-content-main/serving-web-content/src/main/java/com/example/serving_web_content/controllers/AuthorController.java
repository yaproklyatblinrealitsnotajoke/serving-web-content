package com.example.serving_web_content.controllers;

import com.example.serving_web_content.models.Author;
import com.example.serving_web_content.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import com.example.serving_web_content.repo.AuthorRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/author")
    public String authorMain(Model model) {
        Iterable<Author> authors = authorRepository.findAll();
        model.addAttribute("authors", authors);
        return "author-main";
    }

    @GetMapping("/author/add")
    public String authorAdd(Model model) {

        return "author-add";
    }

    @PostMapping("/author/add")
    public String authorPostAdd(@RequestParam String author_name, @RequestParam String nationality, Model model) {
        Author author = new Author(author_name, nationality);
        authorRepository.save(author);
        return "redirect:/book/add";

    }

    @GetMapping("/author/{id}")
    public String authorDetails(@PathVariable(value = "id") long id, Model model) {
        if (!authorRepository.existsById(id)) {
            return "redirect:/book-add";
        }
        Optional<Author> post = authorRepository.findById(id);
        ArrayList<Author> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("author", res);
        return "author-details";
    }

    @GetMapping("/author/{id}/edit")
    public String authorEdit(@PathVariable(value = "id") long id, Model model) {
        if (!authorRepository.existsById(id)) {
            return "redirect:/book-add";
        }
        Optional<Author> post = authorRepository.findById(id);
        ArrayList<Author> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("author", res);
        return "author-edit";
    }

    @PostMapping("/author/{id}/edit")
    public String authorBookUpdate(@PathVariable(value = "id") long id, @RequestParam String author_name, @RequestParam String nationality, Model model) {
        Author author = authorRepository.findById(id).orElseThrow();
        author.setAuthor_name(author_name);
        author.setNationality(nationality);
        authorRepository.save(author);
        List<Book> books = author.getBooks();
        for (Book book : books) {
            book.setAuthor(author);
        }
        return "redirect:/author";


    }


    @PostMapping("/author/{id}/remove")
    public String authorPostDelete(@PathVariable(value = "id") long id, Model model) {
        Author author = authorRepository.findById(id).orElseThrow();
        List<Book> books = author.getBooks();
        if (books != null){
            books.forEach(book -> {
                book.setAuthor(null);
            });
        }
        authorRepository.delete(author);
        return "redirect:/author";

    }


}


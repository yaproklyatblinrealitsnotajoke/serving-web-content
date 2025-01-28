package com.example.serving_web_content.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long author_id;

    private String author_name;
    private String nationality;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "author" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Book> books;


    public Long getAuthor_id() {
        return author_id;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Author(){

    }

    public Author(String author_name, String nationality) {
        this.author_name = author_name;
        this.nationality = nationality;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String toString(){
        return this.author_name;
    }
}

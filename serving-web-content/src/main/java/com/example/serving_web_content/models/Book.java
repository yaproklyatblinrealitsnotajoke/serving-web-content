package com.example.serving_web_content.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long book_id;

    private String book_name;
    private String genre;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToMany(mappedBy = "book")
    private List<Loan> loans;

    @OneToMany(mappedBy = "book")
    private List<Review> reviews;


    public Long getBook_id() {
        return book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public String getGenre() {
        return genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public void setBook_id(Long book_id) {
        this.book_id = book_id;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book() {
    }

    public Book(String book_name, String genre, Author author){
        this.book_name = book_name;
        this.genre = genre;
        this.author = author;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }
    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String toString(){
        return this.book_name;
    }
}

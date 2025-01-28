package com.example.serving_web_content.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userid;

    private String user_name;
    private String email;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userl" , cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Loan> loans;
//
//    @OneToMany(fetch = FetchType.EAGER, mappedBy = "userr" , cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Review> reviews;

    public Long getUserid() {
        return userid;
    }

    public String getEmail() {
        return email;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User(){

    }

    public User(String user_name, String email, Book book) {
        this.user_name = user_name;
        this.email = email;
        this.book = book;
    }

//    public List<Loan> getLoans() {
//        return loans;
//    }
//
//    public void setLoans(List<Loan> loans) {
//        this.loans = loans;
//    }
//
//    public List<Review> getReviews() {
//        return reviews;
//    }
//
//    public void setReviews(List<Review> reviews) {
//        this.reviews = reviews;
//    }

    public String toString(){
        return this.user_name;
    }

}

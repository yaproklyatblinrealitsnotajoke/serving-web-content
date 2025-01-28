//package com.example.serving_web_content.models;
//
//import java.time.LocalDate;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "Review")
//public class Review {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long review_id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User userr;
//
//    @ManyToOne
//    @JoinColumn(name = "book_id")
//    private Book book;
//    private String rtext;
//
//    public User getUserr() {
//        return userr;
//    }
//
//    public Book getBook() {
//        return book;
//    }
//
//    public String getRtext() {
//        return rtext;
//    }
//
//    public Long getReview_id() {
//        return review_id;
//    }
//
//    public void setReview_id(Long review_id) {
//        this.review_id = review_id;
//    }
//
//    public void setUserr(User userr) {
//        this.userr = userr;
//    }
//
//    public void setBook(Book book) {
//        this.book = book;
//    }
//
//    public void setRtext(String rtext) {
//        this.rtext = rtext;
//    }
//
//    public Review(){
//
//    }
//
//    public Review(User userr, Book book, String rtext) {
//        this.userr = userr;
//        this.book = book;
//        this.rtext = rtext;
//    }
//}

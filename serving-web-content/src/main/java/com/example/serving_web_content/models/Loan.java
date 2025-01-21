package com.example.serving_web_content.models;

import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "Loan")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long loan_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userl;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    private LocalDate  loan_date;
    private LocalDate  return_date;

    public User getUserl() {
        return userl;
    }

    public Long getLoan_id() {
        return loan_id;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getLoan_date() {
        return loan_date;
    }

    public LocalDate getReturn_date() {
        return return_date;
    }

    public void setLoan_id(Long loan_id) {
        this.loan_id = loan_id;
    }

    public void setUserl(User userl) {
        this.userl = userl;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setLoan_date(LocalDate loan_date) {
        this.loan_date = loan_date;
    }

    public void setReturn_date(LocalDate return_date) {
        this.return_date = return_date;
    }

    public Loan(){

    }

    public Loan(User userl, Book book, LocalDate loan_date, LocalDate return_date) {
        this.userl = userl;
        this.book = book;
        this.loan_date = loan_date;
        this.return_date = return_date;
    }
}

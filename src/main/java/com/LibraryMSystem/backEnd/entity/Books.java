package com.LibraryMSystem.backEnd.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int bookId;

    @Column(name = "book_name")
    private String bookName;

    @Column(name="author_name")
    private String authorName;

    @Column(name = "Available_books")
    public int noOfCopies;

//    @Column(name = "status", nullable = false)
//    public Boolean status = true;


    @ManyToMany
    @JoinTable(
            name = "books_users",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnore
    private List<Users> users;


    @ManyToMany
    @JoinTable(
            name = "manager_books",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "manager_id")
    )
    @JsonIgnore
    private List<Manager> managers;

    public Books(){}

    public Books(String bookName, String authorName, int noOfCopies, Boolean status) {
        this.bookName = bookName;
        this.authorName = authorName;
        this.noOfCopies = noOfCopies;
//        this.status = status;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public List<Manager> getManagers() {
        return managers;
    }

    public void setManagers(List<Manager> managers) {
        this.managers = managers;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

//    public Boolean getStatus() {
//        return status;
//    }
//
//    public void setStatus(Boolean available) {
//        this.status = available;
//    }
    public int getNoOfCopies() {
        return noOfCopies;
    }

    public void setNoOfCopies(int noOfCopies) {
        this.noOfCopies = noOfCopies;
    }

    @Override
    public String toString() {
        return "Books{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", noOfCopies='" + noOfCopies + '\'' +
//                ", status=" + status +
                ", users=" + users +
                ", managers=" + managers +
                '}';
    }
}

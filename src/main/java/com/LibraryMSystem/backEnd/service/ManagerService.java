package com.LibraryMSystem.backEnd.service;

import com.LibraryMSystem.backEnd.entity.Books;
import com.LibraryMSystem.backEnd.entity.Users;

import java.util.List;

public interface ManagerService {

    Users save(Users users, int managerId);

    Books save(Books books, int managerId);

    Books assignToUser(int managerId, int bookId, int userId);

    List<Users> getAllUsersByManagerId(int managerId);

    List<Books> getAllBooksByManagerId(int managerId);

    void getBookFromUser(int managerId, int bookId, int userId);

    void deleteBookById(int bookId, int managerId);

    void deleteUserById(int userId, int managerId);
}
//
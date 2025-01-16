package com.LibraryMSystem.backEnd.service;

import com.LibraryMSystem.backEnd.entity.Books;
import com.LibraryMSystem.backEnd.entity.Users;

import java.util.List;

public interface ManagerService {

    Users save(Users users, int managerId);

    Books save(Books books, int managerId);

    void assignToUser(int managerId, int bookId, int userId);

    List<Users> getAllUsersByManagerId(int managerId);

    List<Books> getAllBooksByManagerId(int managerId);

    void getBookFromUser(int managerId, int bookId, int userId);

    void deleteRemainingCopies(int bookId, int managerId, int countRemove);

    void deleteUserById(int userId, int managerId);

    Users updateUser(int managerId, int userId, Users updatedUser);

    Books updateBook(int managerId, int bookId, Books updatedBook);
}
//
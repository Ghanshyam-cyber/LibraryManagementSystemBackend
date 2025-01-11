package com.LibraryMSystem.backEnd.service;

import com.LibraryMSystem.backEnd.entity.Books;
import com.LibraryMSystem.backEnd.entity.Manager;
import com.LibraryMSystem.backEnd.entity.Users;
import com.LibraryMSystem.backEnd.repository.BooksRepository;
import com.LibraryMSystem.backEnd.repository.ManagerRepository;
import com.LibraryMSystem.backEnd.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private BooksRepository booksRepository;
    @Autowired
    private UsersRepository usersRepository;

    public ManagerServiceImpl(ManagerRepository theManagerRepository, BooksRepository theBooksRepository, UsersRepository theUsersRepository){
        managerRepository = theManagerRepository;
        booksRepository = theBooksRepository;
        usersRepository = theUsersRepository;
    }

    @Override
    public Users save(Users users, int managerId) {
//      Checking if manager is available or not
        Manager manager = managerRepository.findById(managerId).orElseThrow(() -> new RuntimeException("Manager not found with id: " + managerId));
//        Assigning manager to user
        users.setManager(manager);
//      Saving user to the Database
        Users dbUser = usersRepository.save(users);
        return dbUser;
    }

    @Override
    public Books save(Books books, int managerId) {
//      Checking if manager is available or not
        Manager manager = managerRepository.findById(managerId).orElseThrow(() -> new RuntimeException("Manager not found with id: " + managerId));
//      Saving book in Database
        Books dbBook = booksRepository.save(books);
//      Associate book to Manager
        manager.getBooks().add(dbBook);
        managerRepository.save(manager);

        return dbBook;
    }

    @Override
    public Books assignToUser(int managerId, int bookId, int userId) {
//        Checking user and book availability
        Books books = booksRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not Found with id: " + bookId));

        Users users = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not Found with id: " + userId));
//        Assigning book to user
        users.getBooks().add(books);
        usersRepository.save(users);


        return books;
    }

    @Override
    public List<Users> getAllUsersByManagerId(int managerId) {
        Manager manager = managerRepository.findById(managerId).orElseThrow(() -> new RuntimeException("Manager not found with id: " + managerId));

        return manager.getUsers();
    }

    @Override
    public List<Books> getAllBooksByManagerId(int managerId) {
        Manager manager = managerRepository.findById(managerId).orElseThrow(() -> new RuntimeException("Manager not found with id: " + managerId));

        return manager.getBooks();
    }

    @Override
    public void getBookFromUser(int managerId, int bookId, int userId) {

        Books books = booksRepository.findById(bookId).orElseThrow(()-> new RuntimeException("Book not found with id: " + bookId));
        Users users = usersRepository.findById(userId).orElseThrow(()-> new RuntimeException("User not found with id: " + userId));

//        checking book in user account
        if (users.getBooks().contains(books)){
            users.getBooks().remove(books);
            usersRepository.save(users);
        }else {
            throw new RuntimeException("Book with id " + bookId + "is not assigned with user with id " + userId );
        }

    }

    @Override
    public void deleteBookById(int bookId, int managerId) {
        Manager manager = managerRepository.findById(managerId).orElseThrow(() -> new RuntimeException("Manager Not Found With Id: " + managerId));

        Books books = booksRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found with Id: " + bookId));

        if (!manager.getBooks().contains(books)){
            throw  new RuntimeException("Manager with id " + managerId + "does not contain book with id " + bookId);
        }

        manager.getBooks().remove(books);
        managerRepository.save(manager);
        booksRepository.delete(books);

    }

    @Override
    public void deleteUserById(int userId, int managerId) {

        Manager manager = managerRepository.findById(managerId).orElseThrow(()-> new RuntimeException("Manager not found with id: " + managerId));

        Users users = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        if (!manager.getUsers().contains(users)){
            throw  new RuntimeException("Manager with id " + managerId + "does not contain user with id " + userId);
        }

        manager.getUsers().remove(users);
        managerRepository.save(manager);
        usersRepository.delete(users);
    }


}
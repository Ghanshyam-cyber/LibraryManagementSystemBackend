package com.LibraryMSystem.backEnd.controller;

import com.LibraryMSystem.backEnd.entity.Books;
import com.LibraryMSystem.backEnd.entity.Users;
import com.LibraryMSystem.backEnd.repository.BooksRepository;
import com.LibraryMSystem.backEnd.service.ManagerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/manager")
public class ManagerRestController {

    private ManagerService managerService;

    private BooksRepository booksRepository;


    public ManagerRestController(ManagerService theManagerService, BooksRepository theBookRepository){
        managerService = theManagerService;
        booksRepository = theBookRepository;
    }

//    Getting all user with a specific manager
    @GetMapping("/{managerId}/users")
    public ResponseEntity<List<Users>> getUsers(@PathVariable int managerId){
        List<Users> users = managerService.getAllUsersByManagerId(managerId);
        return ResponseEntity.ok(users);
    }

//    Getting all books with a specific manager
    @GetMapping("/{managerId}/books")
    public ResponseEntity<List<Books>> getBooks(@PathVariable int managerId){
        List<Books> books = managerService.getAllBooksByManagerId(managerId);
        return ResponseEntity.ok(books);
    }

//    Saving User with a specific Manager ID
    @PostMapping("/{managerId}/user")
    public ResponseEntity<String> addUser(@PathVariable int managerId, @RequestBody Users users){
        managerService.save(users, managerId);
        return ResponseEntity.status(HttpStatus.CREATED).body("User added successfully");
    }

//    Saving Book with a specific Manager ID
    @PostMapping("/{managerId}/book")
    public ResponseEntity<String> addBook(@PathVariable int managerId, @RequestBody Books books){
        managerService.save(books, managerId);
        return ResponseEntity.status(HttpStatus.CREATED).body("Book added Successfully");
    }

//   Assigning book to the User
    @PostMapping("/{managerId}/assign")
    public ResponseEntity<String> assignToUser(@PathVariable int managerId, @RequestBody Map<String, Integer> requstBody){

        int userId = requstBody.get("userId");
        int bookId = requstBody.get("bookId");

        managerService.assignToUser(managerId, bookId, userId);
        return  ResponseEntity.ok("Book with bookId " + bookId + " assign to User with userId " + userId);
    }

//    Retrieving Book from the user
    @PostMapping("/{managerId}/return")
    public ResponseEntity<String> returnBookFromUser(@PathVariable int managerId, @RequestBody Map<String, Integer> requestBody){

        int userId = requestBody.get("userId");
        int bookId = requestBody.get("bookId");

        managerService.getBookFromUser(managerId, bookId, userId);
        return ResponseEntity.ok("Book with bookId " + bookId + " has retrieved from User with userId " + userId);
    }

//    Deleting Book with manager and Book id
    @DeleteMapping("/{managerId}/book/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable int managerId, @PathVariable int bookId, @RequestParam int countRemove ){
        managerService.deleteRemainingCopies(bookId, managerId, countRemove);

        return ResponseEntity.ok("Successfully deleted " + countRemove + "un-assigned books ");
    }

//    Deleting User with manager and User id
    @DeleteMapping("/{managerId}/user/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable int managerId, @PathVariable int userId){
        managerService.deleteUserById(userId, managerId);

        return ResponseEntity.ok("The User with userId " + userId + " has been deleted Succesfully");
    }

//    Checking Book Availability Status in Library
//    @GetMapping("/{managerId}/book/{bookId}/status")
//    public ResponseEntity<String> getBookStatus(@PathVariable int managerId, @PathVariable int bookId){
//
//        Books books = booksRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book Not Found"));
//
//        String status = books.getStatus() ? "Available" : "Not Available";
//
//        return ResponseEntity.ok("The Book Status is: " + status);

//    }

//    Updating User
    @PutMapping("/{managerId}/updateUser/{userId}")
    public ResponseEntity<Users> updateUser(@PathVariable int managerId, @PathVariable int userId, @RequestBody Users updatedUser){
        Users users = managerService.updateUser(managerId, userId,updatedUser);
        return ResponseEntity.ok(users);
    }


    //    Updating Book
    @PutMapping("/{managerId}/updateBook/{bookId}")
    public ResponseEntity<Books> updateUser(@PathVariable int managerId, @PathVariable int bookId, @RequestBody Books updatedBook){

        Books books = managerService.updateBook(managerId, bookId, updatedBook);

        return ResponseEntity.ok(books);
    }

}




/*

 CONTROLLER CODE WITHOUT RESPONSE ENTITY
  Getting all users
@GetMapping("/users/{managerId}")
public List<Users> getAllUsers(@PathVariable int managerId){
    return managerService.getAllUsersByManagerId(managerId);
}
    Getting all books
@GetMapping("/books/{managerId}")
public List<Books> getAllBooks(@PathVariable int managerId){
    return managerService.getAllBooksByManagerId(managerId);
}
  Adding User to DB
@PostMapping("/addUser/{managerId}")
public Users save(@RequestBody Users theUser, @PathVariable int managerId){
    return managerService.save(theUser, managerId);
}
    Adding Book to DB
@PostMapping("/addBook/{managerId}")
public Books save(@RequestBody Books theBooks, @PathVariable int managerId){
    return managerService.save(theBooks, managerId);
}
    Assigning book to user
@PutMapping("/assign")
public Books assignBook(@RequestParam(name = "bookId") int bookId, @RequestParam(name = "bookId") int userId){
    return managerService.assignToUser(bookId, userId);
}

    Returning book from user
@PutMapping("/return")
public String returnBook(@RequestParam int bookId, @RequestParam int userId ){
    managerService.getBookFromUser(bookId,userId);
    return "Book with id: "+ bookId + " has been returned from user with id: " + userId;

}

    Deleting book by bookId
@DeleteMapping("{managerId}/deleteBook/{bookId}")
public String deleteRemainingCopies(@PathVariable int managerId, @PathVariable int bookId){
    managerService.deleteRemainingCopies(managerId,bookId);

    return "The Book has been deleted Successfully with id: " + bookId;
}
    Deleting user by userId

@DeleteMapping("{managerId}/deleteUser/{userId}")
public String deleteUserById(@PathVariable int managerId, @PathVariable int userId){
    managerService.deleteUserById(managerId, userId);

    return "The User has been deleted Successfully with id: " + userId;
}
*/








































package com.LibraryMSystem.backEnd.repository;

import com.LibraryMSystem.backEnd.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Books,Integer> {

}

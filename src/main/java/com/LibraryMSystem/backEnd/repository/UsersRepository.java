package com.LibraryMSystem.backEnd.repository;

import com.LibraryMSystem.backEnd.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
}

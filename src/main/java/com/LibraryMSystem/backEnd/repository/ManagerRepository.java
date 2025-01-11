package com.LibraryMSystem.backEnd.repository;

import com.LibraryMSystem.backEnd.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager,Integer> {
}

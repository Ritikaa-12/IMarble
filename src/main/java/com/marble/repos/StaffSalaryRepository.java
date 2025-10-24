package com.marble.repos;

import com.marble.entities.StaffSalary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffSalaryRepository extends JpaRepository<StaffSalary, Integer> {
}
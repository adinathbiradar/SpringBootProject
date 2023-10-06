package com.Emp_System.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Emp_System.entity.Employee;

public interface EmpRepository extends JpaRepository<Employee, Integer>{

}

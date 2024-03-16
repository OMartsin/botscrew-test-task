package dev.martsin.universitytesttask.repositories;

import dev.martsin.universitytesttask.models.Department;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @EntityGraph(attributePaths = {"headOfDepartment"})
    Optional<Department> findByName(String name);
}
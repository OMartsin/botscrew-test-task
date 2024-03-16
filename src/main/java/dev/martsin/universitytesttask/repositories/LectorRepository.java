package dev.martsin.universitytesttask.repositories;

import dev.martsin.universitytesttask.models.Lector;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectorRepository extends JpaRepository<Lector, Long> {
    @EntityGraph(attributePaths = {"degree"})
    List<Lector> findByDepartmentsName(String department);
    Integer countByDepartmentsName(String department);
    List<Lector> findByNameContaining(String template);
}
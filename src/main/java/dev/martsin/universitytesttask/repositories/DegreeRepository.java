package dev.martsin.universitytesttask.repositories;

import dev.martsin.universitytesttask.models.Degree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DegreeRepository extends JpaRepository<Degree, Long> {
}
package net.mohamed.spring_app.repository;

import net.mohamed.spring_app.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,String> {
    Student findByCode(String code);
}

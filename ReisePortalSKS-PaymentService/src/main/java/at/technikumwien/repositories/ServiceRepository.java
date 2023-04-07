package at.technikumwien.repositories;

import at.technikumwien.entities.Author;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Author, Long> {
    @Override
    @EntityGraph("Author.fetchData")
    List<Author> findAll();

    @EntityGraph("Author.fetchData")
    Optional<Author> findById(Long id);

}
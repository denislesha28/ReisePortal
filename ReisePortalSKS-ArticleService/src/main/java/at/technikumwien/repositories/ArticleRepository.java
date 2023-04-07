package at.technikumwien.repositories;

import at.technikumwien.entities.Article;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Override
    @EntityGraph("Article.fetchCategoryAuthors")
    Optional<Article> findById(Long id);

    @Override
    @EntityGraph("Article.fetchCategoryAuthors")
    List<Article> findAll();

    @EntityGraph("Article.fetchCategoryAuthors")
    List<Article> findAllByCategoryId(long categoryId);

    @EntityGraph("Article.fetchCategoryAuthors")
    List<Article> findAllByAuthorsId(long authorId);

    @EntityGraph("Article.fetchCategoryAuthors")
    List<Article> findAllByTitleIgnoreCaseContaining(String searchTerm);
}
package at.technikumwien.repositories;

import at.technikumwien.entities.Entry;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Entry, Long> {

    @EntityGraph(attributePaths = {"title"})
    @Query(value = "SELECT COUNT(t.title) FROM Entry t GROUP BY t.title")
    List<Long> countAllByArticleIds();

    @EntityGraph(attributePaths = {"title"})
    @Query(value = "SELECT t.title FROM Entry t GROUP BY t.title")
    List<String> getAllGroupedByTitle();

    @EntityGraph(attributePaths = {"title"})
    @Query(value = "SELECT COUNT(t) FROM Entry t WHERE t.title = ?1")
    Long countAllByArticleId(String title);

    @EntityGraph(attributePaths = {"title"})
    @Query(value = "SELECT DISTINCT t.title FROM Entry t WHERE t.title = ?1")
    String getEntryByArticleId(String title);

    @Query(value = "SELECT COUNT(t.title) FROM t_statistics_entry t\n" +
            "JOIN t_statistics_entry_author tsea on tsea.entryid = t.id\n" +
            "JOIN t_statistics_author tsa on tsea.authorid = tsa.id\n" +
            "WHERE tsa.firstName = :name OR tsa.lastName = :name GROUP BY title",nativeQuery = true)
    List<Long> countAllByAuthor(@Param("name") String authorName);


    @Query(value = "SELECT distinct(t.title) FROM t_statistics_entry t\n" +
            "JOIN t_statistics_entry_author tsea on tsea.entryid = t.id\n" +
            "JOIN t_statistics_author tsa on tsea.authorid = tsa.id\n" +
            "WHERE tsa.firstName = :name OR tsa.lastName = :name",nativeQuery = true)
    List<String> getEntriesByAuthor(@Param("name") String authorName);

    @Query(value = "select COUNT(*) FROM t_statistics_entry t where YEAR(t.timestamp) = :year AND MONTH(t.timestamp) = :month GROUP BY t.title",nativeQuery = true)
    List<Long> countAllByTimestamp(@Param("year") String year,@Param("month") String month);

    @Query(value = "select DISTINCT(t.title) FROM t_statistics_entry t where YEAR(t.timestamp) = :year AND MONTH(t.timestamp) = :month GROUP BY t.title",nativeQuery = true)
    List<String> getEntriesByTimestamp(@Param("year") String year,@Param("month") String month);

}
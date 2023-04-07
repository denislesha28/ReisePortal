package at.technikumwien.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "t_articleservice_article")
@NamedEntityGraph(
    name = "Article.fetchCategoryAuthors",
    attributeNodes = {
        @NamedAttributeNode("category"),
        @NamedAttributeNode("authors")
    }
)
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 1000)
    private String text;

    @Column(nullable = false)
    private LocalDate publicationDate;

    @ManyToOne(cascade = CascadeType.MERGE)   // EAGER
    @JoinColumn(name = "categoryid")
    private Category category;

    @ManyToMany(cascade = CascadeType.MERGE)   // LAZY
    @JoinTable(
        name = "t_articleservice_news_author",
        joinColumns = @JoinColumn(name = "newsid"),
        inverseJoinColumns = @JoinColumn(name = "authorid")
    )
    private List<Author> authors;

    public Article(String title, String text, LocalDate publicationDate, Category category, List<Author> authors) {
        this(null, title, text, publicationDate, category, authors);
    }
}

package at.technikumwien.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "t_statistics_entry")
@NamedEntityGraph(
        name = "Article.fetchStatistics",
        attributeNodes = {
                @NamedAttributeNode("title")
        }
)
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @ManyToMany(cascade = CascadeType.MERGE)   // LAZY ManyToMany Relation between Entrys and Authors
    @JoinTable(
            name = "t_statistics_entry_author",
            joinColumns = @JoinColumn(name = "entryid"),
            inverseJoinColumns = @JoinColumn(name = "authorid")
    )
    private List<Author> authors;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp timestamp;

    public Entry(String title, List<Author> authors, Timestamp timestamp){
        this(null,title,authors,timestamp);
    }
}

package at.technikumwien.events;

import at.technikumwien.entities.Author;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor   // optional since default
@Getter
@ToString
public class Article {
    private Long id;
    private String title;
    private String text;
    private LocalDate publicationDate;
    private boolean topNews;
    private List<Author> authors;
}

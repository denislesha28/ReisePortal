package at.technikumwien;

import at.technikumwien.entities.Article;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.Instant;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ArticleEvent {
    long timestamp;
    Article article;


    public static ArticleEvent prepareStatistics(Article article) {
        return new ArticleEvent(
                Instant.now().toEpochMilli(),
                article
        );
    }
}

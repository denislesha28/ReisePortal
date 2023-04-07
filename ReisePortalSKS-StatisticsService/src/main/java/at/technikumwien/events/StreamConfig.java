package at.technikumwien.events;

import at.technikumwien.entities.Entry;
import at.technikumwien.repositories.ArticleRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.function.Consumer;

@Configuration
@Log
public class StreamConfig {
    @Autowired
    private ArticleRepository articleRepository;
    @Bean
    public Consumer<ArticleEvent> handleReaderEvent() {
        return event -> {
            saveEntry(event.getArticle());
        };
    }

    private void saveEntry(Article article){
        articleRepository.save(new Entry(article.getTitle(),
                article.getAuthors(),Timestamp.from(Instant.now())));
    }
}

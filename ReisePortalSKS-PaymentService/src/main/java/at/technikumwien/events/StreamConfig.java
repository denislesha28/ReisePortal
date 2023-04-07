package at.technikumwien.events;

import at.technikumwien.entities.Author;
import at.technikumwien.repositories.ServiceRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;
import java.util.function.Consumer;

@Configuration
@Log
public class StreamConfig {
    @Autowired
    private ServiceRepository serviceRepository;
    private AuthorList<Author> managedAuthorList = new AuthorList<>();

    @Bean
    public Consumer<ArticleEvent> handleReaderEvent() {
        return event -> {
            persistClicks(event.getArticle().getAuthors());
            log.info(event.getArticle().getAuthors()+"");
        };
    }

    private void persistClicks(List<Author> authors){
        for (Author author : authors){
            if (!managedAuthorList.containsAuthor(author)){
                managedAuthorList.add(author);
            }
            //todo add click anyway
            Author toBeSaved = managedAuthorList.getManagedAuthor(author);
            toBeSaved.addClick();
            serviceRepository.save(toBeSaved);
        }

    }
}

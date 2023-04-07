package at.technikumwien;

import at.technikumwien.entities.Article;
import at.technikumwien.repositories.ArticleRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/articles")
@CrossOrigin()
@Log
public class ArticleResource {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private Source source;


    @GetMapping
    public List<Article> retrieveAll(
            @RequestParam("categoryid") Optional<Long> optCategoryId,
            @RequestParam("authorid") Optional<Long> optAuthorId,
            @RequestParam("searchterm") Optional<String> optSearchTerm
    ) {
        log.info("retrieveAll() >> categoryId=" + optCategoryId + ", authorId=" + optAuthorId + ", searchTerm=" + optSearchTerm);

        int numberOfSetParameters = (int) Stream.of(optCategoryId, optAuthorId, optSearchTerm)
                .filter(Optional::isPresent)
                .count();

        switch (numberOfSetParameters) {
            case 0:
                return articleRepository.findAll();

            case 1:
                if (optCategoryId.isPresent()) {
                    return articleRepository.findAllByCategoryId(optCategoryId.get());
                }
                else if (optAuthorId.isPresent()) {
                    return articleRepository.findAllByAuthorsId(optAuthorId.get());
                }
                else if (optSearchTerm.isPresent()) {
                    return articleRepository.findAllByTitleIgnoreCaseContaining(optSearchTerm.get());
                }

            default:
                throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
        }
    }


    @GetMapping("/{id}")
    @Transactional
    public Article retrieve(@PathVariable long id) {
        log.info("retrieve() >> id=" + id);
        Article article = new Article();
        try {
            article = articleRepository.findById(id)
                    .orElseThrow(
                            () -> new EmptyResultDataAccessException("can't find article with id " + id, 1)
                    );
            sendStatistics(ArticleEvent.prepareStatistics(article));
        }
        catch (Exception e){
            log.info("Error sending kafka message -> "+e);
        }
        return article;
    }

    private void sendStatistics(ArticleEvent event){
        Message<ArticleEvent> message = MessageBuilder
                .withPayload(event)
                .build();
        source
                .output()
                .send(message);
    }

}

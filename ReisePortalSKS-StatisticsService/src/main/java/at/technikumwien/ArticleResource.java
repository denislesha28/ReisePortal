package at.technikumwien;

import at.technikumwien.dtos.StatisticsDTO;
import at.technikumwien.repositories.ArticleRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static at.technikumwien.dtos.StatisticsDTO.generateOutputList;

@RestController
@RequestMapping("/summary")
@CrossOrigin
@Log
public class ArticleResource {
    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping
    public List<StatisticsDTO> retrieveAll(
            @RequestParam("title") Optional<String> optTitle,
            @RequestParam("authorname") Optional<String> optAuthorName,
            @RequestParam("month") Optional<String> optMonth,
            @RequestParam("year") Optional<String> optYear
    ) {
        log.info("retrieveAll() >> title=" + optTitle + ", authorName=" + optAuthorName);

        int numberOfSetParameters = (int) Stream.of(optTitle, optAuthorName, optMonth, optYear)
                .filter(Optional::isPresent)
                .count();

        switch (numberOfSetParameters) {
            case 0:
                return generateOutputList(articleRepository.getAllGroupedByTitle(), articleRepository.countAllByArticleIds());

            case 1:
                if (optTitle.isPresent()) {
                    return Collections.singletonList(new StatisticsDTO(articleRepository.getEntryByArticleId
                            (optTitle.get()), articleRepository.countAllByArticleId(optTitle.get())));
                }
                else if (optAuthorName.isPresent()) {
                    return generateOutputList(articleRepository.getEntriesByAuthor
                            (optAuthorName.get()), articleRepository.countAllByAuthor(optAuthorName.get()));
                }
            case 2:
                if (optMonth.isPresent() && optYear.isPresent()){
                    log.info("Month Parameter: "+ optMonth.get());
                    log.info("Year Parameter: "+ optYear.get());
                    return generateOutputList(articleRepository.getEntriesByTimestamp(optYear.get(), optMonth.get()),
                            articleRepository.countAllByTimestamp(optYear.get(), optMonth.get()));
                }
            default:
                throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED);
        }
    }


}

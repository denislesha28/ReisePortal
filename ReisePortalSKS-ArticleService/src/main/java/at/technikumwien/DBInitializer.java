package at.technikumwien;

import at.technikumwien.entities.Article;
import at.technikumwien.entities.Author;
import at.technikumwien.entities.Category;
import at.technikumwien.enums.Sex;
import at.technikumwien.repositories.ArticleRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Component
@Log
public class DBInitializer {
    @Autowired
    private ArticleRepository articleRepository;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void handleApplicationReady() {
        log.info("initialize database ...");


        Article article = articleRepository.save(
                new Article(
                        "Der Alpenzoo Innsbruck",
                        "Der Alpenzoo von Innsbruck gehört zu den beliebtesten Sehenswürdigkeiten von Österreich.Er ist der höchstgelegene Zoo Europas. " +
                                "Hier wohnen rund 2000 Tiere aus 150 Arten der alpinen Tierwelt.Gegründet wurde der Zoo 1962, " +
                                "er ist vor allem für seine Arbeit von Wiederansiedlungsprojekten von in Tirol ausgestorbenen " +
                                "oder vom Aussterben bedrohten Tierarten bekannt.Im Alpenzoo könnt Ihr Braunbären, Fischotter, " +
                                "Bartgeier, Wölfe oder Steinadler sehen und aus der Nähe erleben.\n",
                        LocalDate.of(2021, 1, 1),
                        new Category("Wien"),
                        List.of(
                                new Author(Sex.FEMALE, "Martina", "Musterfrau"),
                                new Author(Sex.MALE, "Denis", "Mustermann")
                        )
                )
        );

        Category category = article.getCategory();
        Author author = article.getAuthors().get(0);

        articleRepository.save(
                new Article(
                        "Der Wiener Prater",
                        "Die nächste in Wien ansässige Österreich Sehenswürdigkeit ist der Wiener Prater.\n" +
                                "Besonders bekannt ist der Prater für sein Riesenrad " +
                                "und rund 250 andere Attraktionen.\n" +
                                "Schießbuden, Geisterbahnen, Kaspertheater oder " +
                                "andere Unterhaltungsprogramme erwarten Dich hier.\n"  +
                                "Der Ursprung des Wurstelpraters, wie der Vergnügungspark im Prater heißt, liegt im Jahr 1766.",
                        LocalDate.of(2021, 1, 2),
                        new Category(category.getId(), category.getName()),
                        Collections.singletonList(author)
                )
        );


        articleRepository.save(
                new Article(
                        "Die Tscheppaschlucht in Kärnten",
                        "Die über Jahrtausende vom herabstürzenden Wasser des Loiblbachs geschaffene Schlucht ist " +
                                "die ideale Sehenswürdigkeit in Österreich für den Sommer.\n" +
                                "Das kühle Wasser des Wildbaches sorgt für Abkühlung während Ihr die Schlucht durchwandert.\n" +
                                "Sie ist mit Brücken und Stiegen ausgestattet, sodass Ihr trockenen Fußes durch die Schlucht laufen könnt.",
                        LocalDate.of(2021, 3, 20),
                        new Category("Innsbruck"),
                        List.of(
                                new Author(Sex.FEMALE, "Erika", "Musterfrau"),author
                        )
                )
        );

        articleRepository.save(
                new Article(
                        "Mozarts Geburtshaus in Salzburg",
                        "Die bekannteste ist sicherlich das Geburtshaus von Wolfgang Amadeus Mozart.\n" +
                                "Auf eine spannende Reise durch die Zeit könnt Ihr euch bei einem Besuch von Wolfgang" +
                                " Amadeus Mozart Geburtshaus begeben."+ "Auf insgesamt drei Etagen erfahrt Ihr allerlei " +
                                "Wissenswertes und Interessantes über das Wunderkind und seine musikalische Entwicklung."+
                                "Ihr könnt euch Kostüme und Requisiten aus der Schaffenszeit von Mozart anschauen." +
                                "Außerdem werden Originaldokumente von seinen Reisen, Gemälde und Alltagsgegenstände gezeigt.",
                        LocalDate.of(2022, 1, 10),
                        new Category("Salzburg"),
                        List.of(
                                new Author(Sex.MALE, "Max", "Mustermann")
                        )
                )
        );
    }
}

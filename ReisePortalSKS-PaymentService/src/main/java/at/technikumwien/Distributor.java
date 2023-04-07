package at.technikumwien;

import at.technikumwien.entities.Author;
import at.technikumwien.repositories.ServiceRepository;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log
@EnableAsync
@EnableScheduling
public class Distributor {
    @Autowired
    private ServiceRepository serviceRepository;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void handleApplicationReady() {
        log.info("Payment Service is online!");
        //log.info("Results : "+serviceRepository.findAll());
    }

    @Scheduled(fixedDelay = 10000)
    public void payAuthors() {
        try {
            List<Author> authors = serviceRepository.findAll();
            for (Author author: authors){
                int clicks = author.getClicks();
                if (clicks >= 100){
                    log.info(author.getFirstName() + " " + author.getLastName() + " has been awarded " +
                            "" + (double)clicks / 100 + " Dollars for the generated traffic");
                }
                else {
                    log.info(author.getFirstName() + " " + author.getLastName() + " has been awarded " +
                            "" + clicks + " Cents for the generated traffic");
                }
            }

        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}

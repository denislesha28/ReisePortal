package at.technikumwien;

import at.technikumwien.repositories.CustomJpaRepository;
import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@Log
@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepository.class)
public class MainApp {

    public static void main(String[] args) {
        SpringApplication.run(MainApp.class, args);
    }



}

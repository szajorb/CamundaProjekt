package org.example.camunda.process.solution;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.Deployment;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
@EnableZeebeClient
@Deployment(resources = "classpath*:/models/*.*")
public class ProcessApplication {
    static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    static ConsoleHandler handler = new ConsoleHandler();

    public static void main(String[] args) {
        SpringApplication.run(ProcessApplication.class, args);
        logger.setLevel(Level.ALL);
        handler.setLevel(Level.ALL);
        logger.addHandler(handler);
    }

    @ZeebeWorker(type = "mail", autoComplete = true)
    public void sendMail(final JobClient client, final ActivatedJob job) {
        logger.info("Wyślij upomnienie o oddaniu ksiażki.");
    }

    @ZeebeWorker(type = "naloz_kare", autoComplete = true)
    public void givePenalty(final JobClient client, final ActivatedJob job) {
        logger.info("Nałóż karę za przetrzymywanie książki po terminie.");
    }

    @ZeebeWorker(type = "info", autoComplete = true)
    public void sendInfo(final JobClient client, final ActivatedJob job) {
        logger.info("Wyślij informację o nałożeniu kary.");
    }
}

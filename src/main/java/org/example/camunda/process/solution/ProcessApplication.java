package org.example.camunda.process.solution;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.EnableZeebeClient;
import io.camunda.zeebe.spring.client.annotation.Deployment;
import io.camunda.zeebe.spring.client.annotation.ZeebeVariablesAsType;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.KeyStore;

import java.util.UUID;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;



@SpringBootApplication
@EnableZeebeClient
@Deployment(resources = "classpath*:/models/*.*")
public class ProcessApplication {
    static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    static ConsoleHandler handler = new ConsoleHandler();
    private JobClient client;
    private String recovery_certyficate_uuid;

    public static void main(String[] args) {
    SpringApplication.run(ProcessApplication.class, args);
    logger.setLevel(Level.ALL);
    handler.setLevel(Level.ALL);
    logger.addHandler(handler);

  }
@ZeebeWorker(type= "notify_person_to_quarantine", autoComplete = true)
    public void notifyPersonToQuarantine(final JobClient client, final ActivatedJob job, @ZeebeWorker String person_uuid){
      logger.info("Retrieving contact details for peroson" + person_uuid + "from external database ");
      logger.info("Sent notification to person" + person_uuid + "to quarantine");
}


@ZeebeWorker(type="send_certyficate_of_recovery", autoComplete = true)
public void sendCertyficateOfRecovery(final JobClient client, final ActivatedJob job, @ZeebeWorker String person_uuid, @ZeebeVariablesAsType String recovery_certyficate_uuid){
    logger.info("Retrieving Recovery Certyficate "+ recovery_certyficate_uuid +"from external database" );
    logger.info(" Retriving Recovery details for person "+ person_uuid + "from external database ");
    logger.info("Sending Recovery Certficate to person "+ person_uuid + "Enjoy that ice-cream!");
}

  }



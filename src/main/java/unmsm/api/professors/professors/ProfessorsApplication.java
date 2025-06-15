package unmsm.api.professors.professors;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import unmsm.api.professors.professors.application.dto.OrcidResponse;
import unmsm.api.professors.professors.application.ports.OrcidService;

@SpringBootApplication
public class ProfessorsApplication implements CommandLineRunner {

  @Autowired private OrcidService orcidService;

  public static void main(String[] args) {
    SpringApplication.run(ProfessorsApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    OrcidResponse response = orcidService.getOrcidInfo("0000-0003-3168-6175");
    System.out.println(response);
  }
}

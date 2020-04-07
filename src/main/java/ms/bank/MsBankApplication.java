package ms.bank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MsBankApplication {

  public static void main(String[] args) {
    SpringApplication.run(MsBankApplication.class, args);
  }

}

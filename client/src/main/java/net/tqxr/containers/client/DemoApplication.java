package net.tqxr.containers.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

//        int serverChecks = 0;
//        while (!ServerCheck.serverListening("app", 8080) && ++serverChecks < 100) {
//            try {
//                Thread.sleep(1000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }

        SpringApplication.run(DemoApplication.class, args);
        WireMockProof wmp = new WireMockProof();
        wmp.hitWireMock();

    }
}

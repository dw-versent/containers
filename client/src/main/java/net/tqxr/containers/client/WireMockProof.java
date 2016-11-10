package net.tqxr.containers.client;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

class WireMockProof {
    MessageBody messageBody;

    void hitWireMock() {

        messageBody = hitConfiguredMapping();
        printMessageBody(messageBody);

        messageBody = hitHardCodedMapping();
        printMessageBody(messageBody);


    }

//    private MessageBody hitAdmin(){
//        System.out.println("Hitting ADMIN............");
//        RestTemplate restTemplate = new RestTemplate();
//        return restTemplate.getForObject("http://wiremock:8089/__admin/");
//    }

    private MessageBody hitHardCodedMapping() {
        System.out.println("Hitting HARD CODED MAPPING............");
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://wiremock:8089/some/thing", MessageBody.class);
    }

    private MessageBody hitConfiguredMapping() {
        System.out.println("CONFIGURING Wiremock............");

        WireMock.configureFor("wiremock.host", 8089);
        stubFor(get(urlEqualTo("/mock-hello"))
                .willReturn(aResponse()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("mock-hello.json")
                )
        );

        System.out.println("...........Wiremock configured");

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://wiremock.host:8089/mock-hello", MessageBody.class);
    }

    private void printMessageBody(MessageBody messageBody) {
        System.out.println("@(@)@$($()@$$(@)(@)$(@($)@($)@($)@($)@$(@)$(@)($)@($\n");

        System.out.println(
                String.format("%s", messageBody)
        );

        System.out.println("@(@)@$($()@$$(@)(@)$(@($)@($)@($)@($)@$(@)$(@)($)@($\n\n");
    }

}

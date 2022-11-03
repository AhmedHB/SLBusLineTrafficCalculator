package com.ahmed.sltrafficcalculator;




import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=RANDOM_PORT)
public class SlTrafficCalculatorApplicationTests {
    @Autowired
    private WebTestClient client;

    @Test
    public void trafficCalculateTopNrOfStopsFeeNoParamOk() {
        int max = 10;

        client.get()
                .uri("/api/buslines/topnrofstops" )
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.*", hasSize(max));
    }

    @Test
    public void trafficCalculateTopNrOfStopsFeeOk() {
        int max = 1;

        client.get()
                .uri("/api/buslines/topnrofstops?max=" + max)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.*", hasSize(max));
    }

    @Test
    public void trafficCalculateTopNrOfStopsFeeNegativValueNok() {
        int negativMax = -1;

        client.get()
                .uri("/api/buslines/topnrofstops?max=" + negativMax)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.path").isEqualTo("/api/buslines/topnrofstops?max=" +negativMax)
                .jsonPath("$.message").isEqualTo("Negative value " + negativMax);
    }

    @Test
    public void trafficCalculateTopNrOfStopsFeeNonNumericValueNok() {
        String nonNumeric = "a";

        client.get()
                .uri("/api/buslines/topnrofstops?max=" + nonNumeric)
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isBadRequest()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.path").isEqualTo("/api/buslines/topnrofstops?max=" +nonNumeric);
    }
}

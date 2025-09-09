package com.darlanmarangoni.investment;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

@SpringBootTest
class InvestmentApplicationTests {

	@Test
	void contextLoads() throws InterruptedException {
        var personagens = Arrays.asList(
                "goku", "VEDITA", "TRUNKS", "GOTEN", "GOHAN"
        );

        for (var personagen : personagens) {
            System.out.println(personagen);
            Thread.sleep(1000);
        }

	}

}

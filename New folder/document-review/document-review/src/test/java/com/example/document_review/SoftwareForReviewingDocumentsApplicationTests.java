package com.example.document_review;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2) // koristi H2 u testu

class SoftwareForReviewingDocumentsApplicationTests {

	@Test
	void contextLoads() {
	}

}

package com.example.document_review;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "file:src/test/application-test.properties")
class SoftwareForReviewingDocumentsApplicationTests {

	@Test
	void contextLoads() {
	}

}

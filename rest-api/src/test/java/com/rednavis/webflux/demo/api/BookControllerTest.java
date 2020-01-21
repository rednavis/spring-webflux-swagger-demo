package com.rednavis.webflux.demo.api;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;

import com.rednavis.webflux.demo.DemoApplication;
import com.rednavis.webflux.demo.model.Book;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(classes = {DemoApplication.class}, properties = {"logging.level.root=OFF"})
@AutoConfigureWebTestClient(timeout = "10000")
public class BookControllerTest {

  @Autowired
  protected WebTestClient webTestClient;

  private static Book create(final WebTestClient webTestClient, final Book book) {
    Objects.requireNonNull(webTestClient);
    return webTestClient
        .post()
        .uri("/api/book")
        .contentType(APPLICATION_JSON)
        .accept(APPLICATION_JSON)
        .body(fromValue(toMap(book)))
        .exchange()
        .expectStatus()
        .isCreated()
        .expectBody(Book.class)
        .returnResult()
        .getResponseBody();
  }

  private static Map<String, String> toMap(Book book) {
    return Map.of(
        "name", book.getName(),
        "isbn", book.getIsbn());
  }

  @Test
  public void shouldSaveAndFindById() {

    var book =
        create(
            webTestClient,
            Book.builder().name("Alice in the Wonderland").isbn("978-3-16-148410-0").build());

    Assertions.assertNotNull(book);
    Assertions.assertNotNull(book.getId());

    webTestClient
        .get()
        .uri("/api/book/{id}", book.getId())
        .accept(APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody()
        .jsonPath("$.id")
        .isEqualTo(book.getId())
        .jsonPath("$.name")
        .isEqualTo(book.getName())
        .jsonPath("$.isbn")
        .isEqualTo(book.getIsbn())
        .jsonPath("$.none")
        .doesNotExist();
  }
}
package com.rednavis.webflux.demo.api;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import com.rednavis.webflux.demo.model.Book;
import com.rednavis.webflux.demo.service.BookService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/book")
@RequiredArgsConstructor
public class BookController {

  private final BookService bookService;

  @PostMapping
  @ResponseStatus(CREATED)
  public Mono<Book> create(@RequestBody @Valid Book book) {
    return bookService.save(book);
  }

  @PutMapping("/{id}")
  public Mono<Book> update(@PathVariable String id, @RequestBody @Valid Book book) {
    return bookService.update(id, book);
  }

  @GetMapping("/{id}")
  public Mono<Book> get(@PathVariable String id) {
    return bookService.findById(id);
  }

  @GetMapping
  public Flux<Book> all() {
    return bookService.findAll();
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(NO_CONTENT)
  public Mono<Void> delete(@PathVariable String id) {
    bookService.delete(id);
    return Mono.empty();
  }
}
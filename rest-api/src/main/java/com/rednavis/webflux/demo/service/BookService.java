package com.rednavis.webflux.demo.service;

import com.rednavis.webflux.demo.model.Book;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Temporal in-memory implementation of book service.
 */
@Service
public class BookService {

  private final Map<String, Book> bookRepository;

  public BookService() {
    bookRepository = new ConcurrentHashMap<>();
  }

  public Mono<Book> save(Book book) {
    book = book.withId(UUID.randomUUID().toString());
    bookRepository.put(book.getId(), book);
    return Mono.just(book);
  }

  public Mono<Book> update(String id, Book book) {
    if (!bookRepository.containsKey(id)) {
      throw new IllegalArgumentException();
    }
    bookRepository.put(id, book);
    return Mono.just(book);
  }

  public Mono<Book> findById(String id) {
    return Mono.just(bookRepository.get(id));
  }

  public Flux<Book> findAll() {
    return Flux.fromIterable(bookRepository.values());
  }

  public Mono<Book> delete(String id) {
    Book removed = bookRepository.remove(id);
    return Mono.just(removed);
  }
}
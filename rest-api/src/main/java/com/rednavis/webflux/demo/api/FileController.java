package com.rednavis.webflux.demo.api;


import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/upload")
public class FileController {
    @PostMapping(value = "/")
    public Flux<String> upload(@RequestPart("file") Flux<FilePart> file) throws IOException {
        Flux<String> temp = file.concatMap(filePart -> createTempFile(filePart.filename())
                .flatMap(tempFile -> filePart.transferTo(tempFile)
                        .then(Mono.just("File length: " + tempFile.toFile().length()))));
        return temp;

    }

    @GetMapping
    @ResponseBody
    public String status() {
        return "FileController" ;
    }

    private Mono<Path> createTempFile(String suffix) {
        return Mono.defer(() -> {
            return Mono.just(new File("./rest-api/src/main/resources/" + suffix ).toPath());
        })
                .subscribeOn(Schedulers.boundedElastic());
    }
}

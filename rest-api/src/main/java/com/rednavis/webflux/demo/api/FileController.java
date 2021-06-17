package com.rednavis.webflux.demo.api;


import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

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

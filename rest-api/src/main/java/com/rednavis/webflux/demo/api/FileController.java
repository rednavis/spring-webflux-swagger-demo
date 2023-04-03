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

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/upload")
public class FileController {
    /**
     * Metho save file to temporary file and returns the size in bytes
     * @param file
     * @return
     * @throws IOException
     */
    //   @PostMapping(value = "/")
    @RequestMapping(
            method = POST,
            path = "/",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}
    )
    public Flux<String> upload(@RequestPart("file") Flux<FilePart> file)  {
        Flux<String> temp = file.concatMap(filePart -> createTempFile(filePart.filename())
                .flatMap(tempFile -> filePart.transferTo(tempFile)
                        .then(Mono.just("File length: " + tempFile.toFile().length()))));
        return temp;
    }

    private Mono<Path> createTempFile(String fileName){

        try {
            final File tempFile = File.createTempFile(fileName, "");
            return Mono.defer(() -> {
                return Mono.just(tempFile.toPath());
            })
                    .subscribeOn(Schedulers.boundedElastic());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

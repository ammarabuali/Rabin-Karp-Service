package org.example.fastsearchproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.fastsearchproject.entity.LoadFileResponse;
import org.example.fastsearchproject.entity.LoadingRequest;
import org.example.fastsearchproject.service.FileReader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final FileReader pdfService;

    @PostMapping("/load")
    public ResponseEntity<LoadFileResponse> readPdf(@RequestBody LoadingRequest filePath) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pdfService.readPdf(filePath.getPath()));
    }
}

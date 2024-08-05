package org.example.fastsearchproject.service;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.fastsearchproject.entity.LoadFileResponse;
import org.example.fastsearchproject.entity.PdfFile;
import org.example.fastsearchproject.repo.PdfFileRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class ReadPdf implements FileReader {
    private final PdfFileRepository pdfFileRepository;

    @Override
    public LoadFileResponse readPdfFile(String filePath) {
        List<String> content = new ArrayList<>();
        PdfReader reader = null;

        try {
            reader = new PdfReader(filePath);
            int numberOfPages = reader.getNumberOfPages();

            for (int i = 1; i <= numberOfPages; i++) {
                String pageContent = PdfTextExtractor.getTextFromPage(reader, i);
                content.add(pageContent);
            }
        } catch (IOException e) {
            log.error("Failed to read PDF file: {}", filePath, e);
            return LoadFileResponse.builder().created(false).build();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return LoadFileResponse.builder().created(true).build();
    }

    @Override
    public LoadFileResponse readPdf(String filePath) {
        StringBuilder content = new StringBuilder();
        PdfReader reader = null;
        try {
            reader = new PdfReader(filePath);
            int numberOfPages = reader.getNumberOfPages();

            for (int i = 1; i <= numberOfPages; i++) {
                String pageContent = PdfTextExtractor.getTextFromPage(reader, i);
                content.append(pageContent).append("\n");
            }

            reader.close();
            PdfFile pdfFile = PdfFile.builder().filePath(filePath).content(content.toString()).build();
            pdfFileRepository.save(pdfFile);
        } catch (IOException e) {
            log.error("Failed to read PDF file: {}", filePath, e);
            return LoadFileResponse.builder().created(false).build();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return LoadFileResponse.builder().created(true).build();
    }
}



package org.example.fastsearchproject.service;

import org.example.fastsearchproject.entity.LoadFileResponse;


public interface FileReader {
    LoadFileResponse readPdfFile(String filePath);
    LoadFileResponse readPdf(String filePath);
}

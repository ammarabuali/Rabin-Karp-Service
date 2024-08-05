package org.example.fastsearchproject.repo;

import org.example.fastsearchproject.entity.PdfFile;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PdfFileRepository extends MongoRepository<PdfFile, String> {
}
package com.gss.microservice.helper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gss.microservice.entity.Difficulty;
import com.gss.microservice.entity.Region;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

public class TourFromFile {
    private String packageType, title, description, blurb, price, length,
            bullets, keywords, difficulty, region;

    public TourFromFile() {
    }

    public static List<TourFromFile> read(String fileToImport) throws IOException {
        return new ObjectMapper().setVisibility(FIELD, ANY).
                readValue(new FileInputStream(fileToImport), new TypeReference<List<TourFromFile>>() {
                });
    }

    public String getPackageType() {
        return packageType;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getBlurb() {
        return blurb;
    }

    public Integer getPrice() {
        return Integer.parseInt(price);
    }

    public String getLength() {
        return length;
    }

    public String getBullets() {
        return bullets;
    }

    public String getKeywords() {
        return keywords;
    }

    public Difficulty getDifficulty() {
        return Difficulty.valueOf(difficulty);
    }

    public Region getRegion() {
        return Region.findByValue(region);
    }
}
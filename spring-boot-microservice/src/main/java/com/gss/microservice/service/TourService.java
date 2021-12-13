package com.gss.microservice.service;

import com.gss.microservice.entity.Difficulty;
import com.gss.microservice.entity.Region;
import com.gss.microservice.entity.Tour;
import com.gss.microservice.entity.TourPackage;
import com.gss.microservice.repository.TourPackageRepository;
import com.gss.microservice.repository.TourRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TourService {
    @Autowired
    private TourPackageRepository tourPackageRepository;
    @Autowired
    private TourRepository tourRepository;

    public Tour createTour(String title, String description,
                           String blurb, Integer price,
                           String duration, String bullets,
                           String keywords, String name,
                           Difficulty difficulty, Region region) {
        TourPackage tourPackage = tourPackageRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Do not findEny tour"));
        Tour tour = new Tour(title, description,
                blurb, price,
                duration, bullets,
                keywords, tourPackage,
                difficulty, region);
        log.debug("tour", tour);
        return tourRepository.save(tour);
    }

    public long total() {
        return tourRepository.count();
    }

}

package com.gss.microservice.controller;

import com.gss.microservice.entity.Tour;
import com.gss.microservice.entity.TourRating;
import com.gss.microservice.entity.TourRatingPk;
import com.gss.microservice.repository.TourRatingRepository;
import com.gss.microservice.repository.TourRepository;
import com.gss.microservice.web.RatingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/tours/{tourId}/rating")
public class TourRatingController {

    private TourRatingRepository tourRatingRepository;
    private TourRepository repository;

    @Autowired
    public TourRatingController(TourRatingRepository tourRatingRepository, TourRepository repository) {
        this.tourRatingRepository = tourRatingRepository;
        this.repository = repository;
    }

    private Tour validateTourId(Integer tourId) {
        return repository.findById(tourId)
                .orElseThrow(() -> new NoSuchElementException("Tour does not exist " + tourId));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        return ex.getMessage();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTourRating(@PathVariable(value = "tourId") int tourId,
                                 @RequestBody @Validated RatingDTO ratingDTO) {
        Tour tour = validateTourId(tourId);
        TourRatingPk ratingPk = new TourRatingPk(tour, ratingDTO.getCustomerId());
        TourRating tourRating = new TourRating(ratingPk, ratingDTO.getScore(), ratingDTO.getComment());
        tourRatingRepository.save(tourRating);
    }

    @GetMapping
    public List<RatingDTO> getAllRatingForTour(@PathVariable(value = "tourId") int tourId) {
        validateTourId(tourId);
        return tourRatingRepository.findByPkTourId(tourId)
                .stream().map(RatingDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/average")
    public Map<String, Double> getAverage(@PathVariable(value = "tourId") int tourId) {
        validateTourId(tourId);
        double average = tourRatingRepository.findByPkTourId(tourId).stream()
                .mapToInt(TourRating::getScore).average()
                .orElseThrow(() -> new NoSuchElementException("Tour has no Rating"));
        return Map.of("Average", average);
    }

    @PutMapping
    public RatingDTO updateWithPut(@PathVariable(value = "tourId") int tourId,
                                   @RequestBody @Validated RatingDTO ratingDto) {
        TourRating rating = verifyTourRating(tourId, ratingDto.getCustomerId());
        rating.setScore(ratingDto.getScore());
        rating.setComment(ratingDto.getComment());
        return new RatingDTO(tourRatingRepository.save(rating));
    }

    @PatchMapping
    public RatingDTO updateWithPatch(@PathVariable(value = "tourId") int tourId,
                                     @RequestBody @Validated RatingDTO ratingDto) {
        TourRating rating = verifyTourRating(tourId, ratingDto.getCustomerId());
        if (ratingDto.getScore() != null) {
            rating.setScore(ratingDto.getScore());
        }
        if (ratingDto.getComment() != null) {
            rating.setComment(ratingDto.getComment());
        }
        return new RatingDTO(tourRatingRepository.save(rating));
    }

    @DeleteMapping(path = "/{customerId}")
    public void delete(@PathVariable(value = "tourId") int tourId,
                       @PathVariable(value = "customerId") int customerId) {
        TourRating rating = verifyTourRating(tourId, customerId);
        tourRatingRepository.delete(rating);
    }

    private TourRating verifyTourRating(int tourId, int customerId) throws NoSuchElementException {
        return tourRatingRepository.findByPkTourIdAndPkCustomerId(tourId, customerId)
                .orElseThrow(() -> new NoSuchElementException("Tour-Rating pair for request("
                        + tourId + " for customer" + customerId));
    }


    @GetMapping(path = "page")
    public Page<RatingDTO> getRatings(@PathVariable(value = "tourId") int tourId,
                                      Pageable pageable) {
        validateTourId(tourId);
        Page<TourRating> ratings = tourRatingRepository.findByPkTourId(tourId, pageable);
        return new PageImpl<>(
                ratings.get().map(RatingDTO::new).collect(Collectors.toList()),
                pageable,
                ratings.getTotalElements()
        );
    }

}

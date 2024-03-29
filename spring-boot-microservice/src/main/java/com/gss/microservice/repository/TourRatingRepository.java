package com.gss.microservice.repository;

import com.gss.microservice.entity.TourRating;
import com.gss.microservice.entity.TourRatingPk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface TourRatingRepository extends CrudRepository<TourRating, TourRatingPk> {

    List<TourRating> findByPkTourId(Integer tourId);

    Page<TourRating> findByPkTourId(Integer tourId, Pageable Pageable);

    Optional<TourRating> findByPkTourIdAndPkCustomerId(Integer tourId, Integer customerId);

}

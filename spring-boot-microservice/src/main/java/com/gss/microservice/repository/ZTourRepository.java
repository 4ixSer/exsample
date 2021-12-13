package com.gss.microservice.repository;

import com.gss.microservice.entity.Difficulty;
import com.gss.microservice.entity.Region;
import com.gss.microservice.entity.Tour;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface ZTourRepository extends CrudRepository<Tour, Integer> {

    List<Tour> findByPrice(Integer price);

    Collection<Tour> findByDifficulty(Difficulty difficulty);

    List<Tour> findByTourPackageCodeAndRegion(String code, Region region);

    List<Tour> findByRegionIn(List<Region> regions);

    List<Tour> findByPriceLessThan(Integer maxPrice);

    List<Tour> findByKeywordsContains(String keyword);

    List<Tour> findByTourPackageCodeAndBulletsLike(String code, String searchString);

    @Query("select t from Tour t where t.tourPackage.code = ?1 and t.difficulty = ?2 and t.region =?3 and t.price <= ?4")
    List<Tour> lookupTour(String code, Difficulty difficulty, Region region, Integer maxPrice);

}

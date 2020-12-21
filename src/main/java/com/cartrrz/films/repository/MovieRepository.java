package com.cartrrz.films.repository;

import com.cartrrz.films.model.sql.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Page<Movie> findAllByAvailability(boolean availability, Pageable pageable);

    @Query("SELECT DISTINCT m from Movie m " +
            "WHERE m.title LIKE concat('%', :search, '%') AND " +
            "m.availability = true")
    Page<Movie> searchByTitle(
            @Param("search") String search,
            Pageable pageable
    );
}

package com.cartrrz.films.service.impl;

import com.cartrrz.films.dto.MovieDTO;
import com.cartrrz.films.model.sql.Movie;
import com.cartrrz.films.repository.MovieRepository;
import com.cartrrz.films.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public MovieDTO create(MovieDTO movieDto) {
        Movie movie = populateMovie(movieDto, null);
        movieRepository.save(movie);
        return movieDto;
    }

    @Override
    public MovieDTO update(Long MovieId, MovieDTO movieDTO) throws Exception {

        Movie movie = movieRepository.findById(MovieId).orElseThrow(() -> new Exception("Movie not found"));
        movie = populateMovie(movieDTO, movie);
        movieRepository.save(movie);
        return movieDTO;
    }

    @Override
    public MovieDTO remove(Long MovieId) throws Exception {

        Movie movie = movieRepository.findById(MovieId).orElseThrow(() -> new Exception("Movie not found"));
        movieRepository.deleteById(MovieId);

        MovieDTO dto = new MovieDTO();
        dto.setTitle(dto.getTitle());
        dto.setDescription(movie.getDescription());
        dto.setAvailability(movie.isAvailability());
        dto.setRentPrice(movie.getRentPrice());
        dto.setSalePrice(movie.getSalePrice());
        dto.setStock(movie.getStock());
        return dto;
    }

    private Movie populateMovie(MovieDTO dto, Movie movie){
        if(movie == null){
            movie = new Movie();
        }
        movie.setTitle(dto.getTitle());
        movie.setDescription(dto.getDescription());
        movie.setAvailability(dto.isAvailability());
        movie.setRentPrice(dto.getRentPrice());
        movie.setSalePrice(dto.getSalePrice());
        movie.setStock(dto.getStock());
        return movie;
    }

}

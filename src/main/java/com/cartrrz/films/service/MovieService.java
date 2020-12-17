package com.cartrrz.films.service;

import com.cartrrz.films.dto.MovieDTO;

public interface MovieService {

    MovieDTO create(MovieDTO movieDto);
    MovieDTO update(Long movieId, MovieDTO movieDTO) throws Exception;
    MovieDTO remove(Long movieId) throws Exception;
}

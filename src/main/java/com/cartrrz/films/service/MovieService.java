package com.cartrrz.films.service;

import com.cartrrz.films.dto.MovieDTO;
import com.cartrrz.films.dto.RentDTO;
import com.cartrrz.films.service.impl.UserDetailsImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieService {

    MovieDTO create(MovieDTO movieDto);

    MovieDTO update(Long movieId, MovieDTO movieDTO) throws Exception;

    MovieDTO remove(Long movieId) throws Exception;

    MovieDTO changeAvailability(Long movieId, boolean availability) throws Exception;

    List<MovieDTO> listAll();

    Page<MovieDTO> list(Pageable pageable, String query);

    MovieDTO findMovie(Long id) throws Exception;

    RentDTO rentMovie(UserDetailsImpl userDetails, Long id, Long daysToReturn) throws Exception;

    MovieDTO likeMovie(UserDetailsImpl userDetails, Long id) throws Exception;

    MovieDTO buyMovie(UserDetailsImpl userDetails, Long id) throws Exception;
}

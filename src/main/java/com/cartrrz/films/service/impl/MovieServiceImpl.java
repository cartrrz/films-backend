package com.cartrrz.films.service.impl;

import com.cartrrz.films.dto.MovieDTO;
import com.cartrrz.films.dto.RentDTO;
import com.cartrrz.films.model.sql.*;
import com.cartrrz.films.repository.*;
import com.cartrrz.films.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Override
    public MovieDTO create(MovieDTO movieDto) {
        Movie movie = populateMovie(movieDto, null);
        movie.setModifiedDate(LocalDateTime.now());
        movie.setCreatedDate(LocalDateTime.now());
        movieRepository.save(movie);
        movieDto.setMovieId(movie.getId());
        return movieDto;
    }

    @Override
    public MovieDTO update(Long MovieId, MovieDTO movieDTO) throws Exception {

        Movie movie = movieRepository.findById(MovieId).orElseThrow(() -> new Exception("Movie not found"));
        movie = populateMovie(movieDTO, movie);
        movie.setModifiedDate(LocalDateTime.now());
        movieRepository.save(movie);
        movieDTO.setMovieId(movie.getId());
        return movieDTO;
    }

    @Override
    public MovieDTO remove(Long movieId) throws Exception {

        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new Exception("Movie not found"));
        movieRepository.deleteById(movieId);
        return populateDto(movie);
    }

    @Override
    public MovieDTO changeAvailability(Long movieId, boolean availability) throws Exception {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new Exception("Movie not found"));
        movie.setAvailability(availability);
        movie.setModifiedDate(LocalDateTime.now());
        movieRepository.save(movie);
        return populateDto(movie);
    }

    @Override
    public List<MovieDTO> listAll() {
        List<MovieDTO> movieList = movieRepository.findAll()
                .stream()
                .map(movie -> populateDto(movie))
                .collect(Collectors.toList());
        return movieList;
    }

    @Override
    public Page<MovieDTO> list(Pageable pageable, String query) {
        Page<Movie> movies;
        if(StringUtils.isEmpty(query)){
            movies = movieRepository.findAllByAvailability(true, pageable);
        }else{
            movies = movieRepository.searchByTitle(query, pageable);
        }
        return movies.map(movie -> populateDto(movie));
    }

    @Override
    public MovieDTO findMovie(Long id) throws Exception {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new Exception("Movie not found"));
        return populateDto(movie);
    }

    @Override
    public RentDTO rentMovie(UserDetailsImpl userDetails, Long id, Long daysToReturn) throws Exception {

        Movie movie = movieRepository.findById(id).orElseThrow(() -> new Exception("Movie not found"));
        User user = userRepository.findById(userDetails.getId()).orElseThrow(() -> new Exception("User not found"));

        if(!movie.isAvailability()){
            throw new Exception("the movie is un available");
        }
        if(movie.getStock() <= 0){
            throw new Exception("stock is 0");
        }
        Rent rent = new Rent();
        rent.setMovie(movie);
        rent.setUser(user);
        rent.setRentDate(LocalDateTime.now());
        rent.setReturnDate(LocalDateTime.now().plusDays(daysToReturn));
        rent.setCreatedDate(LocalDateTime.now());
        rent.setModifiedDate(LocalDateTime.now());
        rentRepository.save(rent);
        movie.setStock(movie.getStock() - 1);
        movieRepository.save(movie);
        RentDTO dto = new RentDTO();
        dto.setMovieId(movie.getId());
        dto.setUserId(user.getId());
        dto.setRentDate(rent.getRentDate());
        dto.setReturnDate(rent.getReturnDate());
        return dto;
    }

    @Override
    public MovieDTO likeMovie(UserDetailsImpl userDetails, Long id) throws Exception {
        Like like = new Like();

        Movie movie = movieRepository.findById(id).orElseThrow(() -> new Exception("Movie not found"));
        User user = userRepository.findById(userDetails.getId()).orElseThrow(() -> new Exception("User not found"));
        like.setMovie(movie);
        like.setStatus(true);
        like.setUser(user);
        like.setCreatedDate(LocalDateTime.now());
        like.setModifiedDate(LocalDateTime.now());
        likeRepository.save(like);
        return populateDto(movie);
    }

    @Override
    public MovieDTO buyMovie(UserDetailsImpl userDetails, Long id) throws Exception {
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new Exception("Movie not found"));
        User user = userRepository.findById(userDetails.getId()).orElseThrow(() -> new Exception("User not found"));

        if(!movie.isAvailability()){
            throw new Exception("the movie is un available");
        }
        if(movie.getStock() <= 0){
            throw new Exception("stock is 0");
        }
        Sale sale = new Sale();
        sale.setMovie(movie);
        sale.setUser(user);
        sale.setSaleDate(LocalDateTime.now());
        sale.setCreatedDate(LocalDateTime.now());
        sale.setModifiedDate(LocalDateTime.now());
        movie.setStock(movie.getStock() - 1);
        movieRepository.save(movie);
        return populateDto(movie);
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

    private MovieDTO populateDto(Movie movie){
        MovieDTO dto = new MovieDTO();
        dto.setTitle(movie.getTitle());
        dto.setDescription(movie.getDescription());
        dto.setAvailability(movie.isAvailability());
        dto.setRentPrice(movie.getRentPrice());
        dto.setSalePrice(movie.getSalePrice());
        dto.setStock(movie.getStock());
        dto.setMovieId(movie.getId());
        return dto;
    }

}

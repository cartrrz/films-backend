package com.cartrrz.films.controller;

import com.cartrrz.films.dto.MovieDTO;
import com.cartrrz.films.model.ObjectResponse;
import com.cartrrz.films.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/add")
    @ResponseBody
    public ObjectResponse<MovieDTO> create(
        @RequestBody MovieDTO movieDTO
    ){
        ObjectResponse response = new ObjectResponse();
        try{
            movieDTO = movieService.create(movieDTO);
            response.setObject(movieDTO);
            response.setSuccess(true);
        }catch (Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public ObjectResponse<MovieDTO> update(
            @RequestBody MovieDTO movieDTO,
            @PathVariable("id") Long movieId
    ){
        ObjectResponse response = new ObjectResponse();
        try{
            movieDTO = movieService.update(movieId, movieDTO);
            response.setObject(movieDTO);
            response.setSuccess(true);
        }catch (Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public ObjectResponse<MovieDTO> delete(
            @PathVariable("id") Long movieId
    ){
        ObjectResponse response = new ObjectResponse();
        try{
            MovieDTO movieDTO = movieService.remove(movieId);
            response.setObject(movieDTO);
            response.setSuccess(true);
        }catch (Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }
}

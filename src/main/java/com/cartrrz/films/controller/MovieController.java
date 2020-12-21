package com.cartrrz.films.controller;

import com.cartrrz.films.dto.MovieDTO;
import com.cartrrz.films.dto.RentDTO;
import com.cartrrz.films.model.ObjectResponse;
import com.cartrrz.films.service.MovieService;
import com.cartrrz.films.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/{id}/update")
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

    @DeleteMapping("/{id}/delete")
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

    @PutMapping("/{id}/changeAvailability/{availability}")
    @ResponseBody
    public ObjectResponse<MovieDTO> changeAvailability(
            @PathVariable("id") Long movieId,
            @PathVariable("availability") boolean availability
    ){
        ObjectResponse response = new ObjectResponse();
        try{
            MovieDTO movieDTO = movieService.changeAvailability(movieId, availability);
            response.setObject(movieDTO);
            response.setSuccess(true);
        }catch (Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @GetMapping("/all")
    @ResponseBody
    public ObjectResponse<MovieDTO> listAll(){
        ObjectResponse response = new ObjectResponse();
        try{
            List<MovieDTO> list = movieService.listAll();
            response.setObject(list);
            response.setSuccess(true);
        }catch (Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @GetMapping
    @ResponseBody
    public ObjectResponse<MovieDTO> list(
            @RequestParam(name = "query", required = false) String query,
            @PageableDefault()
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "title", direction = Sort.Direction.DESC),
            }) Pageable pageable
    ){
        ObjectResponse response = new ObjectResponse();
        try{
            Page<MovieDTO> list = movieService.list(pageable, query);
            response.setObject(list);
            response.setSuccess(true);
        }catch (Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }


    @GetMapping("/detail/{id}")
    @ResponseBody
    public ObjectResponse<MovieDTO> findMovie(@PathVariable Long id){
        ObjectResponse response = new ObjectResponse();
        try{
            MovieDTO dto= movieService.findMovie(id);
            response.setObject(dto);
            response.setSuccess(true);
        }catch (Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @PostMapping("/{id}/like")
    @ResponseBody
    public ObjectResponse<MovieDTO> likeMovie(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long id
    ){
        ObjectResponse response = new ObjectResponse();
        try{
            MovieDTO dto= movieService.likeMovie(userDetails, id);
            response.setObject(dto);
            response.setSuccess(true);
        }catch (Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @PostMapping("/{id}/rent/{daysToReturn}")
    @ResponseBody
    public ObjectResponse<MovieDTO> rentMovie(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long id,
            @PathVariable Long daysToReturn
    ){
        ObjectResponse response = new ObjectResponse();
        try{
            RentDTO dto= movieService.rentMovie(userDetails, id, daysToReturn);
            response.setObject(dto);
            response.setSuccess(true);
        }catch (Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    @PostMapping("/{id}/buy")
    @ResponseBody
    public ObjectResponse<MovieDTO> buyMovie(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long id
    ){
        ObjectResponse response = new ObjectResponse();
        try{
            MovieDTO dto= movieService.buyMovie(userDetails, id);
            response.setObject(dto);
            response.setSuccess(true);
        }catch (Exception e){
            response.setSuccess(false);
            response.setMessage(e.getMessage());
        }
        return response;
    }
}

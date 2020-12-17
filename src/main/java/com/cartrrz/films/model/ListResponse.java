package com.cartrrz.films.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListResponse<T> extends BaseResponse{

    private List<T> list;

    private long total;
}

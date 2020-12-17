package com.cartrrz.films.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ObjectResponse<T> extends BaseResponse {

    private T object;
}

package com.cartrrz.films.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class BaseResponse {

    private boolean success = true;

    private String message;

}

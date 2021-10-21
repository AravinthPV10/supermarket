package com.example.supermarket.dto;

import com.example.supermarket.dto.filters.UserView;
import com.example.supermarket.dto.validators.UserValidator;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter@Setter
public class ProductDto {

    @JsonView({UserView.Read.class, UserView.Update.class})
    private int id;
    @JsonView({UserView.Create.class , UserView.Update.class , UserView.Read.class})
    @NotNull(message = "brand id cannot be null",groups = {UserValidator.Create.class})
    private int brandId;
    @JsonView({UserView.Create.class , UserView.Update.class , UserView.Read.class})
    @NotBlank(message = "name cannot be null",groups = {UserValidator.Create.class})
    @NotNull(message = "name cannot be null.",groups = {UserValidator.Create.class})
    private String name;
    @JsonView({UserView.Create.class , UserView.Update.class , UserView.Read.class})
    @NotNull(message = "name cannot be null",groups = {UserValidator.Create.class})
    private int price;
}

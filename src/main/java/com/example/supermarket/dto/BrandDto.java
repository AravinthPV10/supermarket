package com.example.supermarket.dto;

import com.example.supermarket.dto.filters.BaseView;
import com.example.supermarket.dto.filters.UserView;
import com.example.supermarket.dto.validators.UserValidator;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter@Setter
public class BrandDto {
    @JsonView({UserView.Read.class, UserView.Update.class})
    int id;

    @JsonView({UserView.Create.class , UserView.Update.class , UserView.Read.class})
    @NotBlank(message = "name cannot be null",groups = {UserValidator.Create.class})
    private String name;

}
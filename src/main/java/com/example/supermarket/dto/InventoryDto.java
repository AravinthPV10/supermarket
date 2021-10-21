package com.example.supermarket.dto;

import com.example.supermarket.dto.filters.UserView;
import com.example.supermarket.dto.validators.UserValidator;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter@Setter
public class InventoryDto {
    @JsonView({UserView.Read.class, UserView.Update.class})
    private int id;

    @JsonView({UserView.Create.class , UserView.Update.class , UserView.Read.class})
    @NotNull(message = "product id cannot be null",groups = {UserValidator.Create.class})
    private int productId;

    @JsonView({UserView.Create.class , UserView.Update.class , UserView.Read.class})
    @NotNull(message = "quantity cannot be null",groups = {UserValidator.Create.class})
    private int quantity;

}

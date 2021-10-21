package com.example.supermarket.dto;

import com.example.supermarket.dto.filters.UserView;
import com.example.supermarket.dto.validators.UserValidator;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter@Setter
public class PointDto {

    @JsonView({UserView.Read.class, UserView.Update.class})
    private int id;

    @JsonView({UserView.Create.class , UserView.Update.class , UserView.Read.class})
    @NotNull(message = "customer phone number cannot be null",groups = {UserValidator.Create.class})
    private long customerPhone;

    @JsonView({UserView.Create.class , UserView.Update.class , UserView.Read.class})
    @NotNull(message = "transaction id cannot be null",groups = {UserValidator.Create.class})
    private int transactionId;

    @JsonView({UserView.Create.class , UserView.Update.class , UserView.Read.class})
    private int points;

    @JsonView(UserView.Internal.class)
    private String status;
}

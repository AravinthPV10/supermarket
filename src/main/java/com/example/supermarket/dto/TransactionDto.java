package com.example.supermarket.dto;

import com.example.supermarket.dto.filters.BaseView;
import com.example.supermarket.dto.filters.UserView;
import com.example.supermarket.dto.validators.UserValidator;
import com.example.supermarket.enums.Status;
import com.example.supermarket.enums.Type;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.sql.Date;

@Getter
@Setter
public class TransactionDto {

    @JsonView({UserView.Read.class, UserView.Update.class})
    private int id;

    @JsonView({UserView.Create.class , UserView.Update.class , UserView.Read.class})
    @NotNull(message = "customer phone number cannot be null",groups = {UserValidator.Create.class})
    private long customerPhone;

    @JsonView({UserView.Create.class , UserView.Update.class , UserView.Read.class})
    @NotNull(message = "date cannot be null",groups = {UserValidator.Create.class})
    private Date date;

    @JsonView({UserView.Create.class , UserView.Update.class , UserView.Read.class})
    @NotNull(message = "product id cannot be null",groups = {UserValidator.Create.class})
    private int productId;

    @JsonView({UserView.Create.class , UserView.Update.class , UserView.Read.class})
//    @NotNull(message = "quantity cannot be null",groups = {UserValidator.Create.class})
    private int quantity;

    @JsonView({UserView.Create.class , UserView.Update.class , UserView.Read.class})
    @NotNull(message = "amount cannot be null",groups = {UserValidator.Create.class})
    private float amount;

    @JsonView({UserView.Update.class , UserView.Read.class})
    private float total;

    @JsonView(UserView.Internal.class)
    private Status.TransactionsStatus status;

    @JsonView({UserView.Create.class , UserView.Update.class , UserView.Read.class})
    private Type.TransactionsType type;

}

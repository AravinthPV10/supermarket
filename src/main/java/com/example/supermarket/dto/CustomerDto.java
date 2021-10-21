package com.example.supermarket.dto;

import com.example.supermarket.dto.filters.UserView;
import com.example.supermarket.dto.validators.UserValidator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter@Setter
@JsonIgnoreProperties(
        value = "is_deleted",
        allowSetters = true
)
public class CustomerDto {
    @JsonView({UserView.Read.class, UserView.Update.class})
    private int id;

    @JsonView({UserView.Create.class , UserView.Update.class , UserView.Read.class})
    @NotBlank(message = "name cannot be null",groups = {UserValidator.Create.class})
    private String name;

    @JsonView({UserView.Create.class , UserView.Update.class , UserView.Read.class})
    @NotNull(message = "phone number cannot be null",groups = {UserValidator.Create.class})
//    @Pattern(regexp="\\(?([0-9]{3})\\)?([ .-]?)([0-9]{3})\\2([0-9]{4})", groups = {UserValidator.Create.class},message = "Phone number format error")
//    @Pattern(regexp = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
//            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
//            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$")
    private long phone;

    @JsonView({UserView.Create.class , UserView.Update.class , UserView.Read.class})
    @Email(message = "Email is not valid",regexp = "^(?=.{4,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"  +
            "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$",groups = {UserValidator.Create.class})
    @NotEmpty(message = "Email cannot be empty",groups = {UserValidator.Create.class})
    private String email;

}

package com.product.user.application.dto.req;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserUpdateRequest {

    @NotBlank(message = "First name field cannot be empty")
    @Size(min = 1, max = 100, message = "First name must be between 1 and 100 characters long")
    private String firstName;

    @NotBlank(message = "Last name field cannot be empty")
    @Size(min = 1, max = 100, message = "Last name must be between 1 and 100 characters long")
    private String lastName;

    @NotBlank(message = "Patronymic name field cannot be empty")
    @Size(min = 1, max = 100, message = "Patronymic name must be between 1 and 100 characters long")
    private String patronymic;
}

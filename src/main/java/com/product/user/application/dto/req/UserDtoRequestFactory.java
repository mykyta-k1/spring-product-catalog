package com.product.user.application.dto.req;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

public final class UserDtoRequestFactory {

    /**
     * Призначення: DTO для автентифікації користувача
     */
    @Data
    public static class UserLoginDto {

        @NotBlank(message = "Email field cannot be empty")
        @Email(message = "Incorrect email format")
        @Size(max = 254, message = "Email must not exceed 254 characters")
        private String email;

        @NotBlank(message = "Password field cannot be empty")
        @Size(min = 8, max = 72, message = "Password length must be between 8 and 72 characters")
        @Pattern(
            regexp =
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).+$",
            message =
                "Password is not strong enough, use uppercase and lowercase letters, numbers and special characters")
        private String password;
    }

    /**
     * Призначення: DTO для реєстрації користувача
     */
    @Data
    @ToString
    public static class UserRegisterDto {

        @NotBlank(message = "First name field cannot be empty")
        @Size(min = 1, max = 100, message = "First name must be between 1 and 100 characters long")
        private String firstName;

        @NotBlank(message = "Last name field cannot be empty")
        @Size(min = 1, max = 100, message = "Last name must be between 1 and 100 characters long")
        private String lastName;

        @NotBlank(message = "Patronymic name field cannot be empty")
        @Size(min = 1, max = 100, message = "Patronymic name must be between 1 and 100 characters long")
        private String patronymic;

        @NotBlank(message = "Email field cannot be empty")
        @Email(message = "Incorrect email format")
        @Size(max = 254, message = "Email must not exceed 254 characters")
        private String email;

        @NotBlank(message = "Password field cannot be empty")
        @Size(min = 8, max = 72, message = "Password length must be between 8 and 72 characters")
        @Pattern(
            regexp =
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).+$",
            message =
                "Password is not strong enough, use uppercase and lowercase letters, numbers and special characters")
        private String password;
    }

    /**
     * Призначення: DTO для оновлення даних користувача
     */
    @Getter
    public static class UserUpdateRequest {

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

}

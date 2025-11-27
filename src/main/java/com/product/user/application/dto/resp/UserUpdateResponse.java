package com.product.user.application.dto.resp;

public record UserUpdateResponse(
    String firstName,
    String lastName,
    String patronymic
) {

}

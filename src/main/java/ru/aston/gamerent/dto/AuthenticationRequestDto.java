package ru.aston.gamerent.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AuthenticationRequestDto(

        @NotBlank(message = "Email is mandatory!")
        @Pattern(regexp = "^(?=.{6,}$)[\\s]*[a-zA-Z0-9]+([!\"#$%&'()*+,\\-.\\/:;<=>?\\[\\]\\^_{}][a-zA-z0-9]+)*@([\\w]+(-[\\w]+)?)(\\.[\\w]+[-][\\w]+)*(\\.[a-z]{2,})+[\\s]*$", message = "Invalid e-mail address")
        String email,

        @NotBlank(message = "Password is mandatory!")
        String password) {
}

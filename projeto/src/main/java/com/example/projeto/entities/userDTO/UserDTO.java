package com.example.projeto.entities.userDTO;

import jakarta.validation.constraints.NotBlank;

public record UserDTO(@NotBlank String name, @NotBlank String email) {

}

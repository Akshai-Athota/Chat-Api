package com.akshai.web.sockets.chat.Api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;

    @NotEmpty
    @Size(min=3,max=5)
    private String username;

    @NotEmpty
    @NotBlank
    @Size(min=6)
    private String password;

    @Email
    @NotEmpty
    private String email;
}

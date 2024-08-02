package com.akshai.web.sockets.chat.Api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private Long id;

    @NotEmpty
    @NotBlank
    private String sender;

    @NotEmpty
    @NotBlank
    private String receiver;


    private String content;
}

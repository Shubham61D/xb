package com.wishfy.ems.helper;

import lombok.*;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponseMessage {
    private HttpStatus status;

    private String message;

    private boolean success;
}


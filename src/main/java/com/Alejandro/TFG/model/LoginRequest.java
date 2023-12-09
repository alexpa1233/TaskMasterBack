package com.Alejandro.TFG.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {
    String username;
    String password;
}

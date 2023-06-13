package com.example.demo.model.baseResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponeDelete<T> {
    private int status;
    private String message;
}

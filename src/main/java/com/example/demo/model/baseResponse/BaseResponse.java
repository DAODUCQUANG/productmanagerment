package com.example.demo.model.baseResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
    private int status;
    private String message;
    private T data;
}
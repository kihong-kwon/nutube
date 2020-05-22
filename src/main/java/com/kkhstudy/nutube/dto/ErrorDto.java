package com.kkhstudy.nutube.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {
    private String errmsg;
}

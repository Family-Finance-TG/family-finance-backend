package com.fatecmogi.family_finance.common.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageDTO {
    Integer page = 0;
    Integer size = 10;
    String sortBy = "id";
    String direction = "ASC";
}

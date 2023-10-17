package com.marktsoft.practice.customer.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SortField {

    NAME("firstName");

    private final String databaseFieldName;
}

package com.marktsoft.practice.owner.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SortField {

    NAME("name");

    private final String databaseFieldName;
}

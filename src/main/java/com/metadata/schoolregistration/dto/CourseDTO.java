package com.metadata.schoolregistration.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
public class CourseDTO {

    @EqualsAndHashCode.Include
    private Long id;

    private String name;

}

package com.blogging.payloads;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    private Integer catId;
    @NotEmpty
    private String catName;
    @NotEmpty
    private String catDescription;
}

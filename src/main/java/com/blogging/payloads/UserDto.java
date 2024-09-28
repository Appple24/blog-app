package com.blogging.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@Setter
@Getter
public class UserDto {
    private int id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;

    @Email
    private String email;

    @NotEmpty
    private String about;
}

package com.cos.pcom.DTO;


import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Size;

@Getter
public class UserDto {
    @NotNull
    @Size
    private String email;

    @NotNull
    @Size(min= 8, max=300)
    private String password;


}

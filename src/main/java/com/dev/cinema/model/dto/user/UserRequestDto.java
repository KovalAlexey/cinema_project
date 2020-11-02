package com.dev.cinema.model.dto.user;

import com.dev.cinema.annotation.CustomEmailConstraint;
import com.dev.cinema.annotation.FieldsValueMatch;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@FieldsValueMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords do not match!"
)
public class UserRequestDto {
    @CustomEmailConstraint
    private String email;
    @Size(min = 4)
    private String password;
    @Size(min = 4)
    private String repeatPassword;

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

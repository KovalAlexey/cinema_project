package com.dev.cinema.annotation;

import com.dev.cinema.model.dto.user.UserRequestDto;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FieldsMatchValidator implements ConstraintValidator<FieldsValueMatch, UserRequestDto> {
    private String field;
    private String fieldMatch;

    public void initialize(FieldsValueMatch constraint) {
        this.field = constraint.field();
        this.fieldMatch = constraint.fieldMatch();
    }

    public boolean isValid(UserRequestDto userRequestDto, ConstraintValidatorContext context) {
        Object fieldValue = new BeanWrapperImpl(userRequestDto)
                .getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(userRequestDto)
                .getPropertyValue(fieldMatch);
        return Objects.equals(fieldValue, fieldMatchValue);
    }
}

package com.optiscan.validator;

import com.optiscan.constraint.Age;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;

public class AgeLimitValidator implements ConstraintValidator<Age, Calendar> {

    int min;
    int max;


    public void initialize(Age age) {
        this.min = age.min();
        this.max = age.max();
    }

    public boolean isValid(Calendar birthDate, ConstraintValidatorContext constraintContext) {
        if(null == birthDate){
            return true;
        }
        Calendar now = Calendar.getInstance();

        Calendar minimumAge = Calendar.getInstance();
        minimumAge.set(now.get(Calendar.YEAR) - min, now.get(Calendar.MONTH), now.get(Calendar.DATE));

        Calendar maximumAge = Calendar.getInstance();
        maximumAge.set(now.get(Calendar.YEAR) - max, now.get(Calendar.MONTH), now.get(Calendar.DATE));

        boolean isValid = true;
        if (birthDate.before(maximumAge) || birthDate.after(minimumAge)) {
            isValid = false;
        }
        if (!isValid) {
            constraintContext.disableDefaultConstraintViolation();
            constraintContext.buildConstraintViolationWithTemplate("Age limit is " + min + " to " + max + ".").addConstraintViolation();
        }
        return isValid;
    }
}
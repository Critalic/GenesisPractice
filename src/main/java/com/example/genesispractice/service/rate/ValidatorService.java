package com.example.genesispractice.service.rate;

import com.example.genesispractice.exception.RateServiceValidationException;
import com.example.genesispractice.model.coinMarketCap.Rate;
import java.util.Objects;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.springframework.stereotype.Service;

@Service
public class ValidatorService {

    private final Validator validator;

    public ValidatorService(Validator validator) {
        this.validator = validator;
    }

    public void validateRate(Rate rate) {
        if (Objects.isNull(rate)) {
            throw new RateServiceValidationException("The response is null");
        }

        Set<ConstraintViolation<Rate>> violations = validator.validate(rate);

        if (!violations.isEmpty() || rate.getStatus().getErrorCode() != 0) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<Rate> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }
            throw new RateServiceValidationException((sb.length() == 0 ?
                rate.getStatus().getErrorMessage() : sb.toString()));
        }
    }
}

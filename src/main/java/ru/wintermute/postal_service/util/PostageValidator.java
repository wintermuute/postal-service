package ru.wintermute.postal_service.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.wintermute.postal_service.models.Postage;
import ru.wintermute.postal_service.models.Status;


@Component
public class PostageValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Postage.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Postage postage = (Postage) target;
        if(postage.getCurrentWarehouse() == null && postage.getStatus() != Status.ON_THE_WAY) {
            errors.rejectValue("currentWarehouse", "", "Для отправления не находящегося в перевозке необходимо выбрать склад");
        }

    }
}

package com.accenture.tigital.services;

import java.lang.reflect.Field;

public abstract class AbstractService {
    protected void updateNonNullFields(Object source, Object target) {
        Field[] fields = source.getClass().getDeclaredFields();

        for (Field field : fields) {
            try {
                field.setAccessible(true);
                Object value = field.get(source);

                if (value != null) {
                    Field targetField = target.getClass().getDeclaredField(field.getName());
                    targetField.setAccessible(true);
                    targetField.set(target, value);
                }
            } catch (Exception e) {
                // Handle exceptions
            }
        }
    }
}

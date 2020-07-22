package com.example.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
@Component
public class testBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] declaredFields = bean.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            Boy annotation = declaredField.getAnnotation(Boy.class);
            if(annotation==null)
                continue;
            declaredField.setAccessible(true);
            try {
                declaredField.set(bean,annotation.value());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }


        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}

package org.alwyn.minispring.beans.factory.config;

import javafx.beans.binding.ObjectExpression;
import org.alwyn.minispring.beans.BeansException;

public interface BeanPostProcessor {

    Object postProcessBeforeInitialization(String beanName, Object bean) throws BeansException;

    Object postProcessAfterInitialization(String beanName, Object bean) throws BeansException;

}

package org.alwyn.minispring.beans.factory;

import org.alwyn.minispring.beans.BeansException;
import org.alwyn.minispring.beans.factory.config.AutowireCapableBeanFactory;
import org.alwyn.minispring.beans.factory.config.BeanDefinition;
import org.alwyn.minispring.beans.factory.config.ConfigurableBeanFactory;

public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;


}

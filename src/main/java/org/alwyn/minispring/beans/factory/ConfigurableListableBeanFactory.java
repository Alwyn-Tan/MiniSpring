package org.alwyn.minispring.beans.factory;

import org.alwyn.minispring.beans.BeansException;
import org.alwyn.minispring.beans.factory.config.AutowireCapableBeanFactory;
import org.alwyn.minispring.beans.factory.config.BeanDefinition;
import org.alwyn.minispring.beans.factory.config.ConfigurableBeanFactory;

/*
    Configurable + Listable, it can list all Beans names and search for specific Beans,
    knowing the inner status of a Bean, also it can return a BeanDefinition
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /*
        Ensure that all non-lazy-init singletons are instantiated, typically
        invoked at the end of factory setup.
     */
    void preInstantiateSingletons() throws BeansException;
}

package org.alwyn.minispring.beans.factory;

import org.alwyn.minispring.beans.BeansException;

import java.util.List;
import java.util.Map;

/**
 * Just like {@link  java.util.List#isEmpty()} ,{@link java.util.List#size()} and {@link List#iterator()}, a Listable
 * class should provide similar methods. {@link ListableBeanFactory} provides {@link #containsBeanDefinition(String)},
 * {@link #getBeanDefinitionCount()} and {@link #getBeanDefinitionNames()}, used to enumerate all the bean instances,
 * rather than looking up the bean by name.
 */

public interface ListableBeanFactory extends BeanFactory {

    boolean containsBeanDefinition(String beanName);

    int getBeanDefinitionCount();

    String[] getBeanDefinitionNames();

    String[] getBeanNamesForType(Class<?> type);

    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;
}

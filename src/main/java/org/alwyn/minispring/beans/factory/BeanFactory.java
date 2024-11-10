package org.alwyn.minispring.beans.factory;

import org.alwyn.minispring.beans.BeansException;

/**
 * The root interface for accessing a Spring bean Container.
 *
 * <p>Will return an independent instance of a contained object (Prototype design pattern)
 * or a shared singleton instance (Singleton design pattern), depends on the specific bean definition.</>
 */

public interface BeanFactory {
    Object getBean(String name) throws BeansException;

    /**
     * Allows for specifying explicit constructor arguments / factory method arguments.
     *
     * @param name the name of the bean to retrieve
     * @param args arguments used to create a prototype if explicit arguments needed
     * @return an instance of bean
     * @throws BeansException the instance can not be created
     */
    Object getBean(String name, Object... args) throws BeansException;

    /**
     * Behaves the same as {@link #getBean(String)}, but provides type check
     *
     * @param name the name of the bean to retrieve
     * @param type the type that the retrieved bean must match. Can be an interface of the
     *             superclass of the actual bean, or <code>null</code> for any match.
     * @return an instance of bean matching the required type
     * @throws BeansException if the type can not be matched
     */
    <T> T getBean(String name, Class<T> type) throws BeansException;

    /**
     * Is {@link #getBean} able to get the bean of the given name?
     *
     * @param name the name of the bean to query
     * @return whether a bean with the given name is defined
     */
    boolean containsBean(String name);

    boolean isSingleton(String name);

    boolean isPrototype(String name);

    boolean isTypeMatch(String name, Class<?> targetType) throws BeansException;

    Class getType(String name) throws BeansException;
}

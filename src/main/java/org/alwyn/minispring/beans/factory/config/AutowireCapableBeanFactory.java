package org.alwyn.minispring.beans.factory.config;

import org.alwyn.minispring.beans.BeansException;
import org.alwyn.minispring.beans.factory.BeanFactory;

/**
 * This interface can be used to wire and populate existing bean instances
 * that Spring does not control. So that object outside IoC can still be autowired.
 *
 * <p>Hardly ever used by application code</p>
 */

public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * Create an instance even though the instance is not in Spring IoC, that is to say, we can use
     * <code>TestBean testBean = applicationContext.getAutowireCapableBeanFactory.createBean(TestBean.class)</code>
     * to create an instance, but <code>TestBean bean = applicationContext.getBean(TestBean.class)</code> will throw
     * exception for the object is still not in Spring IoC, although being autowired by Spring IoC.
     *
     * @param beanClass the class of the bean to be created
     * @return new bean instance
     */
    <T> T createBean(Class<T> beanClass) throws BeansException;

    void applyBeanPropertyValues(String beanName, Object existingBean) throws BeansException;

    Object initializeBean(String beanName, Object existingBean) throws BeansException;

    Object applyBeanPostProcessorsBeforeInitialization(String beanName, Object existingBean) throws BeansException;

    Object applyBeanPostProcessorsAfterInitialization(String beanName, Object existingBean) throws BeansException;
}

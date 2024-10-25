package org.alwyn.minispring.context.support;

import org.alwyn.minispring.beans.BeansException;
import org.alwyn.minispring.beans.factory.ConfigurableListableBeanFactory;
import org.alwyn.minispring.beans.factory.support.DefaultListableBeanFactory;

public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    private DefaultListableBeanFactory createBeanFactory() throws BeansException {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return null;
    }
}
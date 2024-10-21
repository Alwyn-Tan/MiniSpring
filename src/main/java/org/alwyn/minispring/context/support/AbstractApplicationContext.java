package org.alwyn.minispring.context.support;

import org.alwyn.minispring.beans.BeansException;
import org.alwyn.minispring.context.ConfigurableApplicationContext;
import org.alwyn.minispring.core.io.DefaultResourceLoader;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

@Override
    public void refresh() throws BeansException {
        refreshBeanFactory();
}
    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    private void invokeBeanFactoryPostProcessors(){}

    private void registerBeanFactoryPostProcessor(){}


}

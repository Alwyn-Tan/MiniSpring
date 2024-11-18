package org.alwyn.minispring.context.support;

import org.alwyn.minispring.beans.BeansException;
import org.alwyn.minispring.beans.factory.ConfigurableListableBeanFactory;
import org.alwyn.minispring.beans.factory.support.DefaultListableBeanFactory;
import org.alwyn.minispring.context.ApplicationContext;

/**
 * Base class for {@link ApplicationContext},
 *supporting multiple refreshes.
 *
 * <p>This class has one {@link DefaultListableBeanFactory} as the context bean factory,
 * and a synchronization monitor{@link #beanFactoryMonitor}</p>
 *
 * <p>Note that the only method to be implemented by subclasses is
 * {@link #loadBeanDefinitions}, supposing to load bean definitions
 * into {@link DefaultListableBeanFactory}</p>
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    private final Object beanFactoryMonitor = new Object();

    /*
        Close previous BeanFactory and initialize a new BeanFactory, especially useful when
        the configuration file has been changed.
     */
    @Override
    protected final void refreshBeanFactory() throws BeansException {
        //before the refresh, ensure that no available bean factory.
        if (hasBeanFactory()) {
            destroyBeans();
            closeBeanFactory();
        }
        try {
            DefaultListableBeanFactory beanFactory = createBeanFactory();
            loadBeanDefinitions(beanFactory);
            synchronized (beanFactoryMonitor) {
                this.beanFactory = beanFactory;
            }
        } catch (Exception e) {
            throw new BeansException("Exception parsing XML file");
        }
    }

    private DefaultListableBeanFactory createBeanFactory() throws BeansException {
        return new DefaultListableBeanFactory();
    }

    protected boolean hasBeanFactory() {
        synchronized (this.beanFactoryMonitor) {
            return this.beanFactory != null;
        }
    }

    protected void destroyBeans() {
        getBeanFactory().destroySingletons();
    }

    @Override
    protected final void closeBeanFactory() {
        synchronized (this.beanFactoryMonitor) {
            this.beanFactory = null;
        }
    }

    @Override
    public final ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException {
        synchronized (this.beanFactoryMonitor) {
            if (this.beanFactory == null) {
                throw new IllegalStateException("BeanFactory has not been initialized");
            }
            return this.beanFactory;
        }
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

}

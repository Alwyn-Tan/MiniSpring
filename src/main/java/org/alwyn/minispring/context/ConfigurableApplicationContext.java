package org.alwyn.minispring.context;

import org.alwyn.minispring.beans.BeansException;

/**
 * Provides facilities to configure an application context,
 * encapsulating the configuration and lifecycle methods.
 */
public interface ConfigurableApplicationContext extends ApplicationContext {
    /**
     * refreshed all configuration representatives, destroying all previously created
     * singletons
     */
    void refresh() throws BeansException;

    /**
     * register a shutdown hook with the JVM runtime, closing the context
     * on JVM shutdown.
     */
    void registerShutdownHook();

    /**
     * close the application context, releasing all resources.
     */
    void close();
}

package org.alwyn.minispring.context;

import org.alwyn.minispring.beans.BeansException;

public interface ConfigurableApplicationContext {

    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();
}

package org.alwyn.minispring.context;

import org.alwyn.minispring.beans.BeansException;
import org.alwyn.minispring.beans.factory.Aware;

public interface ApplicationContextAware extends Aware {
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}

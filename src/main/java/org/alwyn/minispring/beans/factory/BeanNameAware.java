package org.alwyn.minispring.beans.factory;

public interface BeanNameAware extends Aware {
    void setBeanName(String beanName);
}

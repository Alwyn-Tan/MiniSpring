package org.alwyn.minispring.beans.factory;

/*
 * Lets a bean perform initialization work after the container has set all necessary properties.
 *
 * However, it is recommended not to use the InitializingBean interface, for the tightly coupling to
 * Spring.
 *
 * Instead, use a custom init-method as an attribute of @Bean
 */

public interface InitializingBean {
    void afterPropertiesSet() throws Exception;
}

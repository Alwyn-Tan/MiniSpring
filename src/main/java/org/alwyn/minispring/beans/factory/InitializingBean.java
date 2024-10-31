package org.alwyn.minispring.beans.factory;

public interface InitializingBean {

    void afterPropertiesSet() throws Exception;
}

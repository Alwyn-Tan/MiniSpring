package org.alwyn.minispring.beans.factory;

public interface DisposableBean {
    void destroy() throws Exception;
}

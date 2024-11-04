package org.alwyn.minispring.beans.factory;

/*
 * lets a bean get a callback when the container containing it has been closed.
 *
 * Also, it is recommanded to use destroy-method rather than DisposableBean().
 */
public interface DisposableBean {
  void destroy() throws Exception;
}

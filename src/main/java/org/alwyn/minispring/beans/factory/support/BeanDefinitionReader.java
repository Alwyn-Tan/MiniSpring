package org.alwyn.minispring.beans.factory.support;

import cn.hutool.core.bean.BeanException;
import org.alwyn.minispring.core.io.Resource;

public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    void loadBeanDefinitions(Resource resource) throws BeanException;

    void loadBeanDefinitions(String location) throws BeanException;

}

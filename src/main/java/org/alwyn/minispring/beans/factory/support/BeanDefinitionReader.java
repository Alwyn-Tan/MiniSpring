package org.alwyn.minispring.beans.factory.support;

import cn.hutool.core.bean.BeanException;
import org.alwyn.minispring.beans.BeansException;
import org.alwyn.minispring.core.io.Resource;
import org.alwyn.minispring.core.io.ResourceLoader;

public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeanException;

    void loadBeanDefinition(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeanException;


}

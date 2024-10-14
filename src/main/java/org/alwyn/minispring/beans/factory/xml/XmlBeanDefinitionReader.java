package org.alwyn.minispring.beans.factory.xml;

import cn.hutool.core.bean.BeanException;
import org.alwyn.minispring.beans.factory.support.AbstractBeanDefinitionReader;
import org.alwyn.minispring.beans.factory.support.BeanDefinitionRegistry;
import org.alwyn.minispring.core.io.Resource;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry){super(registry);}
    @Override
    public BeanDefinitionRegistry getRegistry() {
        return null;
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeanException {

    }

    @Override
    public void loadBeanDefinitions(String location) throws BeanException {

    }
}

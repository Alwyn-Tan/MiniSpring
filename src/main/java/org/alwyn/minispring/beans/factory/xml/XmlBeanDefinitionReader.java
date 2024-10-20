package org.alwyn.minispring.beans.factory.xml;

import cn.hutool.core.bean.BeanException;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import org.alwyn.minispring.beans.BeansException;
import org.alwyn.minispring.beans.PropertyValue;
import org.alwyn.minispring.beans.factory.config.BeanDefinition;
import org.alwyn.minispring.beans.factory.config.BeanReference;
import org.alwyn.minispring.beans.factory.support.AbstractBeanDefinitionReader;
import org.alwyn.minispring.beans.factory.support.BeanDefinitionRegistry;
import org.alwyn.minispring.core.io.DefaultResourceLoader;
import org.alwyn.minispring.core.io.Resource;
import org.alwyn.minispring.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

public class XmlBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry){super(registry);}

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeanException {
        try( InputStream inputStream = resource.getInputStream()){
            doLoadBeanDefinitions(inputStream);
        } catch (IOException | ClassNotFoundException e) {
            throw new BeanException("IOException parsing XML document from " + resource);
        }
    }

    @Override
    public void loadBeanDefinition(Resource... resources) throws BeansException {
        for(Resource resource: resources){
            loadBeanDefinitions(resource);
        }
    }

    @Override
    public void loadBeanDefinitions(String location) throws BeanException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }

    protected void doLoadBeanDefinitions(InputStream inputStream) throws ClassNotFoundException {
        Document document = XmlUtil.readXML(inputStream);
        Element rootElement = document.getDocumentElement();
        NodeList childNodes = rootElement.getChildNodes();

        for(int i =0; i < childNodes.getLength(); i++){
            //check node existence
            if(!(childNodes.item(i) instanceof Element)) continue;
            // check node name
            if(!"bean".equals(childNodes.item(i).getNodeName())) continue;

            Element beanElement = (Element) childNodes.item(i);
            String id = beanElement.getAttribute("id");
            String name = beanElement.getAttribute("name");
            String className = beanElement.getAttribute("class");

            Class<?> clazz = Class.forName(className);
            String beanName = StrUtil.isNotEmpty(id) ? id : name;
            if (StrUtil.isEmpty(beanName)){
                beanName = StrUtil.lowerFirst(clazz.getSimpleName());
            }

            BeanDefinition beanDefinition = new BeanDefinition(clazz);
            for( int j=0; j< beanElement.getChildNodes().getLength(); j++){
                if(!(beanElement.getChildNodes().item(j) instanceof Element)) continue;
                if(!"property".equals(beanElement.getChildNodes().item(j).getNodeName())) continue;

                Element property = (Element) beanElement.getChildNodes().item(j);
                String attributeName = property.getAttribute("name");
                String attributeValue = property.getAttribute("value");
                String attributeRef = property.getAttribute("ref");

                Object value = StrUtil.isNotEmpty(attributeRef) ? new BeanReference(attributeRef) : attributeValue;
                PropertyValue propertyValue = new PropertyValue(attributeName, value);
                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
            }
            if(getRegistry().containsBeanDefinition(beanName)){
                throw new BeanException("Bean " + beanName + " already exists, duplicate bean is not allowed!");
            }
            getRegistry().registerBeanDefinition(beanName, beanDefinition);
        }
    }
}

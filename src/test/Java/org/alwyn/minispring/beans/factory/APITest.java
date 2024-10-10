package org.alwyn.minispring.beans.factory;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.alwyn.minispring.beans.factory.config.BeanDefinition;
import org.alwyn.minispring.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


public class APITest {
    @Test
    public void test_BeanFactory(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition bean = new BeanDefinition(TestBean.class);
        beanFactory.registerBeanDefinition("testBean", bean);
        TestBean testBean = (TestBean) beanFactory.getBean("testBean" );
        testBean.service();
    }

    @Test
    public void test_cglib(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TestBean.class);
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });
        TestBean bean = (TestBean) enhancer.create(new Class[]{String.class}, new Object[]{"Test"});
        bean.service();
    }

    @Test
    public void test_newInstance() throws InstantiationException, IllegalAccessException {
        TestBean bean = TestBean.class.newInstance();
        bean.service();
    }

    @Test
    public void test_constructor() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<TestBean> beanClazz = TestBean.class;
        Constructor<TestBean> declaredConstructor = beanClazz.getDeclaredConstructor(String.class);
        TestBean bean = declaredConstructor.newInstance("Test");
        bean.service();
    }

    @Test
    public void test_getParameterTypes() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<TestBean> beanClazz = TestBean.class;
        Constructor<?>[] declaredConstructors = beanClazz.getDeclaredConstructors();
        Constructor<?> constructor = null;
        for( Constructor<?> ctor: declaredConstructors){
            if(ctor.getParameterTypes().length ==1){
                constructor = ctor;
                break;
            }
        }
        Constructor<TestBean> declaredConstructor = beanClazz.getDeclaredConstructor(constructor.getParameterTypes());
        TestBean bean = declaredConstructor.newInstance("Test");
        bean.service();
    }

}

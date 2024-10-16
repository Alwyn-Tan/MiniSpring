package org.alwyn.minispring.core.io;

import cn.hutool.core.io.IoUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class DefaultResourceLoaderTest {
    private DefaultResourceLoader resourceLoader;
    @Before
    public void setUp(){
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void test_getResource_ByClasspath() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_getResource_ByUrl() throws IOException {
        Resource resource = resourceLoader.getResource("https://www.bilibili.com/");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void test_getResource_ByLocation() throws IOException {
        Resource resource = resourceLoader.getResource("src/test/resources/important.properties");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

}
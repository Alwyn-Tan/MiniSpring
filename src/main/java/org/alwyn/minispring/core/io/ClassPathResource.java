package org.alwyn.minispring.core.io;

import cn.hutool.core.lang.Assert;
import org.alwyn.minispring.util.ClassUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ClassPathResource implements Resource{

    private final String path;

    private ClassLoader classLoader;

    public ClassPathResource(String path){
        this(path,(ClassLoader) null);
    }

    public ClassPathResource(String path, ClassLoader classLoader){
        Assert.notNull(path, "path must not be null");
        this.path = path;
        this.classLoader = (classLoader != null? classLoader : ClassUtils.getDefaultClassLoader());
    }

    @Override
    public InputStream getInputStream() throws IOException {
        InputStream inputStream = classLoader.getResourceAsStream(path);
        if(inputStream == null){
            throw new FileNotFoundException(this.path + " not found");
        }
        return inputStream;
    }
}

package com.funtl.itoken.service.upload.fastdfs;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 李洋
 * @date 2019-09-06 10:50
 */
public class StorageFactory implements FactoryBean<StorageService> {

    @Autowired
    private AutowireCapableBeanFactory acbf;

    /**
     * 存储服务的类型，目前仅支持fastdfs
     */
    @Value("${storage.type}")
    private String type;

    private Map<String, Class<? extends StorageService>> classMap;

    public StorageFactory() {
        classMap = new HashMap<>();
        classMap.put("fastdfs", FastDFSStorageService.class);
    }

    @Override
    public StorageService getObject() throws Exception {
        Class<? extends StorageService> clazz = classMap.get(type);
        if (clazz == null) {
            throw new RuntimeException("Unsupported storage type [" + type + "], valid are " + classMap.keySet());
        }

        StorageService bean = clazz.newInstance();
        acbf.autowireBean(bean);
        acbf.initializeBean(bean, bean.getClass().getSimpleName());
        return bean;
    }

    @Override
    public Class<?> getObjectType() {
        return StorageService.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}

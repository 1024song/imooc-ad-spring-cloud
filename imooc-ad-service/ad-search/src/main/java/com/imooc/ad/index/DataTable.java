package com.imooc.ad.index;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DataTable implements ApplicationContextAware, PriorityOrdered {
    //缓存，使所有的索引服务类，放在Map中。

    private static ApplicationContext applicationContext;

    public static final Map<Class,Object> dataTableMap = new ConcurrentHashMap<>();
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        DataTable.applicationContext = applicationContext;
    }

    @Override
    public int getOrder() {
        return PriorityOrdered.HIGHEST_PRECEDENCE;//越小优先级越高
    }

    @SuppressWarnings("all")
    public static <T> T of(Class<T> clazz){
        T instance = (T) dataTableMap.get(clazz);
        if(instance != null){
            return instance;
        }
        dataTableMap.put(clazz,bean(clazz));
        return (T)dataTableMap.get(clazz);
    }
    @SuppressWarnings("all")
    private static <T> T bean(String beanName){
        return (T)applicationContext.getBean(beanName);
    }

    @SuppressWarnings("all")
    private static <T> T bean(Class clazz){
        return (T)applicationContext.getBean(clazz);
    }
}

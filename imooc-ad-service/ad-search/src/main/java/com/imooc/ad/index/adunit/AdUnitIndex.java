package com.imooc.ad.index.adunit;


import com.imooc.ad.index.IndexAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 实现推广单元索引增删改查的一些服务
 */
@Slf4j
@Component
public class AdUnitIndex implements IndexAware<Long,AdUnitObject> {

    private static Map<Long,AdUnitObject> objectMap;

    static {
        objectMap = new ConcurrentHashMap<>();
    }

    @Override
    public AdUnitObject get(Long key) {
        return objectMap.get(key);
    }

    @Override
    public void add(Long key, AdUnitObject value) {
        log.info("AdUnitIndex before add:{}",objectMap);
        objectMap.put(key, value);
        log.info("AdUnitIndex after add:{}",objectMap);
    }

    @Override
    public void update(Long key, AdUnitObject value) {
        log.info("AdUnitIndex before update:{}",objectMap);
        AdUnitObject object = objectMap.get(key);
        if(object == null){
            objectMap.put(key, value);
        }else {
            object.update(value);
        }
        log.info("AdUnitIndex after update:{}",objectMap);
    }

    @Override
    public void delete(Long key, AdUnitObject value) {
        log.info("AdUnitIndex before delete:{}",objectMap);
        objectMap.remove(key);
        log.info("AdUnitIndex after delete:{}",objectMap);
    }
}

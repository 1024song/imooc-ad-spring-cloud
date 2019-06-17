package com.imooc.ad.index.adplan;


import com.imooc.ad.index.IndexAware;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class AdPlanIndex implements IndexAware<Long,AdPlanObject> {

    private static Map<Long,AdPlanObject> objectMap;

    static {
        objectMap = new ConcurrentHashMap<>();
    }

    @Override
    public AdPlanObject get(Long key) {
        return objectMap.get(key);
    }

    @Override
    public void add(Long key, AdPlanObject value) {
        log.info("AdPlanIndex before add:{}",objectMap);
        objectMap.put(key,value);
        log.info("AdPlanIndex after add:{}",objectMap);
    }

    @Override
    public void update(Long key, AdPlanObject value) {
        log.info("AdPlanIndex before update:{}",objectMap);
        AdPlanObject object = objectMap.get(key);
        if(object == null){
            this.add(key,value);
        }else {
            object.update(value);
        }
        log.info("AdPlanIndex after update:{}",objectMap);
    }

    @Override
    public void delete(Long key, AdPlanObject value) {
        log.info("AdPlanIndex before delete:{}",objectMap);
        objectMap.remove(key);
        log.info("AdPlanIndex after delete:{}",objectMap);
    }
}

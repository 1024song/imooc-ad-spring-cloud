package com.imooc.ad.index.interest;

import com.imooc.ad.index.IndexAware;
import com.imooc.ad.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

@Slf4j
@Component
public class UnitItIndex implements IndexAware<String, Set<Long>> {

    //<itTag,adUnit set> map key and value
    private static Map<String,Set<Long>> itUnitMap;
    //<unitId,itTag set> map key and value
    private static Map<Long,Set<String>> unitItMap;

    static {
        itUnitMap = new ConcurrentHashMap<>();
        unitItMap = new ConcurrentHashMap<>();
    }

    @Override
    public Set<Long> get(String key) {
        return itUnitMap.get(key);
    }

    @Override
    public void add(String key, Set<Long> value) {
        log.info("UnitIndex before add:{}",itUnitMap);

        Set<Long> unitIds = CommonUtils.getorCreate(key,itUnitMap,
                ConcurrentSkipListSet::new);
        unitIds.addAll(value);
        for(Long unitId : value){
            Set<String> itTagSet = CommonUtils.getorCreate(unitId,unitItMap,
                    ConcurrentSkipListSet::new);
            itTagSet.add(key);
        }

        log.info("UnitIndex after add:{}",itUnitMap);
    }

    @Override
    public void update(String key, Set<Long> value) {
        log.error("keyword index can not support update");
    }

    @Override
    public void delete(String key, Set<Long> value) {
        log.info("UnitIndex before delete:{}",itUnitMap);

        Set<Long> unitIds = CommonUtils.getorCreate(key,itUnitMap,
                ConcurrentSkipListSet::new);
        unitIds.removeAll(value);
        for(Long unitId : value){
            Set<String> itTagSet = CommonUtils.getorCreate(unitId,unitItMap,
                    ConcurrentSkipListSet::new);
            itTagSet.remove(key);
        }

        log.info("UnitIndex after delete:{}",itUnitMap);
    }

    public boolean match(Long unitId, List<String> itTags){
        if(unitItMap.containsKey(unitId)
        && CollectionUtils.isNotEmpty(unitItMap.get(unitId))){
            Set<String> itTagSet = unitItMap.get(unitId);

            return CollectionUtils.isSubCollection(itTags,itTagSet);
        }
        return false;
    }
}

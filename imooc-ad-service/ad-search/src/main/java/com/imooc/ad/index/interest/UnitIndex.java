package com.imooc.ad.index.interest;

import com.imooc.ad.index.IndexAware;

import java.util.Map;
import java.util.Set;

public class UnitIndex implements IndexAware<String, Set<Long>> {

    private static Map<String,Set<Long>> itUnitMap;
    @Override
    public Set<Long> get(String key) {
        return null;
    }

    @Override
    public void add(String key, Set<Long> value) {

    }

    @Override
    public void update(String key, Set<Long> value) {

    }

    @Override
    public void delete(String key, Set<Long> value) {

    }
}

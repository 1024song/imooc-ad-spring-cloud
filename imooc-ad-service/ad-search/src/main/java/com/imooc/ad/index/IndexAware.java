package com.imooc.ad.index;

public interface IndexAware<K,V> {
    public V get(K key);

    public void add(K key,V value);

    public void update(K key,V value);

    public void delete(K key,V value);

}

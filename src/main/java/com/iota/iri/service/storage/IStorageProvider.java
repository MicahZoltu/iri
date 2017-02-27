package com.iota.iri.service.storage;

/**
 * Created by paul on 2/26/17.
 */
public interface IStorageProvider<T> {
    void init() throws  Exception;
    void shutdown();


    T find(byte[] hash);
    void put(byte[] hash, T val);

    //IStorageProvider<T> instance();
}

package com.iota.iri.service.storage;

/**
 * Created by paul on 2/26/17.
 */
public class IotaStorage {
    public static void init() {
        try {
            TransactionStorageProvider.instance().init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void shutdown() {
        TransactionStorageProvider.instance().shutdown();
    }
}

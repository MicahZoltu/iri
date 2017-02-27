package com.iota.iri.service.storage;

import com.iota.iri.model.Transaction;
import org.rocksdb.RocksDB;
import org.rocksdb.Options;
import org.rocksdb.RocksDBException;

/**
 * Created by paul on 2/26/17.
 */
public class TransactionStorageProvider implements IStorageProvider<Transaction> {
    private static TransactionStorageProvider instance = new TransactionStorageProvider();
    private RocksDB db;
    private Options options;
    private static final String DB_NAME = "transactionsRock.iri";

    @Override
    public void init() throws Exception {
        RocksDB.loadLibrary();
        Options options = new Options().setCreateIfMissing(true);
        db = RocksDB.open(options, DB_NAME);
    }

    @Override
    public void shutdown() {
        if (db != null) db.close();
        options.dispose();
    }

    @Override
    public Transaction find(byte[] hash) {
        try {
            return new Transaction(db.get(hash), 0);
        } catch (RocksDBException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void put(byte[] hash, Transaction val) {
        try {
            db.put(hash, val.bytes);
        } catch (RocksDBException e) {
            e.printStackTrace();
        }
    }

    public static IStorageProvider<Transaction> instance() {
        return instance;
    }
}

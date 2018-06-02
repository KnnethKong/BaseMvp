package com.base.db;

import java.util.List;

/**
 * Created by KXF on 2018/5/28.
 */

public class WalletDao extends BaseDao {
    @Override
    protected String createTable() {
        return "create table if not exists wallets (" +
                "id bigint primary key,"+
                "name varchar(20)," +
                "pwd varchar(14)," +
                "key_store varchar(200)," +
                "miyao varchar(100)," +
                "pwd_notice varchar(100))";
    }

    @Override
    public List query(String sql) {
        return null;
    }
}

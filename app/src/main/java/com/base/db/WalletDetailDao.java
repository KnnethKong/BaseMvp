package com.base.db;


import java.util.List;

/**
 * Created by KXF on 2018/5/28.
 */

public class WalletDetailDao extends BaseDao {
    @Override
    protected String createTable() {
        return "create table if not  exists wallet_detail (" +
                "id bigint primary key," +
                "wallet_id bigint ," +
                "coin_img varchar(100)," +
                "coin_ration int ," +
                "coin_name varchar(50) ," +
                "coin_price int ," +
                "coin_num int," +
                "coin_cny int," +
                "coin_address varchar(180) ," +
                "coin_abbreviation varchar(100))";
    }

    @Override
    public List query(String sql) {
        return null;
    }
}

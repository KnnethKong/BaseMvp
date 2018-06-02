package com.base.db;

import com.base.db.annotion.DbFiled;
import com.base.db.annotion.DbTable;

/**
 * Created by KXF on 2018/5/29.
 */
@DbTable("wallet_detail")
public class WalletItemEntity {
    @DbFiled("id")
    private Long id;
    @DbFiled("wallet_id")
    private Long walletId;
    @DbFiled("name")
    private String name;
    @DbFiled("price")
    private Long price;
    @DbFiled("num")
    private Integer num;
    @DbFiled("cny")
    private Integer cny;
    @DbFiled("ration")
    private Integer ration;

    public WalletItemEntity() {
    }

    public WalletItemEntity(Long id, Long walletId, String name, Long price, Integer num, Integer cny, Integer ration) {
        this.id = id;
        this.walletId = walletId;
        this.name = name;
        this.price = price;
        this.num = num;
        this.cny = cny;
        this.ration = ration;
    }

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getCny() {
        return cny;
    }

    public void setCny(Integer cny) {
        this.cny = cny;
    }

    public Integer getRation() {
        return ration;
    }

    public void setRation(Integer ration) {
        this.ration = ration;
    }
}

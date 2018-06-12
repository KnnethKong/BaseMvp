package com.base.db;


import com.base.db.annotion.DbFiled;
import com.base.db.annotion.DbTable;

/**
 * Created by KXF on 2018/5/29.
 */
@DbTable("wallet_detail")
public class WalletDetailDB {
    @DbFiled("id")
   public   Long id;
    @DbFiled("wallet_id")
    public Long walletId;
    @DbFiled("coin_img")
    public  String coinImg;
    @DbFiled("coin_ration")
    public    Integer coinRation;
    @DbFiled("coin_name")
    public  String coinName;
    @DbFiled("coin_price")
    public  Long coinPrice;
    @DbFiled("coin_num")
    public   Integer coinNum;
    @DbFiled("coin_cny")
    public   Integer coinCny;
    @DbFiled("coin_address")
    public   String coinAddress;
    @DbFiled("coin_abbreviation")
    public  String coinAbbreviation;//简称

    public WalletDetailDB() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWalletId() {
        return walletId;
    }

    public void setWalletId(Long walletId) {
        this.walletId = walletId;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public Long getCoinPrice() {
        return coinPrice;
    }

    public void setCoinPrice(Long coinPrice) {
        this.coinPrice = coinPrice;
    }

    public Integer getCoinNum() {
        return coinNum;
    }

    public void setCoinNum(Integer coinNum) {
        this.coinNum = coinNum;
    }

    public Integer getCoinCny() {
        return coinCny;
    }

    public void setCoinCny(Integer coinCny) {
        this.coinCny = coinCny;
    }

    public Integer getCoinRation() {
        return coinRation;
    }

    public void setCoinRation(Integer coinRation) {
        this.coinRation = coinRation;
    }

    public String getCoinAddress() {
        return coinAddress;
    }

    public void setCoinAddress(String coinAddress) {
        this.coinAddress = coinAddress;
    }

    public String getCoinImg() {
        return coinImg;
    }

    public void setCoinImg(String coinImg) {
        this.coinImg = coinImg;
    }

    public String getCoinAbbreviation() {
        return coinAbbreviation;
    }

    public void setCoinAbbreviation(String coinAbbreviation) {
        this.coinAbbreviation = coinAbbreviation;
    }

    @Override
    public String toString() {
        return "WalletDetailDB{" +
                "id=" + id +
                ", walletId=" + walletId +
                ", coinImg='" + coinImg + '\'' +
                ", coinRation=" + coinRation +
                ", coinName='" + coinName + '\'' +
                ", coinPrice=" + coinPrice +
                ", coinNum=" + coinNum +
                ", coinCny=" + coinCny +
                ", coinAddress='" + coinAddress + '\'' +
                ", coinAbbreviation='" + coinAbbreviation + '\'' +
                '}';
    }
}

package com.base.wallet;

/**
 * 添加钱包item实体类
 * Created by KXF on 2018/5/29.
 */
public class WalletCoinDetailEntity {

    private String coinName;
    private Integer coinCount;
    private String coinImg;
    private String coinAddress;
    private String coinAbbreviation;//简称


    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public int getCoinCount() {
        return coinCount;
    }

    public void setCoinCount(Integer coinCount) {
        this.coinCount = coinCount;
    }

    public String getCoinImg() {
        return coinImg;
    }

    public void setCoinImg(String coinImg) {
        this.coinImg = coinImg;
    }

    public String getCoinAddress() {
        return coinAddress;
    }

    public void setCoinAddress(String coinAddress) {
        this.coinAddress = coinAddress;
    }

    public String getCoinAbbreviation() {
        return coinAbbreviation;
    }

    public void setCoinAbbreviation(String coinAbbreviation) {
        this.coinAbbreviation = coinAbbreviation;
    }

    public WalletCoinDetailEntity() {
    }

    public WalletCoinDetailEntity(String coinName, Integer coinCount, String coinImg, String coinAddress, String coinAbbreviation) {
        this.coinName = coinName;
        this.coinCount = coinCount;
        this.coinImg = coinImg;
        this.coinAddress = coinAddress;
        this.coinAbbreviation = coinAbbreviation;
    }

    @Override
    public String toString() {
        return "WalletCoinDetailEntity{" +
                "coinName='" + coinName + '\'' +
                ", coinCount=" + coinCount +
                ", coinImg='" + coinImg + '\'' +
                ", coinAddress='" + coinAddress + '\'' +
                ", coinAbbreviation='" + coinAbbreviation + '\'' +
                '}';
    }
}
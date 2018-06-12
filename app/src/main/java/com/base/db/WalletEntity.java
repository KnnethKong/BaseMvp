package com.base.db;

import com.base.db.annotion.DbFiled;
import com.base.db.annotion.DbTable;

/**
 * Created by KXF on 2018/5/28.
 */
@DbTable(value = "wallets")
public class WalletEntity {
    @DbFiled(value = "id")
    public Long id;
    @DbFiled(value = "name")
    public String name;
    @DbFiled(value = "miyao")
    public String miyao;
    @DbFiled(value = "pwd")
    public String pwd;
    @DbFiled(value = "pwd_notice")
    public String pwdNotice;
    @DbFiled(value = "key_store")
    public String keyStore;
    @DbFiled(value = "address")
    public String address;
    @DbFiled(value = "zjc")
    public String zjc;
    @DbFiled(value = "keystorepath")
    public String keystorePath;

    public String getKeystorePath() {

        return keystorePath;
    }

    public void setKeystorePath(String keystorePath) {
        this.keystorePath = keystorePath;
    }

    public String getZjc() {
        return zjc;
    }

    public void setZjc(String zjc) {
        this.zjc = zjc;
    }

    public WalletEntity() {
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

    public String getMiyao() {
        return miyao;
    }

    public void setMiyao(String miyao) {
        this.miyao = miyao;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getPwdNotice() {
        return pwdNotice;
    }

    public void setPwdNotice(String pwdNotice) {
        this.pwdNotice = pwdNotice;
    }

    public String getKeyStore() {
        return keyStore;
    }

    public void setKeyStore(String keyStore) {
        this.keyStore = keyStore;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "WalletEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", miyao='" + miyao + '\'' +
                ", pwd='" + pwd + '\'' +
                ", pwdNotice='" + pwdNotice + '\'' +
                ", keyStore='" + keyStore + '\'' +
                ", address='" + address + '\'' +
                ", zjc='" + zjc + '\'' +
                ", keystorePath='" + keystorePath + '\'' +
                '}';
    }
}

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

    public WalletEntity(Long id, String name, String miyao, String pwd, String pwdNotice, String keyStore) {
        this.id = id;
        this.name = name;
        this.miyao = miyao;
        this.pwd = pwd;
        this.pwdNotice = pwdNotice;
        this.keyStore = keyStore;
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


    @Override
    public String toString() {
        return "WalletEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", miyao='" + miyao + '\'' +
                ", pwd='" + pwd + '\'' +
                ", pwdNotice='" + pwdNotice + '\'' +
                ", keyStore='" + keyStore + '\'' +
                '}';
    }
}

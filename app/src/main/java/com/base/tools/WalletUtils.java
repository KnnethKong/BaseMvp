package com.base.tools;

import android.content.SharedPreferences;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;

import com.base.db.WalletEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDKeyDerivation;
import org.bitcoinj.wallet.DeterministicSeed;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.crypto.Wallet;
import org.web3j.crypto.WalletFile;
import org.web3j.protocol.ObjectMapperFactory;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.List;

public class WalletUtils {

    private static ObjectMapper objectMapper = ObjectMapperFactory.getObjectMapper();
    private static SharedPreferences sharedPreferences;
    public static final SecureRandom secureRandom = SecureRandomUtils.secureRandom();
    private Credentials credentials;
    public static String ETH_JAXX_TYPE = "m/44'/60'/0'/0/0";
    public static String ETH_LEDGER_TYPE = "m/44'/60'/0'/0";
    public static String ETH_CUSTOM_TYPE = "m/44'/60'/1'/0/0";
    private static  String TAG = "WalletUtils";

    /**
     * 创建助记词
     *
     * @param walletName
     * @param pwd
     * @return
     */
    public static WalletEntity generateMnemonic(String walletName, String pwd) {
        String[] pathArray = ETH_JAXX_TYPE.split("/");
        String passphrase = "";
        long creationTimeSeconds = System.currentTimeMillis() / 1000;
        DeterministicSeed ds = new DeterministicSeed(secureRandom, 128, passphrase, creationTimeSeconds);
        return generateWalletByMnemonic(walletName, ds, pathArray, pwd);
    }



    /**
     * @param walletName 钱包名称
     * @param ds         助记词加密种子
     * @param pathArray  助记词标准
     * @param pwd        密码
     * @return
     */
    @Nullable
    public static WalletEntity generateWalletByMnemonic(String walletName, DeterministicSeed ds,
                                                        String[] pathArray, String pwd) {
        //种子
        byte[] seedBytes = ds.getSeedBytes();
        //助记词
        List<String> mnemonic = ds.getMnemonicCode();
        if (seedBytes == null)
            return null;
        DeterministicKey dkKey = HDKeyDerivation.createMasterPrivateKey(seedBytes);
        for (int i = 1; i < pathArray.length; i++) {
            ChildNumber childNumber;
            if (pathArray[i].endsWith("'")) {
                int number = Integer.parseInt(pathArray[i].substring(0,
                        pathArray[i].length() - 1));
                childNumber = new ChildNumber(number, true);
            } else {
                int number = Integer.parseInt(pathArray[i]);
                childNumber = new ChildNumber(number, false);
            }
            dkKey = HDKeyDerivation.deriveChildKey(dkKey, childNumber);
        }
        ECKeyPair keyPair = ECKeyPair.create(dkKey.getPrivKeyBytes());
        WalletEntity ethWallet = generateWallet(walletName, pwd, keyPair);
        if (ethWallet != null) {
            ethWallet.setZjc(convertMnemonicList(mnemonic));
        }
        return ethWallet;
    }

    private static String convertMnemonicList(List<String> mnemonics) {
        StringBuilder sb = new StringBuilder();
        for (String mnemonic : mnemonics
                ) {
            sb.append(mnemonic);
            sb.append("-");
        }
        return sb.toString();
    }

    @Nullable
    private static WalletEntity generateWallet(String walletName, String pwd, ECKeyPair ecKeyPair) {
        WalletFile walletFile;
        try {
            walletFile = Wallet.create(pwd, ecKeyPair, 1024, 1); // WalletUtils. .generateNewWalletFile();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        BigInteger publicKey = ecKeyPair.getPublicKey();
        String s = publicKey.toString();
        Log.i(TAG, "publicKey = " + s);
        String wallet_dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/1/wallet";
        Log.i(TAG, "wallet_dir = " + wallet_dir);
        String keystorePath = "keystore_" + walletName + ".json";
        File destination = new File(wallet_dir, keystorePath);
        if (!createParentDir(destination)) {
            return null;
        }
        try {
            objectMapper.writeValue(destination, walletFile);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        WalletEntity ethWallet = new WalletEntity();
        ethWallet.setName(walletName);
        ethWallet.setAddress(Keys.toChecksumAddress(walletFile.getAddress()));
        ethWallet.setKeystorePath(destination.getAbsolutePath());
        ethWallet.setPwd(pwd);
        return ethWallet;
    }

    private static boolean createParentDir(File file) {

        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdirs()) {
                return false;
            }
        }
        return true;
    }

}

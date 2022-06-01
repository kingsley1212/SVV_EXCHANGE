package com.svv.jys.code.common.utils;

import android.util.Base64;

import com.svv.jys.R;
import com.svv.jys.code.module.app.MAppliaction;

import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * Created by js on 2018/10/10.
 */

public class RSAUtils {
    private static RSAPublicKey publicKey = null;
    private static String key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC63G283DDab8oqIVfoixIqaMMv" +
            "U0WpnVF9ggDZrA23jS9F6KmLsa1bc5xGx6KcSuHhtrdOBNir+5k54ibz+kBEsQAT" +
            "x72r6olmjs8+wTMFAO8gazwcZNfgz76e9LZ4ayLkkQTSmRD/KMdteCSzUlEv+iV4" +
            "Qf7kT7kHYMJir+JsgwIDAQAB";

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * 将base64编码后的公钥字符串转成PublicKey实例
     * @param publicKey 公钥字符
     * @return publicKEY
     * @throws Exception exception
     */
    public static PublicKey getPublicKey(String publicKey) throws Exception {
        byte[] keyBytes = Base64.decode(publicKey, Base64.NO_WRAP);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }
    /**
     * 将base64编码后的私钥字符串转成PrivateKey实例
     * @param privateKey 私钥字符串
     * @return 私钥对象
     * @throws Exception exception
     */
    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
        byte[] keyBytes = Base64.decode(privateKey, Base64.NO_WRAP);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }

    public static String encrypt(String content) throws Exception {
        PublicKey publicKey =getPublicKey(key);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");//java默认"RSA"="RSA/ECB/PKCS1Padding"
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] data = content.getBytes();
        int inputLen = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段加密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
                cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(data, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_ENCRYPT_BLOCK;
        }
        byte[] encryptedData = out.toByteArray();
        out.close();
        return new String(Base64.encode(encryptedData, Base64.NO_WRAP));
    }

    /**
     * RSA解密
     * @param content 密文
     * @return 明文
     * @throws Exception exception
     */
    public static String decrypt(String content) throws Exception {
        PrivateKey privateKey=getPrivateKey(s);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] encryptedData = Base64.decode(content, Base64.NO_WRAP);
        int inputLen = encryptedData.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offSet = 0;
        byte[] cache;
        int i = 0;
        // 对数据分段解密
        while (inputLen - offSet > 0) {
            if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
                cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
            } else {
                cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * MAX_DECRYPT_BLOCK;
        }
        byte[] decryptedData = out.toByteArray();
        out.close();
        return new String(decryptedData);
    }


    public static void loadPublicKey(String pubKey) throws Exception {
        try {
            byte[] buffer = Base64.decode(pubKey, Base64.DEFAULT);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            publicKey = (RSAPublicKey) keyFactory.generatePublic(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception(MAppliaction.getApp().getString(R.string.RSAUtils_string));
        } catch (InvalidKeySpecException e) {

            throw new Exception(MAppliaction.getApp().getString(R.string.RSAUtils_string1));
        } catch (NullPointerException e) {
            throw new Exception(MAppliaction.getApp().getString(R.string.RSAUtils_string2));
        }
    }


    /**
     * 从字符串中加载私钥
     * <p>
     * 加载时使用的是PKCS8EncodedKeySpec（PKCS#8编码的Key指令）。
     *
     * @param privateKeyStr
     * @return
     * @throws Exception
     */
    public static PrivateKey loadPrivateKey(String privateKeyStr) throws Exception {
        try {
            byte[] buffer = Base64.decode(privateKeyStr, Base64.DEFAULT);
            // X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
        } catch (NoSuchAlgorithmException e) {
            throw new Exception(MAppliaction.getApp().getString(R.string.RSAUtils_string));
        } catch (InvalidKeySpecException e) {
            throw new Exception(MAppliaction.getApp().getString(R.string.RSAUtils_string1));
        } catch (NullPointerException e) {
            throw new Exception(MAppliaction.getApp().getString(R.string.RSAUtils_string2));
        }
    }

    public  static String s="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANa6+8/aa/mAjsZB" +
            "wG4J1gkAoBPYeY9EqHov3xbVpBXVggvvg4wCLc4ytmwTqE+xJRntRGOqnOSr/p80" +
            "0sYoG/kWQY31agzlt1pnfakKEDbvTsHxKTBN/ueuEdJlXC63ReoIjzW8n/2m45Zw" +
            "6/iGc+ntmyW/xEZs+4wIZeD0BR/FAgMBAAECgYEArntvxFzCohGxci3ZdFCvx87n" +
            "RFkBpcXvdKHTkBV3aHp+Wr570Newar3Qd4+dk2vpLuEaUjY/mJydoLZmkrfiyYze" +
            "e+2VC9GwySN3mo7dSzU/Z9T7RzIG2dh1gJ/36PDk5Tmzn0pBUQECoucSMwnKFh7i" +
            "GReyOcqDu9FjfEQ7rx0CQQD+UXx6RBh4miBmfpeUCE+9TOhNoyjKH4uOVn3cBao/" +
            "8+hnJPayPlv+jHnaBtZZ/QAJnGz+4R1+SfEc+vTU5tqnAkEA2CZ7f+caXcHGQjyW" +
            "GuGpQZlV5jbAlm7bSxADoNtynOnrXsD1lOnc1Ujvf7lB3PKEkYuC7/kFWHv0ali6" +
            "hZR7swJAHROp/LyuY5OTosRUhktQ+p7BbKb78egbZ6HB8BcBuSAk8S4LuqNgl0Du" +
            "aZwgg9dmWrL0z0tjuN2xYJPczD68vwJARdCoMzfCHGZqUxqeoTAiKz4LsMAelHhZ" +
            "/HS/t0UqT+R+UayhpeejnIBiWy7LR0JN73TQZhJY16ux1meyV1MOYQJAWkqQVoTB" +
            "gHXFIUy/Zk4xH1JVlESmR89GNE2/qbe5nA3/qj16blvhXidk45A+ASUMkTiXgSYf" +
            "0C6wSWkZKGzwZg==";

    public static String jie(String data) throws Exception {
        PrivateKey privateKey = RSAUtils.loadPrivateKey(s);
        // 因为RSA加密后的内容经Base64再加密转换了一下，所以先Base64解密回来再给RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        String decryptStr = new String(cipher.doFinal(data.getBytes()));
        return decryptStr;
    }


    public static String encryptWithRSA(String plainData) throws Exception {
        loadPublicKey(key);
        if (publicKey == null) {
            throw new NullPointerException("encrypt PublicKey is null !");
        }
        Cipher cipher = null;
        cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");// 此处如果写成"RSA"加密出来的信息JAVA服务器无法解析
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] output = cipher.doFinal(plainData.getBytes("utf-8"));
        // 必须先encode成 byte[]，再转成encodeToString，否则服务器解密会失败
        byte[] encode = Base64.encode(output, Base64.DEFAULT);
        return Base64.encodeToString(encode, Base64.DEFAULT);
    }

}

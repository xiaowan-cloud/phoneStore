package com.ziyun.mqtt.sender.utile;

import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

public class Aes256Utils {
	static {
    	java.security.Security.addProvider(new BouncyCastleProvider());
    }
	public static final String INVALID_AES_CONTENT_OR_KEY="Invalid AES content or key";
	public static final String AES_ENCRYPT_EXCEPTION="AES encrypt exception";
	public static byte[] encrypt(byte[] content, String key){
//		if(StringUtils.isBlank(content) || StringUtils.isBlank(key)) {
//    		throw new IllegalArgumentException(INVALID_AES_CONTENT_OR_KEY);
//    	}
		if(32 != key.length()) {
			throw new IllegalArgumentException("Invalid KEY not 32 bits");
		}
		
		try{
    		byte[] byteContent = content;
    		byte[] byteKey = key.getBytes();
    		return encrypt(byteContent, byteKey);
    	}catch (Exception e) {
    		throw new RuntimeException(AES_ENCRYPT_EXCEPTION);
    	}
	}
	
	private static byte[] encrypt(byte[] content, byte[] key) {
    	if(null == content || null == key) {
    		throw new IllegalArgumentException(INVALID_AES_CONTENT_OR_KEY);
    	}
    	IvParameterSpec ivspec = new IvParameterSpec(createIVBytes());
    	try{
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            Key sKeySpec = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, ivspec);
            return cipher.doFinal(content);
    	}catch(Exception e) {
    		throw new RuntimeException(AES_ENCRYPT_EXCEPTION);
    	}
    }
	
	private static byte[] createIVBytes() {
    	byte[] bs = new byte[16];
    	for(byte i=1; i<=16; i++) {
    		bs[i-1] = i;
    	}
    	return bs;
    }
	
	public static byte[] decrypt(byte[] content, String key) {
		if(null == content || StringUtils.isBlank(key)) {
    		throw new IllegalArgumentException(INVALID_AES_CONTENT_OR_KEY);
    	}
		if(32 != key.length()) {
			throw new IllegalArgumentException("Invalid KEY not 32 bits");
		}
    	try{
    		byte[] byteKey = key.getBytes();
    		return decrypt(content, byteKey);
    	}catch (Exception e) {
    		throw new RuntimeException(AES_ENCRYPT_EXCEPTION);
    	}
    }
	
	private static byte[] decrypt(byte[] content, byte[] key) {
    	if(null == content || null == key) {
    		throw new IllegalArgumentException(INVALID_AES_CONTENT_OR_KEY);
    	}
    	IvParameterSpec ivspec = new IvParameterSpec(createIVBytes());
    	try{
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            Key sKeySpec = new SecretKeySpec(key, "AES");
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, ivspec);
            return cipher.doFinal(content);
    	}catch(Exception e) {
    		throw new RuntimeException(AES_ENCRYPT_EXCEPTION);
    	}
    }
}
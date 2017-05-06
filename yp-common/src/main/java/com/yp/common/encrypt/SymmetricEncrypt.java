package com.yp.common.encrypt;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import org.apache.commons.codec.binary.Base64;

import com.springcryptoutils.core.cipher.Mode;
import com.springcryptoutils.core.cipher.symmetric.Base64EncodedCipherer;
import com.springcryptoutils.core.cipher.symmetric.Base64EncodedCiphererImpl;
import com.springcryptoutils.core.cipher.symmetric.Base64EncodedKeyGeneratorImpl;

/**
 * 
 * 对称加密解密
 * 2015年5月21日 下午3:08:58
 */
public class SymmetricEncrypt {
	
	private static final String initkey = "key.init";//必须为8位
	
	private static final String key = "";//建议写在配置文件中

	/**
	 * 
	 * @return Base64EncodedCipherer
	 * 2015年5月21日 下午3:15:35
	 */
	public static Base64EncodedCipherer getInstance(Mode mode){
		Base64EncodedCiphererImpl cipherer = new Base64EncodedCiphererImpl();
		cipherer.setMode(mode);
		return cipherer;
	}
	
	/**
	 * 得到一个初始化向量，用于操作密码
	 * @return String
	 
	 * 2015年5月21日 下午3:19:42
	 */
	public static String getInitVector(){
		byte[] keyBytes = Base64.encodeBase64(initkey.getBytes());
		return new String(keyBytes);
	}
	
	/**
	 * 获取key
	 * @return String
	 * 2015年5月21日 下午3:21:35
	 */
	public static String getKey(){
		Base64EncodedKeyGeneratorImpl keygenerator = new Base64EncodedKeyGeneratorImpl();
		try {
			keygenerator.afterPropertiesSet();
			String key = keygenerator.generate();
			return key;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	/**
	 * 加密字符串
	 * @return String
	 * 2015年5月21日 下午3:24:20
	 */
	public static String encryptStr(String message){
		String initializationVector = getInitVector();
		if(key == null)
			return null;
		Base64EncodedCipherer cipherer = getInstance(Mode.ENCRYPT); 
		String encryptStr = cipherer.encrypt(key, initializationVector, message);
		return encryptStr;
	}
	
	/**
	 * 解密字符串
	 * @return String
	 * 2015年5月21日 下午3:27:51
	 */
	public static String decryptStr(String message){
		String initializationVector = getInitVector();
		if(key == null)
			return null;
		Base64EncodedCipherer cipherer = getInstance(Mode.DECRYPT); 
		String decryptStr = cipherer.encrypt(key, initializationVector, message);
		return decryptStr;
	}
	
	public static void main(String[] args) {
		String str = "ddsfasasdf";
		String enstr = encryptStr(str);
		System.out.println(enstr);
		System.out.println(decryptStr(enstr));
	}
	
}

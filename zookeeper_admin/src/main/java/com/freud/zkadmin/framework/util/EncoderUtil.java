package com.freud.zkadmin.framework.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncoderUtil {

	public static String base64Encode(String src) throws IOException {
		return new BASE64Encoder().encode(src.getBytes());
	}

	public static String base64Decode(String src) throws IOException {
		return new String(new BASE64Decoder().decodeBuffer(src));
	}

	public static String SHA1(String src) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA");
		md.update(src.getBytes());
		return Hex.encodeHexString(md.digest());
	}

	public static String MD5(String s) {
		if (StringUtils.isEmpty(s) || StringUtils.isBlank(s)) {
			return null;
		}

		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		try {
			byte[] btInput = s.getBytes();
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	public static String getHashCode(Object object) throws IOException {
		if (object == null)
			return "";

		String ss = null;
		ObjectOutputStream s = null;
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		try {
			s = new ObjectOutputStream(bo);
			s.writeObject(object);
			s.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (s != null) {
				s.close();
				s = null;
			}
		}
		ss = MD5(bo.toString());
		return ss;
	}

}
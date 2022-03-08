package home.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {
	public static String getMD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			return convertByteToHex(messageDigest);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		System.out.println(getMD5("admin1234"));
		System.out.println(getMD5("phong11234"));
		System.out.println(getMD5("phong21234"));
	}

	public static String convertByteToHex(byte[] data) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			sb.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString();
	}

	public static int getTotalPage(int size) {
		int totalPage;
		if (size % Constants.LIMIT_PAGE != 0) {
			totalPage = size / Constants.LIMIT_PAGE + 1;
		} else {
			totalPage = size / Constants.LIMIT_PAGE;
		}
		return totalPage;
	}
}

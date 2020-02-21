package cn.zs.learn.java.messageDigest;


import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class JavaTest {
	public static void main(String[] args) throws Exception {
		System.out.println(getMD5_32("test"));
		System.out.println(getMD5_16("test"));
		System.out.println(getSHA1("test"));
		
		
		System.out.println();
		
		byte[] bs = MessageDigest.getInstance("MD5").digest("test".getBytes(StandardCharsets.UTF_8));
		
		long num = (bs[4] & 0xffL) 
				| ((bs[5] & 0xffL) << 8) 
				| ((bs[6] & 0xffL) << 16) 
				| ((bs[7] & 0xffL) << 24)
				| ((bs[8] & 0xffL) << 32)
				| ((bs[9] & 0xffL) << 40)
				| ((bs[10] & 0xffL) << 48)
				| ((bs[11] & 0xffL) << 56);
		
		System.out.println(num);
		
		byte[] bs1 = MessageDigest.getInstance("SHA-256").digest("test".getBytes(StandardCharsets.UTF_8));
		System.out.println(toHexSTring(bs1));
		
		System.out.println(get("md5","test"));


		System.out.println("###########################################");
		MessageDigest md1 = MessageDigest.getInstance("SHA");
		String str = "test";
		md1.update(str.getBytes(StandardCharsets.UTF_8));
		md1.update("123456".getBytes());
		byte[] secret1 = md1.digest();

		byte[] secret2 = MessageDigest.getInstance("SHA-256").digest("test".getBytes());
		byte[] secret3 = MessageDigest.getInstance("SHA").digest("123456".getBytes());
		for (byte s:secret1) {
			System.out.print(s+"\t");
		}
		System.out.println();
		for (byte s:secret2) {
			System.out.print(s+"\t");
		}
		System.out.println();
		for (byte s:secret3) {
			System.out.print(s+"\t");
		}
		System.out.println();
		System.out.println(secret1.length);
		System.out.println(secret2.length);
		System.out.println(secret3.length);

	}
	
	public static String get(String algorithm, String str) throws Exception {
		if("MD5".equalsIgnoreCase(algorithm))
			return getMD5_32(str);
		if("SHA1".equalsIgnoreCase(algorithm))
			return getSHA1(str);
		throw new Exception("no algorithm");
	}
	
	
	public static String getMD5_32(String str) throws NoSuchAlgorithmException {
		return toHexSTring(MessageDigest.getInstance("MD5").digest(str.getBytes(StandardCharsets.UTF_8)));
	} 
	
	public static String getMD5_16(String str) throws NoSuchAlgorithmException {
		return getMD5_32(str).substring(8, 24);
	}
	
	
	public static String getSHA1(String str) throws NoSuchAlgorithmException {
		return toHexSTring(MessageDigest.getInstance("SHA-1").digest("test".getBytes(StandardCharsets.UTF_8)));
	}
	
	public static String toHexSTring(byte[] bs) {
		StringBuffer sb = new StringBuffer();
		String hex;
		for(byte b:bs) {
			hex = Integer.toHexString(b & 0xff);
			if(hex.length() == 1) 
				hex = 0 + hex;
			sb.append(hex);
		}
		return sb.toString();
	}
	
	public static String bytesToHex(byte[] bytes) {
		StringBuffer md5str = new StringBuffer();
		// 把数组每一字节换成16进制连成md5字符串
		int digital;
		for (int i = 0; i < bytes.length; i++) {
			digital = bytes[i];
			
			if (digital < 0) {
				digital += 256;
			}
			if (digital < 16) {
				md5str.append("0");
			}
			md5str.append(Integer.toHexString(digital));
		}
		return md5str.toString().toUpperCase();
	}
}

class father {
	public father() {
		System.out.println("father");
	}
}

class son {
	public son(String name) {
		System.out.println("son" + name);
	}
}
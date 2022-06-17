package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

//암호화, 복호화하는 메소드를 가진 클래스
//기본적으로 java에서 암호화패키지를 제공을 한다.
//javs.crypto, java.security패키지의 클래스를 이용해서 양방향 암호화를 할 수 있다.
//AES방식의 대칭키 암호화처리하기
//한개의 키를 가지고 암호화, 복호화 처리하기 -> 키관리가 중요함.
public class AESEncrypt {
	
	private static SecretKey key;//암호화,복호화키 역할
	private String path;//키객체를 유일하게 저장하기 위해 파일로 관리하자
	
	public AESEncrypt() {
		//객체가 최초에 생성됐을때 키값을 세팅하기
		//1. 생성된 키(key객체 저장파일)가 있으면 그 키를 가져옴
		//2. 생성된 키(key객체 저장파일)가 없으면 생성해서 저장함.
		this.path=AESEncrypt.class.getResource("/").getPath();
		this.path=this.path.substring(0,this.path.indexOf("classes"));//WEB-INF폴더에 저장
		System.out.println(this.path);
		File keyFile=new File(path+"/pw-key");
		if(keyFile.exists()) {
			//파일이 있다면 -> 그파일에서 key값을 가져옴
			try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(keyFile))) {
				this.key=(SecretKey)ois.readObject();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else {
			//파일이 없다면 -> key를 생성하기.
			getGenerateKey();
		}
	}
	
	private void getGenerateKey() {
		//Secretkey를 생성하고 파일로 저장하는 메소드
		//키생성에 대한 랜덤값가져오기
		SecureRandom rnd=new SecureRandom();
		//KeyGenerator클래스를 이용해서 암호화키를 생성
		KeyGenerator keygen=null;
		ObjectOutputStream oos=null;
		try {
			keygen=KeyGenerator.getInstance("AES");
			keygen.init(128,rnd);
			this.key=keygen.generateKey();
			File key=new File(this.path+"/pw-key");
			oos=new ObjectOutputStream(new FileOutputStream(key));
			oos.writeObject(this.key);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try{
				if(oos!=null) oos.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//암호화기능 구현하기
	public static String encrypt(String oriData) throws Exception{
		//암호화처리
		Cipher cipher=Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, AESEncrypt.key);
		
		byte[] enctemp=oriData.getBytes(Charset.forName("UTF-8"));
		byte[] encResult=cipher.doFinal(enctemp);
		
		return Base64.getEncoder().encodeToString(encResult);
	}
	
	public static String decrypt(String encData) throws Exception {
		//복호화처리
		Cipher cipher=Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, AESEncrypt.key);
		
		byte[] dectemp=Base64.getDecoder().decode(encData.getBytes(Charset.forName("UTF-8")));
		byte[] decResult=cipher.doFinal(dectemp);
		
		return new String(decResult);
		
	}
	
}








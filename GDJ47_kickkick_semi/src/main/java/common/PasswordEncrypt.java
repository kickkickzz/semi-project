package common;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class PasswordEncrypt extends HttpServletRequestWrapper{

	
	public PasswordEncrypt(HttpServletRequest request) {
		super(request);
	}
	
	
	@Override 
	public String getParameter(String name) {
		if(name.equals("password")||name.equals("password_new")||name.equals("oriPw")||name.equals("newPw")) {
			return getSHA512(super.getParameter(name));
		}
		return super.getParameter(name);
	}
	
	
	
	private String getSHA512(String oriData) {
		//단방향암호화 SHA512알고리즘 적용하기
		String encData="";//암호화된 값을 저장하는 변수
		MessageDigest md=null;
		try {
			//1. 적용할 암호알고리즘을 선택
			md=MessageDigest.getInstance("SHA-512");
		}catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		//암호화처리는 비트단위연산을 하기 때문에 원본데이터를 byte[]로 전환
		byte[] oriDataByte=oriData.getBytes(Charset.forName("UTF-8"));
		//sha-512방식으로 암호화하기
		md.update(oriDataByte);
		byte[] changeData=md.digest();
		//Base64인코더를 이용해서 변환된 값을 문자열로 변경
		encData=Base64.getEncoder().encodeToString(changeData);
		
		return encData;
	}
	
	
}




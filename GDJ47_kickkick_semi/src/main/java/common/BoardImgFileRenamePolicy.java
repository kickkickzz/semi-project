package common;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class BoardImgFileRenamePolicy  implements FileRenamePolicy{

	@Override
	public File rename(File originFile) { //originFile: ��������
		long currentTime= System.currentTimeMillis();
		SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddHHmmss"); //�⵵/��/��/��/��/�� ���·� currentTime�� ������ �ٲ�.
		
		
		int randNum= (int)(Math.random()*100000);
		
		String name= originFile.getName(); //name: �������� �����̸�
		String ext=null;
		int dot=name.lastIndexOf(".");
		if(dot!=-1) {
			ext=name.substring(dot);
		}else {
			ext="";
		}
		
		// java.util.Date �� �̿�.
		String fileName= "BOARD_"+sdf.format(new Date(currentTime))+randNum+ext;
		File newFile= new File(originFile.getParent(), fileName);
		return newFile;
	}
}

package common;

import java.io.File;
import java.text.SimpleDateFormat;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamedPolicy implements FileRenamePolicy {
	   
	   @Override
	   public File rename(File originFile) {
	      
	      long currentTime = System.currentTimeMillis();
	      
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	      int ranNum = (int)(Math.random()*100000);
	      
	      String name = originFile.getName();
	      String ext = null;
	      int dot = name.lastIndexOf(".");
	      if(dot != -1) {
	         ext = name.substring(dot);
	      } else {
	         ext = "";
	      }
	      
	      String fileName = originFile.getName();
	      File newFile = new File(originFile.getParent(), fileName);
	      
	      return newFile;
	   }
}

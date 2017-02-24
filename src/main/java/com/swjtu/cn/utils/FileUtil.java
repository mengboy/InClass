package com.swjtu.cn.utils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	public static String downloadFile(HttpServletRequest request, MultipartFile file,String itempathname)
			throws IOException {
		if(file == null)
			return null;
		byte[] buffer = new byte[1444];
		InputStream in = file.getInputStream();
		String time = TimeUtil.gettime();
		String folder = time.substring(0, 8);
		String basicpath = request.getSession().getServletContext().getRealPath("/")+itempathname+"\\"+folder+"\\";
		String returnpath = HostUtil.geturl(request) + itempathname +"/"+folder+"\\"+time+".jpg";
		String savepath = basicpath + time + ".jpg";
		File f = new File(basicpath);
		if(!f.exists()){
			f.mkdirs();
		}
		File localFile = new File(savepath);
		FileOutputStream fs = new FileOutputStream(localFile);
		try {
			while (in.read(buffer) != -1) {
				fs.write(buffer);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}finally{
			in.close();
			fs.close();
		}
		return returnpath;
	}

}

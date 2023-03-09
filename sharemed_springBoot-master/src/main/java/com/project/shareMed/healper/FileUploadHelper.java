package com.project.shareMed.healper;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {
	
	public final String UPLOAD_DIR="C:\\Users\\madhi\\eclipse-workspace\\Eclipse_workspace_2\\ShareMedBackEnd\\src\\main\\resources\\static\\upload_images";
	
	public boolean uploadFile(MultipartFile file) {
		boolean uploaded = false;
		try {
			//#usual File Upload Code
//			InputStream is = file.getInputStream();
//			byte data[] = new byte[is.available()];
//			is.read(data);
//			
//			//write
//			FileOutputStream fos = new FileOutputStream(new File(UPLOAD_DIR+"\\"+file.getOriginalFilename()));
//			fos.write(data);
//			
//			fos.flush();
//			fos.close();
			
			//#Using NIO
			Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR+"\\"+file.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
			
			uploaded = true;
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return uploaded;
	}
	
	public String uploadPrescription(MultipartFile file) {
		try {
			Files.copy(file.getInputStream(), Paths.get(UPLOAD_DIR+"\\prescription\\"+file.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return UPLOAD_DIR+"\\prescription\\"+file.getOriginalFilename();
	}
	
}

package com.chinesejr.controller;

import java.nio.file.Paths;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.chinesejr.conf.StaticProperties;
import com.chinesejr.vo.util.FileUploadVO;

@RestController
public class FileController {
	@Autowired
	private StaticProperties sp;
	private static final ResourceLoader resourceLoader = new DefaultResourceLoader();  
	@RequestMapping(method = RequestMethod.GET, value = "/api/static/{filename:.+}")  
    public ResponseEntity<?> getFile(@PathVariable String filename) {  
  
        try {  
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(sp.getUpload(), filename).toString()));  
        } catch (Exception e) {  
            return ResponseEntity.notFound().build();  
        }  
    }  
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ResponseEntity uploadFile(MultipartHttpServletRequest request) {
		try {
			Iterator<String> itr = request.getFileNames();

			while (itr.hasNext()) {
				String uploadedFile = itr.next();
				MultipartFile file = request.getFile(uploadedFile);
				String mimeType = file.getContentType();
				String filename = file.getOriginalFilename();
				byte[] bytes = file.getBytes();

				FileUploadVO newFile = new FileUploadVO(filename, bytes, mimeType);

			}
		} catch (Exception e) {
			return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>("{}", HttpStatus.OK);
	}
}

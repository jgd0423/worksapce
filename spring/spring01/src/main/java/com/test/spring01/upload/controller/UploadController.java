package com.test.spring01.upload.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.tika.Tika;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadController {
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	//xml에 설정된 리소스 참조
    //servlet-contexct.xml 파일에 설정된 bean의 id가 uploadPath인 태그를 참조
/*
	pom.xml 에 다음 내용 추가
	<!-- https://mvnrepository.com/artifact/javax.annotation/javax.annotation-api -->
	<dependency>
	    <groupId>javax.annotation</groupId>
	    <artifactId>javax.annotation-api</artifactId>
	    <version>1.3.2</version>
	</dependency>
*/	

	
//    @Resource(name = "uploadPath")
//    String uploadPath;  //String uploadPath = "C:/hj/attach"; 처럼 해도 된다..
//    String uploadPath = "C:/hj/attach/product_img";
//    String uploadPath = "/Users/macbook01/Desktop/hj/attach/test_img";
	String uploadPath = "C:/jgd/attach/test_img";
    
//	메소드 오버로딩...
    @RequestMapping(value = "upload/uploadForm", method = RequestMethod.GET)
    public void uploadForm() {
        // upload/uploadForm.jsp로 포워딩
    }
    
//  업로드 버튼 => 임시디렉토리에 업로드 => 파일정보가 file에 저장 => 지정된 디렉토리에 저장 =>
    @RequestMapping(value = "upload/uploadForm", method = RequestMethod.POST)
    public ModelAndView uploadForm(MultipartFile file, ModelAndView mav) throws Exception { //MultipartFile file 요기로 업로드된 파일이 쌓이게 된다.
        logger.info("파일이름:" + file.getOriginalFilename());
        logger.info("파일크기:" + file.getSize());
        logger.info("컨텐트 타입:" + file.getContentType());
        
        String originalFileName = file.getOriginalFilename();
        String newFileName = uploadFile(originalFileName, file.getBytes());
        String contentType = file.getContentType();
        
//        https://github.com/devheedoo/TIW/blob/master/%5BJava%5D%20%ED%8C%8C%EC%9D%BC%20%EC%97%85%EB%A1%9C%EB%93%9C%20%EC%8B%9C%20MIME%20%ED%83%80%EC%9E%85%20%ED%99%95%EC%9D%B8.md
        	
        InputStream inputStream;
        inputStream = file.getInputStream();
        Tika tika = new Tika();
        String mimeType = tika.detect(inputStream);
        
/*
        String[] allowedMIMETypesEquals = {
            "application/zip",    // .zip
            "application/pdf",    // .pdf
            "application/msword", // .doc, .dot
            "application/x-hwp", "applicaion/haansofthwp", "application/x-tika-msoffice", // .hwp
            "application/x-tika-ooxml"  // .xlsx, .pptx, .docx
        };
        for (int i=0; i<allowedMIMETypesEquals.length; i++) {
            if (mimeType.equals(allowedMIMETypesEquals[i])) {
                return true;
            }
        }
        
        
        
        String[] allowedMIMETypesStartsWith = {
            "image",    // .png, .jpg, .jpeg, .gif, .bmp
            "text",     // .txt, .html 등
            "application/vnd.ms-word",          // .docx 등 워드 관련
            "application/vnd.ms-excel",         // .xls 등 엑셀 관련
            "application/vnd.ms-powerpoint",    // .ppt 등 파워포인트 관련
            "application/vnd.openxmlformats-officedocument",    // .docx, .dotx, .xlsx, .xltx, .pptx, .potx, .ppsx
            "applicaion/vnd.hancom"     // .hwp 관련
        };
        for (int i=0; i<allowedMIMETypesStartsWith.length; i++) {
            if (mimeType.startsWith(allowedMIMETypesStartsWith[i])) {
                return true;
            }
        }
*/
            
/* 
        int failCounter = 0;
        String[] arrInvalidContentTypes = "application/octet-stream;application/x-msdownload;application/x-sh".split(";");
        for(int j=0, iLen=arrInvalidContentTypes.length;j<iLen;j++) {
            if(contentType.equalsIgnoreCase(arrInvalidContentTypes[j])) {
            	failCounter++;
            }
        }
*/
        
        int failCounter = 0;
        if (!contentType.equals(mimeType)) {
        	failCounter++;
        }
        
        Map<String, String> map = new HashMap<>();
        map.put("originalFileName", originalFileName);
        map.put("newFileName", newFileName);
        map.put("newFileSize", file.getSize() + "");
        map.put("newFileType", file.getContentType());
        map.put("mimeType", mimeType);
        map.put("failCounter", failCounter + "");
        
        mav.setViewName("upload/uploadFormResult");
        mav.addObject("map", map);
        return mav; //uploadResult.jsp로 포워딩
    }
    //파일 이름이 중복되지 않도록 처리
    private String uploadFile(String originalFileName, byte[] fileData) throws Exception {
    	String ext = originalFileName.substring(originalFileName.lastIndexOf('.')).toLowerCase(); // 확장자 
    	
        //UUID 생성 (Universal Unique IDentifier, 범용 고유 식별자)
        UUID uid = UUID.randomUUID();
//        String newFileName = uid.toString() + "_" + originalFileName;
        String newFileName = uid.toString() + ext;
        java.io.File f1 = new java.io.File(uploadPath, newFileName);
        //임시 디렉토리에 저장된 업로드된 파일을 지정된 디렉토리로 복사
        //FileCopyUtils.copy( 바이트배열, 파일객체 );
        FileCopyUtils.copy(fileData, f1);
        return newFileName;
    }
}

package myboard.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import myboard.constants.CommonConstants;

public class TestCommon {
	
	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dirName = sdf.format(new Date(System.currentTimeMillis())) + "/";
		
		String uploadedFileName1 = "image.jsp";
		String uploadedFileName2 = "image.jsp";
		// hashcode() 문제점: 동일한 문자열에서는 hashcode()가 동일 => Math.random() 사용
		
		uploadedFileName1 = "" + Math.floor(Math.random()*1000000000);
		uploadedFileName2 = "" + uploadedFileName2.hashCode() + Math.floor(Math.random()*1000000000);
		
		System.out.println(CommonConstants.props.getProperty("FILE_UPLOAD_DIR") + dirName + uploadedFileName1);
		System.out.println(CommonConstants.props.getProperty("FILE_UPLOAD_DIR") + dirName + uploadedFileName2);
		
	} // main

} // class

package com.chinesejr.mail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.chinesejr.mail.vo.EmailValidVO;

public class ParseHtml {
	public String parse(EmailValidVO vo) {
		String html = "";
		try {
			html = this.readFileByClassLoader("templates/email/registerValid.html");
			html = html.replaceAll("<jr:email/>", vo.getUserEmail()).replaceAll("<jr:validcode/>", vo.getValidCode());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return html;
	}
	
	 // 使用ClassLoader读取资源文件，相对于类目录(即classes)
    private synchronized String readFileByClassLoader(String filePath) throws IOException {
        // 获取到装载当前类的类装载器
        ClassLoader loader = ParseHtml.class.getClassLoader();
        InputStream in = loader.getResourceAsStream(filePath);
        InputStreamReader isr = new InputStreamReader(in, "UTF-8");  
        BufferedReader br = new BufferedReader(isr);
        StringBuffer s = new StringBuffer();
        String line = br.readLine();
        while(line != null) {
        	s.append(line);
        	line = br.readLine();
        }
        in.close();
        
        return s.toString();
    }
}

package com.chinesejr.mail;

import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.chinesejr.util.L;
import com.chinesejr.util.StringUtil;

public class EmailUtils {
	private static final ResourceBundle bundle = ResourceBundle.getBundle("application");
	private static JavaMailSenderImpl senderImpl = null;
	static {
		senderImpl = new JavaMailSenderImpl();  

        // 设定mail server  
        senderImpl.setHost(bundle.getString("mail.host"));  
//      senderImpl.setPort(345);
        senderImpl.setUsername(bundle.getString("mail.username"));         // 根据自己的情况,设置发件邮箱地址  
        senderImpl.setPassword(bundle.getString("mail.password"));         // 根据自己的情况, 设置password  
        senderImpl.setDefaultEncoding(bundle.getString("mail.encoding"));
        Properties prop = new Properties();  
        prop.put("mail.smtp.auth", bundle.getString("mail.smtp.auth"));     // 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确  
        prop.put("mail.smtp.ssl.enable", bundle.getString("mail.smtp.ssl.enable"));
        prop.put("mail.smtp.socketFactory.class", bundle.getString("mail.smtp.socketFactory.class"));
        prop.put("mail.smtp.starttls.enable", bundle.getString("mail.smtp.starttls.enable"));                       
        prop.put("mail.smtp.starttls.required", bundle.getString("mail.smtp.starttls.required"));
        prop.put("mail.smtp.connectiontimeout", bundle.getString("mail.smtp.connectiontimeout"));                   
        prop.put("mail.smtp.timeout", bundle.getString("mail.smtp.timeout"));
        prop.put("mail.smtp.writetimeout", bundle.getString("mail.smtp.writetimeout"));
        senderImpl.setJavaMailProperties(prop);
	}
    public static boolean sendHtmlMail(String subject,String content,String receiver){

        return false;
    }
    
    private boolean sendMail(String subject,String content,String receiver) {
    	boolean result = true;

        try{

            // 建立邮件消息,发送简单邮件和html邮件的区别  
            MimeMessage mailMessage = senderImpl.createMimeMessage();  
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage);  
            
            if(StringUtil.isEmpty(receiver)) {
            	receiver = bundle.getString("mail.receiver.username");
            }
            
            // 设置收件人，寄件人  
            messageHelper.setTo(receiver);  
            messageHelper.setFrom(bundle.getString("mail.username"));
            // 邮件标题
            messageHelper.setSubject(subject);
            // true 表示启动HTML格式的邮件  
            messageHelper.setText(content,true);  

            // 发送邮件  
            senderImpl.send(mailMessage);  
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            result = false;
            e.printStackTrace();
            L.e("EmailUtils.method [sendHtmlMail]: email send result-" + result +",error message-" + e);
        }

        return result;
    }
    
	public static void sendHtmlMail(String subject,String content) {
		// TODO Auto-generated method stub
		sendHtmlMail(subject, content, null);
	}
	
	public void sendMail(String subject,String content) {
		sendMail(subject, content, null);
	}

}
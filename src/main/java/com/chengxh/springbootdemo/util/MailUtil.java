package com.chengxh.springbootdemo.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(MailUtil.class);
	
	//协议名称
    private static final String TRANSPORT_PROTOCOL;
    //主机名,一般是stmp.xxx.com
    private static final String SMTP_HOST;
    //身份认证
    private static final String SMTP_AUTH;
    //发送者邮箱帐号
    private static final String USER_NAME;
    //发送者邮箱密码,有些邮箱需要用授权码代替密码
    private static final String PASSWORD;
    //html格式
    public static final String HTML_BODY = "text/html;charset=UTF-8";
    //text格式
    public static final String TEXT_BODY = "text/plain;charset=UTF-8";
    
    static{
    	TRANSPORT_PROTOCOL = "smtp";
        SMTP_HOST = "smtp.qq.com";
        SMTP_AUTH = "true";
        USER_NAME = "1634074023@qq.com";
        PASSWORD = "gjsnetdvlbckdadb";
    }

    /**
     * 发送html类型邮件
     * @param subject 邮件标题
     * @param body 邮件内容
     * @param tos  收件人邮箱帐号,多个收件人用逗号隔开
     * @param files 附件，多个附件用逗号隔开,附件地址要物理路径
     * @return 发送过程不出异常则返回true,否则返回false
     */
    public static boolean sendHtmlEmail(String subject, String body, String tos, String[] files) {
    	return sendEmail(subject, body, HTML_BODY, tos, files);
    }
    
    /**
     * 发送text类型邮件
     * @param subject 邮件标题
     * @param body 邮件内容
     * @param tos  收件人邮箱帐号,多个收件人用逗号隔开
     * @param files 附件，多个附件用逗号隔开,附件地址要物理路径
     * @return 发送过程不出异常则返回true,否则返回false
     */
    public static boolean sendTextEmail(String subject, String body, String tos, String[] files) {
    	return sendEmail(subject, body, TEXT_BODY, tos, files);
    }
    
    /**
     * 发送html类型邮件(不带附件)
     * @param subject 邮件标题
     * @param body 邮件内容
     * @param tos  收件人邮箱帐号,多个收件人用逗号隔开
     * @return 发送过程不出异常则返回true,否则返回false
     */
    public static boolean sendHtmlEmail(String subject, String body, String tos) {
    	return sendEmail(subject, body, HTML_BODY, tos, null);
    }
    
    /**
     * 发送text类型邮件(不带附件)
     * @param subject 邮件标题
     * @param body 邮件内容
     * @param tos  收件人邮箱帐号,多个收件人用逗号隔开
     * @param files 附件，多个附件用逗号隔开,附件地址要物理路径
     * @return 发送过程不出异常则返回true,否则返回false
     */
    public static boolean sendTextEmail(String subject, String body, String tos) {
    	return sendEmail(subject, body, TEXT_BODY, tos, null);
    }
    
    /**
     * 发送邮件
     * @param subject 邮件标题
     * @param body 邮件内容
     * @param bodyTp 邮件内容格式,html或者text格式
     * @param tos  收件人邮箱帐号,多个收件人用逗号隔开
     * @param files 附件，多个附件用逗号隔开,附件地址要物理路径
     * @return 发送过程不出异常则返回true,否则返回false
     */
    public static boolean sendEmail(String subject, String body, String bodyTp, String tos, String[] files) {
        //1.设置配置信息
        Properties prop = getProp();
        // 2.创建邮件会话实例
        Session session = Session.getInstance(prop);
        try {
            // 3.根据会话实例创建邮件信息对象
            MimeMessage message = getMailMessage(subject, body, bodyTp, tos, files, session);
            // 4.创建smtp协议的邮箱传输对象
            Transport transport = session.getTransport();
            //5.设置发件人的账户名和密码
            transport.connect(USER_NAME, PASSWORD);
            //6.把邮件信息发送给所有指定的收件人
            transport.sendMessage(message, message.getAllRecipients());
            //7.关闭传输对象
            transport.close();
        } catch (Exception e) {
        	logger.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

	private static Properties getProp() {
		Properties prop = new Properties();
        //协议名称
        prop.setProperty("mail.transport.protocol", TRANSPORT_PROTOCOL);
        //主机名
        prop.put("mail.smtp.host", SMTP_HOST);
        //身份验证
        prop.put("mail.smtp.auth", SMTP_AUTH);
		return prop;
	}

	private static MimeMessage getMailMessage(String subject, String body, String bodyTp, String tos, String[] files,
			Session session) throws MessagingException, AddressException, UnsupportedEncodingException {
		
		MimeMessage message = new MimeMessage(session);
		//设置发件人
		message.setFrom(new InternetAddress(USER_NAME));
		//设置收件人(这里是设置多个收件人，tos用逗号隔开)
		//TO(主送地址)、CC(抄送地址)、BCC(秘密抄送)
		InternetAddress[] internetAddressTo = InternetAddress.parse(tos);
		message.setRecipients(Message.RecipientType.TO, internetAddressTo);
		//设置邮件发送日期(默认当前立即发送)
		message.setSentDate(new Date());
		//设置邮件主题
		message.setSubject(subject);
		//设置邮件主体内容(包括内容和附件)
		MimeMultipart mm = new MimeMultipart();
		//设置邮件内容部分
		MimeBodyPart content = new MimeBodyPart();
		content.setContent(body, bodyTp);
		mm.addBodyPart(content);

		// 设置多个附件
		if(files!=null && files.length>0) {
		    for (String f : files) {
		        // 设置附件部分
		        MimeBodyPart attachment = new MimeBodyPart();
		        // 读取文件
		        DataHandler dh = new DataHandler(new FileDataSource(f));
		        // 将文件关联到附件上
		        attachment.setDataHandler(dh);
		        // 设置附件的文件名（需要编码避免中文乱码）
		        attachment.setFileName(MimeUtility.encodeText(dh.getName()));
		        mm.addBodyPart(attachment);
		    }
		}
		message.setContent(mm);
		return message;
	}

}

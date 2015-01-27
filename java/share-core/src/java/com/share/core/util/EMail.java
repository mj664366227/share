package com.share.core.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 电子邮件类(基于javax.mail实现)
 * @author ruan
 *
 */
public class EMail {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(EMail.class);
	/**
	 * 加载配置文件
	 */
	private final static Properties config = FileSystem.loadProperties("mail.properties");
	/**
	 * smtp服务器地址
	 */
	private final static String smtpHost = config.getProperty("mail.smtp.host").trim();
	/**
	 * 发件人用户名
	 */
	private final static String senderUser = config.getProperty("mail.sender.user").trim();
	/**
	 * 发件人密码
	 */
	private final static String senderPass = config.getProperty("mail.sender.pass").trim();
	/**
	 * 消息结构体
	 */
	private MimeMessage message;
	/**
	 * session会话
	 */
	private Session s;

	/**
	 * 构造函数
	 */
	public EMail() {
		s = Session.getInstance(config);
		message = new MimeMessage(s);
	}

	/**
	 * 发送邮件
	 * @author ruan
	 * @param title 邮件标题
	 * @param mailType 邮件类型(可选：纯文本、html格式)
	 * @param content 邮件内容
	 * @param fileList 附件列表
	 * @param receiver 收件人
	 */
	public void send(String title, MailType mailType, String content, List<String> fileList, String... receiver) {
		try {
			// 发件人
			InternetAddress from = new InternetAddress(senderUser);
			message.setFrom(from);

			// 收件人
			StringBuilder sb = new StringBuilder();
			for (String s : receiver) {
				sb.append(s.trim());
				sb.append(",");
			}
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sb.toString()));

			// 邮件标题
			message.setSubject(title.trim());

			// 邮件内容
			Multipart mailPart = new MimeMultipart();

			// 文字内容
			MimeBodyPart mailContent = new MimeBodyPart();
			mailContent.setContent(content.trim(), "text/" + mailType.toString() + ";charset=utf-8");
			mailPart.addBodyPart(mailContent);

			// 添加附件
			for (String file : fileList) {
				MimeBodyPart attachment = new MimeBodyPart();
				FileDataSource fileDataSource = new FileDataSource(file.trim());
				attachment.setDataHandler(new DataHandler(fileDataSource));
				attachment.setFileName(fileDataSource.getName().trim());
				mailPart.addBodyPart(attachment);
			}

			// 设置邮件内容
			message.setContent(mailPart);
			message.setSentDate(new Date());
			message.saveChanges();
			Transport transport = s.getTransport("smtp");

			// smtp验证，就是你用来发邮件的邮箱用户名密码
			transport.connect(smtpHost, senderUser, senderPass);

			// 发送
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

			logger.info("all recipients : {}", JSONObject.encode(message.getAllRecipients()));
		} catch (MessagingException e) {
			logger.error("", e);
		}
	}

	/**
	 * 发送邮件
	 * @author ruan
	 * @param title 邮件标题
	 * @param mailType 邮件类型(可选：纯文本、html格式)
	 * @param content 邮件内容
	 * @param fileList 附件列表
	 * @param receiver 收件人
	 */
	public void send(String title, MailType mailType, String content, ArrayList<File> fileList, String... receiver) {
		List<String> filePathList = new ArrayList<String>();
		for (File file : fileList) {
			filePathList.add(file.getPath());
		}
		send(title, mailType, content, filePathList, receiver);
	}

	/**
	 * 邮件类型
	 * @author ruan
	 *
	 */
	public final static class MailType {
		/**
		 * 文本
		 */
		public final static MailType PLAIN = new MailType("plain");
		/**
		 * html
		 */
		public final static MailType HTML = new MailType("html");
		/**
		 * 邮件类型描述
		 */
		private String type;

		/**
		 * 构造函数
		 * @param type
		 */
		private MailType(String type) {
			this.type = type.toLowerCase();
		}

		/**
		 * toString
		 * @author ruan
		 * @return
		 */
		public String toString() {
			return type;
		}
	}
}
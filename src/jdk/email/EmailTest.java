package jdk.email;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 * The Class EmailTest.
 * 可以考虑，多线程队列方式收发邮件。
 * 
 * @date 2013-6-26 15:47:19
 * @author 宿晓斐
 * @version 1.0
 * @since jdk 1.6,common_tools 1.0,javamail 1.5
 */
public class EmailTest {

	public static void send() {
		Properties p = new Properties();
		p.put("mail.smtp.host", "mail.lafaso.com");
		p.put("mail.smtp.port", 25);
		p.put("mail.smtp.auth", true);
		// p.put("mail.smtp.ssl.enable", true);
		InternetAddress address;
		Authenticator au = new Authenticator() {
			private PasswordAuthentication pa = new PasswordAuthentication(
					"suxiaofei", "787814SXF!SXF");

			public PasswordAuthentication getPasswordAuthentication() {
				return pa;
			}
		};
		Session session = Session.getDefaultInstance(p, au);
		session.setDebug(true);
		Message message = new MimeMessage(session);
		try {
			address = new InternetAddress("suxiaofei@lafaso.com");
			message.setFrom(address);
			message.setRecipient(RecipientType.TO, address);
			message.setSubject("java html mail test!");
			
			// 组装内容
			BodyPart bp = new MimeBodyPart();
			
			// 正文内容
			bp.setContent("12<br/>123<br/>", "text/html;charset=utf8");
			Multipart mt = new MimeMultipart();
			mt.addBodyPart(bp);
			
			//附件 
//			BodyPart fujian = new MimeBodyPart(new FileInputStream("D:/test/123.txt"));
//			fujian.setFileName("123.txt");
//			mt.addBodyPart(fujian);
			
			
			message.setContent(mt);
//			message.setText("java mail text!");
			message.setSentDate(new Date());
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	public static void receive() {
		Properties p = new Properties();
		p.put("mail.pop3.host", "mail.lafaso.com");
		p.put("mail.pop3.port", 110);
		p.put("mail.pop3.auth", true);

		// p.put("mail.smtp.ssl.enable", true);
		// InternetAddress address;
		Authenticator au = new Authenticator() {
			private PasswordAuthentication pa = new PasswordAuthentication(
					"suxiaofei", "787814SXF!qs");

			public PasswordAuthentication getPasswordAuthentication() {
				return pa;
			}
		};
		Session session = Session.getDefaultInstance(p, au);
		//session.setDebug(true);

		try {
			Store store = session.getStore("pop3");
			store.connect();
			Folder folder = store.getFolder("INBOX");
			folder.open(Folder.READ_ONLY);
			System.out.println("folder name:" + folder.getFullName());
			System.out.println("message count:" + folder.getMessageCount());
			System.out.println("new message count:"
					+ folder.getNewMessageCount());
			Message m = folder.getMessage(1);
			System.out.println(m.getSubject());
			System.out.println(m.getFileName());
			System.out.println(m.getDescription());
			folder.close(true);
			store.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		 send();
		//receive();
	}
}

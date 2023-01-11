package com.abs.Tool;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailTool {
    public static boolean send(String  title,String comtent, String toPeopleEmail){

        try {
            Properties properties = new Properties();
            properties.setProperty("mail.debug","true");


            properties.setProperty ( "mail.smtp.auth", "true ");
            properties.setProperty ( "mail.host", "smtp.qq.com" );
            properties.setProperty ( "mail.transport.protocol", "smtp");
            MailSSLSocketFactory socketFactory = new MailSSLSocketFactory();
            socketFactory.setTrustAllHosts(true);
            properties.put( "mail.smtp.ssl.enable", "true ");
            properties.put( "mail.smtp.ssl.socketFactory" , socketFactory);
            Session session = Session.getDefaultInstance(properties);
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setSubject(title);//标题


//            mimeMessage.setText(comtent);//内容
            StringBuffer strBuilder = new StringBuffer();
            strBuilder.append(comtent);//内容

            mimeMessage.setText(strBuilder.toString());
            mimeMessage.setFrom(new InternetAddress("1661153310@qq.com"));//发送方的邮箱地址
            Transport transport = session.getTransport();
            transport.connect("smtp.qq.com","1661153310","eidyfwlxktbrchfd");

            transport.sendMessage(mimeMessage,InternetAddress.parse(toPeopleEmail));
             transport.close();
             return true;

        } catch (Exception e) {
            return false;
        }
    }
}

package com.maple.mail;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import javax.imageio.ImageIO;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Random;

@Slf4j
public class JavaMailTest {

    @Test
    public void testSendMail() throws IOException, MessagingException {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        InputStream resourceAsStream = contextClassLoader.getResourceAsStream("mail.properties");
        final Properties properties = new Properties();
        properties.load(resourceAsStream);
//        properties.setProperty("mail.smtp.host", "smtp.qq.com");
//        properties.put("mail.smtp.auth", "true");
//        properties.setProperty("mail.user", "maple_zh@foxmail");
//        properties.setProperty("mail.password", "gazbiauxkbgvjhbc");
//        properties.load(resourceAsStream);
        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(properties.getProperty("mail.user"), properties.getProperty("mail.password")); //发件人邮件用户名、密码
            }
        });
        MimeMessage mimeMessage = new MimeMessage(session);
        // Set From: 头部头字段
        mimeMessage.setFrom("maple_zh@foxmail.com");
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("1435267512@qq.com"));
        mimeMessage.setSubject("Verification code for Registration");
        mimeMessage.setText("Hello, this is example of sending email  ");
        Transport.send(mimeMessage);
    }

    @Test
    public void testSendVerificationCodeToEmail() {
        int width = 60, height = 20;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        // Get the brush
        Graphics g = image.getGraphics();
        // Set background color
        g.setColor(new Color(200, 200, 200));
        g.fillRect(0, 0, width, height);
        // Take randomly generated captcha (4 Digit number )
        Random rnd = new Random();
        int randNum = rnd.nextInt(8999) + 1000;
        String randStr = String.valueOf(randNum);
        // Store the verification code in session

        // Display the captcha in the image
        g.setColor(Color.black);
        g.setFont(new Font("", Font.PLAIN, 20));
        g.drawString(randStr, 10, 17);
        // Randomly generated 100 A point of interruption , Make the captcha in the image difficult to detect by other programs
        for (int i = 0; i < 100; i++) {

            int x = rnd.nextInt(width);
            int y = rnd.nextInt(height);
            g.drawOval(x, y, 1, 1);
        }
        OutputStream out = null;
        try {
            out = Files.newOutputStream(Paths.get("code.png"));
            ImageIO.write(image,"JPEG", ImageIO.createImageOutputStream(out));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.maple.service.impl;

import com.maple.service.VerifyCodeService;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@Slf4j
public class VerifyCodeServiceImpl implements VerifyCodeService {

    @Override
    public boolean verifyVerificationCode(String code, String correct) {
        return code.equals(correct);
    }

    @Override
    public String generateVerificationCode(ServletOutputStream servletOutputStream) {
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
        System.out.println(randStr);
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
        try {
            ImageIO.write(image, "JPEG", servletOutputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return randStr;
    }

    @Override
    public String generateVerificationCode() {
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
        System.out.println(randStr);
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
        return randStr;
    }
}

package com.knowlode.model;

import org.scilab.forge.jlatexmath.TeXConstants;
import org.scilab.forge.jlatexmath.TeXFormula;
import org.scilab.forge.jlatexmath.TeXIcon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TexParser {

    public static void parseTexFile(String filePath, String outputImagePath) {
        try {
            // 读取TeX文件内容
            String texContent = new String(java.nio.file.Files.readAllBytes(new File(filePath).toPath()));

            // 创建TeX公式对象
            TeXFormula formula = new TeXFormula(texContent);

            // 创建TeX图标对象
            TeXIcon icon = formula.createTeXIcon(TeXConstants.STYLE_DISPLAY, 20);

            // 创建图像缓冲区
            BufferedImage image = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = image.createGraphics();
            g2.setColor(Color.white);
            g2.fillRect(0, 0, icon.getIconWidth(), icon.getIconHeight());
            g2.setColor(Color.black);
            icon.paintIcon(null, g2, 0, 0);
            g2.dispose();

            // 保存图像到文件
            ImageIO.write(image, "png", new File(outputImagePath));

            System.out.println("TeX file parsed and saved as image: " + outputImagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 示例：解析resources/tex/sample.tex文件并保存为image.png
        String texFilePath = "src/main/resources/tex/sample.tex";
        String outputImagePath = "src/main/resources/tex/image.png";
        parseTexFile(texFilePath, outputImagePath);
    }
}
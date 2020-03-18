package com.linhuanjie.common.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;

/**
 * @Author : linhuanjie
 * @Email : lhuanjie@qq.com
 * @Description :
 * @CreateTime : 2020.03.06 18:48
 **/
public class QRCodeUtils {

    public static String generateQrCode(String content, int qrCodeSize, String imageFormat) throws WriterException, IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        //设置二维码纠错级别
        Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();
        // 矫错级别
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        //创建比特矩阵(位矩阵)的QR码编码的字符串
        BitMatrix byteMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hintMap);
        // 使BufferedImage勾画QRCode  (matrixWidth 是行二维码像素点)
        int matrixWidth = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth-200, matrixWidth-200, BufferedImage.TYPE_INT_RGB);
        image.createGraphics();
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);
        // 使用比特矩阵画并保存图像
        graphics.setColor(Color.BLACK);
        for (int i = 0; i < matrixWidth; i++){
            for (int j = 0; j < matrixWidth; j++){
                if (byteMatrix.get(i, j)){
                    graphics.fillRect(i-100, j-100, 1, 1);
                }
            }
        }

        boolean write = ImageIO.write(image, imageFormat, out);
        if (write){
            String imgUrl = QiniuUtil.upload(out.toByteArray() , content );
            return  imgUrl;

//            // 保存到本地文件中
//            //创建存储数据的文件
//            File file =new File("H:\\file.jpg");
//            //创建一个用于操作文件的字节输出流对象。一创建就必须明确数据存储目的地。
//            //输出流目的是文件，会自动创建，如果文件存在，则覆盖。
//            FileOutputStream fos=new FileOutputStream(file);
//            //调用父类中的write方法
//            byte[] data=out.toByteArray();
//            fos.write(data);
//            //关闭流资源
//            fos.close();

        }

        return null;
    }

    public static void main(String[] args) throws IOException, WriterException {
        String res = QRCodeUtils.generateQrCode("http://www.baidu.com", 1200, "JPEG");
        System.out.println(res);



    }
}

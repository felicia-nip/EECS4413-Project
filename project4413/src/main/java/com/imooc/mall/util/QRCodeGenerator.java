package com.imooc.mall.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.imooc.mall.common.Constant;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

/**
 * Generate QR code utility
 */
public class QRCodeGenerator {


    /**
     * generate a QR code
     * @param text information that included in QR code
     * @param width width of QR code
     * @param height height of QR code
     * @param filePath path of QR code img
     * @throws WriterException
     * @throws IOException
     */
    public static void generateQRCodeImage(String text, int width, int height, String filePath)
            throws WriterException, IOException {
//        get a brand new QRCodeWriter class.
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

//        creating a QR bit matrix by using encode method with BarcodeFormat.QR_CODE param
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

//        Set the path, and then the program will convert the bit matrix into QR code and save it to the specific image
        Path path = FileSystems.getDefault().getPath(filePath);

//        set matrix, file format, and file path, then process to create QR code
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
    }

}

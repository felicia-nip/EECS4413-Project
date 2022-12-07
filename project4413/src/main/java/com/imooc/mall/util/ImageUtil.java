package com.imooc.mall.util;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

/**
 * Image utility class
 * {@code @Deprecated}
 */
@Deprecated
public class ImageUtil {

    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\shuai\\Desktop\\localCodesRepository\\EECS4413-Project\\project4413\\src\\main\\resources\\images\\";
        //Cutting
        Thumbnails.of(path + "caomei2.jpg").sourceRegion(Positions.BOTTOM_RIGHT, 200, 200).size(200, 200).toFile(path+"crop.jpg");

        //Scaling
        Thumbnails.of(path+"caomei2.jpg").scale(0.7).toFile(path+"scale1.jpg");
        Thumbnails.of(path+"caomei2.jpg").scale(1.5).toFile(path+"scale2.jpg");
        Thumbnails.of(path+"caomei2.jpg").size(500,500).keepAspectRatio(false).toFile(path+"size1.jpg");
        Thumbnails.of(path+"caomei2.jpg").size(500,500).keepAspectRatio(true).toFile(path+"size2.jpg");

        //Rotating
        Thumbnails.of(path+"caomei2.jpg").size(500,500).keepAspectRatio(true).rotate(90).toFile(path+"rotate1.jpg");
        Thumbnails.of(path+"caomei2.jpg").size(500,500).keepAspectRatio(true).rotate(180).toFile(path+"rotate2.jpg");

    }


}

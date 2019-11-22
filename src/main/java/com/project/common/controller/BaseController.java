package com.project.common.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.common.entity.FileInfo;
import com.project.common.entity.PageQuery;
import com.project.common.mapper.FileInfoMapper;
import com.project.common.utils.ImageUtil;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * 基础 控制器
 *
 * @author zhuyifa
 * @since 2019-06-19
 */
public class BaseController {

    @Autowired
    private FileInfoMapper fileInfoMapper;

    /**
     * mybatis-plus分页封装
     *
     * @param query 分页参数
     * @param <T>   实体
     * @return 分页信息
     */
    public static <T> IPage<T> initPage(PageQuery query) {
        Integer pageNumber = query.getPageNumber();
        if (pageNumber == null) {
            pageNumber = 1;
        }
        Integer pageSize = query.getPageSize();
        if (pageSize == null) {
            pageSize = 10;
        }
        String orderBy = query.getOrderBy();
        String orderMode = query.getOrderMode();

        Page<T> page = new Page<>(pageNumber, pageSize);
        if (StringUtils.isBlank(orderBy)) {
            return page;
        }
        // 倒序
        if (PageQuery.DESC.equalsIgnoreCase(orderMode)) {
            page.setDesc(orderBy);
        }
        // 默认顺序
        else {
            page.setAsc(orderBy);
        }

        return page;
    }

    /**
     * 批量上传文件
     *
     * @param fileArray 文件数组
     * @return 文件链接
     */
    public String upload(MultipartFile... fileArray) {
        // 数组长度
        if (fileArray == null || fileArray.length == 0) {
            return null;
        }
        // 文件url列表
        List<String> fileUrlList = new ArrayList<>();

        Integer fileNo = 0;
        // 循环上传文件
        for (MultipartFile multipartFile : fileArray) {

            fileNo = fileNo + 1;

            if (multipartFile == null) {
                //System.out.printf(" file upload null: %d ;", fileNo);
                continue;
            }

            byte[] bytes = new byte[0];
            try {
                // 获取byte数组
                bytes = multipartFile.getBytes();
                System.out.printf(" fileNo: %d size: %d ;", fileNo, bytes.length);

            } catch (IOException e) {
                e.printStackTrace();
            }
            // md5加密
            String md5Hex = DigestUtils.md5Hex(bytes);
            // 查询是否上传过此文件
            FileInfo fileInfo = fileInfoMapper.selectById(md5Hex);
            // 存在则获取文件url
            if (fileInfo == null) {
                // 文件后缀
                String originalFilename = multipartFile.getOriginalFilename();

                System.out.printf(" originalFilename: %s ;", originalFilename);

                assert originalFilename != null;
                String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

                //System.out.printf(" size: %d ;", multipartFile.getSize());

                // 文件信息对象
                fileInfo = new FileInfo(md5Hex, suffix);

                // 文件对象
                File file = fileInfo.getFile();

                // 上传文件
                try {
                    multipartFile.transferTo(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                // 插入文件信息
                fileInfoMapper.insert(fileInfo);

                String fileExtentionName = ImageUtil.getFileExtention(originalFilename);

                System.out.printf(" fileExtentionName: %s ;", fileExtentionName);

                if (ImageUtil.isImage(fileExtentionName)) {
                    BufferedImage sourceImg = null;
                    try {
                        sourceImg = ImageIO.read(new FileInputStream(file));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (sourceImg == null) {
                        continue;
                    }

                    Image src = Toolkit.getDefaultToolkit().getImage(file.getPath());
                    BufferedImage image = this.toBufferedImage(src);
                    BufferedImage imageBig = null;
                    BufferedImage imageSmall = null;

                    if ((float) sourceImg.getWidth() * 0.5625 <= (float) sourceImg.getHeight()) {
                        try {
                            imageBig = ImageUtil.createNewPicture(480, 9600, image);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            imageBig = ImageUtil.createNewPicture(5400, 270, image);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    String url = fileInfo.getFilePath() + fileInfo.getFileName() + "-thumbnail" + fileInfo.getFileSuffix();
                    //System.out.printf(" url: %s ;",url);
                    File outfile = new File(FileInfo.DEST_PATH + url);
                    try {
                        ImageIO.write(imageBig, fileExtentionName, outfile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if ((float) sourceImg.getWidth() * 0.5625 <= (float) sourceImg.getHeight()) {
                        try {
                            imageSmall = ImageUtil.createNewPicture(320, 6400, image);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            imageSmall = ImageUtil.createNewPicture(3600, 180, image);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    url = fileInfo.getFilePath() + fileInfo.getFileName() + ".thumbnail" + fileInfo.getFileSuffix();
                    outfile = new File(FileInfo.DEST_PATH + url);
                    try {
                        ImageIO.write(imageSmall, fileExtentionName, outfile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } else if (ImageUtil.isImageOthers(fileExtentionName)) {

                    BufferedImage sourceImg = null;
                    try {
                        sourceImg = ImageIO.read(new FileInputStream(file));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (sourceImg == null) {
                        continue;
                    }

                    //压缩文件
                    //路径格式为前后都不需要\\所以都去掉
                    String path = fileInfo.getFilePath().substring(1, fileInfo.getFilePath().length() - 1);
                    path = path.replace("/", "//");
                    //System.out.printf(" path: %s ;",path);

                    ////文件格式为前面不需要\\所以去掉
                    String originalFile = fileInfo.getFileUrl().substring(1);
                    originalFile = originalFile.replace("/", "//");
                    //System.out.printf(" originalFile: %s ;",originalFile);

                    if ((float) sourceImg.getWidth() * 0.5625 <= (float) sourceImg.getHeight()) {
                        try {
                            ImageUtil.createSmallPicture(320, 6400, path, originalFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            ImageUtil.createBigPicture(480, 9600, path, originalFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            ImageUtil.createSmallPicture(3600, 180, path, originalFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            ImageUtil.createBigPicture(5400, 270, path, originalFile);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

            fileUrlList.add(fileInfo.getFileUrl());
        }

        if (fileUrlList.isEmpty()) {
            return null;
        }

        return StringUtils.join(fileUrlList.toArray(), ";");
    }


    public BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }
        // This code ensures that all the pixels in the image are loaded
        image = new ImageIcon(image).getImage();
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            int transparency = Transparency.OPAQUE;
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparency);
        } catch (HeadlessException e) {
            // The system does not have a screen
        }
        if (bimage == null) {
            // Create a buffered image using the default color model
            int type = BufferedImage.TYPE_INT_RGB;
            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }
        // Copy image to buffered image
        Graphics g = bimage.createGraphics();
        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return bimage;
    }
}

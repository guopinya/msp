package com.project.common.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 文件信息表
 *
 * @author zhuyifa
 * @since 2019-06-28
 */
public class FileInfo implements Serializable {

    public static final String PREFIX = "/file/";
    public static final String DEST_PATH = System.getProperty("user.dir");
    private static final long serialVersionUID = 1L;
    /**
     * 文件ID
     */
    @TableId
    private String fileId;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件路径
     */
    private String filePath;

    /**
     * 文件后缀
     */
    private String fileSuffix;

    public FileInfo() {
    }

    public FileInfo(String fileId, String suffix) {
        this.fileId = fileId;
        this.fileName = IdWorker.getIdStr();
        DateTimeFormatter yyyyMMdd = DateTimeFormatter.ofPattern("yyyyMMdd/");
        this.filePath = PREFIX + yyyyMMdd.format(LocalDateTime.now());
        this.fileSuffix = suffix;
    }

    /**
     * 获取文件对象
     *
     * @return
     */
    public File getFile() {
        File file = new File(DEST_PATH + getFileUrl());
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }

    /**
     * 获取文件url
     *
     * @return
     */
    public String getFileUrl() {
        return filePath + fileName + fileSuffix;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileSuffix() {
        return fileSuffix;
    }

    public void setFileSuffix(String fileSuffix) {
        this.fileSuffix = fileSuffix;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "fileId=" + fileId +
                ", fileName=" + fileName +
                ", filePath=" + filePath +
                ", fileSuffix=" + fileSuffix +
                "}";
    }
}

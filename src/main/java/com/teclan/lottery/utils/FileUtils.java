package com.teclan.lottery.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
    private final static Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

    /**
     * @author Teclan 向文件追加内容，如果文件不存在，创建文件
     * @param fileName
     *            文件路径
     * @param content
     *            文件内容
     */
    public static void write2File(String fileName, String content) {
        FileWriter writer = null;
        try {
            creatIfNeed(fileName);
            writer = new FileWriter(fileName, true);
            writer.write(content);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                LOGGER.error(e.getMessage(), e);
            }

        }
    }

    public static void creatIfNeed(String fileName) {
        try {
            File parentFile = new File(fileName).getParentFile();
            if (parentFile != null) {
                parentFile.mkdirs();
            }
            new File(fileName).createNewFile();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

}

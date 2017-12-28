package cn.gtapc.util.common;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

/**
 * 文件（java.io.File）工具類
 *
 * @author 斷亂
 */
public class FileUtils {

    private FileUtils() {
    }

    /**
     * Struts 文件上传
     *
     * @param file         文件
     * @param fileFilePath 文件路徑
     * @param fileFileName 文件名
     * @param fileFileType 文件類型
     * @param readNumber
     * @throws IOException
     */
    public static boolean strutsUpload(File file, String fileFileName,
                                       String fileFileType, String fileFilePath, int readNumber)
            throws IOException {
        InputStream is = null;
        OutputStream os = null;
        // 参数不完整不上传
        if (file != null && StringUtils.isNotEmpty(fileFileName)
                && StringUtils.isNotEmpty(fileFileType)
                && StringUtils.isNotEmpty(fileFilePath)) {
            // 上傳
            is = new FileInputStream(file);
            os = new FileOutputStream(new File(fileFilePath, fileFileName));
            // 字節流
            byte[] b = new byte[readNumber];
            // 循環寫入本地文件
            while (is.read(b, 0, b.length) != -1) {
                os.write(b);
            }
            close(os, is);
            return true;
        }
        return false;
    }

    /**
     * 根據目錄和文件名保存文件
     *
     * @param file       文件
     * @param savePath   上傳路徑
     * @param readNumber 每次循環讀取字節數量
     * @throws IOException
     */
    public static boolean save(File file, String savePath, int readNumber)
            throws IOException {
        // 創建上傳目錄的 File 對象
        File uploadFile = new File(savePath);
        // 如果目錄不存在或不是一個目錄則創建目錄
        if (!uploadFile.exists() || !uploadFile.isDirectory()) {
            uploadFile.mkdir();
        }
        InputStream is = null;
        OutputStream os = null;
        // 参数不完整不上传
        if (file != null) {
            // 上傳圖片
            is = new FileInputStream(file);
            os = new FileOutputStream(uploadFile);
            // 字節流
            byte[] b = new byte[readNumber];
            // 循環寫入本地文件
            while (is.read(b, 0, b.length) != -1) {
                os.write(b);
            }
            close(os, is);
            return true;
        }
        return false;
    }

    /**
     * 根據目錄和文件名保存文件
     *
     * @param inputStream 文件輸入流
     * @param savePath    上傳路徑
     * @param readNumber  每次循環讀取字節數量
     * @throws IOException
     */
    public static boolean save(InputStream inputStream, String savePath,
                               int readNumber) throws IOException {
        // 創建上傳目錄的 File 對象
        File uploadFile = new File(savePath);
        // 如果目錄不存在或不是一個目錄則創建目錄
        if (!uploadFile.exists() || !uploadFile.isDirectory()) {
            uploadFile.mkdir();
        }
        InputStream is = null;
        OutputStream os = null;
        // 参数不完整不上传
        if (inputStream != null) {
            // 上傳圖片
            is = inputStream;
            os = new FileOutputStream(uploadFile);
            // 字節流
            byte[] b = new byte[readNumber];
            // 循環寫入本地文件
            while (is.read(b, 0, b.length) != -1) {
                os.write(b);
            }
            close(os, is);
            return true;
        }
        return false;
    }

    /**
     * 根據字符讀取文件（文本），默認每次循環讀取 1024 字符，不去除文本中的“\r”
     *
     * @param path 文件路徑
     * @return
     * @throws IOException
     */
    public static StringBuilder readByChar(String path) throws IOException {
        StringBuilder fileContent = new StringBuilder();
        char[] tempChars = new char[1024];
        // 獲取文件對象
        File file = new File(path);
        // 獲取文件的字節輸入流對象
        FileInputStream fileInputStream = new FileInputStream(file);
        // 獲取讀取字符流的抽象類
        Reader reader = new InputStreamReader(fileInputStream);
        // 臨時 Char
        int readNumber = 0;
        // 如果能從 Reader 中讀取到數據（大於 0 小於需滿足的數量）
        while ((readNumber = reader.read(tempChars)) != -1) {
            fileContent.append(new String(tempChars, 0, readNumber));
        }
        if (reader != null) {
            reader.close();
        }
        return fileContent;
    }

    public static void close(OutputStream outputStream, InputStream inputStream)
            throws IOException {
        // 關閉對象
        if (outputStream != null) {
            outputStream.close();
        }
        if (inputStream != null) {
            inputStream.close();
        }
    }

    /**
     * 根據字符讀取文件（文本）
     *
     * @param path       文件路徑
     * @param readNumber 每次循環讀取字符數量
     * @param isFormat   去除字符中“\r”
     * @return
     * @throws IOException
     */
    public static StringBuilder readByChar(String path, int readNumber,
                                           boolean isFormat) throws IOException {
        StringBuilder fileContent = new StringBuilder();
        char[] tempChars = new char[readNumber];
        // 獲取文件對象
        File file = new File(path);
        // 獲取文件的字節輸入流對象
        FileInputStream fileInputStream = new FileInputStream(file);
        // 獲取讀取字符流的抽象類
        Reader reader = new InputStreamReader(fileInputStream);
        boolean isWrap = true;
        // 如果能從 Reader 中讀取到數據（大於 0 小於需滿足的數量）
        while ((readNumber = reader.read(tempChars)) != -1) {
            if (isFormat) {
                isWrap = true;
                // 循環讀取到的字符數組，看讀取到的字符數組中是否有“\r”
                for (int i = 0; i < readNumber; i++) {
                    if (tempChars[i] != '\r') {
                        isWrap = false;
                    }
                }
            }
            // 不去 \r 以下代码一行解决
            // fileContent.append(new String(tempChars, 0, readNumber));

            // 如果臨時 Char 達到需滿足的數量且沒有換行
            if (readNumber == tempChars.length && isWrap) {
                fileContent.append(String.valueOf(tempChars));
            } else {
                // 根據讀取到的字節數循環
                for (int i = 0; i < readNumber; i++) {
                    if (isFormat && tempChars[i] == '\r') {
                        continue;
                    }
                    fileContent.append(tempChars[i]);
                }
            }
        }
        if (reader != null) {
            reader.close();
        }
        return fileContent;
    }

    /**
     * 根据 url 保存文件
     *
     * @param url
     * @param readNumber
     * @param filePath
     */
    public static void save(String url, int readNumber, String filePath) {
        try {
            URLConnection urlConnection = new URL(url).openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            byte[] bytes = new byte[readNumber];
            int count = 0;
            while ((count = inputStream.read(bytes)) > 0) {
                fileOutputStream.write(bytes, 0, count);
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File saveByLocalOrUrl(String filePathOrUrl,
                                        int urlFileReadNumber, String urlFilePath) {
        File newFile = null;
        if ("http".equals(filePathOrUrl.substring(0, 4).toLowerCase())) {
            FileUtils.save(filePathOrUrl, urlFileReadNumber, urlFilePath);
            newFile = new File(urlFilePath);
        } else {
            newFile = new File(filePathOrUrl);
        }
        return newFile;
    }

    /**
     * 刪除文件或文件夾
     *
     * @return
     */
    public static boolean delete(File file) {
        // 若目錄不存在或不是一個目錄
        if (file.exists()) {
            if (file.isDirectory()) {
                // 刪除文件夾下的所有文件(包括子目錄)
                File[] files = file.listFiles();
                for (File file1 : files) {
                    if (file1.isFile()) {
                        if (!deleteFile(file1.getPath())) {
                            return false;
                        }
                    } else {
                        // 遞歸
                        if (!delete(file1.getPath())) {
                            return false;
                        }
                    }
                }
                return file.delete();
            } else {
                return deleteFile(file);
            }
        }
        return false;
    }

    /**
     * 刪除文件或文件夾
     *
     * @param path
     * @return
     */
    public static boolean delete(String path) {
        File file = new File(path);
        // 若目錄不存在或不是一個目錄
        if (file.exists()) {
            if (file.isDirectory()) {
                // 刪除文件夾下的所有文件(包括子目錄)
                File[] files = file.listFiles();
                for (File file1 : files) {
                    if (file1.isFile()) {
                        if (!deleteFile(file1.getPath())) {
                            return false;
                        }
                    } else {
                        // 遞歸
                        if (!delete(file1.getPath())) {
                            return false;
                        }
                    }
                }
                return file.delete();
            } else {
                return deleteFile(path);
            }
        }
        return false;
    }

    /**
     * 根據文件路徑刪除文件
     *
     * @param path
     * @return
     */
    public static boolean deleteFile(String path) {
        File file = new File(path);
        // File 對象為文件且不為空
        if (file.isFile() && file.exists()) {
            file.delete();
            return true;
        }
        return false;
    }

    /**
     * 根據文件路徑刪除文件
     *
     * @return
     */
    public static boolean deleteFile(File file) {
        // File 對象為文件且不為空
        if (file.isFile() && file.exists()) {
            file.delete();
            return true;
        }
        return false;
    }

    /**
     * 生成臨時文件路徑
     *
     * @param filePath 源路徑
     * @return
     */
    public static String genTempPath(String filePath) {
        return filePath.substring(0, filePath.lastIndexOf(".")) + "_temp_"
                + UUID.randomUUID() + filePath.substring(filePath.lastIndexOf("."));
    }
}

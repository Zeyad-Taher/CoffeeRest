package com.example.coffeerest.utility;

import java.io.*;
import java.nio.file.*;

import org.springframework.web.multipart.MultipartFile;

/**
 * Utility class for uploading files to the server local storage
 */
public class FileUploadUtil {

    /**
     * Utility function for uploading files to the server local storage
     *
     * @param uploadDir     String path to the directory to upload to
     * @param fileName      String contains file name
     * @param multipartFile Multipart file that references the file we wish to upload
     * @return false if failed, true if upload was successful
     */
    public static boolean saveFile(String uploadDir, String fileName,
                                   MultipartFile multipartFile) {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            try {
                Files.createDirectories(uploadPath);
            } catch (IOException io) {
                io.printStackTrace();
                return false;
            }
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        }
        return true;
    }
}
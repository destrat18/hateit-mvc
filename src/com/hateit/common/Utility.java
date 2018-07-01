package com.hateit.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

public class Utility {
    public static boolean isEmail(String s) {
        return s.matches("^(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");
    }

    public static boolean hasLetter(String s) {
        return s.matches(".*[a-zA-Z]+.*");
    }

    public static boolean hasNumber(String s) {
        return s.matches(".*[0-9]+.*");
    }

    public static String getUniqueId()
    {
        return UUID.randomUUID().toString();
    }

    public static String uploadFile(MultipartFile file )
    {
        if (file != null && !file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                String rootPath = "/home/des/.hateit/image";
                File dir = new File(rootPath);
                if (!dir.exists())
                    dir.mkdirs();
                // Create the file on server
                String fileName = Utility.getUniqueId() + ".jpg";
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + fileName);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                return fileName;
            } catch (Exception e) {
                throw new HateItException("/profile", "پروفایل", e.toString());
            }
        }
        return null;
    }

}

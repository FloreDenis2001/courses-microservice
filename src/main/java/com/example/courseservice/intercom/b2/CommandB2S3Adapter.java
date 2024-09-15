package com.example.courseservice.intercom.b2;

import com.example.courseservice.intercom.b2.exceptions.UploadFileException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CommandB2S3Adapter {


    private final CommandB2S3 commandB2S3;

    public CommandB2S3Adapter(CommandB2S3 commandB2S3) {
        this.commandB2S3 = commandB2S3;
    }

    /**
     * Metoda pentru a încărca fișierul folosind serviciul extern.
     *
     * @param file      fișierul de încărcat
     * @param fileName  numele fișierului care va fi generat
     * @return URL-ul fișierului încărcat
     * @throws RuntimeException dacă fișierul nu poate fi încărcat
     */
    public String uploadFile(MultipartFile file, String fileName) {
        ResponseEntity<String> response = commandB2S3.uploadFile(file, fileName);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }

        throw new UploadFileException("Error while uploading file. Status code: " + response.getStatusCode());
    }


}

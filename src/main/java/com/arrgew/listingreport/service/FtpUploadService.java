package com.arrgew.listingreport.service;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FtpUploadService {

    @Value("${ftpUsername}")
    private String ftpUsername;
    @Value("${ftpPassword}")
    private String ftpPassword;
    @Value("${ftpPort}")
    private String ftpPort;
    @Value("${ftpHost}")
    private String ftpHost;
    @Value("${ftpDir}")
    private String ftpDir;


    public void upload(File file){
        FTPClient client = new FTPClient();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            client.connect(ftpHost,Integer.parseInt(ftpPort));
            client.login(ftpUsername, ftpPassword);
            InputStream inputStream = new FileInputStream("report.json");
            client.storeFile(ftpDir+"report.json",inputStream);
            System.out.println("report.json uploaded to the ftp server");
            inputStream.close();
            client.logout();

        } catch (
                IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

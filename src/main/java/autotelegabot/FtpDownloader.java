package autotelegabot;

import java.io.*;
import java.nio.file.Files;


import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FtpDownloader {
    static Logger logger
            = LoggerFactory.getLogger(FtpDownloader.class);

    public static void ftpDownlodFiles() throws IOException {

        // create a instance of FtpConnector
        FtpConnector ftpConnector = new FtpConnector();

        // get ftp client object.
        FTPClient ftpClient = ftpConnector.connect();

        // list all the files which will be downloaded.
        FTPFile[] ftpFiles = ftpClient.listFiles("/123/reportBank (XLS).xls");

        // set downloading dir where all the files will be
        // stored on local directory.
        String downloading_dir
                = "";

        for (FTPFile file : ftpFiles) {

           File fileObj = new File(downloading_dir

                    + file.getName());
         //   Files.createFile(fileObj.toPath());
//
            try (OutputStream outputStream
                         = new BufferedOutputStream(
                    new FileOutputStream(fileObj))) {

                // ftpclient.retrieveFile will get the file
                // from Ftp server and write it in
                // outputStream.
                   boolean isFileRetrieve
                           = ftpClient.retrieveFile("/123/"+file.getName(),
                           outputStream);
                   System.out.println(isFileRetrieve);
                   outputStream.close();

               logger.info("{} file is downloaded : {}",
                        file.getName(), isFileRetrieve);

            }

        }
    }
}



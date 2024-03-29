package autotelegabot;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FtpConnector {

    Logger logger
            = LoggerFactory.getLogger(FtpConnector.class);

    public FTPClient connect() throws IOException
    {
        HashMap<String,String> map = new HashMap<>();
        map = FtpKey.getFtpKey();


        // Create an instance of FTPClient
        FTPClient ftpClient = new FTPClient();
        try {
            // establish a connection with specific host and
            // port.
            ftpClient.connect(map.get("host"), Integer.parseInt(map.get("port")));

            int replyCode = ftpClient.getReplyCode();

            if (!FTPReply.isPositiveCompletion(replyCode)) {
                logger.info (

                        "Operation failed. Server reply code: "+
                         replyCode

                );
                ftpClient.disconnect();
            }

            // login to ftp server with username and
            // password.
            boolean success
                    = ftpClient.login(map.get("login"), map.get("password"));
            if (!success) {
                ftpClient.disconnect();
            }
            // assign file type according to the server.
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE );
            ftpClient.enterLocalPassiveMode();

            // change specific directory of ftp server from
            // you want to download files.
//            boolean changedRemoteDir
//                    = ftpClient.changeWorkingDirectory(
//                    "/home/testuser/directory");
//            if (!changedRemoteDir) {
//                logger.info(
//                        "Remote directory not found."
//                );
//            }
        }
        catch (UnknownHostException E) {
            logger.info(
                    "No such ftp server"
            );
        }
        catch (IOException e) {
            logger.info(e.getMessage());
        }
        return ftpClient;
    }
}

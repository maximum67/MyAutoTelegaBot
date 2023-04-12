package autotelegabot;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.logging.Logger;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

public class FtpConnector {

//    Logger logger
//            = LoggerFactory.getLogger(FtpConnector.class);

    public FTPClient connect() throws IOException
    {
        // Create an instance of FTPClient
        FTPClient ftpClient = new FTPClient();
        try {
            // establish a connection with specific host and
            // port.
            ftpClient.connect("localhost", 21);

            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
//                logger.info(
//                        "Operation failed. Server reply code: "
//                                + replyCode);
                ftpClient.disconnect();
            }

            // login to ftp server with username and
            // password.
            boolean success
                    = ftpClient.login("aanki99", "Aspirin15");
            if (!success) {
                ftpClient.disconnect();
            }
            // assign file type according to the server.
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();

            // change specific directory of ftp server from
            // you want to download files.
            boolean changedRemoteDir
                    = ftpClient.changeWorkingDirectory(
                    "/home/testuser/directory");
            if (!changedRemoteDir) {
//                logger.info("Remote directory not found.");
            }
        }
        catch (UnknownHostException E) {
//            logger.info("No such ftp server");
        }
        catch (IOException e) {
//            logger.info(e.getMessage());
        }
        return ftpClient;
    }
}

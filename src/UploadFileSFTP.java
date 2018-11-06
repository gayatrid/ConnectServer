import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
/**
 * 
 * @author srikanth
 *
 */
public class UploadFileSFTP {
 
 public static void main(String[] args) throws Exception {
 
  JSch jsch = new JSch();
        Session session = null;
        try {
            session = jsch.getSession("adm_sgovada","sjgemappdevn05", 22);
            //session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword("|oaZmK_u#E8w");
            session.connect();
            
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;
            sftpChannel.put("C:/Temp/test.txt", "/tmp/test.txt");  
            sftpChannel.exit();
            session.disconnect();
        } catch (JSchException e) {
            e.printStackTrace();  
        } catch (SftpException e) {
            e.printStackTrace();
        }
 	}
}
package com.connect.common.util;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.connect.common.domain.User;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.jcraft.jsch.UserInfo;

public class ConnectionUtil {
	
	@Autowired
	private JSch jSch;
	
	private static String password;
	
 private static final String INTERNAL_FILE="Records.pdf";
	 
	 @Autowired
	 ServletContext context;


	Session session;
	
	public boolean connect(User user) {
		try {
			password=user.getPassword();
			session = jSch.getSession(user.getUserName(), user.getServerName(), 22);
			UserInfo ui = new MyUserInfo();
			session.setUserInfo(ui);
			session.connect();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String execute(String command) {
		String returnValue = "";
		try {
			Channel channel = session.openChannel("exec");
			((ChannelExec)channel).setPty(true);
			((ChannelExec) channel).setCommand(command);
			channel.setInputStream(null);
			((ChannelExec) channel).setErrStream(System.err);
			InputStream in = channel.getInputStream();
			channel.connect();
			byte[] tmp = new byte[1024];
			int exitCode=0;
			while (true) {
				while (in.available() > 0) {
					int i = in.read(tmp, 0, 1024);
					if (i < 0){
						break;
					}
					returnValue = new String(tmp, 0, i);
					exitCode++;
				}
				if (channel.isClosed()|| exitCode>0) {
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
			channel.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnValue;
	}
	
	public static class MyUserInfo implements UserInfo {

		public String getPassword() {
			return password;
		}

		public boolean promptYesNo(String str) {
			str = "Yes";
			return true;
		}

		public String getPassphrase() {
			return null;
		}

		public boolean promptPassphrase(String message) {
			return true;
		}

		public boolean promptPassword(String message) {
			return true;
		}

		public void showMessage(String message) {

		}

	}
	
	public void sftpPut(User user) {
		try {
	           
	            Channel channel = session.openChannel("sftp");
	            channel.connect();
	            ChannelSftp sftpChannel = (ChannelSftp) channel;
	            System.out.println(user.getFile().getOriginalFilename());
	            try {
					sftpChannel.put(user.getFile().getInputStream(),user.getFile().getOriginalFilename());
				} catch (IOException e) {
					e.printStackTrace();
				}
	            sftpChannel.exit();
	            channel.disconnect();
	        } catch (JSchException e) {
	            e.printStackTrace();  
	        } catch (SftpException e) {
	            e.printStackTrace();
	        }
	}

	public JSch getjSch() {
		return jSch;
	}

	public void setjSch(JSch jSch) {
		this.jSch = jSch;
	}

}

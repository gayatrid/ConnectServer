package com.connect.common.util;
import java.io.*;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

public class Exec {
	static String passwd = null;

	public static void main(String[] arg) {
		try {
			BufferedReader br = null;
			JSch jsch = new JSch();
			String host = null;
			String user = null;
			host="linuxzoo.net";
			user="root";
			passwd="secure";
			Session session = jsch.getSession(user, host, 22); 
			UserInfo ui = new MyUserInfo();
			session.setUserInfo(ui);
			session.connect();
		//String command = "ping -c 2 localhost"; 
			//String command = "ping -c 2 www.google.com";
			String command = "telnet localhost111 22";
			//String command = "ls";
			//String command ="sdfdf";
			System.out.print("Connected successfuly\n");
			Channel channel = session.openChannel("exec");
			((ChannelExec)channel).setPty(true);
			((ChannelExec) channel).setCommand(command);
			channel.setInputStream(null);
			((ChannelExec) channel).setErrStream(System.err);
			InputStream in = channel.getInputStream();
			channel.connect();
			byte[] tmp = new byte[1024];
			String returnValue="";
			while (true) {
				while (in.available() > 0) {
					int i = in.read(tmp, 0, 1024);
					if (i < 0){
						break;
					}
					returnValue = new String(tmp, 0, i);
					System.out.println("returnValue"+returnValue);
					
					String  result="pass";
					if(!returnValue.contains("bytes from")){
						result ="Fail";
					}
					System.out.println(result);
					
				}
				if (channel.isClosed()) {
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (Exception ee) {
					ee.printStackTrace();
				}
			}
				/*while (true) {
					System.out.println("channel.isClosed()"+channel.isClosed());
					System.out.println(channel.getExitStatus());
					int inAvailable=in.available();
					System.out.println("in.available()="+inAvailable);
					while (in.available() > 0) {
						int i = in.read(tmp, 0, 1024);
						System.out.println("i==="+i);
						if (i < 0){
							break;	
						}
						String value =new String(tmp, 0, i);
						System.out.println("value===="+value);
						tmpValue=1;
						
					}
					
					System.out.println("channel.isClosed()"+channel.isClosed());
					if (channel.isClosed() || tmpValue==1) {
						break;
					}
					try {
						Thread.sleep(1000);
					} catch (Exception ee) {
						ee.printStackTrace();
					}
				}*/
				channel.disconnect();
				session.disconnect();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	public static class MyUserInfo implements UserInfo {

		public String getPassword() {
			return passwd;
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
}
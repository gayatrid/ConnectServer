package com.connect.common.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.connect.common.domain.Display;
import com.connect.common.domain.User;
import com.connect.common.util.CommonUtil;
import com.connect.common.util.ConnectionUtil;

public class ConnectBL {
	@Autowired
	private ConnectionUtil connectionUtil;

	public ConnectionUtil getConnectionUtil() {
		return connectionUtil;
	}

	public void setConnectionUtil(ConnectionUtil connectionUtil) {
		this.connectionUtil = connectionUtil;
	}

	public boolean connectToserver(User user) {
		return connectionUtil.connect(user);
	}

	public List<Display> executeShellCommands(User user) {
		String commandValue = user.getShellCommand();
		List<Display> listOfOutput = new ArrayList<Display>();
		List<String> listOfCommands = new ArrayList<String>();
		if (commandValue != null && !commandValue.equals("")) {
			listOfCommands = CommonUtil.splitString(commandValue);
		}else{
			listOfCommands = getFileContent(user);
		}
		for (String command : listOfCommands) {
			Display display = new Display();
			String returnValue =connectionUtil.execute(command);
			System.out.println("result"+returnValue);//PING localhost (127.0.0.1) 56(84) bytes of data.
			String  result="";
			if(command.toLowerCase().contains("wget") || command.toLowerCase().contains("ping") ||
					command.toLowerCase().contains("telnet")){
				if(!returnValue.contains("bytes from") && !returnValue.toLowerCase().contains("connected")){
					result ="Fail";
				}else{
					result="Pass";
				}
			}
			
			display.setResult(result);
			display.setCommand(command);
			display.setOutput(returnValue);
			
			listOfOutput.add(display);
		}
		
		
		return listOfOutput;
	}
	
	public  String importCertificate(User user) {
		connectionUtil.sftpPut(user);
		String alias =user.getAlias();
		String password =  user.getPassphrase();
		String jksFilePath =user.getCustomeKeyStore();
		String certificate  ="$HOME/"+user.getFile().getOriginalFilename();
		System.out.println("certificate"+certificate);
		String keyTool_Home ="/gembin/oracle/jdk/jrockit-jdk1.6.0_151/bin";
		String shellCommand = keyTool_Home+"/keytool -importcert -noprompt -alias "+alias+" -keystore "+jksFilePath+" -file "+certificate+" -storepass "+password;
		System.out.println("ShellCommand"+shellCommand);
		String returnValue= connectionUtil.execute(shellCommand);
		System.out.println("result"+returnValue);
		return returnValue; 
		
	}

	private List<String> getFileContent(User user) {
		List<String> returnListOfString = new ArrayList<String>();
		try {
			MultipartFile file = user.getFile();
			InputStream in = file.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String line;
			while ((line = reader.readLine()) != null) {
				splitURLAndSetCommand(line, returnListOfString);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return returnListOfString;

	}
	
	private List<String> splitURLAndSetCommand(String url,List<String> listofCommands){
	try{
		URL aURL = new URL(url);
		listofCommands.add("ping\t"+aURL.getHost());
		int port=0;
		if(aURL.getPort()!=-1){
			port=aURL.getPort();
		}else{
			if(aURL.getProtocol().equals("http")){
				port =80 ;
				listofCommands.add("wget \t"+url);
			}else if (aURL.getProtocol().equals("https")){
				port =443 ;
				listofCommands.add("wget \t"+url);
			}
		}
		listofCommands.add("telnet \t"+aURL.getHost()+"\t"+port);
	}catch(Exception e){
		e.printStackTrace();
	}
		return listofCommands;
		
	}

}

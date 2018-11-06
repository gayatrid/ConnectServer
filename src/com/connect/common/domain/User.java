package com.connect.common.domain;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * 
 * @author gaytari devi
 *
 */
public class User {
	private String serverName;

	private String userName;

	private String password;

	private String shellCommand;
	private String shellCommandParameter;
	private String displayResult;
	MultipartFile file;
	
	List<Display> listOfOutput;
	
	//------------------ import ket stroe
	private String pageContent;
	
	private String keyStore;
	
	private String customeKeyStore;
	
	private String alias;
	private String sudoOracle;
	
	private String passphrase;
	
	
	

	public String getSudoOracle() {
		return sudoOracle;
	}

	public void setSudoOracle(String sudoOracle) {
		this.sudoOracle = sudoOracle;
	}

	public String getKeyStore() {
		return keyStore;
	}

	public void setKeyStore(String keyStore) {
		this.keyStore = keyStore;
	}

	public String getCustomeKeyStore() {
		return customeKeyStore;
	}

	public void setCustomeKeyStore(String customeKeyStore) {
		this.customeKeyStore = customeKeyStore;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getPassphrase() {
		return passphrase;
	}

	public void setPassphrase(String passphrase) {
		this.passphrase = passphrase;
	}

	public String getPageContent() {
		return pageContent;
	}

	public void setPageContent(String pageContent) {
		this.pageContent = pageContent;
	}

	public List<Display> getListOfOutput() {
		return listOfOutput;
	}

	public void setListOfOutput(List<Display> listOfOutput) {
		this.listOfOutput = listOfOutput;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getShellCommand() {
		return shellCommand;
	}

	public String getShellCommandParameter() {
		return shellCommandParameter;
	}

	public String getDisplayResult() {
		return displayResult;
	}

	public void setShellCommand(String shellCommand) {
		this.shellCommand = shellCommand;
	}

	public void setShellCommandParameter(String shellCommandParameter) {
		this.shellCommandParameter = shellCommandParameter;
	}

	public void setDisplayResult(String displayResult) {
		this.displayResult = displayResult;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
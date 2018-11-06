package com.connect.common.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.connect.common.dao.ConnectBL;
import com.connect.common.domain.Display;
import com.connect.common.domain.User;
import com.connect.common.util.CreatePDF;
import com.itextpdf.text.Document;

@Controller
public class ConnectController {

	@Autowired
	private ConnectBL connectBL;
	User user;
	private static final String INTERNAL_FILE = "Records.pdf";

	@Autowired
	ServletContext context;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String viewConnect(Map<String, Object> model, HttpServletRequest request) {
		user = new User();
		model.put("userForm", user);
		// clean
		File pdfFile = new File(getFileName());
		if (pdfFile.exists()) {
			pdfFile.delete();
		}
		return "login";
	}

	@RequestMapping(value = "/connect", params = "connect", method = RequestMethod.POST)
	public String doConnect(@ModelAttribute("userForm") @Valid User userForm, BindingResult result,
			Map<String, Object> model) {
		user = userForm;
		String returnPage = "login";
		System.out.println("Result;----" + result.hasErrors());
		// if (!result.hasErrors()) {
		boolean connected = connectBL.connectToserver(userForm);
		if(user.getSudoOracle()!=null && !user.getSudoOracle().equals("")){
			user.setShellCommand("sudo su - oracle");
		}
		
		connectBL.executeShellCommands(user);
		System.out.println("connected" + connected);
		if (!connected) {
			model.put("connectMessage", "Provided details are not correct or server is down");
		}else{
			model.put("connectMessage", "success");
			returnPage = "SSLCert";
		}
		// }
		return returnPage;
	}


	@RequestMapping(value = "/displayCert", params = "certParam", method = RequestMethod.GET)
	public String displayCert(Map<String, Object> model, HttpServletRequest request) {
		user = new User();
		String page = request.getParameter("certParam");
		user.setPageContent(page);
		model.put("userForm", user);
		// clean
		File pdfFile = new File(getFileName());
		if (pdfFile.exists()) {
			pdfFile.delete();
		}
		
		System.out.println("-------------------" + request.getParameter("certParam"));
		if(page.equalsIgnoreCase("listkeystore")) {
			user.setShellCommand("");
			List<Display> listOfOutput=connectBL.executeShellCommands(user);
			model.put("listOfOutput", "true");
		}
		return "connectServer";
	}
	// --------------import certificate
	@RequestMapping(value = "/connect", params = "importAction", method = RequestMethod.POST)
	public String importCert(@ModelAttribute("userForm") @Valid User userForm,Map<String, Object> model) {
		user = userForm;
		String returnPage = "connectServer";
		String result = connectBL.importCertificate(userForm);
		System.out.println("connected" + result);
		model.put("result", result);
		return returnPage;
	}

	

	@RequestMapping(value = "/connect", params = "downloadPDF")
	public void downloadPDF(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			File pdfFile = new File(getFileName());
			InputStream is = new FileInputStream(pdfFile);
			IOUtils.copy(is, response.getOutputStream());
			response.setHeader("Content-Disposition", "attachment; filename=" + INTERNAL_FILE);
			response.flushBuffer();
			is.close();
		} catch (IOException ex) {
			throw new RuntimeException("IOError writing file to output stream");
		}
	}

	@RequestMapping(value = "/leftPage", method = RequestMethod.GET)
	public String displayPage(Map<String, Object> model, HttpServletRequest request) {
		user = new User();
		model.put("userForm", user);
		// clean
		File pdfFile = new File(getFileName());
		if (pdfFile.exists()) {
			pdfFile.delete();
		}
		return "left";
	}

	@RequestMapping(value = "/rightPage", method = RequestMethod.GET)
	public String displayRightPage(Map<String, Object> model, HttpServletRequest request) {
		user = new User();
		model.put("userForm", user);
		// clean
		File pdfFile = new File(getFileName());
		if (pdfFile.exists()) {
			pdfFile.delete();
		}
		return "connectServer";
	}

	private String getFileName() {
		return context.getRealPath("/WEB-INF/files/") + "\\" + INTERNAL_FILE;
	}

	public ConnectBL getConnectBL() {
		return connectBL;
	}

	public void setConnectBL(ConnectBL connectBL) {
		this.connectBL = connectBL;
	}

	

	/*@RequestMapping(value = "/connect", params = "executeCommand", method = RequestMethod.POST)
	public String doExecuteShellCommand(@ModelAttribute("userForm") @Valid User userForm, BindingResult result,
			Map<String, Object> model, HttpServletRequest request) {
		user = userForm;
		if (!result.hasErrors()) {
			List<Display> listOfOutput = connectBL.executeShellCommands(userForm);
			userForm.setListOfOutput(listOfOutput);
			model.put("connectMessage", "success");
			model.put("listOfOutput", "true");
			Document document = CreatePDF.createPDF(getFileName(), listOfOutput);
		}
		return "connectServer";
	}*/
}

/*
 * @Override protected ModelAndView handleRequestInternal(HttpServletRequest
 * request, HttpServletResponse response) throws Exception { //ModelAndView
 * model = new ModelAndView("HelloWorldPage"); //model.addObject("msg",
 * "hello world"); // return model;}
 */

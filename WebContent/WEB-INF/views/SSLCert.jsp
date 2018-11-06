<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Frames Test</title>
  <style>
   .menu {
      float:left;
      width:20%;
      height:900px;
      border:0px;
     }
    .mainContent {
      float:left;
      width:75%;
      height:900px;
      border:0px;
    }
  </style>
  <script type="text/javascript">
      function iframeLoaded() {
          var iFrameID = document.getElementById('leftFrame');
          if(iFrameID) {
                // here you can make the height, I delete it first, then I make it again
                iFrameID.height = "";
                iFrameID.height = iFrameID.contentWindow.document.body.scrollHeight + "px";
          }   
      }
    </script> 
</head>
<body>

  <iframe class="menu" src="./leftPage" name="leftFrame" id="leftFrame"></iframe>
  <iframe class="mainContent" src="./rightPage" name="rightFrame" scrolling="no"></iframe>
</body>
</html>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
.accordion {
    background-color: khaki;
    color: #444;
    cursor: pointer;
    padding: 15px;
    width: 100%;
    border: 2px;
    text-align: left;
    outline: none;
    font-size: 15px;
    transition: 0.4s;
    border-radius: 15px;
    border: 2px solid  	royalblue;
}

.active, .accordion:hover {
    background-color:peachpuff ; 
    border: 2px solid  	dodgerblue;
}

.panel {
    padding: 0 18px;
    display: none;
    background-color: moccasin;
    overflow: hidden;
}
a:link, a:visited {
    text-align: center;
    text-decoration: none;
    display: inline-block;
    border-radius: 15px;
    background-color: royalblue;
    padding: 8px;
    color: white
    
    
}
a:visited{

}

a:hover, a:active {
   background-color:peachpuff ;
   color: black 
   
}
li{
margin: 0 0 3px 0;
list-style-type:none
}
}
</style>
</head>
<body bgcolor="moccasin">

<h5>	You are now connected to <i>sjgemappdevn05</i></h5>

<button class="accordion">SSL Certificates</button>
<div class="panel">
	<ul>
	 	<li><a href="./displayCert?certParam=listkeystore" target="rightFrame">List KeyStores</a></li>
	  	<li><a href="./displayCert?certParam=ImportCert" target="rightFrame">Import Certificate</a></li>
	  <li> <a href="./displayCert?certParam=ImportCert" target="rightFrame">Export Certificate</a></li>
	  <li> <a href="./connect" target="rightFrame">View Certificate</a></li>
	  <li> <a href="./connect" target="rightFrame">Check Expiration</a></li>
  </ul>
</div>

<button class="accordion">Command Line Interface</button>
<div class="panel">
  <ul>
	 	<li><a href="./connect" target="rightFrame">open</a></li>
	 	</ul>
</div>



<script>
var acc = document.getElementsByClassName("accordion");
var i;

for (i = 0; i < acc.length; i++) {
    acc[i].addEventListener("click", function() {
        this.classList.toggle("active");
        var panel = this.nextElementSibling;
        if (panel.style.display === "block") {
            panel.style.display = "none";
        } else {
            panel.style.display = "block";
        }
    });
}
</script>

</body>
</html>
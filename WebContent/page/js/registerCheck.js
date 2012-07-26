function getXmlHttpRequest() {
	var xmlHttp = null;

	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlHttp = new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	return xmlHttp;
}

function userNameCheck() {
	var name = document.getElementById("name");
	if (name.value == null || name.value.trim().length == 0
			|| name.value.trim().length > 20) {
		alert("the size of the username should be at least 1 and not be longer than 20");
		name.value = "";
		name.focus();
		return false;
	}
	var xmlHttp = getXmlHttpRequest();
	if (xmlHttp != null) {
		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				if (xmlHttp.responseText == "success") {
					alert("congratulation! you can use it as your username");
					name.focus();
				}else{
					alert("sorry! please change to other characters");
					name.value = "";
					name.focus();
				}
			}
		};
		var method = "GET";
		var url = "user/userNameCheck?&name=" + name.value;

		xmlHttp.open(method, url, true);
		xmlHttp.send();
	}
}

function emailCheck() {
	var email = document.getElementById("email");
	if ((email.value == null) || (email.value.trim().length == 0)) {
		alert("email cannot be empty");
		email.focus();
		return false;
	}
	
	var xmlHttp = getXmlHttpRequest();
	if (xmlHttp != null) {
		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
				if (xmlHttp.responseText == "success") {
					alert("congratulations! you can use it as your email address");
					email.focus();
				}else{
					alert("sorry! please change to other email address");
					email.value = "";
					email.focus();
				}
			}
		};
		var method = "GET";
		var url = "user/emailCheck?email=" + email.value;
		xmlHttp.open(method, url, true);
		xmlHttp.send();
	}
	
}

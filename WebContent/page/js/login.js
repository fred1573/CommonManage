function login(){
	
	var name = document.getElementById("name");
	var password = document.getElementById("password");
	if ((name.value.length < 1) || (name.value.length > 20)) {
		alert("the size of the username should be at least 1 and not be longer than 20");
		name.value = "";
		name.focus();
		return false;
	}
	if ((password.length < 1) || (password.length > 10)) {
		alert("the size of the password should be at least 1 and not be longer than 20");
		document.getElementById(name).value = "";
		document.getElementById("name").focus();
		return false;
	}
	return true;
}
const validationForm=()=>{
const username = document.getElementById("email").value;
const password = document.getElementById("pass").value;
const conPassword = document.getElementById("conPass").value;
const errorField = document.getElementById("error_msg");
let errorMsg = "";

// Check if username is empty
if (username.trim() === "") {
    errorMsg += "Username is required.\n";
}

// Check if password is empty
if (password.trim() === "") {
    errorMsg += "Password is required.\n";
}

// Check password length (example validation)
if (password.length < 6) {
    errorMsg += "Password must be at least 6 characters long.\n";
}
if(password != conPassword){
	 errorMsg += "Password and confirm password is not matching";
}

// Display error message if any validation fails
if (errorMsg !== "") {
   errorField.textContent = errorMsg; // Set the error message
    errorField.style.display = "block"; // Show the error message
    return false;// Prevent form submission
}

return true;
}
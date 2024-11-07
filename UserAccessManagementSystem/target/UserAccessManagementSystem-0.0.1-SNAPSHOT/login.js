const validationForm=()=>{
const username = document.getElementById("email").value;
const password = document.getElementById("pass").value;
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

if (errorMsg !== "") {
   errorField.textContent = errorMsg; // Set the error message
    errorField.style.display = "block"; // Show the error message
    return false;// Prevent form submission
}

return true;
}/**
 * 
 */
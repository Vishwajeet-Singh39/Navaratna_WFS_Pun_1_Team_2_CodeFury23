/*
Navratan 

Admin add vendor - 
  Adds vendor to backend(json)

*/

var fs = require("fs");

function getFormDetails() {
    // Get the input elements by their ids
    var nameInput = document.getElementById("name");
    var emailInput = document.getElementById("email");
    var passwordInput = document.getElementById("password");
    var confirmPasswordInput = document.getElementById("confirm_password");
  
    // Create an object to store the form details
    var formDetails = {
      name: nameInput.value,
      email: emailInput.value,
      password: passwordInput.value,
      confirm_password: confirmPasswordInput.value
    };
  
    // Return the object
    return formDetails;
  }
  
  // A function to convert the form details object into a JSON file and download it
  function convertToJSON() {
    // Get the form details object
    var formDetails = getFormDetails();
  
    // Convert the object into a JSON string
    var jsonString = JSON.stringify(formDetails);
    var fs = require("fs");
    fs.writeFile("data.json", jsonString, function(err) {
      if (err) {
        console.log(err);
      } else {
        console.log("JSON file saved successfully.");
      }
    });
    


  
  }
  
  // Call the function
  convertToJSON();
  
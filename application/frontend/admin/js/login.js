/*
<!--
    Navratan 

    Admin login -
     It provides validation for login

-->
*/

var userCred = {}
function getUserDetails(){
    userCred['username'] = document.getElementById('username').value;
    userCred['password'] = document.getElementById('pass').value;
    console.log(userCred);
    hardCodedCreds(userCred)
}

function hardCodedCreds(userCred){
    if(userCred.username=="n" && userCred.password=='n'){
        validateUser({validUser:true});
    }else{
        validateUser({validUser:false});
    }
}

function validateUser(validation){
    if(validation.validUser){
         window.location.href = 'dashboard.html';
    }
    else{
         window.location.href = 'adminlogin.html'
    }
}

function getVisibility(){
    var img = document.getElementById("eye-img");
    var getPassword = document.getElementById("pass");
    if (getPassword.type === "password") {  
        getPassword.type = "text";  
        img.src = "../images/hide.png"
    } else {  
        getPassword.type = "password";
        img.src = "../images/view.png"
    }  
}
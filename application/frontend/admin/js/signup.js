

const phoneInputField = document.querySelector("#phone");
      const phoneInput = window.intlTelInput(phoneInputField, {
        initialCountry: "in",
        separateDialCode: true,
        utilsScript:
          "https://cdnjs.cloudflare.com/ajax/libs/intl-tel-input/17.0.8/js/utils.js",
      });

function PassGetVisibility(){
  // console.log(document.getElementsByClassName("password-div").querySelector("i"))
  var img = document.getElementById("pass-img")
  var getPassword = document.getElementById("pass");
  console.log(getPassword.value);
  if (getPassword.type === "password") {  
      getPassword.type = "text";  
      img.src = "../images/hide.png";
      } else {  
      getPassword.type = "password";
      img.src = "../images/view.png";
  }  
}

function CPassGetVisibility(){
  // console.log(document.getElementsByClassName("password-div").querySelector("i"))
  var img = document.getElementById("cpass-img")
  var getPassword = document.getElementById("cpass");
  console.log(getPassword.value);
  if (getPassword.type === "password") {  
      getPassword.type = "text";  
      img.src = "../images/hide.png";
      } else {  
      getPassword.type = "password";
      img.src = "../images/view.png";
  }  
}


// var form = document.getElementById('form-1');
//   var fname = document.getElementById("firstname");
//   var lname = document.getElementById("lastname");
//   var pass = document.getElementById("pass");
//   var cpass = document.getElementById("cpass");
//   var email = document.getElementById("floatingLabel");
//   var phone = document.getElementById("phone");
//   var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
//    var flag = 0;
// form.addEventListener("keyup", (e) => {
//   e.preventDefault();
//   console.log(flag)
//   if(e.target.id=="firstname"){
//     if(fname.value==""){
//       document.getElementById("fname-error").innerHTML = "*Please enter first name";
//       fname.style.borderBottom = "2px solid red";
//       fname.style.opacity = "0.6"
//     }
//     else{
//       document.getElementById("fname-error").innerHTML = " ";
//       fname.style.borderBottom ='1px solid #DDDDDD';
//       flag +=1;
//     }
//   }
//   else if(e.target.id=="lastname"){
//     if(lname.value==""){
//       document.getElementById("lname-error").innerHTML = "*Please enter last name";
//       lname.style.borderBottom = "2px solid red";
//       lname.style.opacity = "0.6"
//     }
//     else{
//       document.getElementById("lname-error").innerHTML = " ";
//       lname.style.borderBottom ='1px solid #DDDDDD';
//       flag +=1;
//     }
//   }
//    else if(e.target.id=="floatingLabel"){
//     if(email.value==""){
//       document.getElementById("email-error").innerHTML = "*Please enter email";
//       email.style.borderBottom = "2px solid red";
//       email.style.opacity = "0.6"
//     }else if(!email.value.match(mailformat)){
//       document.getElementById("email-error").innerHTML = "*Please enter valid email";
//       email.style.borderBottom = "2px solid red";
//       email.style.opacity = "0.6"
//     }
//     else{
//       document.getElementById("email-error").innerHTML = " ";
//       email.style.borderBottom ='1px solid #DDDDDD';
//       flag +=1;
//     }
//   }
// else if(e.target.id == "pass"){
//   if(pass.value==""){
//     document.getElementById("pass-error").innerHTML = "*Please enter password";
//     pass.style.borderBottom = "2px solid red";
//     pass.style.opacity = "0.6"
    
//   } else if(!pass.value.match(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{6,15}$/)){
//     var text = document.getElementById("pass-error");
//     // text.id = "pass-error-text";
//     text.innerHTML = "*Password should contain at least one lowercase letter, one uppercase letter, one numeric digit,one special character and characters between 8 to 15";
//     pass.style.borderBottom = "2px solid red";
//     pass.style.opacity = "0.6"
//   }
//   else{
//     document.getElementById("pass-error").innerHTML = " ";
//     pass.style.borderBottom ='1px solid #DDDDDD';
//     flag +=1;
//   }
//   }
//   else if(e.target.id =="cpass"){
//   if(cpass.value==""){
//     document.getElementById("cpass-error").innerHTML = "*Please enter password";
//     cpass.style.borderBottom = "2px solid red";
//     cpass.style.opacity = "0.6"
//   }else{
//       if(pass.value!=cpass.value){
//         document.getElementById("cpass-error").innerHTML = "*Password doesn't match";
//         cpass.style.borderBottom = "2px solid red";
//         cpass.style.opacity = "0.6"
//       }else{
//         document.getElementById("cpass-error").innerHTML = " ";
//         cpass.style.borderBottom ='1px solid #DDDDDD';
//         flag +=1;
//       }
//     }
//   }
//   else if(e.target.id=="phone"){
//     if(phone.value==""){
//       document.getElementById("mobile-error").innerHTML = "*Please enter mobile number";
//       phone.style.borderBottom = "2px solid red";
//       phone.style.opacity = "0.6"
//     }else if(phone.value.length!=10){
//       console.log('here')
//       document.getElementById("mobile-error").innerHTML = "*Please enter valid mobile number";
//       phone.style.borderBottom = "2px solid red";
//       phone.style.opacity = "0.6"
//     }
//     else if(!(/^[0-9]*$/).test(phone.value)){
//       document.getElementById("mobile-error").innerHTML = "*Please enter valid mobile number";
//       phone.style.borderBottom = "2px solid red";
//       phone.style.opacity = "0.6"
//     }
//     else{
//       document.getElementById("mobile-error").innerHTML = " ";
//       phone.style.borderBottom ='1px solid #DDDDDD';
//       flag +=1;
//     }
//   }
//   if(flag==6){
//     document.getElementById("next-step-id").disabled = false;
//   }
// });
// function getVisibility(){
//   var img = document.getElementById("eye-img");
//   var getPassword = document.getElementById("pass");
//   console.log(getPassword.value);
//   if (getPassword.type === "password") {  
//       getPassword.type = "text";  
//       img.src = "../images/hide.png"
//   } else {  
//       getPassword.type = "password";
//       img.src = "../images/view.png"
//   }  
// }
// function registerNow(){
//   window.location = "../html/signup.html";
// }
// var form1 = document.getElementById("form-2");
// var institute = document.getElementById("inst");
// var state = document.getElementById("st");
// var city = document.getElementById("cty");
// var country = document.getElementById('ct');
// var zip = document.getElementById("zp");
// var val = 0;

// form1.addEventListener("keyup", (e) =>{
//   e.preventDefault();
//   if(e.target.id=="inst"){
//     if(institute.value ==""){
//       document.getElementById("school-error").innerHTML = "*Please enter School / University / Institute Name";
//       institute.style.borderBottom = "2px solid red";
//       cpass.style.opacity = "0.6"
//     }
//     else{
//       document.getElementById("school-error").innerHTML = " ";
//       institute.style.borderBottom = '1px solid #DDDDDD';
//       val += 1;
//     }
//   }
//   else if(e.target.id == "st"){
//     if(state.value==""){
//       document.getElementById("state-error").innerHTML = "*Please enter State";
//       state.style.borderBottom = "2px solid red";
//       state.style.opacity = "0.6";
//     }
//     else{
//       document.getElementById("state-error").innerHTML = " ";
//       state.style.borderBottom = '1px solid #DDDDDD';
//       val += 1;
//     }
//   }
//   else if(e.target.id=="cty"){
//     if(city.value==""){
//       document.getElementById("city-error").innerHTML = "*Please enter City";
//       city.style.borderBottom = "2px solid red";
//       city.style.opacity = "0.6";
//     }else{
//       document.getElementById("city-error").innerHTML = " ";
//       city.style.borderBottom = "1px solid #DDDDDD";
//       val +=1;
//     }
//   }
//   else if(e.target.id=="ct"){
//     if(country.value==""){
//       document.getElementById("country-error").innerHTML = "*Please enter Country";
//       country.style.borderBottom = "2px solid red";
//       country.style.opacity = "0.6";
//     }
//     else{
//       document.getElementById("country-error").innerHTML = " ";
//       country.style.borderBottom = "1px solid #DDDDDD";
//       val +=1;
//     }
//   }
//   else if(e.target.id == "zp"){
//     if(zip.value==""){
//       document.getElementById("zip-error").innerHTML = "*Please enter Zip Code";
//       zip.style.borderBottom = "2px solid red";
//       zip.style.opacity = "0.6";
//     }else if(zip.value.length!=6){
//       document.getElementById("zip-error").innerHTML = "*Please enter Valid Zip Code";
//       zip.style.borderBottom = "2px solid red";
//       zip.style.opacity = "0.6";
//     }
//     else{
//       document.getElementById("zip-error").innerHTML = " ";
//       zip.style.borderBottom = "1px solid #DDDDDD";
//       val +=1;
//     }
//   }
//   if(val==5){
//     if(!document.getElementById("checkbox").style.backgroundColor =="green"){
//         setCookie(1)
//         document.getElementById("submit-button").disabled = true;
//     }
    
// }
// });




function NextPage(){
  document.getElementById("div-1").style.display="none";
  document.getElementById("div-2").style.display="block";
  document.getElementById("step1-span").style.color = "#AFC4E1";
  document.getElementById("step-1").style.borderBottom = "3.5px solid #AFC4E1";
  document.getElementById("step2-span").style.color = "#386CB5";
  document.getElementById("step-2").style.borderBottom = "3.5px solid  #386CB5";
}

function BackPage(){
  document.getElementById("div-1").style.display="block";
  document.getElementById("div-2").style.display="none";
  // console.log(document.getElementById("step1-span"))
  document.getElementById("step2-span").style.color = "#AFC4E1";
  document.getElementById("step-2").style.borderBottom = "3.5px solid #AFC4E1";
  document.getElementById("step1-span").style.color = "#386CB5";
  document.getElementById("step-1").style.borderBottom = "3.5px solid  #386CB5";
}

function colorchange(){
  console.log("hello")
  console.log(document.getElementById("checkbox").style.backgroundColor)
  if (document.getElementById("checkbox").style.backgroundColor =="green"){
      document.getElementById("checkbox").style.backgroundColor = "white";
  }
  else{
      document.getElementById("checkbox").style.backgroundColor = "green";
  }
}
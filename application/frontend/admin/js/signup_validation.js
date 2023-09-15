
const form1 = document.getElementById("form-1");
const form2 = document.getElementById("form-2");
const dob = document.getElementById("dob");
const institute = document.getElementById("inst");
const state = document.getElementById("st");
const city = document.getElementById("cty");
const country = document.getElementById('ct');
const zip = document.getElementById("zp");
const fname = document.getElementById("firstname");
const lname = document.getElementById("lastname");
const pass = document.getElementById("pass");
const cpass = document.getElementById("cpass");
const email = document.getElementById("floatingLabel");
const username = document.getElementById("username");
const phone = document.getElementById("phone");
const NextButton = document.getElementById("next-step-id");
const SubmitButton = document.getElementById("submit-button");
const check = document.getElementById("checkbox");
const validators = {
    fname: function (value) {
      if (!value) {
        return "*First Name is required";
      }
      return "";
    },
    lname: function (value) {
      if (!value) {
          return "*Password is required";
        }
      return "";
    },
    email : function (value) {
        if(!value){
            return "*Email is required";
        }
        if(!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(value)){
            return "*Email is invalid";
        }
        return "";
    },
    username : function (value) {
        if(!value){
            return "*Username is required";
        }
        // if(!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(value)){
        //     return "*Email is invalid";
        // }
        return "";
    },
    mobile : function(value){
        if(!value){
            return "*Mobile number is required";
          }
        if(value.length!=10 || !(/^[0-9]*$/).test(value)){
            return "*Mobile number is invalid";
          }
        return "";
    },
    pass : function (value) {
        if(!value){
            return "*Password is required";  
          } 
        if(!pass.value.match(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9])(?!.*\s).{6,15}$/)){
            return "*Invalid Password";
          }
        return "";
    },
    cpass : function(value){
        if(!value){
            return "*Password is required";
          }
        if(pass.value!=cpass.value){
                return "*Password doesn't match";
              }
        return "";
    },

    dob : function(value){
        if(!value){
            return "*DOB is required";
        }
        return "";
    },
    inst : function(value){
        if(!value){
            return "*Department is required";
        }
        return "";
    },
    st : function(value){
        if(!value){
            return "*State is required";
        }
        return "";
    },
    cty : function(value){
        if(!value){
            return "*City is required";
        }
        return "";
    },
    ct : function(value){
        if(!value){
            return "*Country is required";
        }
        return "";
    },
    zp : function(value){
        if(!value){
            return "*Country is required";
        }
        if(value.length!=6){
            return "*Invalid zipcode";
          }
        return "";
    }
  };
  

fname.addEventListener("input", validateInput);
lname.addEventListener("input", validateInput);
pass.addEventListener("input", validateInput);
cpass.addEventListener("input", validateInput);
email.addEventListener("input", validateInput);
username.addEventListener("input", validateInput);
phone.addEventListener("input", validateInput);
dob.addEventListener("input",validateInput);
institute.addEventListener("input", validateInput);
state.addEventListener("input", validateInput);
city.addEventListener("input", validateInput);
country.addEventListener("input", validateInput);
zip.addEventListener("input", validateInput);


function validateInput(event) {
    if(event.type!="click"){
    const inputElement = event.target;
    const inputName = inputElement.name;
    const inputValue = inputElement.value;
    const errorMessage = validators[inputName](inputValue);
  
    const errorElement = document.getElementById(`${inputName}-error`);
    errorElement.textContent = errorMessage;
    inputElement.style.borderBottom = errorMessage == "" ? "" : "2px solid red";
    inputElement.style.opacity = errorMessage == "" ? "" : "0.6"
    }
  
    const isValidEmail = validators.email(email.value) == "";
    const isValidUsername = validators.username(username.value) == "";
    const isValidPhone = validators.mobile(phone.value) == "";
    const isValidfname = validators.fname(fname.value) == "";
    const isValidlname = validators.lname(lname.value) == "";
    const isValidPass = validators.pass(pass.value) == "";
    const isValidCpass = validators.cpass(cpass.value) == "";
    NextButton.disabled = !isValidEmail || !isValidUsername || !isValidPass || !isValidCpass || !isValidfname || !isValidlname || !isValidPhone;

    const isValidDob = validators.dob(dob.value) == "";
    const isValidInst = validators.inst(institute.value) == "";
    const isValidState = validators.st(state.value) == "";
    const isValidCountry = validators.ct(country.value) == "";
    const isValidCity = validators.cty(city.value) == "";
    const isValidZip = validators.zp(zip.value) == "";
    const isValidTerms = check.style.backgroundColor == "green";
    SubmitButton.disabled = !isValidDob || !isValidCity || !isValidCountry || !isValidInst || !isValidState || !isValidZip || !isValidTerms;
}

form1.addEventListener("submit", function (event){
    event.preventDefault();
    NextPage()
    
});    


form2.addEventListener("submit",(e) => {
    alert("Account Created");
    window.location = "../html/login.html"
})

check.addEventListener("click",(e) =>{
    console.log(e)
    colorchange()
    validateInput(e)
})
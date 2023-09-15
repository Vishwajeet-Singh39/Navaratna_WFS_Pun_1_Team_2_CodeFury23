// for dashboard to be active
function Link(){
  console.log(window.location.pathname)
  if(window.location.pathname == "/html/home.html"){
    document.getElementById("text-1").classList += ' navactive';
  }
}
window.onload = Link();

// for other side bar links to be active on click
function textactive(val){
  const sidenav = document.querySelectorAll(".text")
  sidenav.forEach(nav => {
    if(nav.id==`text-${val}`){
      document.getElementById(nav.id).classList += ' navactive';
    }
    else{
      document.getElementById(nav.id).classList.remove('navactive');
    }
  });
}

// for toggle of dropdown
function getProfilePop(){
  var bar = document.getElementById('profile-pop');
  if(bar.style.display=='none'){
    bar.style.display = 'block';
  }else{
    bar.style.display = 'none';
  }
}
// -----------------------------------------------------------------------------
// for toggle of sidebar
function getBar(){
  var bar = document.getElementById('side');
  if(bar.style.display=='none'){
    bar.style.display = 'block';
  }else{
    bar.style.display = 'none';
  }
}
// --------------------------------------------------------------------------

// for dropdown options
function handlesetting(){
  var pop = document.getElementById('pop');
  if(pop.style.display=='none'){
    pop.style.display = 'grid';
  }else{
    pop.style.display = 'none';
  }
}
// ------------------------------------------------------------------------
// avtar/profile image chnage
function ChangeImage(){
  const dp = document.getElementById('dp');
  const display = window.getComputedStyle(dp).display;
  if (display == "block"){
    document.getElementById("name-img").style.display = "block";
    document.getElementById("dp").style.display = "none";
  }
  else{
    document.getElementById("name-img").style.display = "none";
    document.getElementById("dp").style.display = "block";
  }
}





// JavaScript to toggle the user menu
// function toggleMenu() {
//   var menu = document.getElementById("user-menu");
//   menu.style.display = (menu.style.display === "block") ? "none" : "block";
// }


// JavaScript to change the content of the page
function changeContent(content) {
  var pageContent = document.getElementById("page-content");
  pageContent.innerHTML = content;
}

// JavaScript function to handle the password change form submission
function changePassword() {
  var username = document.getElementById("username").value;
  var oldPassword = document.getElementById("old-password").value;
  var newPassword = document.getElementById("new-password").value;

  // You can add validation and submit the data to the server for processing here
  // For this example, we'll just display a message
  var message = "Username: " + username + "<br>Old Password: " + oldPassword + "<br>New Password: " + newPassword;
  changeContent(message);
}

function showChangePasswordForm() {
  var formHtml = `
      <div id="change-password-form"  class="password-change-form">
          <h2>Change Password</h2>
          <form>
              <label for="username">Username:</label>
              <input type="text" id="username" name="username" required><br>

              <label for="old-password">Old Password:</label>
              <input type="password" id="old-password" name="old-password" required><br>

              <label for="new-password">New Password:</label>
              <input type="password" id="new-password" name="new-password" required><br>

              <button type="button" onclick="changePassword()">Submit</button>
          </form>
      </div>
  `;
  
  changeContent(formHtml);
  changePasswordJson();
}

function changePasswordJson() {
  const uname = document.getElementById("username").value;
  const oldPass = document.getElementById("old-password").value;
  const newPass = document.getElementById("new-password").value;

  // Create a JSON object to store the user input
  const passwordJson = {
        "username":uname,
        "oldPassword":oldPass,
        "newPassword":newPass
  };
  
  const passwordJsonData = JSON.stringify(passwordJson);
}

function showChangeProfileForm(){
  var formHtmlProfile=`
      <div id="profile-details" class="profile-details">
          <h2>User Profile</h2>
          <label>User ID:</label>
          <p id="user-id"></p><br>

          <label>Username:</label>
          <p id="username"></p><br>

          <label>Full Name:</label>
          <p id="full-name"></p><br>

          <label>Date of Joining:</label>
          <p id="date-joining"></p><br>

          <label>Date of Birth:</label>
          <p id="date-birth"></p><br>

          <label>Department:</label>
          <p id="department"></p><br>

          <label>Mobile:</label>
          <p id="mobile"></p><br>

          <label>Email:</label>
          <p id="email"></p><br>

          <label>Location:</label>
          <p id="location"></p><br>

          <label>Status:</label>
          <p id="status"></p><br>
      </div>
  `;
   changeContent(formHtmlProfile);
   populateProfileDetails();
}

function showChangeQuotationForm(){
  var formHtmlQuotation=`
      <div id="quotation-list" class="quotation-list">
      <h2>Quotation List</h2>

      </div>
  `;
  changeContent(formHtmlQuotation);
  populateQuotationList();
}

function showChangeRequestForm(){
  var formHtmlRequest=`

    <div class="plan-request-form">
      <h2>Send Plan Request</h2>
      <form id="plan-request-form">
      <div class="form-group">
        <label for="request-id">Request ID:</label>
        <input type="text" id="request-id" name="request_id">
      </div>

      <div class="form-group">
        <label for="from-date">From Date:</label>
        <input type="date" id="from-date" name="from_date">
      </div>

      <div class="form-group">
        <label for="to-date">To Date:</label>
        <input type="date" id="to-date" name="to_date">
      </div>

      <div class="form-group">
        <label for="no-of-persons">No. of Persons:</label>
        <input type="number" id="no-of-persons" name="no_of_persons" min="1">
      </div>

      <div class="form-group">
        <label for="services-dropdown">Services:</label>
        <select id="services-dropdown" name="services" multiple>
            <option value="service1">Decoration</option>
            <option value="service2">Photography</option>
            <option value="service3">Catering</option>
        </select>
      </div>

      <div class="form-group">
        <label for="other-services-dropdown">Other Services:</label>
        <select id="other-services-dropdown" name="other_services" multiple>
            <option value="other-service1">Dress</option>
            <option value="other-service2">Dance</option>
            <option value="other-service3">Music</option>
        </select>
      </div>

      <div class="form-actions">
        <button type="button" onclick="submitPlanRequest()">Submit</button>
      </div>
      </form>
      </div>
  `;
  changeContent(formHtmlRequest);
  submitPlanRequest()
}
function populateProfileDetails() {
document.getElementById("user-id").textContent = userProfile.user_id;
document.getElementById("username").textContent = userProfile.username;
document.getElementById("full-name").textContent = userProfile.full_name;
document.getElementById("date-joining").textContent = userProfile.date_joining;
document.getElementById("date-birth").textContent = userProfile.date_birth;
document.getElementById("department").textContent = userProfile.department;
document.getElementById("mobile").textContent = userProfile.mobile;
document.getElementById("email").textContent = userProfile.email;
document.getElementById("location").textContent = userProfile.location;
document.getElementById("status").textContent = userProfile.status;
}

var userProfile = {
"user_id": 1,
"username": "navratan",
"full_name": "navratan planner",
"date_joining": "2023-09-15",
"date_birth": "2001-05-20",
"department": "IT",
"mobile": "1234567890",
"email": "naratan@gmail.com",
"location": "Pune",
"status": "Non-Active"
};

var quotationList = [
{
  "quotation_id": 1,
  "package_type": "Basic",
  "estimated_amount": 10000.00,
  "vendor_id": 101,
  "user_id": 1,
  "plan_request_id": 1001,
  "status": "Pending"
},
{
  "quotation_id": 2,
  "package_type": "Classic",
  "estimated_amount": 25000.00,
  "vendor_id": 102,
  "user_id": 2,
  "plan_request_id": 1002,
  "status": false
},
{
  "quotation_id": 3,
  "package_type": "Premium",
  "estimated_amount": 50000.00,
  "vendor_id": 103,
  "user_id": 3,
  "plan_request_id": 1003,
  "status": true
}
];

function populateQuotationList() {
var quotationListContainer = document.getElementById("quotation-list");


quotationListContainer.innerHTML = "";


for (var i = 0; i < quotationList.length; i++) {
  var quotation = quotationList[i];

  var quotationItem = document.createElement("div");
  quotationItem.className = "quotation-item";


  var quotationDetails = document.createElement("h3");
  quotationDetails.innerHTML = `
      Quotation ID: ${quotation.quotation_id}<br>
      Package Type: ${quotation.package_type}<br>
      Estimated Amount: ${quotation.estimated_amount.toFixed(2)}<br>
      Vendor ID: ${quotation.vendor_id}<br>
      User ID: ${quotation.user_id}<br>
      Plan Request ID: ${quotation.plan_request_id}<br>
      Status: ${quotation.status ? "Accepted" : "Inactive"}
  `;

  quotationItem.appendChild(quotationDetails);
  quotationListContainer.appendChild(quotationItem);
}
}

function submitPlanRequest() {
const requestId = document.getElementById("request-id").value;
const fromDate = document.getElementById("from-date").value;
const toDate = document.getElementById("to-date").value;
const noOfPersons = document.getElementById("no-of-persons").value;
const servicesDropdown = document.getElementById("services-dropdown");
const otherServicesDropdown = document.getElementById("other-services-dropdown");

const selectedServices = Array.from(servicesDropdown.selectedOptions).map(option => option.value);

const selectedOtherServices = Array.from(otherServicesDropdown.selectedOptions).map(option => option.value);

// Create a JSON object to store the user input
const planRequestData = {
  "request_id": requestId,
  "from_date": fromDate,
  "to_date": toDate,
  "no_of_persons": noOfPersons,
  "services": selectedServices,
  "other_services": selectedOtherServices
};

const jsonData = JSON.stringify(planRequestData);

console.log(jsonData);
}
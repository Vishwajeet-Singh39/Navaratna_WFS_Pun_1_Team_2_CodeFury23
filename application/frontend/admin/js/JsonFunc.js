/*
<!--
    Navratan 

    Admin JsonFunc -
        It includes functions which works with json

-->
*/
function UpdateProfile() {
    const id = document.getElementById("admin_id").value;
    const name = document.getElementById("admin_name").value;
    const email = document.getElementById("admin_email").value;
  
    // Create a JSON object to store the user input
    const profileJson = {
          "admin_id":id,
          "admin_name":name,
          "admin_email":email
    };
    
    const profileJsonData = JSON.stringify(profileJson);
    console.log(profileJsonData);

    alert("Updated successfully");
  }

  
function addVendor() {
    const id = document.getElementById("vendor_id").value;
    const name = document.getElementById("vendor_name").value;
    const email = document.getElementById("email").value;
    const mobile = document.getElementById("mobile").value;
    const address = document.getElementById("address").value;
    const package = document.getElementById("package").value;
  
    // Create a JSON object to store the user input
    const vendorJson = {
          "vendor_id":id,
          "vendor_name":name,
          "vendor_email":email,
          "vendor_mobile":mobile,
          "vendor_address":address,
          "vendor_package":package
    };
    
    const vendorJsonData = JSON.stringify(vendorJson);
    console.log(vendorJsonData);

    alert("Added successfully");
  }

  function viewvendor(){
     // Simulated JSON data (replace with your JSON file)
     const jsonData = [
        {
            "id": 1,
            "name": "Ratna decoration",
            "email": "ratna@gmail.com",
            "contact": "9870645284",
            "address":"Pune",
            "packages":"Basic"
        },
        {
            "id": 1,
            "name": "Swaad catering",
            "email": "swaad@gmail.com",
            "contact": "9820689284",
            "address":"Mumbai",
            "packages":"Premium"
        },
        {
            "id": 1,
            "name": "Innovative Photography",
            "email": "innovate@gmail.com",
            "contact": "9374892493",
            "address":"Chennai",
            "packages":"Classic"
        }
    ];

    const vendorTableBody = document.getElementById("vendor-table-body");

    // Loop through the JSON data and create table rows
    jsonData.forEach((vendor) => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${vendor.id}</td>
            <td>${vendor.name}</td>
            <td>${vendor.email}</td>
            <td>${vendor.contact}</td>
            <td>${vendor.address}</td>
            <td>${vendor.packages}</td>
        `;
        vendorTableBody.appendChild(row);
    });
  }

  function viewuser(){
    // Simulated JSON data (replace with your JSON file)
    const jsonData = [
       {
           "id": 1,
           "name": "Ramesh",
           "joiningdate":"2023-20-12",
           "birthdate":"2000-12-01",
           "department":"IT",
           "contact": "9870645284",
           "email": "ramesh@gmail.com",
           "location":"Pune",
           "status":"Non-Active"
       },
       {
            "id": 1,
            "name": "Mahesh",
            "joiningdate":"2023-23-12",
            "birthdate":"2000-07-01",
            "department":"Sales",
            "contact": "9889645284",
            "email": "mahesh@gmail.com",
            "location":"Mumbai",
            "status":"Non-Active"
        },
       {
            "id": 1,
            "name": "Suresh",
            "joiningdate":"2023-09-02",
            "birthdate":"2000-03-01",
            "department":"Marketing",
            "contact": "9870645284",
            "email": "suresh@gmail.com",
            "location":"Pune",
            "status":"Active"
       }
   ];

   const vendorTableBody = document.getElementById("vendor-table-body");

   // Loop through the JSON data and create table rows
   jsonData.forEach((user) => {
       const row = document.createElement("tr");
       row.innerHTML = `
           <td>${user.id}</td>
           <td>${user.name}</td>
           <td>${user.joiningdate}</td>
           <td>${user.birthdate}</td>
           <td>${user.department}</td>
           <td>${user.contact}</td>
           <td>${user.email}</td>
           <td>${user.location}</td>
           <td>${user.status}</td>
       `;
       vendorTableBody.appendChild(row);
   });
 }
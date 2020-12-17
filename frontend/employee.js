const url = 'http://localhost:8080/project-1/';
document.getElementById("user-body").onload = function() {loadData()};
var user;

function loadData() {
    let data = localStorage.getItem("userData");
    user = JSON.parse(data);
    console.log(user);

    if(user) {
        let row = document.createElement("tr");

        let id = document.createElement("td");
        id.innerHTML = user.userId;
        row.appendChild(id);

        let firstname = document.createElement("td");
        firstname.innerHTML = user.firstname;
        row.appendChild(firstname);

        let lastname = document.createElement("td");
        lastname.innerHTML = user.lastname;
        row.appendChild(lastname);

        let email = document.createElement("td");
        email.innerHTML = user.email;
        row.appendChild(email);

        let role = document.createElement("td");
        role.innerHTML = user.role.userRole;
        row.appendChild(role);

        document.getElementById("avbody").appendChild(row);
    }
    if(user.role.userRoleId === 1) {
       document.getElementById("employee-div1").hidden = false;
       
    } else {
        document.getElementById("manager-div1").hidden = false;
        
    }
}

document.getElementById("logout").addEventListener("click", function() {
    let c = confirm("Do you want to logout?");
    if(c==true) {
        location.href="user.html";
    } 
});


var select = document.getElementById("emp-select");
var intSelect = select.value;
var menu = document.getElementById("menu");
var reqForm = document.getElementById('request-form');

select.addEventListener("change", menuSelect);


function menuSelect() {
    console.log("IntSelect= " + intSelect);
    let newSelect = select.value;   
    if(newSelect!=intSelect) {
        console.log("NewSelect= " + newSelect);
        if(newSelect=='request') {
            reqForm.hidden = false;
            menu.hidden = true;               
        } else if(newSelect=='menu') {
            select.value = "menu";
            menu.hidden = false;
            reqForm.hidden = true;
        }
        intSelect = newSelect;
    }
}

document.getElementById('submit').addEventListener('click', ersSubmit);


async function ersSubmit() {      
    let c = confirm("Do you want to submit?");
    if (c === true) {
        let ersAmountVal = document.getElementById("amount").value;
        let ersOptValue = document.getElementById("rb-type").value;
        let ersDesVal = document.getElementById("description").value;
        let amountHelper = document.getElementById("amountHelp");
        if (isNaN(ersAmountVal)) {
            amountHelper.innerHTML = 'The enter amount is not a number';
            amountHelper.style.color = 'red';
        } else if (ersAmountVal <= 0) {
            amountHelper.innerHTML = 'The amount must greater than 0.0';
            amountHelper.style.color = 'red';
        } else {
            let rib = {
                amount: ersAmountVal,
                description: ersDesVal,
                author: user.userId,
                status: {
                    statusId: 200,
                    status: ""
                },
                type: {
                    typeId: ersOptValue,
                    type: ""
                }
            };

            let resp = await fetch(url + 'emp/submit', {
                method: "POST",
                body: JSON.stringify(rib),
                credentials: 'include',
            });

            select.value = "menu";
            intSelect = "menu";
            reqForm.hidden = true;
            menu.innerHTML = "";
            menu.hidden = false;

            if (resp.status === 200) {
                menu.innerHTML = "Your reimbursment has submitted successfully";
                menu.style.color = "green";
            } else {
                let message = await resp.text();
                menu.innerHTML = message + ". \nSubmit failed. Please try again";
                menu.style.color = "red";
            }
        }
    }          
}
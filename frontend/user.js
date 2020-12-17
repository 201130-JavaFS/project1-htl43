
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

var select = document.getElementById("emp-select");
var intSelect = select.value;

select.addEventListener("click", menuSelect);

function menuSelect() {
    let newSelect = select.value;
    if(newSelect!=intSelect) {
        intSelect = newSelect;
        if(newSelect==='request') {
            document.getElementById('ers-form').hidden = false;
            document.getElementById('submit').addEventListener('click', ()=> {
                let ersAmountVal = document.getElementById("amount").value;
                let ersOptValue = document.getElementById("rb-type").value;
                let ersDesVal = document.getElementById("description").value;
                let amountHelper = document.getElementById("amountHelp");
                if(isNaN(ersAmountVal)) {
                    amountHelper.innerHTML = 'The enter amount is not a number';
                    amountHelper.style.color = 'red';
                } else if(ersAmountVal <= 0) {
                    amountHelper.innerHTML = 'The amount must greater than 0.0';
                    amountHelper.style.color = 'red';
                } else {
                
                    // let rib = {
                    //     amount : ersAmountVal,
                    //     description : ersDesVal,
                    //     author : user.userId
                    //   };
                    
                    //   let resp = await fetch(url+'login', {
                    //     method:"POST",
                    //     body: JSON.stringify(user),
                    //     credentials: 'include'
                    //     //Credentials:include will ensure that they cookie is captured, future fetch requests
                    //     //will also require this value in order to send the cookie back. 
                    //   });
                    
                    //   if(resp.status===200){
                    //     let data = await resp.json();
                    //     console.log(data);
                    //     localStorage["userData"] = JSON.stringify(data);
                    //     location.href="user.html";    
                    //   }else{
                    //     let message = await resp.text();
                    //     document.getElementById('login_message').innerText =  message + ". \nLogin failed. Please try again"; 
                    //   }
                }        
            });           
        } else {
            

        }
    }
    
}
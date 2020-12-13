
document.getElementById("user-body").onload = function() {loadData()};

function loadData() {
    let data = localStorage.getItem("userData");
    let user = JSON.parse(data);
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
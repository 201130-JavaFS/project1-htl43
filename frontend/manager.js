const url = 'http://localhost:8080/project-1/';
document.getElementById("man-body").onload = function() {loadData()};
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

        document.getElementById("man-table-info-body").appendChild(row);
    }

}

document.getElementById("logout").addEventListener("click", function() {
    let c = confirm("Do you want to logout?");
    if(c==true) {
        location.href="user.html";
    } 
});

var select = document.getElementById("man-select");
var menu = document.getElementById("menu");
var viewRibs = document.getElementById("view-reimbs");

var intSelect = select.value;

select.addEventListener("change", menuSelect);
function menuSelect() {
    let newSelect = select.value;   
    if(newSelect!=intSelect) {
        if(newSelect=='menu') {
            menu.hidden = false;
            viewRibs.hidden = true;          
        } else {
            menu.hidden = true;
            viewRibs.hidden = false;
            getAllRibs(newSelect, viewAllRibs);
            
        } 
        intSelect = newSelect;
    }
}

let msg = document.getElementById("view-reimbs-message");
let viewTableRibs = document.getElementById("man-table-view-reimbs-body");

function viewAllRibs(dataSelect) { 
    for(let rimb of dataSelect) {
        let row = document.createElement("tr");

        let id = document.createElement("td");
        id.innerHTML = rimb.reimbId;
        row.appendChild(id);

        let submit = document.createElement("td");
        let s = new Date(rimb.submitted);
        submit.innerHTML = s.toDateString();
        row.appendChild(submit);

        let amount = document.createElement("td");
        amount.innerHTML = "$" + rimb.amount;
        row.appendChild(amount);

        let authorId = document.createElement("td");
        authorId.innerHTML = rimb.author.userId;
        row.appendChild(authorId);

        let type = document.createElement("td");
        type.innerHTML = rimb.type.type;
        row.appendChild(type);

        let des = document.createElement("td");
        des.innerHTML = rimb.description;
        row.appendChild(des);

        let status = document.createElement("td");
        status.innerHTML = rimb.status.status;
        row.appendChild(status);

        
        let resolverId = document.createElement("td");
        if(rimb.resolver!=null) {
            resolverId.innerHTML = rimb.resolver.userId;
        }  
        row.appendChild(resolverId);

        let resolved = document.createElement("td");
        resolved.innerHTML = rimb.resolved;
        row.appendChild(resolved);
    
        viewTableRibs.appendChild(row);
    }       
      
}

async function getAllRibs(newSelect, displayRibs) {
    msg.innerHTML = "";
    let pc = document.getElementById('pending-change');
    let reimbIdSelect = document.getElementById('reimbId-select');
    let reimbStatusSelect = document.getElementById('reimbStatus-select'); 
    try {
        let resp = await fetch(url+"man/view-all", {credentials: 'include'});
        if (resp.status === 200) {
                viewTableRibs.hidden = false;
                let data = await resp.json();
                console.log(data);
                document.getElementById("man-table-view-reimbs").hidden=false;
                document.getElementById("man-table-view-reimbs-body").innerHTML="";
                let dataSelect = [];
                if(newSelect=="viewAll") {
                    pc.hidden = true;
                    dataSelect = data;
                } else if (newSelect=="viewPast") {
                    pc.hidden = true;
                    for(let rimb of data) {
                        if(rimb.status.statusId!=100) {
                            dataSelect.push(rimb);
                        }
                    }
                } else {
                    pc.hidden = false;
                    for(let rimb of data) {
                        if(rimb.status.statusId==100) {
                            let reimbIdOpt = document.createElement('option');
                            reimbIdOpt.value = rimb.reimbId;
                            reimbIdOpt.innerHTML =  rimb.reimbId;
                            reimbIdSelect.appendChild(reimbIdOpt);
                            dataSelect.push(rimb);
                        }
                    }
                    let approveStatusOpt = document.createElement('option');
                    approveStatusOpt.value = 101;
                    approveStatusOpt.innerHTML =  "APPROVED";
                    reimbStatusSelect.appendChild( approveStatusOpt);
                    let denyStatusOpt = document.createElement('option');
                    denyStatusOpt.value = 102;
                    denyStatusOpt.innerHTML =  "DENINED";
                    denyStatusOpt.style.backgroundColor = "red";
                    reimbStatusSelect.appendChild(denyStatusOpt);
                }
                displayRibs(dataSelect);
                
        } else {
            viewTableRibs.hidden = true;
            let message = await resp.text();
            msg.innerHTML = message;
            msg.style.color = "red";           
        }
    } catch (error) {
        viewTableRibs.hidden =  true;
        msg.innerHTML = error.message + "<br/>Sorry! Can't reach to server. Please login again";
        msg.style.color = "red";
        setTimeout(()=>{
            let c = confirm("Do you want to logout?");
            if(c==true) {
                    location.href="user.html";
            } 
        },2000); 
    }
}
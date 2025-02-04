const url = 'http://localhost:8080/project-1/';

document.getElementById("loginbtn").addEventListener('click', loginFunc);

async function loginFunc() {
  let usern = document.getElementById("username").value;
  let userp = document.getElementById("password").value;

  let user = {
    username:usern,
    password:userp
  };
  try {
    let resp = await fetch(url+'user/login', {
      method:"POST",
      body: JSON.stringify(user),
      credentials: 'include'
      //Credentials:include will ensure that they cookie is captured, future fetch requests
      //will also require this value in order to send the cookie back. 
    });
      if(resp.status===200){
        let data = await resp.json();
        let dataString = JSON.stringify(data);
        localStorage["userData"] = dataString;
        if(data.role.userRoleId==1) {
          location.href="employee.html"; 
        } else {
          location.href="manager.html";
        }  
      }else{
        let message = await resp.text();
        document.getElementById('login_message').innerText =  message + ". \nLogin failed. Please try again";
        document.getElementById('login_message').style.color = "red"; 
      }
    } catch (error) {
      document.getElementById('login_message').innerText= "\nServer failed to respond. Sorry, service is not available at this time.";
      document.getElementById('login_message').style.color = "red";
    }
}


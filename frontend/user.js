const url = 'http://localhost:8080/project-1/';

document.getElementById("loginbtn").addEventListener('click', loginFunc);

async function loginFunc() {
  let usern = document.getElementById("username").value;
  let userp = document.getElementById("password").value;

  let user = {
    username:usern,
    password:userp
  };

  let resp = await fetch(url+'user/login', {
    method:"POST",
    body: JSON.stringify(user),
    credentials: 'include'
    //Credentials:include will ensure that they cookie is captured, future fetch requests
    //will also require this value in order to send the cookie back. 
  });

  if(resp.status===200){
    let data = await resp.json();
    console.log(data);
    localStorage["userData"] = JSON.stringify(data);
    location.href="employee.html";    
  }else{
    let message = await resp.text();
    document.getElementById('login_message').innerText =  message + ". \nLogin failed. Please try again"; 
  }

}


import { useRef } from "react";
import { useNavigate } from "react-router-dom";
import classes from "./MainPages.module.css";

function Login() {
  const emailRef = useRef();
  const passwordRef = useRef();
  const navigate = useNavigate();

  function login() {
    const email = emailRef.current.value;
    const password = passwordRef.current.value;

    const bodyRequest = {
      email: email,
      password: password,
    };

    if(email === "" || password === ""){
      alert("Complete the fields to perform login!");
    }else{
      fetch("http://localhost:8080/login", {
      method: "POST",
      body: JSON.stringify(bodyRequest),
      headers: {
        "Content-Type": "application/json",
      },
    }).then((response) => {
      if(response.status === 500){
        alert("User not found!");
      }else{
        response.json().then((body) => {
          const role = body.role;
          console.log(role);
          if (role === "ADMIN") {
            navigate("/admin");
          } else if (role === "DOCTOR") {
            navigate("/doctor");
          } else if (role === "DONOR") {
            navigate("/donor");
          }
        });
      }
     
    });
    }

    
  }

  return (
    <div>
      <h2>Login</h2>
      <div>
        <label className={classes.label}>Username</label>
        <input className={classes.input} ref={emailRef} type="text" />
      </div>
      <div>
        <label className={classes.label}>Password</label>
        <input className={classes.input} ref={passwordRef} type="password" />
      </div>
      <button className={classes.button} onClick={login}>Login</button>
    </div>
  );
}

export default Login;

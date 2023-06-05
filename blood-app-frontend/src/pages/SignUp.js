import classes from "../components/Components.module.css";
import { useRef } from "react";
import { Link } from "react-router-dom";
import signUpClasses from "./MainPages.module.css";

function SignUp() {
  const emailRef = useRef();
  const passwordRef = useRef();
  const nameRef = useRef();
  const surnameRef = useRef();
  const countyRef = useRef();
  const cnpRef = useRef();

  function signupFunction() {
    const email = emailRef.current.value;
    const password = passwordRef.current.value;
    const name = nameRef.current.value;
    const surname = surnameRef.current.value;
    const county = countyRef.current.value;
    const cnp = cnpRef.current.value;

    const bodyRequest = {
      email: email,
      password: password,
      name: name,
      surname: surname,
      cnp: cnp,
      county: county,
    };

    if (
      email === "" ||
      password === "" ||
      name === "" ||
      surname === "" ||
      cnp === "" ||
      county === ""
    ) {
      alert("Complete the fields to complete the registration!");
    } else {
      fetch("http://localhost:8080/donor/register", {
        method: "POST",
        body: JSON.stringify(bodyRequest),
        headers: {
          "Content-Type": "application/json",
        },
      }).then((response) => {
        response.json().then((body) => {
          console.log(body);
        });
      });
    }
  }

  return (
    <div className={classes.modal}>
      <div className={signUpClasses.div}>
        <label className={signUpClasses.label}> Email</label>
        <input className={signUpClasses.input} ref={emailRef} type="email" />
      </div>
      <div className={signUpClasses.div}>
        <label className={signUpClasses.label}> Password</label>
        <input
          className={signUpClasses.input}
          ref={passwordRef}
          type="password"
        />
      </div>
      <div className={signUpClasses.div}>
        <label className={signUpClasses.label}> Name</label>
        <input className={signUpClasses.input} ref={nameRef} type="text" />
      </div>
      <div className={signUpClasses.div}>
        <label className={signUpClasses.label}> Surname</label>
        <input className={signUpClasses.input} ref={surnameRef} type="text" />
      </div>
      <div className={signUpClasses.div}>
        <label className={signUpClasses.label}> CNP</label>
        <input className={signUpClasses.input} ref={cnpRef} type="text" />
      </div>
      <div className={signUpClasses.div}>
        <label className={signUpClasses.label}> County</label>
        <input className={signUpClasses.input} ref={countyRef} type="text" />
      </div>
      <div>
        <div className={signUpClasses.div}>
          <Link to="/login">
            <button className={signUpClasses.button} onClick={signupFunction}>
              Sign Up
            </button>
          </Link>
        </div>
      </div>
    </div>
  );
}

export default SignUp;

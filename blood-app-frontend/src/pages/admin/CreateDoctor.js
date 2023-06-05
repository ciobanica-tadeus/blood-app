import classes from "../../components/Components.module.css";
import adminClasses from "./Admin.module.css";
import { useRef } from "react";

function CreateDoctor(props) {
  const emailRef = useRef();
  const passwordRef = useRef();
  const nameRef = useRef();
  const surnameRef = useRef();
  const countyRef = useRef();
  const cnpRef = useRef();

  function createDoctor() {
    const email = emailRef.current.value;
    const password = passwordRef.current.value;
    const name = nameRef.current.value;
    const surname = surnameRef.current.value;
    const cnp = cnpRef.current.value;
    const county = countyRef.current.value;

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
      fetch("http://localhost:8080/doctors/create_doctor", {
        method: "POST",
        body: JSON.stringify(bodyRequest),
        headers: {
          "Content-Type": "application/json",
        },
      }).then((response) => {
        if (response.status === 200) {
          alert("Doctor register successfully!!");
        }
        response.json().then((body) => {
          console.log(body);
        });
      });
    }
  }

  function cancelComponent() {
    props.onCancel();
  }

  return (
    <div className={classes.modal}>
      <div className={adminClasses.div}>
        <label className={adminClasses.label}>Email</label>
        <input className={adminClasses.input} ref={emailRef} type="text" />
      </div>
      <div className={adminClasses.div}>
        <label className={adminClasses.label}>Password</label>
        <input
          className={adminClasses.input}
          ref={passwordRef}
          type="password"
        />
      </div>
      <div className={adminClasses.div}>
        <label className={adminClasses.label}>Name</label>
        <input className={adminClasses.input} ref={nameRef} type="text" />
      </div>
      <div className={adminClasses.div}>
        <label className={adminClasses.label}>Surname</label>
        <input className={adminClasses.input} ref={surnameRef} type="text" />
      </div>
      <div className={adminClasses.div}>
        <label className={adminClasses.label}>CNP</label>
        <input className={adminClasses.input} ref={cnpRef} type="text" />
      </div>
      <div className={adminClasses.div}>
        <label className={adminClasses.label}>County</label>
        <input className={adminClasses.input} ref={countyRef} type="text" />
      </div>
      <div>
        <button className={adminClasses.prettybutton} onClick={cancelComponent}>
          Cancel
        </button>
        <button className={adminClasses.prettybutton} onClick={createDoctor}>
          Confirm
        </button>
      </div>
    </div>
  );
}

export default CreateDoctor;

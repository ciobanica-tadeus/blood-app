import classes from "./Donor.module.css";
import components from "../../components/Components.module.css";
import { useRef } from "react";

function EditDonor(props) {
  const emailRef = useRef();
  const passwordRef = useRef();
  const nameRef = useRef();
  const surnameRef = useRef();
  const countyRef = useRef();

  const id = props.id;
  const url = "http://localhost:8080/donor/get/" + id;
  fetch(url, {
    method: "GET",
  }).then((response) => {
    response.json().then((body) => {
      console.log(body);
      const email = document.getElementById("textEmail");
      email.value = body.email;
      const password = document.getElementById("textPassword");
      password.value = body.password;
      const name = document.getElementById("textName");
      name.value = body.name;
      const surname = document.getElementById("textSurname");
      surname.value = body.surname;
      const county = document.getElementById("textCounty");
      county.value = body.county;
    });
  });

  function actualizeDonor() {
    const email = emailRef.current.value;
    const password = passwordRef.current.value;
    const name = nameRef.current.value;
    const surname = surnameRef.current.value;
    const county = countyRef.current.value;
    const url = "http://localhost:8080/donor/edit/" + id;

    const bodyRequest = {
      email: email,
      password: password,
      name: name,
      surname: surname,
      county: county,
    };

    if (
      email === "" ||
      password === "" ||
      name === "" ||
      surname === "" ||
      county === ""
    ) {
      alert("Complete the fields to complete editing!");
    } else {
      fetch(url, {
        method: "PUT",
        body: JSON.stringify(bodyRequest),
        headers: {
          "Content-Type": "application/json",
        },
      }).then((response) => {
        if (response.status === 200) {
          alert("Edit with success!");
        }
        response.json().then((body) => {
          console.log(body);
        });
      });
    }
  }

  return (
    <div className={components.modal}>
      <div className={classes.div}>
        <label className={classes.label}>Email</label>
        <input
          className={classes.input}
          ref={emailRef}
          type="text"
          id="textEmail"
        />
      </div>
      <div className={classes.div}>
        <label className={classes.label}>Password</label>
        <input
          className={classes.input}
          ref={passwordRef}
          type="password"
          id="textPassword"
        />
      </div>
      <div className={classes.div}>
        <label className={classes.label}>Name</label>
        <input
          className={classes.input}
          ref={nameRef}
          type="text"
          id="textName"
        />
      </div>
      <div className={classes.div}>
        <label className={classes.label}>Surname</label>
        <input
          className={classes.input}
          ref={surnameRef}
          type="text"
          id="textSurname"
        />
      </div>
      <div className={classes.div}>
        <label className={classes.label}>County</label>
        <input
          className={classes.input}
          ref={countyRef}
          type="text"
          id="textCounty"
        />
      </div>
      <div className={classes.div}>
        <button className={classes.prettybutton} onClick={props.onCancel}>
          Cancel
        </button>
        <button className={classes.prettybutton} onClick={actualizeDonor}>
          Confirm
        </button>
      </div>
    </div>
  );
}

export default EditDonor;

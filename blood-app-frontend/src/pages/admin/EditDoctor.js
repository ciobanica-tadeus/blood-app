import classes from "../../components/Components.module.css";
import adminClasses from "./Admin.module.css";
import { useRef } from "react";

function EditDoctor(props) {
  const idRef = useRef();
  const emailRef = useRef();
  const passwordRef = useRef();
  const nameRef = useRef();
  const surnameRef = useRef();
  const countyRef = useRef();
  const cnpRef = useRef();

  function printInfoDoctor() {
    const id = idRef.current.value;
    const url = "http://localhost:8080/doctors?id=" + id;
    if (id === "") {
      alert("Complete ID-field for information about doctor!");
    } else {
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
          const cnp = document.getElementById("textCNP");
          cnp.value = body.cnp;
          const county = document.getElementById("textCounty");
          county.value = body.county;
        });
      });
    }
  }

  function editDoctor() {
    const id = idRef.current.value;
    const email = emailRef.current.value;
    const password = passwordRef.current.value;
    const name = nameRef.current.value;
    const surname = surnameRef.current.value;
    const cnp = cnpRef.current.value;
    const county = countyRef.current.value;
    const url = "http://localhost:8080/doctors/edit/" + id;

    const bodyRequest = {
      id: id,
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

  function cancelComponent() {
    props.onCancel();
  }

  return (
    <div className={classes.modal}>
      <div className={adminClasses.div}>
        <label className={adminClasses.label}>ID</label>
        <input className={adminClasses.input} ref={idRef} type="text" />
      </div>
      <div>
        <button className={adminClasses.prettybutton} onClick={printInfoDoctor}>
          Print information
        </button>
      </div>
      <hr></hr>
      <div className={adminClasses.div}>
        <label className={adminClasses.label}>Email</label>
        <input
          className={adminClasses.input}
          ref={emailRef}
          type="text"
          id="textEmail"
        />
      </div>
      <div className={adminClasses.div}>
        <label className={adminClasses.label}>Password</label>
        <input
          className={adminClasses.input}
          ref={passwordRef}
          type="password"
          id="textPassword"
        />
      </div>
      <div className={adminClasses.div}>
        <label className={adminClasses.label}>Name</label>
        <input
          className={adminClasses.input}
          ref={nameRef}
          type="text"
          id="textName"
        />
      </div>
      <div className={adminClasses.div}>
        <label className={adminClasses.label}>Surname</label>
        <input
          className={adminClasses.input}
          ref={surnameRef}
          type="text"
          id="textSurname"
        />
      </div>
      <div className={adminClasses.div}>
        <label className={adminClasses.label}>CNP</label>
        <input
          className={adminClasses.input}
          ref={cnpRef}
          type="text"
          id="textCNP"
        />
      </div>
      <div className={adminClasses.div}>
        <label className={adminClasses.label}>County</label>
        <input
          className={adminClasses.input}
          ref={countyRef}
          type="text"
          id="textCounty"
        />
      </div>
      <div>
        <button className={adminClasses.prettybutton} onClick={cancelComponent}>
          Cancel
        </button>
        <button className={adminClasses.prettybutton} onClick={editDoctor}>
          Confirm
        </button>
      </div>
    </div>
  );
}

export default EditDoctor;

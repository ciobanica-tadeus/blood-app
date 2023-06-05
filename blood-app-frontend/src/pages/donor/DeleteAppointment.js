import classes from "./Donor.module.css";
import { useRef } from "react";

function DeleteAppointment() {
  const idRef = useRef();

  function deleteAppointment() {
    const id = idRef.current.value;
    const url = "http://localhost:8080/appointments/delete/" + id;
    if (id === "") {
      alert("Please insert a future appointment ID!");
    } else {
      fetch(url, {
        method: "DELETE",
      }).then((response) => {
        if (response.status === 200) {
          alert("Appointment deleted with success!");
        }
        response.json().then((body) => {
          console.log(body);
        });
      });
    }
  }

  return (
    <div className={classes.modal}>
      <div className={classes.div2}>
        <label className={classes.label}> Appointment ID </label>
        <input className={classes.input} ref={idRef} type="text" />
      </div>
      <div className={classes.div2}>
        <button className={classes.prettybutton} onClick={deleteAppointment}>
          DELETE
        </button>
      </div>
    </div>
  );
}

export default DeleteAppointment;

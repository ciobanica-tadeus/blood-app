import classes from "./Doctor.module.css";
import { useRef } from "react";

function ChangeAppointmentStatus() {
  const idRef = useRef();

  function changeStatus(){
    const id = idRef.current.value;
    const url = "http://localhost:8080/appointments/change_status/" + id;
    if(id === ""){
      alert("Please insert the appointment ID!");
    }else{
      fetch(url,{
        method: "PUT"
      }).then((response) => {
        if(response.status===200){
          alert("Confirmed with success!");
        }
        response.json().then((body) => {
          console.log(body);
        })
      })
    
    }
  }

  return (
    <div className={classes.modal}>
      <div className={classes.div}>
        <label className={classes.label}> Appointment ID </label>
      <input
          className={classes.input}
          ref={idRef}
          type="text"
        />
        </div>
        <div className={classes.div}>
      <button className={classes.prettybutton} onClick={changeStatus}>Confirm</button>
      </div>
    </div>
  );
}

export default ChangeAppointmentStatus;

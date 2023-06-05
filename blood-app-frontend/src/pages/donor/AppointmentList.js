import React, { useState } from "react";
import classes from "./Donor.module.css";

function AppointmentsList(props) {
  const [appointments, setAppointmentsData] = useState([]);
  const donorId = props.id;

  function getAllAppoinments() {
    const url =
      "http://localhost:8080/appointments/get_all_appointments/" + donorId;
    fetch(url, {
      method: "GET",
    }).then((response) => {
      response.json().then((body) => {
        setAppointmentsData(body.appointmentResponses);
        console.log(body.appointmentResponses);
      });
    });
  }

  return (
    <div className={classes.new_modal}>
      <div>
        <h2>Appointments for Donor</h2>
      </div>
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Location Name</th>
            <th>Location Address</th>
            <th>Appointment Date</th>
            <th>Confirmed</th>
          </tr>
        </thead>
        <tbody>
          {appointments.map((appointment, index) => (
            <tr key={index}>
              <td>{appointment.id}</td>
              <td>{appointment.locationName}</td>
              <td>{appointment.locationAddress}</td>
              <td>{appointment.appointmentDate}</td>
              <td>{appointment.confirmed ? "Yes" : "No"}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <div className={classes.div}>
        <button className={classes.prettybutton} onClick={getAllAppoinments}>
          {" "}
          Find appointments
        </button>
      </div>
    </div>
  );
}

export default AppointmentsList;

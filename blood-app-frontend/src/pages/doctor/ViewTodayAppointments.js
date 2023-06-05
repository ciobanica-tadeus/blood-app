import * as React from "react";
import { useState } from "react";
import classes from "./Doctor.module.css";
import CardButton from "../../components/CardButton";

function ViewTodayAppointments(props) {
  const [data, setData] = useState([]);

  function getData() {
    console.log(props.id);
    fetch("http://localhost:8080/doctors?id=" + props.id, {
      method: "GET",
    }).then((response) => {
      response.json().then((body) => {
        console.log(body.location.id);
        const url =
          "http://localhost:8080/appointments/get_all_appoinments_by_today/";
        console.log(url + body.location.id);
        fetch(url + body.location.id, {
          method: "GET",
        }).then((response) => {
          response.json().then((body) => {
            setData(body.appointmentResponses);
            console.log(body);
          });
        });
      });
    });
  }

  return (
    <div className={classes.modal}>
      <table>
        <thead>
          <th>ID</th>
          <th>Donor ID</th>
          <th>NAME</th>
          <th>Location ID</th>
          <th>Appointment Date</th>
          <th>Confirmed</th>
        </thead>
        <tbody>
          {data.map((item, index) => (
            <tr key={index}>
              <td>{item.id}</td>
              <td>{item.donorId}</td>
              <td>{item.name}</td>
              <td>{item.locationId}</td>
              <td>{item.appointmentDate}</td>
              <td>{item.confirmed === true ? "True" : "False"}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <div className={classes.div}>
        <CardButton text="View appointments" onClick={getData} />
      </div>
    </div>
  );
}

export default ViewTodayAppointments;

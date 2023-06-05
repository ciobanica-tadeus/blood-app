import React, { useState } from "react";
import classes from "./Doctor.module.css";
var offset = 0;

function PaginationTable(props) {
  const [value, setValue] = useState(1);
  const [data, setData] = useState([]);
  const [locationID, setLocationID] = useState(0);
  const [nmbOfAppointments, setNumberOfAppointments] = useState(100);
  
  const findLocationId = () => {
    fetch("http://localhost:8080/doctors?id=" + props.id, {
      method: "GET",
    }).then((response) => {
      response.json().then((body) => {
        setLocationID(body.location.id);
        fetch(
          "http://localhost:8080/appointments/get_all_appointments_by_location/" +
            body.location.id,
          {
            method: "GET",
          }
        ).then((response) => {
          response.json().then((body) => {
            setNumberOfAppointments(body.numberOfAppointments);
          });
        });
      });
    });
  };

  function incrementOffset() {
    if (offset <= nmbOfAppointments - 3) {
      offset += 3;
    }

    showPage();
  }

  function decrementOffset() {
    if (offset > 0) {
      offset -= 3;
    }
    showPage();
  }

  function showPage() {
    const numberPage = offset / 3 + 1;
    setValue(numberPage);
    const url =
      "http://localhost:8080/appointments/get_all_appoinments_pagination/" +
      locationID +
      "?pageNo=" +
      offset / 3 +
      "&pageSize=3";

    fetch(url, {
      method: "GET",
    }).then((response) => {
      response.json().then((body) => {
        setData(body.appointmentResponses);
      });
    });
  }

  return (
    <div className={classes.modal}>
      {findLocationId()}
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
              <td>{item.confirmed === true ? "Confirmed" : "Pending"}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <h5>Page {value}</h5>
      <div className={classes.div}>
        <button className={classes.prettybutton} onClick={showPage}>
          View appointments
        </button>
        <button className={classes.prettybutton} onClick={decrementOffset}>
          {" "}
          Previous{" "}
        </button>
        <button className={classes.prettybutton} onClick={incrementOffset}>
          {" "}
          Next{" "}
        </button>
      </div>
    </div>
  );
}

export default PaginationTable;

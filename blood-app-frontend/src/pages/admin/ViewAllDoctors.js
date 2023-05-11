import { useState } from "react";
import classes from "../../components/Components.module.css";
import mainClasses from "../MainPages.module.css";
import adminClasses from "./Admin.module.css";

function ViewAllDoctors() {
  const [doctors, setDoctorsData] = useState([]);

  function getAllDoctors() {
    fetch("http://localhost:8080/doctors/get_all", {
      method: "GET",
    }).then((response) => {
      response.json().then((body) => {
        setDoctorsData(body.doctorList);
        console.log(body.doctorList);
      });
    });
  }

  return (
    <div className={classes.modal}>
      <table>
        <thead>
          <th>ID</th>
          <th>EMAIL</th>
          <th>NAME</th>
          <th>COUNTY</th>
          <th>CNP</th>
        </thead>
        <tbody>
          {doctors.map((item, index) => (
            <tr key = {index}>
              <td>{item.id}</td>
              <td>{item.email}</td>
              <td>{item.name + " " + item.surname}</td>
              <td>{item.county}</td>
              <td>{item.cnp}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <div className={mainClasses.div}>
        <button className={adminClasses.prettybutton} onClick={getAllDoctors}>
          Find doctors
        </button>
      </div>
    </div>

  );
}

export default ViewAllDoctors;

import { useState } from "react";
import components from "../../components/Components.module.css";
import classes from "./Donor.module.css";

function ViewAllLocations(props) {
  const [locations, setLocations] = useState([]);
  function getLocations() {
    const id = props.id;
    const url = "http://localhost:8080/donor/get_all_locations/" + id;
    fetch(url, {
      method: "GET",
    }).then((response) => {
      response.json().then((body) => {
        setLocations(body.locationResponses);
        console.log(body.locationResponses);
      });
    });
  }
  return (
    <div className={components.modal}>
      <table>
        <thead>
          <th>ID</th>
          <th>Name</th>
          <th>Address</th>
          <th>Hour Program</th>
          <th>Day Program</th>
        </thead>
        <tbody>
          {locations.map((item, index) => (
            <tr key={index}>
              <td>{item.id}</td>
              <td>{item.name}</td>
              <td>{item.address}</td>
              <td>{item.startHour + " - " + item.endHour}</td>
              <td>{item.startDay + " - " + item.endDay}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <div className={classes.div}>
        <button className={classes.prettybutton} onClick={props.onCancel}>
          {" "}
          Cancel
        </button>
        <button className={classes.prettybutton} onClick={getLocations}>
          See locations
        </button>
      </div>
    </div>
  );
}

export default ViewAllLocations;

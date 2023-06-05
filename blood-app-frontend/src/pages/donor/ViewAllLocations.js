import { useState } from "react";
import components from "../../components/Components.module.css";
import classes from "./Donor.module.css";
import CardLocation from "../../components/CardLocation";

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
      <div className={classes.div}>
        <button className={classes.prettybutton} onClick={props.onCancel}>
          {" "}
          Cancel
        </button>
        <button className={classes.prettybutton} onClick={getLocations}>
          See locations
        </button>
      </div>
      <div className={classes.div} />

      <div className={classes.div}>
        {locations.map((item, index) => (
          <CardLocation
            key={index}
            locationId={item.id}
            text={item.name}
            address={item.address}
            program={item.startDay + " - " + item.endDay}
            hours={item.startHour + " - " + item.endHour}
            donorId={props.id}
          />
        ))}
      </div>
    </div>
  );
}

export default ViewAllLocations;

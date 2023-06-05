import React, { useState, useEffect } from "react";
import classes from "./Donor.module.css";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import { parseISO } from "date-fns";

function MakeAppointment(props) {
  const [date, setDate] = useState(new Date());
  const donorId = props.donorId;
  const locationId = props.locationId;
  const formattedDate = formatDate(date);
  const [availableDate, setAvailableDate] = useState([]);
  const [isFetching, setIsFetching] = useState(false);
  const [startDate, setStartDate] = useState(new Date());
  const [endDate, setEndDate] = useState(new Date());

  useEffect(() => {
    if (!isFetching) {
      setIsFetching(true);
      const location_id = locationId;
      const urlGetAll =
        "http://localhost:8080/appointments/get_all_available_slots/" + location_id;
      fetch(urlGetAll, {
        method: "GET",
      }).then((response) => {
        response.json().then((body) => {
          const dates = [];
          body.availableDays.forEach((element) => {
            dates.push(new Date(parseISO(element)));
          });
          setAvailableDate(dates);
          setStartDate(new Date(body.startDate));
          setEndDate(new Date(body.endDate));

          console.log(dates);
        });
      });
    }
  },[isFetching, locationId,startDate,endDate]);

  function formatDate(dateString) {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, "0");
    const day = String(date.getDate()).padStart(2, "0");
    return year + "-" + month + "-" + day;
  }

  function createAppointment() {
    // console.log("Donor id: " + donorId);
    // console.log("Location id: " + locationId);
    // console.log("Selected date : " + date);
    // console.log("Formatted date : " + formattedDate);
    const url = "http://localhost:8080/appointments/create";
    const bodyRequest = {
      donorId: donorId,
      locationId: locationId,
      appointmentDate: formattedDate,
    };

    fetch(url, {
      method: "POST",
      body: JSON.stringify(bodyRequest),
      headers: {
        "Content-Type": "application/json",
      },
    }).then((response) => {
      if (response.status === 200) {
        alert("Appointment scheduled with success!");
      } else if (response.status === 400) {
        alert("Appointment can't be scheduled,try with another date!");
      } else {
        response.json().then((body) => {
          console.log(body);
        });
      }
    });
  }
  function isWeekendDay(data) {
    const date = data.getDay();
    return date !== 0 && date !== 6;
  }

  return (
    <div className={classes.modal}>
      <div className={classes.div}>
        <h1>Create new appointment</h1>
      </div>
      <div className={classes.div}>
        <DatePicker
          selected={date}
          minDate={startDate}
          maxDate={endDate}
          excludeDates={availableDate}
          onChange={(date) => setDate(date)}
          filterDate={isWeekendDay}
        />
      </div>
      <div>
        <button className={classes.prettybutton} onClick={props.onCancel}>
          CANCEL
        </button>
        <button className={classes.prettybutton} onClick={createAppointment}>
          SCHEDULE
        </button>
      </div>
    </div>
  );
}

export default MakeAppointment;

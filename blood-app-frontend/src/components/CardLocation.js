import Card from "@mui/material/Card";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";
import { Button, CardActionArea, CardActions } from "@mui/material";
import MakeAppointment from "../pages/donor/MakeAppointment";
import Backdrop from "../components/Backdrop";
import { useState, useEffect,useRef } from "react";
//import ReactStars from 'react-stars';
import React from "react";

function CardLocation(props) {
  const ratingRef = useRef(true);

  const [renderAppointment, setRenderAppointment] = useState(false);
  const [ratingEdit, setRatingEdit] = useState(false);
  const [isFetching, setIsFetching] = useState(false);
  const [ratingValue, setRatingValue] = useState(0);
  const donorId = props.donorId;
  const locationId = props.locationId;

  useEffect(() => {
    console.log("Rating edit changed:", ratingEdit);
  }, [ratingEdit]);

  useEffect(() => {
    const url = "http://localhost:8080/locations/getRating/" + locationId;

    fetch(url,{
      method : "GET"
    }).then((response) => {
      response.json().then((body) => {
        setRatingValue(body.averageValue);
      })
    })
  });

  useEffect(() => {
    if (!isFetching) {
      setIsFetching(true);
      const location_id = locationId;
      const donor_id = donorId;
      const url =
        "http://localhost:8080/appointments/get_all_locations_confirmed?donorId=" +
        donor_id +
        "&locationId=" +
        location_id;

      fetch(url, {
        method: "GET",
      }).then((response) => {
        response.json().then((body) => {
          setRatingEdit(body.confirmed);
        });
      });
    }
  }, [isFetching, locationId, donorId, ratingEdit]);

  function renderMakeAppointment() {
    setRenderAppointment(true);
  }
  function cancelMakeAppointment() {
    setRenderAppointment(false);
  }

  function insertNewRating(){
    const location_id = locationId; 
    const rating = ratingRef.current.value;
    const url = "http://localhost:8080/locations/add_new_rating";
    const bodyRequest = {
      locationId : location_id,
      ratingValue : rating
    }
    if (rating === "") {
      alert("Please complete the rate field!");
    }else{
      fetch(url,{
        method: "POST",
        body: JSON.stringify(bodyRequest),
        headers: {
          "Content-Type": "application/json",
        },
      }).then((response) => {
        if(response.status === 200){
          alert("Rate added with succes!");
        }
        response.json().then((body) => {
          console.log(body.averageValue);
          const rate  = document.getElementById("textRate");
          rate.value = "";
        })
      })
    }
  }

  return (
    <div style={{ padding: 20 }}>
      <Card sx={{ maxWidth: 400 }}>
        <CardActionArea>
          <CardContent>
            <Typography gutterBottom variant="h5" component="div">
              {props.text}
            </Typography>
            <Typography variant="body2" color="text.secondary">
              ID: {props.locationId}
            </Typography>
            <Typography variant="body2" color="text.secondary">
              {props.address}
            </Typography>
            <Typography variant="body2" color="text.secondary">
              {props.program}
            </Typography>
            <Typography variant="body2" color="text.secondary">
              {props.hours}
            </Typography>
            <Typography variant="body2" color="text.secondary">
              Rating : {ratingValue}
            </Typography>
          </CardContent>
        </CardActionArea>
        <CardActions>
          <Button
            variant="contained"
            size="small"
            color="primary"
            onClick={renderMakeAppointment}
          >
            {" "}
            Make an appointment
          </Button>
        </CardActions>
        {ratingEdit && (
          <CardActions>
            <input type="text" id="textRate"ref={ratingRef} disabled={!ratingEdit} />
          </CardActions>
        )}
        {ratingEdit && (
          <CardActions>
            <Button
              variant="contained"
              size="small"
              color="primary"
              onClick={insertNewRating}
            >
              {" "}
              Rate{" "}
            </Button>
          </CardActions>
        )}
      </Card>
      {renderAppointment && (
        <MakeAppointment
          donorId={props.donorId}
          locationId={props.locationId}
          onCancel={cancelMakeAppointment}
        />
      )}
      {renderAppointment && <Backdrop onCancel={cancelMakeAppointment} />}
    </div>
  );
}

export default CardLocation;

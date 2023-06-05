import classes from "./Donor.module.css";
import components from "../../components/Components.module.css";
import { useNavigate } from "react-router-dom";

function DeleteDonor(props) {
  const navigate = useNavigate();

  function deleteDonor() {
    const id = props.id;
    console.log(id);
    const url = "http://localhost:8080/donor/delete/" + id;

    fetch(url, {
      method: "DELETE",
    }).then((response) => {
      response.json().then((body) => {
        console.log(body.id);
        if (body.id === id) {
          alert("User deleted with success!");
          navigate("/");
        } else {
          alert("Something goes wrong, try later!");
        }
      });
    });
  }

  return (
    <div className={components.modal}>
      <div className={classes.div2}>
        <h3> Are you sure?</h3>
      </div>
      <div>
        <button className={classes.prettybutton} onClick={props.onCancel}>
          Cancel
        </button>
        <button className={classes.prettybutton} onClick={deleteDonor}>
          Delete
        </button>
      </div>
    </div>
  );
}

export default DeleteDonor;

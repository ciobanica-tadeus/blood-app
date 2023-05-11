import classes from "../../components/Components.module.css";
import adminClasses from "./Admin.module.css";
import { useRef } from "react";

function DeleteDoctor(props) {
  const idRef = useRef();
  function cancelDelete() {
    props.onCancel();
  }
  function deleteDoctor() {
    const id = idRef.current.value;
    const url = "http://localhost:8080/doctors/delete/" + id;

    if (id === "") {
      alert("Complete id field to perform deleting!");
    }
    console.log(url);
    fetch(url, {
      method: "DELETE",
    }).then((response) => {
      if (response.status === 200) {
        alert("Doctor deleted!");
      }
    });
  }

  return (
    <div className={classes.modal}>
      <div className={adminClasses.div}>
        <label className={adminClasses.label}>ID</label>
        <input className={adminClasses.input} ref={idRef} type="text" />
      </div>
      <div>
        <button className={adminClasses.prettybutton} onClick={cancelDelete}>
          Cancel
        </button>
        <button className={adminClasses.prettybutton} onClick={deleteDoctor}>
          Delete
        </button>
      </div>
    </div>
  );
}

export default DeleteDoctor;

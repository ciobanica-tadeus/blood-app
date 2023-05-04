import classes from "./Components.module.css";

function CreateDoctor(props) {
  function cancelComponent() {
    props.onCancel();
  }

  return (
    <div className={classes.modal}>
      <div>
        <label>ID:</label>
        <input type="text" />
      </div>
      <div>
        <button onClick={cancelComponent}>Cancel</button>
      </div>
    </div>
  );
}

export default CreateDoctor;

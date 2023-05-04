import CreateDoctor from "../../components/CreateDoctor";
import classes from "./Admin.module.css";
import { useState } from "react";
import Backdrop from "../../components/Backdrop";
import CardButton from "../../components/CardButton";

function AdminPage(props) {
  const [createIsOpen, setCreateIsOpen] = useState(false);

  function renderCreateComponent() {
    setCreateIsOpen(true);
  }

  function closeCreateComponent() {
    setCreateIsOpen(false);
  }

  return (
    <div>
      <h1>Hello, admin!</h1>
      <div className={classes.div}>
        <CardButton text="Find doctor" />
      </div>
      <div className={classes.div}>
        <CardButton onClick={renderCreateComponent} text=" Create Doctor" />
      </div>
      <div className={classes.div}>
        <CardButton text = "Edit Doctor"/>
      </div>
      <div className={classes.div}>
        <CardButton text = "Delete Doctor" />
      </div>
      {createIsOpen && <CreateDoctor onCancel={closeCreateComponent} />}
      {createIsOpen && <Backdrop onCancel={closeCreateComponent} />}
    </div>
  );
}

export default AdminPage;

import CreateDoctor from "./CreateDoctor";
import classes from "./Admin.module.css";
import { useState } from "react";
import Backdrop from "../../components/Backdrop";
import CardButton from "../../components/CardButton";
import ViewAllDoctors from "./ViewAllDoctors";
import DeleteDoctor from "./DeleteDoctor";
import EditDoctor from "./EditDoctor";
import ComponentNavBar from "../../components/ComponentNavBar";

function AdminPage(props) {
  const [createIsOpen, setCreateIsOpen] = useState(false);
  const [createGetAllDoctors, setGetAllDoctors] = useState(false);
  const [createDeleteDoctor, setDeleteDoctor] = useState(false);
  const [createEditDoctor, setEditDoctor] = useState(false);

  function renderCreateComponent() {
    setCreateIsOpen(true);
  }

  function closeCreateComponent() {
    setCreateIsOpen(false);
  }

  function renderGetAllDoctors() {
    setGetAllDoctors(true);
  }

  function closeGetAllDoctors() {
    setGetAllDoctors(false);
  }

  function renderDeleteDoctor() {
    setDeleteDoctor(true);
  }

  function closeDeleteDoctor() {
    setDeleteDoctor(false);
  }

  function renderEditDoctor() {
    setEditDoctor(true);
  }

  function closeEditDoctor() {
    setEditDoctor(false);
  }

  return (
    <div>
      <div>
      <ComponentNavBar text = "Hello, Admin!"/>
      </div>
      <div className={classes.div}>
        <CardButton onClick={renderGetAllDoctors} text="Get all doctors" />
      </div>
      <div className={classes.div}>
        <CardButton onClick={renderCreateComponent} text=" Create Doctor" />
      </div>
      <div className={classes.div}>
        <CardButton onClick={renderEditDoctor} text="Edit Doctor" />
      </div>
      <div className={classes.div}>
        <CardButton onClick={renderDeleteDoctor} text="Delete Doctor" />
      </div>
      {createIsOpen && <CreateDoctor onCancel={closeCreateComponent} />}
      {createIsOpen && <Backdrop onCancel={closeCreateComponent} />}
      {createGetAllDoctors && <ViewAllDoctors onCancel={closeGetAllDoctors} />}
      {createGetAllDoctors && <Backdrop onCancel={closeGetAllDoctors} />}
      {createDeleteDoctor && <DeleteDoctor onCancel={closeDeleteDoctor} />}
      {createDeleteDoctor && <Backdrop onCancel={closeDeleteDoctor} />}
      {createEditDoctor && <EditDoctor onCancel={closeEditDoctor} />}
      {createEditDoctor && <Backdrop onCancel={closeEditDoctor} />}
    </div>
  );
}

export default AdminPage;

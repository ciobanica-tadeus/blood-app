import ComponentNavBar from "../../components/ComponentNavBar";
import CardButton from "../../components/CardButton";
import { useState } from "react";
import Backdrop from "../../components/Backdrop";
import { useLocation } from "react-router-dom";
import PaginationTable from "./PaginationTable";
import ViewTodayAppointments from "./ViewTodayAppointments";
import ChangeAppointmentStatus from "./ChangeAppointmentStatus";

function DoctorPage() {
  const location = useLocation();
  const [viewTodays, setViewTodays] = useState(false);
  const [viewAll, setViewAll] = useState(false);
  const [confirmed, setConfirmed] = useState(false);

  function renderViewTodays() {
    setViewTodays(true);
  }

  function closeViewTodays() {
    setViewTodays(false);
  }

  function renderViewAll() {
    setViewAll(true);
  }

  function closeViewAll() {
    setViewAll(false);
  }

  function renderConfirmed() {
    setConfirmed(true);
  }

  function closeConfirmed() {
    setConfirmed(false);
  }

  return (
    <div>
      <div>
        <ComponentNavBar text="Hello, DOCTOR!" />
      </div>
      <div>
        <CardButton text="Today appointments" onClick={renderViewTodays} />
      </div>
      <div>
        <CardButton text="All appointments" onClick={renderViewAll} />
      </div>
      <div>
        <CardButton text="Confirm appointment" onClick={renderConfirmed} />
      </div>
      {viewTodays && <Backdrop onCancel={closeViewTodays} />}
      {viewTodays && <ViewTodayAppointments id={location.state.doctorId} />}
      {viewAll && <Backdrop onCancel={closeViewAll} />}
      {viewAll && <PaginationTable id={location.state.doctorId} />}
      {confirmed && <Backdrop onCancel={closeConfirmed}/>}
      {confirmed && <ChangeAppointmentStatus/>}
    </div>
  );
}

export default DoctorPage;

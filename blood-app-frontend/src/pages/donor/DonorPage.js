import { useState } from "react";
import CardButton from "../../components/CardButton";
import ComponentNavBar from "../../components/ComponentNavBar";
import EditDonor from "./EditDonor";
import Backdrop from "../../components/Backdrop";
import { useLocation } from "react-router-dom";
import DeleteDonor from "./DeleteDonor";
import ViewAllLocations from "./ViewAllLocations";
import AppointmentsList from "./AppointmentList";
import DeleteAppointment from "./DeleteAppointment";

function DonorPage() {
  const location = useLocation();
  const [editModal, setEditModal] = useState(false);
  const [deleteModal, setDeleteModal] = useState(false);
  const [viewLocationsModal, setViewLocationModal] = useState(false);
  const [viewAppointmentsModal, setViewAppointmentsModal] = useState(false);
  const [deleteAppointment, setDeleteAppointment] = useState(false);

  function renderEditModal() {
    setEditModal(true);
  }

  function closeEditModal() {
    setEditModal(false);
  }

  function renderDeleteModal() {
    setDeleteModal(true);
  }

  function closeDeleteModal() {
    setDeleteModal(false);
  }

  function renderViewLocationsModal() {
    setViewLocationModal(true);
  }

  function closeViewLocationsModal() {
    setViewLocationModal(false);
  }
  function renderViewAppointmentsModal() {
    setViewAppointmentsModal(true);
  }

  function closeViewAppointmentsModal() {
    setViewAppointmentsModal(false);
  }
  function renderDeleteAppointment() {
    setDeleteAppointment(true);
  }

  function closeDeleteAppointment() {
    setDeleteAppointment(false);
  }

  return (
    <div>
      <div>
        <ComponentNavBar text="Hello, Donor!" />
      </div>
      <div>
        <CardButton onClick={renderEditModal} text="Edit account" />
      </div>
      <div>
        <CardButton onClick={renderDeleteModal} text="Delete account" />
      </div>
      <div>
        <CardButton
          onClick={renderViewLocationsModal}
          text="View my locations"
        />
      </div>
      <div>
        <CardButton
          onClick={renderViewAppointmentsModal}
          text="View my appointments"
        />
      </div>
      <div>
        <CardButton
          onClick={renderDeleteAppointment}
          text="Delete an appointment"
        />
      </div>
      {editModal && (
        <EditDonor onCancel={closeEditModal} id={location.state.donorId} />
      )}
      {editModal && <Backdrop onCancel={closeEditModal} />}
      {deleteModal && (
        <DeleteDonor onCancel={closeDeleteModal} id={location.state.donorId} />
      )}
      {deleteModal && <Backdrop onCancel={closeDeleteModal} />}
      {viewLocationsModal && (
        <ViewAllLocations
          onCancel={closeViewLocationsModal}
          id={location.state.donorId}
        />
      )}
      {viewLocationsModal && <Backdrop onCancel={closeViewLocationsModal} />}
      {viewAppointmentsModal && (
        <AppointmentsList
          onCancel={closeViewAppointmentsModal}
          id={location.state.donorId}
        />
      )}
      {viewAppointmentsModal && (
        <Backdrop onCancel={closeViewAppointmentsModal} />
      )}
      {deleteAppointment && (
        <DeleteAppointment onCancel={closeDeleteAppointment} />
      )}
      {deleteAppointment && <Backdrop onCancel={closeDeleteAppointment} />}
    </div>
  );
}

export default DonorPage;

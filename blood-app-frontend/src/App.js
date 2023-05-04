import AdminPage from "./pages/admin/AdminPage";
import Login from "./pages/Login";
import Welcome from "./pages/Welcome";
import SignUp from "./pages/SignUp";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import DoctorPage from "./pages/doctor/DoctorPage";
import DonorPage from "./pages/donor/DonorPage";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" exact element={<Welcome />}></Route>
        <Route path="/login" element={<Login />}></Route>
        <Route path="/signup" element={<SignUp />}></Route>
        <Route path="/admin" element={<AdminPage />}></Route>
        <Route path="/doctor" element={<DoctorPage />}></Route>
        <Route path="/donor" element={<DonorPage />}></Route>
      </Routes>
    </Router>
  );
}

export default App;

import { useEffect, useState } from "react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Home from "./components/Home";
import Meetings from "./components/Meetings";
import MeetingForm from "./components/MeetingForm";
import MeetingConfirmDelete from "./components/MeetingConfirmDelete";
import Login from "./components/Login";
import Register from "./components/Register";
import Nav from "./components/Nav";
import NotFound from "./components/NotFound";
import { refresh } from "./services/auth";
import AuthContext from "./contexts/AuthContext";


function App() {

  const [user, setUser] = useState();

  useEffect(() => {
    refresh().then(setUser).catch(logout);
  }, []);

  const logout = () => {
    setUser();
    localStorage.removeItem("jwt");
  };

  const context = {
    user,
    login: setUser,
    logout
  };

  return (
    <AuthContext.Provider value={context}>
      <BrowserRouter>
        <Nav />
        <div className="container mt-2">
          <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/meetings" element={<Meetings />} />
            <Route path="/add" element={user ? <MeetingForm /> : <Login />} />
            <Route path="/edit/:meetingId" element={user ? <MeetingForm /> : <Login />} />
            <Route path="/delete/:meetingId" element={user ? <MeetingConfirmDelete /> : <Login />} />
            <Route path="/login" element={<Login />} />
            <Route path="/register" element={<Register />} />
            <Route path="*" element={<NotFound />} />
          </Routes>
        </div >
      </BrowserRouter>
    </AuthContext.Provider >
  );
}

export default App;

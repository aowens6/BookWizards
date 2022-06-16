import { useContext } from "react";
import { Link } from "react-router-dom";
import AuthContext from "../contexts/AuthContext";

export default function Nav() {

    const { user, logout } = useContext(AuthContext);

    return (


        <nav className="navbar navbar-expand-xl navbar-light bg-light mt-3 px-5">
            <Link className="navbar-brand" to="/">Home</Link>
            <div className="container-fluid" id="navbarNav">
                <Link className="nav-link" to="/meetings">Meetings</Link>
                <div className="col d-flex justify-content-end">
                    {user ? <button className="btn btn-danger" onClick={logout}>Log Out</button>
                        : <Link to="/login" className="btn btn-success">Log In</Link>}
                    {user && <Link to="/add" className="btn btn-primary ms-2">Add Meeting</Link>}
                </div>
            </div>
        </nav>
    );
}
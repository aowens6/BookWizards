import { useContext, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import AuthContext from "../contexts/AuthContext";
import { create } from "../services/auth";

function Register() {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [hasError, setHasError] = useState(false);

    //const navigate = useNavigate();

    //const { login } = useContext(AuthContext);

    const handleUsername = (evt) => setUsername(evt.target.value);
    const handlePassword = (evt) => setPassword(evt.target.value);
    const handleSubmit = (evt) => {
        evt.preventDefault();
        create({ username, password })
    };

    return (
        <form onSubmit={handleSubmit}>
            <h1>Register New User</h1>
            <div>
                <label htmlFor="username" className="form-label">Username</label>
                <input type="text" className="form-control" id="username" name="username"
                    value={username} onChange={handleUsername} />
            </div>
            <div>
                <label htmlFor="password" className="form-label">Password</label>
                <input type="password" className="form-control" id="password" name="password"
                    value={password} onChange={handlePassword} />
            </div>
            <div className="mt-2">
                <button type="submit" className="btn btn-primary">Register</button>
                <Link to="/login" className="btn btn-secondary ms-2">Cancel</Link>
            </div>

            {hasError && <div className="alert alert-danger mt-3">
                Bad Credentials
            </div>}
        </form>);
}

export default Register;
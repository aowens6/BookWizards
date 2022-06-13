import { useContext, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import AuthContext from "../contexts/AuthContext";
import { authenticate } from "../services/auth";

function Login() {

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [hasError, setHasError] = useState(false);

    const navigate = useNavigate();

    const { login } = useContext(AuthContext);

    const handleUsername = (evt) => setUsername(evt.target.value);
    const handlePassword = (evt) => setPassword(evt.target.value);
    const handleSubmit = (evt) => {
        evt.preventDefault();
        authenticate({ username, password })
            .then(user => {
                login(user);
                navigate("/");
            })
            .catch(() => setHasError(true));
    };

    return (
        <form onSubmit={handleSubmit}>
            <h1>Login</h1>
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
                <button type="submit" className="btn btn-primary">Log In</button>
                <Link to="/" className="btn btn-secondary ms-2">Cancel</Link>
            </div>
            {hasError && <div className="alert alert-danger mt-3">
                Bad Credentials
            </div>}
        </form>);
}

export default Login;
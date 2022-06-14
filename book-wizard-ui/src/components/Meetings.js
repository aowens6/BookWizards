import { useState, useEffect, useContext } from "react";
import { Link } from "react-router-dom";
import AuthContext from "../contexts/AuthContext";
import { findAll } from "../services/meeting";

function Meetings (){

    const [meetings, setMeetings] = useState([]);
    const { user } = useContext(AuthContext);

    useEffect(() => {
        findAll()
            .then(setMeetings)
            .catch(console.error);
    }, []);

    return (
        <div>
            <div className="row row-cols-lg-12 row-cols-md-12 row-cols-12 mx-3 g-3">
                {meetings.map(m =>
                    <div className="card" key={m.meetingId}>
                        <div className="card-body">
                            <h5 className="card-title">{m.groupName}</h5>
                            <p>{m.description}</p>
                        </div>
                        {user && <div className="card-footer d-flex justify-content-center">
                        {user.authorities === "ADMIN" &&
                            <Link to={`/delete/${m.meetingId}`} className="btn btn-danger me-2">Delete</Link>}
                        {user.authorities === "ADMIN" &&
                            <Link to={`/edit/${m.meetingId}`} className="btn btn-secondary">Edit</Link>}
                    </div>}
                    </div>
                )}
            </div>

        </div>
    );
}

export default Meetings;
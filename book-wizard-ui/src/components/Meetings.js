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

        </div>
    );
}

export default Meetings;
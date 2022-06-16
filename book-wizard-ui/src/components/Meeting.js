import { useParams, Link } from "react-router-dom";
import { useState, useEffect, useContext } from "react";
import AuthContext from "../contexts/AuthContext";

function Meeting (){

    const {id} = useParams();
    const { user } = useContext(AuthContext);
    
    return (
        <div>
            {user.authorities === "ROLE_ADMIN" &&
                <Link to={`/delete/${id}`} className="btn btn-danger me-2">Delete</Link>}
            {user.authorities === "ROLE_ADMIN" &&
                <Link to={`/edit/${id}`} className="btn btn-secondary">Edit</Link>}
        </div>
    );
}
export default Meeting;
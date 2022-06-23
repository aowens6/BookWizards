import { useParams, Link } from "react-router-dom";
import { useState, useEffect, useContext } from "react";
import AuthContext from "../contexts/AuthContext";
import { findById } from "../services/meeting";

function Meeting (){

    const [meeting, setMeeting] = useState(null);
    const {meetingId} = useParams();
    const { user } = useContext(AuthContext);

    useEffect(() => {
        findById(meetingId)
            .then(meeting => {
                setMeeting(meeting);
            })
            .catch(console.error);
    }, []);
    
    return (<>
            {meeting && (<div>
                <div className="card text-dark bg-light" key={meeting.meetingId}>
                    <div className="card-header">
                        <h5 className="card-title">{meeting.groupName}</h5>
                    </div>
                    <div className="card-body">
                        <p><strong>Group Description : &nbsp;&nbsp;&nbsp;&nbsp;</strong> <em>{meeting.description}</em></p>
                        <p><strong>Current Book: &nbsp;&nbsp;&nbsp;&nbsp;</strong> <em>{`Title: ${meeting.book.title} `}</em></p>
                        <p>{`Author: ${meeting.book.author.firstName} ${meeting.book.author.lastName}`}</p>
                        <p>{`Genre: ${meeting.book.genre.genreName}`}</p>
                        <p>{`Start Time:  ${meeting.startDateTime}`}</p>
                        <p>{`End Time:  ${meeting.endDateTime}`}</p>

                        <p><strong>Organized By: &nbsp;&nbsp;&nbsp;&nbsp;</strong> <em>{meeting.organizer.username}</em></p>
                    </div>
                    {user && <div className="card-footer d-flex justify-content-end">
                        <Link to={`/videoCallPage`} className="btn btn-info me-2">Join Meeting</Link>
                    </div>}
                </div>
            </div>)
            }
            {user && <div className="mt-2">
                {user.authorities === "ROLE_ADMIN" &&
                    <Link to={`/delete/${meetingId}`} className="btn btn-danger me-2">Delete</Link>}
                {user.authorities === "ROLE_ADMIN" &&
                    <Link to={`/edit/${meetingId}`} className="btn btn-secondary">Edit</Link>}
            </div>
            }
        </>
    );
}
export default Meeting;
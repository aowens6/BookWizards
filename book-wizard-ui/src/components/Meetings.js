import { useState, useEffect, useContext } from "react";
import { Link } from "react-router-dom";
import AuthContext from "../contexts/AuthContext";
import { findAll } from "../services/meeting";
import { bookResult } from "../services/bookObject"

function Meetings (){

    const [meetings, setMeetings] = useState([]);
    const [unsearchedMeetings, setUnsearchedMeetings] = useState([]);
    const [searchedMeetings, setSearchedMeetings] = useState([]);
    const { user } = useContext(AuthContext);

    useEffect(() => {
        findAll()
            .then(meetingList => {
            
                const tmpMeetings = [];
                let tmpBook = {};
                for (let i = 0; i < meetingList.length; i++){
                    tmpBook = bookResult(meetingList[i].bookId);
                    let book = tmpBook.book;
                    tmpMeetings.push({...meetingList[i], book});
                }

                setMeetings(tmpMeetings);
                
                setUnsearchedMeetings(meetingList);
            })
            .catch(console.error);
    }, []);

    function filterMeetings(filterKey, searchTerm) {

        return meetings.filter(m => m.book[filterKey].toLowerCase().includes(searchTerm.toLowerCase()));

    };

    const handleSubmit = (evt) => {
        evt.preventDefault();
        let selectBox = document.getElementById("search-select");
        let filterKey = selectBox.options[selectBox.selectedIndex].value;
        let searchBox = document.getElementById("search-box");
        let searchTerm = searchBox.value;

        if (filterKey === "Search Filter" || searchTerm.length === 0){
            setSearchedMeetings(meetings);
        }else {
            let tmpFilteredMeetings = filterMeetings(filterKey, searchTerm);
            setSearchedMeetings(tmpFilteredMeetings);
        };
    };

    return (
        <div>
            <form onSubmit={handleSubmit} className="m-5">
                <div className="input-group">
                    <input id="search-box" type="search" className="form-control rounded" placeholder="Search" aria-label="Search" aria-describedby="search-addon" />
                    <button type="submit" className="btn btn-outline-primary">Search</button>
                </div>
                <select id="search-select" className="form-select form-select-sm mt-2" aria-label=".form-select-sm example">
                    <option defaultValue>Search Filter</option>
                    <option value="title">Title</option>
                    <option value="author">Author</option>
                    <option value="genre">Genre</option>
                </select>
            </form>
            <div className="row row-cols-lg-12 row-cols-md-12 row-cols-12 mx-3 g-3">
                
                {searchedMeetings.length === 0 && unsearchedMeetings.map(m =>
                    <div className="card text-dark bg-light" key={m.meetingId}>
                        <div className="card-header">
                            <h5 className="card-title">{m.groupName}</h5>
                        </div>
                        <div className="card-body">
                            <p><strong>Current Book: &nbsp;&nbsp;&nbsp;&nbsp;</strong> <em>{`Title: ${m.book.title} | Author: ${m.book.author.firstName} ${m.book.author.lastName} | Genre: ${m.book.genre.genreName}`}</em></p>
                        </div>
                        {user && <div className="card-footer d-flex justify-content-end">
                            <Link to={`/meeting/${m.meetingId}`} className="btn btn-info me-2">More Info</Link>
                        </div>}
                    </div>
                )}
                {searchedMeetings.map(m =>
                    <div className="card text-dark bg-light" key={m.meetingId}>
                        <div className="card-header">
                            <h5 className="card-title">{m.groupName}</h5>
                        </div>
                        <div className="card-body">
                            <p><strong>Current Book: &nbsp;&nbsp;&nbsp;&nbsp;</strong> <em>{`Title: ${m.book.title} | Author: ${m.book.author} | Genre: ${m.book.genre.genreName}`}</em></p>
                        </div>
                        {user && <div className="card-footer d-flex justify-content-end">
                            <Link to={`/meeting/${m.meetingId}`} className="btn btn-info me-2">More Info</Link>
                        </div>}
                    </div>
                )}
            </div>

        </div>
    );
}

export default Meetings;
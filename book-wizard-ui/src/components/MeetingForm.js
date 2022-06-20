import { useState, useEffect, useContext } from "react";
import DateTimePicker from 'react-datetime-picker';
import AuthContext from "../contexts/AuthContext";
import { bookResult, allBookResults, allBookResultsTest } from "../services/bookObject";
import { findAll as findAllBooks } from "../services/book";
import { findAll as findAllGenres } from "../services/genre";
import { findAll as findAllAuthors } from "../services/author";
import { useBootstrapBreakpoints } from "react-bootstrap/esm/ThemeProvider";

function MeetingForm (){

    const [addBook, setAddBook] = useState(false);
    const [booksAreSet, setBooksAreSet] = useState(false);
    const [books, setBooks] = useState([]);
    const [startDateTime, setStartDateTime] = useState(new Date());
    const [endDateTime, setEndDateTime] = useState(new Date());
    const { user } = useContext(AuthContext);
    const [book, setBook] = useState({
        "bookId": 0
    });

    useEffect(() => {
        allBookResultsTest()
            .then((br) => {
                setBooks(br);
            })
            .catch(console.log);
    }, []);


    const handleSubmit = (evt) => {
        evt.preventDefault();
    }

    function toggleAddMode(){
        setAddBook(!addBook);
        console.log(user.sub);
    }
    function onChange(){

    }

    // <form onSubmit={handleSubmit} className="m-5">
    // {!addBook ? : }

    return (

        <div>
            <h1 className="display-4">Add your book club</h1>
            <form onSubmit={handleSubmit} className="m-5">
                <div>
                {!addBook ? <div>
                    <select id="search-select" className="form-select form-select-sm m-4" aria-label=".form-select-sm">
                        <option defaultValue>Choose a book...</option>
                        {books.map((br, i) => 
                            <option key={i} value={br.book.bookId}>Title: {br.book.title} | Author: {br.book.author} | Genre: {br.book.genre}</option>)}
                    </select>
                    <h5>Don't see your book in our list?</h5>
                    <button className="btn btn-primary m-3" onClick={toggleAddMode}>Add a book</button>
                </div>
                :   <div>
                        <div className="form-group">
                            <label htmlFor="bookTitle">Book Title</label>
                            <input type="url" className="form-control" id="bookTitle" name="bookTitle"
                                value={book.title} onChange={onChange} />
                        </div>
                        <div className="form-group">
                            <label htmlFor="bookTitle">Book Author</label>
                            <input type="url" className="form-control" id="bookAuthor" name="bookAuthor"
                                value={book.title} onChange={onChange} />
                        </div>
                        <div className="form-group">
                            <label htmlFor="bookTitle">Book Genre</label>
                            <input type="url" className="form-control" id="bookGenre" name="bookGenre"
                                value={book.title} onChange={onChange} />
                        </div>
                        <button className="btn btn-primary m-3" onClick={toggleAddMode}>See our book list</button>
                    </div>
                }
            </div>
            <div className="form-group m-3">
                <label htmlFor="groupName"><h5>Group Name</h5></label>
                <input type="url" className="form-control" id="groupName" name="groupName"
                    value={book.title} onChange={onChange} />
            </div>
            <div className="form-group m-3">
                <label htmlFor="groupDescription" className="form-label"><h5>Group Description</h5></label>
                <textarea type="url" className="form-control" id="groupDescription" name="groupDescription"
                    value={book.title} onChange={onChange} rows="4"></textarea>
            </div>
            <div>
                <div className="m-4">
                    <h5>Start Time</h5>
                    <DateTimePicker onChange={setStartDateTime} value={startDateTime} />
                </div>
                <div className="m-4">
                    <h5>End Time</h5>
                    <DateTimePicker onChange={setEndDateTime} value={endDateTime} />
                </div>
            </div>
            </form>
        </div>

    );
}

export default MeetingForm;
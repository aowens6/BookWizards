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
    const [errorMessages, setErrorMessages] = useState([]);
    const [books, setBooks] = useState([]);
    const [startDateTime, setStartDateTime] = useState(new Date());
    const [endDateTime, setEndDateTime] = useState(new Date());
    const { user } = useContext(AuthContext);
    const [book, setBook] = useState({
        "bookId": 0
    });

    const [meeting, setMeeting] = useState({
        "meetingId": 0,
        "groupName": "",
        "description": "",
        "bookId": 0,
        "organizerId": 0,
        "startDateTime": "",
        "endDateTime": ""
    })

    useEffect(() => {
        allBookResultsTest()
            .then((br) => {
                setBooks(br);
            })
            .catch(console.log);
    }, []);


    const handleSubmit = (evt) => {
        evt.preventDefault();
        
        const tmpErrorMessages = [];
        const tmpMeeting = {...meeting};

        if (addBook){

            const bookTitleInputBox = document.getElementById("bookTitle");
            const bookTitleValue = bookTitleInputBox.value;
            const bookAuthorInputBox = document.getElementById("bookAuthor");
            const bookAuthorValue = bookAuthorInputBox.value;
            const bookGenreInputBox = document.getElementById("bookGenre");
            const bookGenreValue = bookGenreInputBox.value;

            if (bookTitleValue.length === 0){
                tmpErrorMessages.push("Please enter book title");
            }
            if (bookAuthorValue.length === 0){
                tmpErrorMessages.push("Please enter book author");
            }
            if (bookGenreValue.length === 0){
                tmpErrorMessages.push("Please enter book genre");
            }

        } else {

            const bookSelectBox = document.getElementById("search-select");
            const selectedBookId = bookSelectBox.options[bookSelectBox.selectedIndex].value;
            if (selectedBookId === "Choose a book...") {
                tmpErrorMessages.push("Please choose book from list, or add a book");
            } else {

            }
        }

    }

    function toggleAddMode(){
        setAddBook(!addBook);
        //console.log(user.sub);

    }

    function onChange(){
        //console.log(startDateTime);
    }

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
                            <label htmlFor="bookAuthor">Book Author</label>
                            <input type="url" className="form-control" id="bookAuthor" name="bookAuthor"
                                value={book.title} onChange={onChange} />
                        </div>
                        <div className="form-group">
                            <label htmlFor="bookGenre">Book Genre</label>
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
                    <DateTimePicker format="yyyy-MM-dd HH:mm:ss" onChange={setStartDateTime} value={startDateTime} />
                </div>
                <div className="m-4">
                    <h5>End Time</h5>
                    <DateTimePicker format="yyyy-MM-dd HH:mm:ss" onChange={setEndDateTime} value={endDateTime} />
                </div>
            </div>
            </form>
        </div>

    );
}

export default MeetingForm;
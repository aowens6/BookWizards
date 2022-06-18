import { useState, useEffect, useContext } from "react";
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
    const { user } = useContext(AuthContext);

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

    function addMode(){
        setAddBook(true);
    }

    // <form onSubmit={handleSubmit} className="m-5">

    return (

        <div>
            <h1 className="display-4">Add your book club</h1>
            <select id="search-select" className="form-select form-select-sm m-4" aria-label=".form-select-sm">
                <option defaultValue>Choose a book...</option>
                {books.map((br, i) => 
                    <option key={i} value={br.book.bookId}>Title: {br.book.title} | Author: {br.book.author} | Genre: {br.book.genre}</option>)}
            </select>
            <h5>Don't see your book in our list?</h5>
            <button className="btn btn-primary " onClick={addMode}>Add a book</button>
        </div>

    );
}

export default MeetingForm;
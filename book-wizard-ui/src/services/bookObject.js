import {findById as findBookById, findAll as findAllBooks} from "./book";
import {findById as findAuthorById} from "./author";
import {findById as findGenreById} from "./genre";


export function bookResult(id) {

    const result = {
        "book": {
            "bookId": id,
            "title": "",
            "author": "",
            "genre": ""
        },
        "errors": []
    }

    findBookById(id)
        .then(b => {

            result.book.title = b.title;

            findAuthorById(b.authorId)
                .then(a => result.book.author = `${a.firstName} ${a.lastName}`)
                .catch(err => result.errors.push(err));

            findGenreById(b.genreId)
                .then(g => result.book.genre = g.genreName)
                .catch(err => result.errors.push(err));
        })
        .catch(err => result.errors.push(err));

    return result;

}

export function allBookResults() {

    let allResults = [];

    findAllBooks()
        .then(b => {
            for (let i = 0; i < b.length; i++){

                let result = {
                    "book": {
                        "bookId": 0,
                        "title": "",
                        "author": "",
                        "genre": ""
                    },
                    "errors": []
                }

                let tmpResult = result;
    
                tmpResult.book.bookId = b[i].bookId;
                tmpResult.book.title = b[i].title;
    
                findAuthorById(b[i].authorId)
                    .then(a => tmpResult.book.author = `${a.firstName} ${a.lastName}`)
                    .catch(err => tmpResult.errors.push(err));
    
                findGenreById(b[i].genreId)
                    .then(g => tmpResult.book.genre = g.genreName)
                    .catch(err => tmpResult.errors.push(err));
    
                allResults.push(tmpResult);
    
            }

        })
    

    return allResults;

}

export async function allBookResultsTest() {

    let allResults = [];

    const b = await findAllBooks();
    for (let i = 0; i < b.length; i++){

        let result = {
            "book": {
                "bookId": 0,
                "title": "",
                "author": "",
                "genre": ""
            },
            "errors": []
        }

        let tmpResult = result;

        tmpResult.book.bookId = b[i].bookId;
        tmpResult.book.title = b[i].title;

        findAuthorById(b[i].authorId)
            .then(a => tmpResult.book.author = `${a.firstName} ${a.lastName}`)
            .catch(err => tmpResult.errors.push(err));

        findGenreById(b[i].genreId)
            .then(g => tmpResult.book.genre = g.genreName)
            .catch(err => tmpResult.errors.push(err));

        allResults.push(tmpResult);

    }
    return allResults;

}
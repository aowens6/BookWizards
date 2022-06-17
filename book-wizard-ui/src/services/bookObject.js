import {findById as findBookById} from "./book";
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
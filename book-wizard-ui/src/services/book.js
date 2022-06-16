// const url = `${process.env.REACT_APP_API_URL}/api/book`;
const url = "http://localhost:8080/api/book";
// const url = "http://localhost:3010/books";

export async function findAll() {
    const response = await fetch(url);
    if (response.status === 200) {
        return response.json();
    }
    throw new Error("fetch all books was not 200 OK");
}

export async function findById(id) {
    const response = await fetch(`${url}/${id}`);
    if (response.status === 200) {
        return response.json();

    } else if (response.status === 404) {
        const errors = await response.json();
        return Promise.reject(errors);

    } else if (!response.ok) {
        return Promise.reject([`Request book failed. ${response.status}`])
    }

    return Promise.reject();
}

export async function save(book) {

    const init = {
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${localStorage.getItem("jwt")}`
        },
        body: JSON.stringify(book)
    }

    let saveUrl;

    // update book
    if (book.bookId > 0) {
        init.method = "PUT";
        saveUrl = `${url}/${book.bookId}`;

    // add book
    } else {
        init.method = "POST";
        saveUrl = url;
    }

    const response = await fetch(saveUrl, init);

    if (response.status === 400) {
        const errors = await response.json();
        return Promise.reject(errors);

    } else if (!response.ok) {
        return Promise.reject([`Request failed. ${response.status}`])
    }

}

export async function deleteById(bookId) {

    const init = {
        method: "DELETE",
        headers: {
            "Authorization": `Bearer ${localStorage.getItem("jwt")}`
        }
    };

    const response = await fetch(`${url}/${bookId}`, init);
    if (!response.ok) {
        throw new Error("Delete was not 204.");
    }
}
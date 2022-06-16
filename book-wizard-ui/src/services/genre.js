// const url = `${process.env.REACT_APP_API_URL}/api/genre`;
const url = "http://localhost:8080/api/genre";
// const url = "http://localhost:3010/genres";

export async function findAll() {
    const response = await fetch(url);
    if (response.status === 200) {
        return response.json();
    }
    throw new Error("fetch all genres was not 200 OK");
}

export async function findById(id) {
    const response = await fetch(`${url}/${id}`);
    if (response.status === 200) {
        return response.json();

    } else if (response.status === 404) {
        const errors = await response.json();
        return Promise.reject(errors);

    } else if (!response.ok) {
        return Promise.reject([`Request genre failed. ${response.status}`])
    }

    return Promise.reject();
}

export async function save(genre) {

    const init = {
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${localStorage.getItem("jwt")}`
        },
        body: JSON.stringify(genre)
    }

    let saveUrl;

    // update genre
    if (genre.genreId > 0) {
        init.method = "PUT";
        saveUrl = `${url}/${genre.genreId}`;

    // add genre
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

export async function deleteById(genreId) {

    const init = {
        method: "DELETE",
        headers: {
            "Authorization": `Bearer ${localStorage.getItem("jwt")}`
        }
    };

    const response = await fetch(`${url}/${genreId}`, init);
    if (!response.ok) {
        throw new Error("Delete was not 204.");
    }
}
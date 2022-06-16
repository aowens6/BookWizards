// const url = `${process.env.REACT_APP_API_URL}/api/author`;
const url = "http://localhost:8080/api/author";
// const url = "http://localhost:3010/authors";

export async function findAll() {
    const response = await fetch(url);
    if (response.status === 200) {
        return response.json();
    }
    throw new Error("fetch all authors was not 200 OK");
}

export async function findById(id) {
    const response = await fetch(`${url}/${id}`);
    if (response.status === 200) {
        return response.json();

    } else if (response.status === 404) {
        const errors = await response.json();
        return Promise.reject(errors);

    } else if (!response.ok) {
        return Promise.reject([`Request author failed. ${response.status}`])
    }

    return Promise.reject();
}

export async function save(author) {

    const init = {
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${localStorage.getItem("jwt")}`
        },
        body: JSON.stringify(author)
    }

    let saveUrl;

    // update author
    if (author.authorId > 0) {
        init.method = "PUT";
        saveUrl = `${url}/${author.authorId}`;

    // add author
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

export async function deleteById(authorId) {

    const init = {
        method: "DELETE",
        headers: {
            "Authorization": `Bearer ${localStorage.getItem("jwt")}`
        }
    };

    const response = await fetch(`${url}/${authorId}`, init);
    if (!response.ok) {
        throw new Error("Delete was not 204.");
    }
}
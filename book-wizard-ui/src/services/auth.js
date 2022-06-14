// const url = process.env.REACT_APP_API_URL;
// const url = "http://localhost:8080";
const url = "http://localhost:3010";

function makeUser(body) {
    const token = body.jwt_token;
    const sections = token.split(".");
    const payload = atob(sections[1]);
    const user = JSON.parse(payload);
    localStorage.setItem("jwt", token);
    return user;
}

export async function authenticate(credentials) {

    const init = {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(credentials)
    }

    const response = await fetch(`${url}/authenticate`, init);

    if (response.ok) {
        return makeUser(await response.json());
    }

    return Promise.reject();
}

export async function refresh() {

    if (!localStorage.getItem("jwt")) {
        return Promise.reject();
    }

    const init = {
        method: "POST",
        headers: {
            "Authorization": `Bearer ${localStorage.getItem("jwt")}`
        }
    };

    const response = await fetch(`${url}/refresh`, init);
    if (response.ok) {
        return makeUser(await response.json());
    }

    return Promise.reject();
}
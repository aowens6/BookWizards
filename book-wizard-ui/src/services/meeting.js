// const url = `${process.env.REACT_APP_API_URL}/api/meeting`;
const url = "http://localhost:8080/api/meeting";
// const url = "http://localhost:3010/meetings";

export async function findAll() {
    const response = await fetch(url);
    if (response.status === 200) {
        return response.json();
    }
    throw new Error("fetch all meetings wasn't not 200 OK");
}

export async function findById(id) {
    const response = await fetch(`${url}/${id}`);
    if (response.status === 200) {
        return response.json();
    }
    return Promise.reject();
}

export async function save(meeting) {

    const init = {
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${localStorage.getItem("jwt")}`
        },
        body: JSON.stringify(meeting)
    }

    let saveUrl;

    // update meeting
    if (meeting.meetingId > 0) {
        init.method = "PUT";
        saveUrl = `${url}/${meeting.meetingId}`;

    // add meeting
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

export async function deleteById(meetingId) {

    const init = {
        method: "DELETE",
        headers: {
            "Authorization": `Bearer ${localStorage.getItem("jwt")}`
        }
    };

    const response = await fetch(`${url}/${meetingId}`, init);
    if (!response.ok) {
        throw new Error("Delete was not 204.");
    }
}
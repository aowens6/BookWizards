// const url = `${process.env.REACT_APP_API_URL}/api/app_user`;
const url = "http://localhost:8080/api/app_user";
// const url = "http://localhost:3010/app_users";

// just to get user names
export async function findById(id) {
    const response = await fetch(`${url}/${id}`);
    if (response.status === 200) {
        return response.json();

    } else if (response.status === 404) {
        const errors = await response.json();
        return Promise.reject(errors);

    } else if (!response.ok) {
        return Promise.reject([`Request user failed. ${response.status}`])
    }

    return Promise.reject();
}
import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8080'
axios.defaults.headers.post["Content-Type"] = 'application/json'

export const getToken = () => {
    return window.localStorage.getItem("auth_token");
};

export const setToken = (token) => {
    window.localStorage.setItem("auth_token", token);
}

export const request = (method, url, data) => {
    let headers = {};
    if (getToken() !== null && getToken() !== "null"){
        headers = {"Authorization": `Bearer ${getToken()}`};
    }

    return axios({
        method: method,
        headers: headers,
        url: url,
        data: data
    });
}
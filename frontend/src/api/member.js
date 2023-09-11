import { apiInstance } from "@/api/index.js"

const api = apiInstance();
const suffix = '/api/members';
const Bearer = "Bearer ";

function login(loginRequestDto, success, fail) {
    api.post("/api/login", JSON.stringify(loginRequestDto), {
        withCredentials: true
    })
        .then(response => {
            console.log(response.headers)
            console.log(response.headers['authorization'])
            success(response.headers['authorization'], response.headers['authorization-refresh']) // 모두 소문자
        })
        .catch((error) => {
            fail(error);
        });
}

function getMemberById(memberId, success, fail) {
    api.get(`${suffix}/auth/member/${memberId}`, {
        headers: {
            Authorization: Bearer + sessionStorage.getItem("access-token"),
        }
    })
        .then(({ data }) => {
            success(data);
        })
        .catch(error => {
            fail(error);
    })
}

function getMemberInfo(token, success, fail) {
    api.get(`${suffix}/auth/member`, {
        headers: {
        Authorization: Bearer + token,
    }})
        .then(({ data }) => {
            success(data);
        })
        .catch(error => {
            fail(error);
    })
    
}

function regist(registRequestDto, success, fail) {
    api.post(`${suffix}/sign-up`, JSON.stringify(registRequestDto))
        .then(() => {
            success();
        })
        .catch(error => {   
            fail(error);
    })
}

function logout(success, fail) {
    api.get(`${suffix}/auth/sign-out`, {
        headers: {
            Authorization: Bearer + sessionStorage.getItem("access-token"),
        }
    })
        .then(() => {
            success();
        })
        .catch(() => {
            fail();
    })
}


export { login, regist, getMemberInfo, logout, getMemberById }
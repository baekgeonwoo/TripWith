import { apiInstance } from "@/api/index.js";

const api = apiInstance();
const suffix = "/api/boards"

function getBoards(pageRequest, success) {
    api.get(suffix, { params: pageRequest })
    .then(({ data }) => {
        success(data.content)
    })
    .catch(error => {
            console.log(error);
    })
}

function getBoard(id, success) {
    api.get(`${suffix}/${id}`)
    .then(({data}) => {
        success(data);
    })
    .catch(error => {
        console.log(error);
    })
}

function writeBoard(writeBoardRequestDto, success, fail) {
    api.post(suffix, JSON.stringify(writeBoardRequestDto))
        .then(() => {
            success();
        })
        .catch(error => {
            fail(error);
    })
}

function editBoard(id, editBoardRequestDto, success, fail) {
    api.put(`${suffix}/${id}`, JSON.stringify(editBoardRequestDto))
        .then(() => {
            success();
        })
        .catch(error => {
            fail(error);
    })
}

function deleteBoard(id, success) {
    api.delete(`${suffix}/${id}`)
        .then(() => {
            success();
        })
        .catch(error => {
            console.log(error);
    })
}

function writeBoardReply(boardId, boardReplyWriteRequestDto, success, fail) {
    api.post(`${suffix}/${boardId}/replies`, JSON.stringify(boardReplyWriteRequestDto))
    .then(() => {
            success();
    })
    .catch(error => {
            fail(error);
    })
}

function deleteBoardReply(boardId, replyId, success) {
    api.delete(`${suffix}/${boardId}/replies/${replyId}`)
        .then(() => {
            success();
        }).catch(error => {
            console.log(error);
        })
}

export {getBoards, getBoard, writeBoard, editBoard, deleteBoard, writeBoardReply, deleteBoardReply}
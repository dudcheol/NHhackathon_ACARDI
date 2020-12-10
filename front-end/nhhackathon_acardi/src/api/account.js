import { createInstance } from "./index.js";

const instance = createInstance();

function NumberAccount(para, success, fail) {
    instance
        .post(`InquireDepositorAccountNumber.nh`, para) 
        .then(success)
        .catch(fail);
};

function CreatePinAccount(para, success, fail) {
    instance
        .post(`OpenFinAccountDirect.nh`, para) 
        .then(success)
        .catch(fail);
};

function CheckPinAccount(para, success, fail) {
    instance
        .post(`CheckOpenFinAccountDirect.nh`, para) 
        .then(success)
        .catch(fail);
};

function SearchAccount(para, success, fail) {
    instance
        .post(`InquireBalance.nh`, para) 
        .then(success)
        .catch(fail);
};

function WithdrawalAccount(para, success, fail) {
    instance
        .post(`DrawingTransfer.nh`, para) 
        .then(success)
        .catch(fail);
};

function DepositAccount(para, success, fail) {
    instance
        .post(`ReceivedTransferAccountNumber.nh`, para) 
        .then(success)
        .catch(fail);
};

// function registerArticle(article, success, fail) {
//     instance
//         .post(`article`, JSON.stringify(article)) //params는 정해져 있음.
//         .then(success)
//         .catch(fail);
// };

// function getArticle(articleno, success, fail) {
//     instance
//         .get(`article/${articleno}`) //문자열은 백틱(`) 사용
//         .then(success)
//         .catch(fail);
// };

// function modifyArticle(article, success, fail) {
//     instance
//         .put(`article`, JSON.stringify(article)) 
//         .then(success)
//         .catch(fail);
// };

// function deleteArticle(articleno, success, fail) {
//     instance
//         .delete(`article/${articleno}`) 
//         .then(success)
//         .catch(fail);
// };

export {NumberAccount, CreatePinAccount, CheckPinAccount, SearchAccount, WithdrawalAccount, DepositAccount};
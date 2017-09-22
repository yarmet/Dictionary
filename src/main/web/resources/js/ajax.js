


function ajax(url, type, json) {
    return new Promise(function (resolve, reject) {
        var xhr = new XMLHttpRequest();
        xhr.open(type, url, true);
        xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
        xhr.send(json);

        xhr.onreadystatechange = function () {
            if (xhr.readyState !== 4) return;
            if (xhr.status === 200) {
                resolve(this.response);
            } else {
                var error = new Error(this.responseText);
                error.code = this.status;
                reject(error);
            }
        };
        xhr.onerror = function () {
            reject(new Error("Network Error"));
        };
    });
}


// ajax('http://localhost:8080/getWords', 'GET', null).then(function (resolve) {
//     console.log("ответ : " + resolve)
// }, function (error) {
//     console.log("ошибка " + error)
// });
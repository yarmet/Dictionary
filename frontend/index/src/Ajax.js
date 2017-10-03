const rootPath = document.getElementById("rootPath").innerText;
const csrfToken = document.getElementById("csrfToken").innerText;

function ajax(url, type, json, async) {
    return new Promise(function (resolve, reject) {
        var xhr = new XMLHttpRequest();
        xhr.open(type, rootPath.concat(url), async);
        xhr.setRequestHeader('X-CSRF-TOKEN', csrfToken);
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


export default ajax;
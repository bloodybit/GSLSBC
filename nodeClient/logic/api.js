const URL = require('url');
import * as http from 'http'; // http requests 

console.log(URL);

let Api = (function() {

    let options = {
        host: 'localhost',
        path: '/socialrecord/',
        //since we are listening on a custom port, we need to specify it by hand
        port: '8080',
        //This is what changes the request to a POST request
        method: 'GET'
    };

    function get(url) {

        let parsedUrl = URL.parse(url);
        options.method = 'GET';
        options.host = parsedUrl.hostname;
        options.port = parsedUrl.port;
        options.path = parsedUrl.pathname;

        return new Promise((resolve, reject) => {
            let req = http.request(options, (response) => {
                var str = ''
                response.on('data', function(chunk) {
                    str += chunk;
                });

                response.on('end', function() {
                    console.log(str);
                    let message = JSON.parse(str).message;
                    console.log("message: ", message);
                    resolve(JSON.parse(message));
                });
            });

            req.end();
        });
    }

    function put(url, body) {

        let parsedUrl = URL.parse(url);
        options.method = 'PUT';
        options.host = parsedUrl.hostname;
        options.port = parsedUrl.port;
        options.path = parsedUrl.pathname;

        return new Promise((resolve, reject) => {
            let req = http.request(options, (response) => {
                var str = ''
                response.on('data', function(chunk) {
                    str += chunk;
                });

                response.on('end', function() {
                    console.log(str);
                    let message = JSON.parse(str).message;
                    console.log("message: ", message);

                    resolve(JSON.parse(message));
                });
            });
            req.write(body);
            req.end();
        });
    }

    return {
        get,
        put
    }

})();
export { Api };
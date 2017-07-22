const URL = require('url');
import * as http from 'http'; // http requests 

// call the gsls
let Api = (function() {

    // default options
    let options = {
        host: 'localhost',
        path: '/socialrecord/',
        port: '8080',
        method: 'GET'
    };

    // get method
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
                    let parsedResponse = JSON.parse(str);
                    console.log(parsedResponse);

                    if (parsedResponse.status != 200) {
                        reject(parsedResponse.message);
                    } else {
                        let message = parsedResponse.message;
                        // console.log("message: ", message);
                        if (typeof message == 'string') {
                            message = JSON.parse(message)
                        }
                        resolve(message);
                    }
                });
            });

            req.end();
        });
    }

    // put method
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
                    // console.log(str);
                    let message = JSON.parse(str).message;
                    // console.log("message: ", message);

                    resolve(message);
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
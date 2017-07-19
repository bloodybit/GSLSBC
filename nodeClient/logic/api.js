const { URL } = require('url');
import * as http from 'http'; // http requests 

let Api = (function() {

    var options = {
        host: 'localhost',
        path: '/socialrecord/' + globalID,
        //since we are listening on a custom port, we need to specify it by hand
        port: '8080',
        //This is what changes the request to a POST request
        method: 'GET'
    };

    this.get = function(url) {

        let parsedUrl = new URL(url);
        console.log();
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
                    console.log(`Got a response!\n\n`);

                    console.log(str);
                    resolve(str);
                });
            });

            req.end();
        });
    }


    this.post = function(url, body) {
        let parsedUrl = new URL(url);
        console.log(parsedUrl);
        options.method = 'POST';
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
                    console.log(`Got a response!\n\n`);

                    console.log(str);
                    resolve(str);
                });
            });
            req.write(body);
            req.end();
        });
    }
})();

export { Api }
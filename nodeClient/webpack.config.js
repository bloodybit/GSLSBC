var webpack = require('webpack');
var path = require('path');
const webpackTargetElectronRenderer = require('webpack-target-electron-renderer');

var BUILD_DIR = path.resolve(__dirname, 'public');
var APP_DIR = path.resolve(__dirname, 'src');

var config = {
    entry: APP_DIR + '/index.jsx',
    output: {
        path: BUILD_DIR,
        filename: 'bundle.js'
    },
    module: {
        loaders: [{
                test: /\.js$/,
                loader: 'babel-loader',
                exclude: /node_modules/
            },
            {
                test: /\.jsx?/,
                include: APP_DIR,
                loader: 'babel-loader'
            },
            {
                test: /\.json$/,
                loader: 'json-loader'
            }
        ]
    }
};

config.target = webpackTargetElectronRenderer(config);

module.exports = config;
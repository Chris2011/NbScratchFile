const CopyWebpackPlugin = require('copy-webpack-plugin'),
        SpriteLoaderPlugin = require('svg-sprite-loader/plugin'),
        path = require('path'),
        ExtractTextPlugin = require('extract-text-webpack-plugin');

module.exports = {
    entry: ["./app/main", './app/main.scss'],
    output: {
        path: path.resolve(__dirname, 'dist'),
        publicPath: '/generated/',
        filename: "app.js"
    },
    resolve: {
        // Add '.ts' as a resolvable extension.
        extensions: ['.ts', '.js']
    },
    module: {
        rules: [{
                // all files with a '.ts' extension will be handled by 'ts-loader'
                test: /\.ts$/, loader: ['babel-loader', 'ts-loader']
            }, {
                // regular css files
                test: /\.css$/,
                loader: ExtractTextPlugin.extract({
                    use: 'css-loader?importLoaders=1'
                })
            }, {
                // https://jonathanmh.com/webpack-sass-scss-compiling-separate-file/
                test: /\.scss$/, loader: ExtractTextPlugin.extract(['css-loader', 'sass-loader'])
            }, {
                // https://github.com/kisenka/svg-sprite-loader
                test: /\.svg$/,
                loader: 'svg-sprite-loader',
                options: {
                    extract: true,
                    dest: 'dist',
                    spriteFilename: 'sprite.svg'
                }
            }]
    },
    devtool: 'source-map',
    plugins: [
        new CopyWebpackPlugin([{
                from: 'app/index.html',
                to: 'index.html'
            }]),
        new ExtractTextPlugin({
            filename: '[name].css',
            allChunks: true
        }),
        new SpriteLoaderPlugin()
    ],
    devServer: {
        compress: true,
        inline: true,
        port: 9000
    }
};
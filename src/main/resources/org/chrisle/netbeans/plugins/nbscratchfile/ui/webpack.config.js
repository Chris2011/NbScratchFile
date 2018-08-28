const CopyWebpackPlugin = require('copy-webpack-plugin'),
    SpriteLoaderPlugin = require('svg-sprite-loader/plugin'),
    BundleAnalyzerPlugin = require('webpack-bundle-analyzer').BundleAnalyzerPlugin,
    VueLoaderPlugin = require('vue-loader/lib/plugin'),
    path = require('path');

module.exports = {
    entry: './app/main.ts',
    output: {
        path: path.resolve(__dirname, './dist'),
        filename: 'app.js',
        publicPath: '/'
    },
    module: {
        rules: [{
                test: /\.scss$/,
                use: [{
                    loader: 'style-loader'
                }, {
                    loader: 'css-loader',
                    options: {
                        url: false
                    }
                }, {
                    loader: 'sass-loader'
                }]
            }, {
            // all files with a '.ts' and '.vue' extension will be handled by
            // 'ts-loader' and after that with babel-loader
            // to transpile from ES2015 to ES5
            test: /\.ts$/,
            use: [{
                loader: 'babel-loader'
            }, {
                loader: 'ts-loader',
                options: {
                    appendTsSuffixTo: [/\.vue$/]
                }
            }]
        }, {
            test: /\.vue$/,
            loader: 'vue-loader',
            options: {
                loaders: {
                    // Since sass-loader (weirdly) has SCSS as its default parse mode, we map
                    // the "scss" and "sass" values for the lang attribute to the right configs here.
                    // other preprocessors should work out of the box, no loader config like this necessary.
                    'scss': 'vue-style-loader!css-loader!sass-loader',
                    ts: 'babel-loader!ts-loader'
                }
            // other vue-loader options go here
            }
        }]
    },
    resolve: {
        // Add '.ts' as a resolvable extension.
        extensions: ['.ts', '.js', '.vue', '.json']
    },
    devtool: 'source-map',
    plugins: [
        new CopyWebpackPlugin([{
                from: 'app/index.html',
                to: 'index.html'
            }, {
                from: 'app/sprite.png',
                to: 'sprite.png'
            }]),
        new SpriteLoaderPlugin(),
        new BundleAnalyzerPlugin({
            generateStatsFile: true,
            statsFilename: '../reports/webpack-bundle-analyzer/stats.json',
            reportFilename: '../reports/webpack-bundle-analyzer/report.html',
            analyzerMode: 'static',
            openAnalyzer: false
        }),
        new VueLoaderPlugin()
    ],
    devServer: {
        compress: true,
        inline: true,
        port: 9000
    }
};
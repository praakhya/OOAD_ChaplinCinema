const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
    app.use(
        '/api/v1',
        createProxyMiddleware({
          target: 'http://localhost:8080',
          changeOrigin: true,
        })
      );
      app.use(
        '/images',
        createProxyMiddleware({
          target: 'http://localhost:8080/images',
          changeOrigin: true,
        })
      );
};
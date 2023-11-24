// websocket-demo-ui/app.js

const express = require('express');
const path = require('path');

const app = express();
const port = process.env.PORT || 3333;

// 静态文件中间件，指定静态资源文件夹
app.use(express.static(path.join(__dirname, 'dist')));

// 所有请求都返回index.html，让Vue路由处理路由
app.get('*', (req, res) => {
  res.sendFile(path.join(__dirname, 'dist/index.html'));
});

app.listen(port, () => {
  console.log(`Server is running on port ${port}`);
});
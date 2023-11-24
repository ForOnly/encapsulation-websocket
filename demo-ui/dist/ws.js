// WebSocket对象
let webSocket;
let endpointId = "255566666"

class WSMessage {
    constructor(msg, rm, ep = "255566666",) {  //初始化函数，创建对象时自动调用该方法
        this.endpointId = ep;
        this.message = msg;
        this.remark = rm;
    }
}

// WebSocket初始化
function initWebSocket() {
    //判断当前浏览器是否支持WebSocket, 主要此处要更换为自己的地址,ws等同于http，wss等同于https
    if ('WebSocket' in window) {
        webSocket = new WebSocket("ws://localhost:8080/ws/" + endpointId);
        //console.log("WebSocket连接成功");

        // 打开事件
        webSocket.onopen = function () {
            console.log("WebSocket打开");
        };

        // 关闭事件
        webSocket.onclose = function () {
            console.log("WebSocket关闭");
        };

        // 消息事件
        webSocket.onmessage = function (message) {
            console.log("WebSocket接收消息：", message);
        };

        // 错误事件
        webSocket.onerror = function (err) {
            console.log("WebSocket发生错误:", err);
        };
    } else {
        console.log("WebSocket连接失败");
    }
}

// 初始化WebSocket
initWebSocket();

// 使用定时器发送心跳包
setInterval(() => {
    if (webSocket.readyState == 1) {
        let wsm = new WSMessage("心跳包检测", "30s");
        console.log("发送心跳检测"+JSON.stringify(wsm));
        webSocket.send(JSON.stringify(wsm));
    } else {
        console.log("WebSocket链接断开");
    }
}, 30 * 1000);
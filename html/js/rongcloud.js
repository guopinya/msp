// 初始化 SDK
RongIMLib.RongIMClient.init('25wehl3u2gyzw');

// 连接状态监听器
RongIMClient.setConnectionStatusListener({
    onChanged: function (status) {
        // status 标识当前连接状态
        switch (status) {
            case RongIMLib.ConnectionStatus.CONNECTED:
                console.log('链接成功');
                break;
            case RongIMLib.ConnectionStatus.CONNECTING:
                console.log('正在链接');
                break;
            case RongIMLib.ConnectionStatus.DISCONNECTED:
                console.log('断开连接');
                break;
            case RongIMLib.ConnectionStatus.KICKED_OFFLINE_BY_OTHER_CLIENT:
                console.log('其他设备登录');
                break;
            case RongIMLib.ConnectionStatus.DOMAIN_INCORRECT:
                console.log('域名不正确');
                break;
            case RongIMLib.ConnectionStatus.NETWORK_UNAVAILABLE:
                console.log('网络不可用');
                break;
        }
    }
});

// 消息监听器
RongIMClient.setOnReceiveMessageListener({
    // 接收到的消息
    onReceived: function (message) {

        onReceiveMessage(message);
    }
});

/**
 * 获取融云令牌
 */
function getRongToken() {
    // 令牌
    let token = localStorage.getItem('rong_token');
    if (null !== token) return token;

    $.ajax({
        url: '/api/user/user/getOneSocial',
        type: 'GET',
        data: {
            requestId: md5('getOneSocial'),
            timestamp: new Date().getTime()
        }
    }).done(function (response) {
        handler({
            res: response,
            fun1: function () {
                return response.data['ryToken'];
            }
        })
    });
}

// 连接服务器
RongIMClient.connect(getRongToken(), {
    onSuccess: function (userId) {
        console.log('Connect successfully. ' + userId);

        onConnectSuccess();
    },
    onTokenIncorrect: function () {
        console.log('token 无效');
    },
    onError: function (errorCode) {
        switch (errorCode) {
            case RongIMLib.ErrorCode.TIMEOUT:
                console.log('超时');
                break;
            case RongIMLib.ConnectionState.SERVER_UNAVAILABLE:
                console.log('服务器不可用');
                break;
            case RongIMLib.ConnectionState.IDENTIFIER_REJECTED:
                console.log('appkey不正确');
                break;
            case RongIMLib.ConnectionState.UNACCEPTABLE_PROTOCOL_VERSION:
                console.log('不可接受的协议版本');
                break;
        }
    }
});

/**
 * 获取消息内容
 * @param message 消息对象
 */
function getMessageContent(message) {
    // 判断消息类型
    switch (message.messageType) {
        case RongIMClient.MessageType.TextMessage:
            // message.content.content => 文字内容
            return message.content.content;
        case RongIMClient.MessageType.VoiceMessage:
            // message.content.content => 格式为 AMR 的音频 base64
            break;
        case RongIMClient.MessageType.ImageMessage:
            // message.content.content => 图片缩略图 base64
            // message.content.imageUri => 原图 URL
            break;
        case RongIMClient.MessageType.LocationMessage:
            // message.content.latiude => 纬度
            // message.content.longitude => 经度
            // message.content.content => 位置图片 base64
            break;
        case RongIMClient.MessageType.RichContentMessage:
            // message.content.content => 文本消息内容
            // message.content.imageUri => 图片 base64
            // message.content.url => 原图 URL
            break;
        case RongIMClient.MessageType.InformationNotificationMessage:
            // do something
            break;
        case RongIMClient.MessageType.ContactNotificationMessage:
            // do something
            break;
        case RongIMClient.MessageType.ProfileNotificationMessage:
            // do something
            break;
        case RongIMClient.MessageType.CommandNotificationMessage:
            // do something
            break;
        case RongIMClient.MessageType.CommandMessage:
            // do something
            break;
        case RongIMClient.MessageType.UnknownMessage:
            // do something
            break;
        default:
        // do something
    }
}

Date.prototype.format = function (fmt) {
    let o = {
        "M+": this.getMonth() + 1, // 月份
        "d+": this.getDate(), // 日
        "h+": this.getHours(), // 小时
        "m+": this.getMinutes(), // 分
        "s+": this.getSeconds(), // 秒
        "q+": Math.floor((this.getMonth() + 3) / 3), // 季度
        "S": this.getMilliseconds() // 毫秒
    };

    if (/(y+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }

    for (let k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }

    return fmt;
};
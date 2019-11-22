function getImage() {
    var host = window.location.host;
    return "http://" + host + '/share_logo.jpg';
}

function getLink() {
    var host = window.location.host;
    return "http://" + host;
}

function getMessage() {
    return "关注有礼 普天同庆享太平";
}

$(function () {
    /* $.get("http://api.e75.cn/wechat/getsign.php",{},function(item){
        if(item.appId!=undefined){
            onWechatApiInt(item);
        }
    },"json"); */


    var paramObject = getParamObject();
    paramObject.currentURL = window.location.href; // 请求时间戳
    $.ajax({
        url: link + "/wechat/config_data?" + $.param(paramObject),
        dataType: "Json",
        success: function (res) {
            console.log(res);
            handler({
                res: res,
                fun1: function () {
                    if (Istrue(res.appId)) {
                        onWechatApiInt(res);
                    }
                }
            })
        },
        error: function (e) {

        }
    });

});

function onWechatApiInt(signPackage) {

    wx.config({
        appId: signPackage.appId,
        timestamp: signPackage.timestamp,
        nonceStr: signPackage.nonceStr,
        signature: signPackage.signature,
        jsApiList: [
            'onMenuShareTimeline',
            'onMenuShareAppMessage',
            'addCard'
        ]
    });

    wx.ready(function () {
        onWeichatShare();
    });


}

function onWeichatShare() {
    wx.onMenuShareTimeline({
        title: getMessage(),
        link: getLink(),
        imgUrl: getImage(),
        success: function () {
        },
        cancel: function () {
        }
    });

    wx.onMenuShareAppMessage({
        title: '太平人寿',
        desc: getMessage(),
        link: getLink(),
        imgUrl: getImage(),
        type: '',
        dataUrl: '',
        success: function () {
        },
        cancel: function () {
        }
    });
}

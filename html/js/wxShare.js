var share_title, share_desc, share_img;
var nativeShare = new NativeShare();

function shareMsg(title, desc, img) {
    // alert(img)
    // console.log(title,desc,img)
    if (Istrue(title)) {
        share_title = title
    } else {
        share_title = "模型APP内测招募"
    }
    if (Istrue(desc)) {
        share_desc = desc
    } else {
        share_desc = "最强最走心的模型APP登场！去模型圈大佬面对面交流，晒单拍卖无缝连接。"
    }
    if (Istrue(img)) {
        share_img = img
    } else {
        var host = window.location.host;
        share_img = "http://" + host + '/img/share_logo.jpg'
    }


    if (isWeiXin() == true) {
        // alert(isWeiXin())
        shareWx()
    } else {
        var shareData = {
            title: share_title,
            desc: share_desc,
            // 如果是微信该link的域名必须要在微信后台配置的安全域名之内的。
            link: getLink(),
            icon: share_img,
            // icon: img,
            // 不要过于依赖以下两个回调，很多浏览器是不支持的
            success: function () {
                // alert('success')
            },
            fail: function () {
                // alert('fail')
            }
        };
        nativeShare.setShareData(shareData)
    }

}

function call(command) {
    // alert(isWeiXin())
    if (isWeiXin() == true) {
        // alert(isWeiXin())
        // shareWx()
        $("#more_box").hide();
        $("#share_box").show()
    } else {
        shareWay(command)
    }
}


function shareWay(command) {
    try {
        nativeShare.call(command)
    } catch (err) {
        // 如果不支持，你可以在这里做降级处理
        alert('请使用浏览器自带分享功能分享')
    }
}

// alert(isWeiXin())


//判断是否是微信浏览器
function isWeiXin() {
    //window.navigator.userAgent属性包含了浏览器类型、版本、操作系统类型、浏览器引擎类型等信息，这个属性可以用来判断浏览器类型
    var ua = window.navigator.userAgent.toLowerCase();
    //通过正则表达式匹配ua中是否含有MicroMessenger字符串
    if (ua.match(/MicroMessenger/i) == 'micromessenger') {
        return true;
    } else {
        return false;
    }
}

function setTitle(title) {
    nativeShare.setShareData({
        title: title,
    })
}


function shareWx() {
    var paramObject = getParamObject();
    paramObject.currentURL = window.location.href; // 请求时间戳
    $.get(link + "/wechat/config_data?" + $.param(paramObject), {}, function (item) {
        // alert(item.data.appId)
        if (Istrue(item.data.appId)) {
            onWechatApiInt(item);
        }
    }, "json");


}

/* function shareWx(){
	$.get("http://api.e75.cn/wechat/getsign.php",{},function(item){
		if(item.appId!=undefined){
			onWechatApiInt(item);
		}
	},"json");
	
}; */

function onWechatApiInt(signPackage) {
    /* wx.config({
        appId: signPackage.appId,
        timestamp: signPackage.timestamp,
        nonceStr: signPackage.nonceStr,
        signature: signPackage.signature,
        jsApiList: [
          'updateTimelineShareData',
          'updateAppMessageShareData'
          // 'addCard'
        ]
    }); */
    // alert('id:'+signPackage.data.appId)
    // alert('时间:'+signPackage.data.timestamp)
    // alert('随机窜:'+signPackage.data.nonceStr)
    // alert('签名:'+signPackage.data.signature)
    wx.config({
        // debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: signPackage.data.appId, // 必填，公众号的唯一标识
        timestamp: signPackage.data.timestamp, // 必填，生成签名的时间戳
        nonceStr: signPackage.data.nonceStr, // 必填，生成签名的随机串
        signature: signPackage.data.signature,// 必填，签名
        jsApiList: [
            'updateAppMessageShareData',
            'updateTimelineShareData'
        ] // 必填，需要使用的JS接口列表
    });
    // wx.ready(function () {onWeichatShare();});

    wx.ready(function () {
        onWeichatShare();
    });
    // wx.error(function () {alert('错误')});
}

/* function getImage(){ //分享图片
	var host = window.location.host;
	// alert(host)
	return "http://"+host+'/img/share_logo.jpg';
} */

function getLink() {//分享链接
    var host = window.location.href;
    return host;
}

function getMessage() {//分享标题
    return "分享文案";
}

function onWeichatShare() {
    // alert(share_title)
    // alert(share_desc)
    // alert(share_img)
    // alert(getLink())

    // 分享给朋友
    wx.updateAppMessageShareData({
        title: share_title, // 分享标题
        desc: share_desc, // 分享描述
        link: getLink(), // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
        imgUrl: share_img, // 分享图标
        success: function () {
            // 设置成功
            // alert('设置成功')
        }
    });
    // 分享到朋友圈
    wx.updateTimelineShareData({
        title: share_title, // 分享标题
        link: getLink(), // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
        imgUrl: share_img, // 分享图标
        success: function () {
            // 设置成功
        }
    })

}

function getPath() {
    var path = "";
    if (window.localStorage && window.localStorage.getItem("path")) {
        path = window.localStorage.getItem("path");
    }
    return path;
}

function setPath(path) {
    if (window.localStorage) {
        window.localStorage.removeItem("path");
        window.localStorage.setItem("path", path);
    }
}

$("#share_box,.share").click(function () {
    $("#share_box").fadeOut(200)
});
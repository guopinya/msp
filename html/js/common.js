// document.write('<script type="text/javascript" src="js/appback.js"></script>');
var dateModified = document.lastModified;
var mymonth = dateModified.slice(0, 2);
var mydata = dateModified.slice(3, 5);
var myyear = dateModified.slice(6, 10);
var mydate = myyear + mymonth + mydata;
document.write("<script type=\"text/javascript\" src=\"http://res.wx.qq.com/open/js/jweixin-1.4.0.js\"></script>");
document.write("<script type=\"text/javascript\" src=\"js/NativeShare.js?hq=" + mydate + "\"></script>");
document.write("<script type=\"text/javascript\" src=\"js/wxShare.js?hq=" + mydate + "\"></script>");
document.write("<script type=\"text/javascript\" src=\"https://cdn.bootcss.com/blueimp-md5/2.10.0/js/md5.min.js\"></script>");
// <script src="https://cdn.bootcss.com/blueimp-md5/2.10.0/js/md5.min.js"></script>

var Fuse = Fuse || {};
Fuse.Event = ('ontouchstart' in window) ? {
    START: 'touchstart',
    MOVE: 'touchmove',
    END: 'touchend',
    MOVE_END: 'webkitAnimationEnd animationend'
} : {
    START: 'mousedown',
    MOVE: 'mousemove',
    END: 'mouseup'
};

//去除字符串中的所有标签符号
function delHtmlTag(str) {
    return str.replace(/<[^>]+>/g, "");
}

//手机正则式
var reg = /^(((13[0-9]{1})|(18[0-9]{1})|(15[0-9]{1})|(14[0-9]{1})|(17[0-9]{1})|(16[0-9]{1})|(19[0-9]{1}))+\d{8})$/;

//接口前缀连接
var link = "/api3";

$("input").focus(function () {
    $(this).css({'color': "#1C2027"})
});
$("textarea").focus(function () {
    $(this).css({'color': "#1C2027"})
});

//接口回调通用处理方法
function handler(obj) {
    var res = obj.res;
    if (res.code == 200) {
        // 公共处理
        if (obj.fun1) {
            obj.fun1();
        }
    } else if (res.code == 400) {
        // 公共处理
        hint(res.msg);
        if (obj.fun2) {
            obj.fun2();
        }
    } else if (res.code == 1001) {
        window.location.href = "login.html"
    } else if (res.code == 1002) {
        if (obj.fun4) {
            obj.fun4();
        }
    } else if (res.code == 1003) {
        if (obj.fun5) {
            obj.fun5();
        }
        alert(res.msg)
    } else if (res.code == 1004) {
        if (obj.fun5) {
            obj.fun6();
        }
        alert(res.msg)
    }

}

//小于10时往前补0
function checkTime(i) {
    if (i < 10) {
        i = "0" + i;
    }
    return i;
}

//显示更多浮层
function more() {
    $("#more_box").fadeIn(200)
}


//判断数据是否为空
function Istrue(str) {
    if (str == "" || str == "null" || str == null || str == undefined || str == "undefined" || str == "-1" || str == -1) {
        return false //不为空
    } else {
        return true//为空
    }
}


//连接参数获取方法
//getQueryStringByName(id)
function getQueryStringByName(name) {
    var result = location.search.match(new RegExp("[\?\&]" + name + "=([^\&]+)", "i"));
    if (result == null || result.length < 1) {
        return ""
    }
    return result[1]
}

//剩余时间
var day = 0,
    hour = 0,
    minute = 0;

function residueTime(time) {
    var timestamp = (new Date()).getTime();
    var residueTime = parseInt(time) - timestamp;
    //	console.log(residueTime)
    residueTime = residueTime / 1000;

    day = Math.floor(residueTime / (60 * 60 * 24));
    hour = Math.floor(residueTime / (60 * 60)) - (day * 24);
    minute = Math.floor(residueTime / 60) - (day * 24 * 60) - (hour * 60);

}

//超过一千的数字处理方法
function NumberHandle(figure) {
    let t;
    let nubr;
    if (figure > 1000) {
        t = figure / 1000;
        t = t.toFixed(1);
        console.log(t);
        nubr = t.toString().split(".")[1][0]; //获取小数点最后一位数字
        console.log(nubr);
        if (nubr > 0) {
            t = t + "K"
        } else {
            t = parseInt(t) + "K"
        }
        console.log(t)
    } else {
        t = figure
    }
    return t;
}


//带天数的倒计时
function countDown(times) {
    var timer = null;
    times = times / 1000;
    timer = setInterval(function () {
        var day = 0,
            hour = 0,
            minute = 0,
            second = 0; //时间默认值

        if (times > 0) {
            day = Math.floor(times / (60 * 60 * 24));
            hour = Math.floor(times / (60 * 60)) - (day * 24);
            minute = Math.floor(times / 60) - (day * 24 * 60) - (hour * 60);
            second = Math.floor(times) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
        }
        if (day <= 9) day = '0' + day;
        if (hour <= 9) hour = '0' + hour;
        if (minute <= 9) minute = '0' + minute;
        if (second <= 9) second = '0' + second;
        //

        times--;
        if (times <= 0) {
            clearInterval(timer);
        }
        $(".time_box .data").text(day);
        $(".time_box .hour").text(hour);
        $(".time_box .min").text(minute);
        $(".time_box .sec").text(second);
        // alert(day + "天" + hour + "时" + minute + "分" + second + "秒")
        $(".payment_box .time span").text(day + "天" + hour + "时" + minute + "分" + second + "秒")
    }, 1000);
    if (times <= 0) {
        clearInterval(timer);
    }
}


//手机验证码倒计时
function count_down() {
    var second = 60;
    var time = setInterval(function () {
        if (second > 0) {
            second--;
            //	        console.log(second)
            $(".send_code").text(second + "秒后重发");
            $(".send_code").addClass("hover");
        } else {
            second = 59;
            $(".send_code").text("发送验证码");
            clearInterval(time);
            $(".send_code").removeAttr("disabled");
            $(".send_code").removeClass("hover");
        }
    }, 1000)
}

//提示语
function hint(text) {
    console.log("dddddd");
    let settime;
    $(".hint_box").hide();
    clearTimeout(settime);
    $(".hint_box").fadeIn(500);
    $(".hint_box p").text(text);
    settime = setTimeout(function () {
        $(".hint_box").fadeOut(500)
    }, 3000)
}

//图片验证码更换
function img_yzm() {
    var timestamp = (new Date()).getTime();
    $("#img_code img").attr("src", "/api/kaptcha?v=" + timestamp)
}

//弹窗出现禁止滑动
function toggleBody(isPin) {
    if (isPin) {
        document.body.style.height = '100vh';
        document.body.style['overflow-y'] = 'hidden'
    } else {
        document.body.style.height = 'unset';
        document.body.style['overflow-y'] = 'auto'
    }
}

//toggleBody(1)  在跳出弹窗的时候
//toggleBody(0)  弹窗消失的时候


//点击跳转至商品详情
function productDetail(id) {
    window.location.href = "productDetail.html?productId=" + id
}

//点击跳转至分类列表
function sifyMenu(id) {
    window.location.href = "sify_menu.html?id=" + id
}

//点击跳转至产品详情
function productList(name) {
    window.location.href = "productList.html?cateId=" + name
}

//关闭发布选项浮层
function issue_close() {
    $("#issue_select").fadeOut(200);
    toggleBody(0)
}

function issue_show() {
    $("#issue_select").fadeIn(200);
    toggleBody(1)
}


//关闭提示浮层
function closeHint() {
    $(".hint_box2").hide();
}


//身份证格式验证
function IdentityCodeValid(code) {
    var city = {
        11: "北京",
        12: "天津",
        13: "河北",
        14: "山西",
        15: "内蒙古",
        21: "辽宁",
        22: "吉林",
        23: "黑龙江 ",
        31: "上海",
        32: "江苏",
        33: "浙江",
        34: "安徽",
        35: "福建",
        36: "江西",
        37: "山东",
        41: "河南",
        42: "湖北 ",
        43: "湖南",
        44: "广东",
        45: "广西",
        46: "海南",
        50: "重庆",
        51: "四川",
        52: "贵州",
        53: "云南",
        54: "西藏 ",
        61: "陕西",
        62: "甘肃",
        63: "青海",
        64: "宁夏",
        65: "新疆",
        71: "台湾",
        81: "香港",
        82: "澳门",
        91: "国外 "
    };
    var tip = "";
    var pass = true;
    if (!code || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[0-2])(0[1-9]|[12]\d|3[01])\d{3}(\d|X|x)$/i.test(code)) {
        tip = "身份证号格式错误";
        pass = false;
    } else if (!city[code.substr(0, 2)]) {
        tip = "地址编码错误";
        pass = false;
    } else {
        //		18位身份证需要验证最后一位校验位
        if (code.length == 18) {
            code = code.split('');
            //∑(ai×Wi)(mod 11)
            //加权因子

            var factor = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];         //校验位

            var parity = [1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2];
            var sum = 0;
            var ai = 0;
            var wi = 0;
            for (var i = 0; i < 17; i++) {
                ai = code[i];
                wi = factor[i];
                sum += ai * wi;
            }
            var last = parity[sum % 11];         //最后一位不区分大小写

            if (code[17] == 'x') code[17] = code[17].toUpperCase();
            console.log(parity[sum % 11]);
            console.log(last);
            console.log(code[17]);
            if (parity[sum % 11] != code[17]) {
                tip = "校验位错误";
                pass = false;
            }
            console.log()
        }
    }
    //	alert(pass, tip)
    return {
        'status': pass,
        'msg': tip
    };
    return pass;
}

// 发送短信验证码
function sendSmsCode(action, authType) {
    let mobile = $(".mobile").val();
    if (mobile === '') {
        mobile = $(".telMobile").val();
    }
    console.log(mobile);

    if (mobile === '') {
        return hint('请输入手机号码');
    }

    if (!reg.test(mobile)) {
        return hint('手机号码格式错误');
    }

    $.ajax({
        url: link + '/sendSmsCode',
        type: 'POST',
        data: {
            mobile: mobile,
            action: action,
            authType: authType
        }
    }).done(function (response) {
        console.log(response);

        handler({
            res: response,
            fun1: function () {
                $(".send_code").attr("disabled", "disabled");
                count_down();
            }
        });
    });
}

// 添加或取消种草
function addZan(auctionId, callback) {
    $.ajax({
        url: link + "/goods/collects",
        type: "POST",
        data: {
            auctionId: auctionId
        },
        success: function (res) {
            console.log(res);

            handler({
                res: res,
                fun1: callback(res)
            });
        },
        error: function (res) {
        }
    });
}


//筛选框条件重置
function resetCond() {
    $("#screen_box .screen input").val("")
}

//筛选框条件保存

function saveCond() {
    var keyword = $("#keyword").val();
    var currentPriceMin = $("#price_1").val();
    var currentPriceMax = $("#price_2").val();
    var buyoutPriceMin = $("#price_3").val();
    var buyoutPriceMax = $("#price_4").val();
    var auctionId = $("#productId").val();
    var sellerName = $("#sellerName").val();
    if (currentPriceMax < currentPriceMin) {
        hint(hint('请输入正确的价格区间'));
        return false;
    }
    if (buyoutPriceMax < buyoutPriceMin) {
        hint(hint('请输入正确的价格区间'));
        return false;
    }
    $.ajax({
        url: link + "/goods/screenRecords",
        type: "POST",
        data: {
            requestId: 'd79b9efa995f',
            timestamp: new Date().getTime(),
            keyword: keyword,
            currentPriceMin: currentPriceMin,
            currentPriceMax: currentPriceMax,
            buyoutPriceMin: buyoutPriceMin,
            buyoutPriceMax: buyoutPriceMax,
            auctionId: auctionId,
            sellerName: sellerName,
        },
        dataType: "Json",
        success: function (res) {

            handler({
                res: res,
                fun1: function () {
                    console.log(res);
                    resetCond();
                    getCond()
                }
            })
        },
        error: function (e) {
            mescroll.endErr()
        }
    })
}


//获取筛选条件
var screenRecords = "";

function getCond() {
    $.ajax({
        url: link + "/goods/screenRecords",
        type: "GET",
        data: {
            requestId: 'd79b9efa995f',
            timestamp: new Date().getTime(),
        },
        dataType: "Json",
        success: function (res) {

            handler({
                res: res,
                fun1: function () {
                    console.log(res);
                    console.log(res.data);
                    screenRecords = res.data;
                    console.log(screenRecords);
                    if (screenRecords.length > 0) {
                        var result = "";
                        for (var i = 0; i < screenRecords.length; i++) {
                            console.log(screenRecords[i]);
                            console.log(i);
                            result += '<li>';
                            if (screenRecords[i].keyword) {
                                result += '关键字:' + screenRecords[i].keyword + ';'
                            }
                            if (screenRecords[i].currentPriceMin) {
                                result += '当前价格:' + screenRecords[i].currentPriceMin + ';'
                            }
                            if (screenRecords[i].currentPriceMax) {
                                result += '当前最高价:' + screenRecords[i].currentPriceMax + ';'
                            }
                            if (screenRecords[i].buyoutPriceMin) {
                                result += '一口价最低价:' + screenRecords[i].buyoutPriceMin + ';'
                            }
                            if (screenRecords[i].buyoutPriceMax) {
                                result += '一口价最高价:' + screenRecords[i].buyoutPriceMax + ';'
                            }
                            if (screenRecords[i].auctionId) {
                                result += '拍品ID:' + screenRecords[i].auctionId + ';'
                            }
                            if (screenRecords[i].sellerName) {
                                result += '卖家昵称:' + screenRecords[i].sellerName + ';'
                            }
                            result += '</li>'
                        }
                        console;
                        $(".record_box ul").html(result)
                    }
                    selectCond()
                }
            })
        },
        error: function (e) {
        }
    })
}

function selectCond() {
    $(".record_box ul li").click(function () {
        var index = $(this).index();
        console.log(screenRecords[index]);
        $("#keyword").val(screenRecords[index].keyword || '');
        $("#price_1").val(screenRecords[index].currentPriceMin || '');
        $("#price_2").val(screenRecords[index].currentPriceMax || '');
        $("#price_3").val(screenRecords[index].buyoutPriceMin || '');
        $("#price_4").val(screenRecords[index].buyoutPriceMax || '');
        $("#productId").val(screenRecords[index].auctionId || '');
        $("#sellerName").val(screenRecords[index].sellerName || '')
    })
}

//筛选框条件清空

function deleteCond() {
    $.ajax({
        url: link + "/goods/screenRecords",
        type: "DELETE",
        data: {
            requestId: 'd79b9efa995f',
            timestamp: new Date().getTime(),
        },
        dataType: "Json",
        success: function (res) {

            handler({
                res: res,
                fun1: function () {
                    console.log(res);
                    $(".record_box ul").html("")
                }
            })
        },
        error: function (e) {
            mescroll.endErr()
        }
    })
}

//清楚搜索框内容
function searchClose() {
    $(".search_input").val("");
    $(".search_input").focus()
}


function closeHint() {
    $(".hint_box2").fadeOut(200)
}

// 文件路径转base64
function getBase64Image(img) {
    var canvas = document.createElement("canvas");
    canvas.width = img.width;
    canvas.height = img.height;
    var ctx = canvas.getContext("2d");
    ctx.drawImage(img, 0, 0, img.width, img.height);
    var ext = img.src.substring(img.src.lastIndexOf(".") + 1).toLowerCase();
    var dataURL = canvas.toDataURL("image/" + ext);
    return dataURL;
}

// base64编码转文件流
/* function base64toFile(dataurl, filename = 'file') {//将base64转换为文件
	var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
		bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
	while(n--){
		u8arr[n] = bstr.charCodeAt(n);
	}
	return new File([u8arr], filename, {type:mime});
} */
function base64toFile(dataurl, filename = 'file') {
    console.log(dataurl);
    let arr = dataurl.split(',');
    console.log(arr);
    let mime = arr[0].match(/:(.*?);/)[1];
    let suffix = mime.split('/')[1];
    let bstr = atob(arr[1]);
    let n = bstr.length;
    let u8arr = new Uint8Array(n);
    while (n--) {
        u8arr[n] = bstr.charCodeAt(n)
    }
    return new File([u8arr], `${filename}.${suffix}`, {
        type: mime
    })
}

function sumitImageFile(base64Codes) {
    var form = document.forms[0];
    var formData = new FormData(form);   //这里连带form里的其他参数也一起提交了,如果不需要提交其他参数可以直接FormData无参数的构造函数  
    //convertBase64UrlToBlob函数是将base64编码转换为Blob  
    formData.append("imageName", convertBase64UrlToBlob(base64Codes));  //append函数的第一个参数是后台获取数据的参数名,和html标签的input的name属性功能相同
    console.log(formData)
}

/**
 * 将以base64的图片url数据转换为Blob
 * @param urlData
 *            用url方式表示的base64图片数据
 */
function convertBase64UrlToBlob(urlData) {
    var bytes = window.atob(urlData.split(',')[1]);        //去掉url的头，并转换为byte
    //处理异常,将ascii码小于0的转换为大于0  
    var ab = new ArrayBuffer(bytes.length);
    var ia = new Uint8Array(ab);
    for (var i = 0; i < bytes.length; i++) {
        ia[i] = bytes.charCodeAt(i);
    }
    return new Blob([ab], {type: 'image/png'});
}


// 获取参数对象
function getParamObject() {
    var paramObject = {};
    var paramString = window.location.href.split('?')[1];
    if (!$.trim(paramString)) {
        return paramObject;
    }
    var paramArray = paramString.split('&');
    for (var i = 0; i < paramArray.length; i++) {
        var param = paramArray[i].split('=');
        paramObject[param[0]] = decodeURIComponent(param[1]);
    }
//  console.log(paramObject);
    return paramObject;
}


//添加临时商品
function send_product(postStatus) {
    $("body").append('<div id="loading"><div class="gif"><img src="img/timg.gif"></div></div>');
    var form = new FormData();
    var paramArray = [];//存放自定义标题内容
    if (videoArr.length) {
        form.append('videoFile', videoArr[0]);
    }
    if (dataArr[1].length) {
        console.log(dataArr[1]);
        for (var i = 0; i < dataArr[1].length; i++) {
            console.log(i);
            console.log(dataArr[1][i]);
            form.append('imageFiles', base64toFile(dataArr[1][i].base64));
        }
    }
    console.log(form.get('imageFiles'));
    //获取自定义标题内容
    if ($(".custom li").length > 0) {
        for (var i = 0; i < $(".custom li").length; i++) {
            var title = $(".custom li").eq(i).children(".name").text();
            var texts = $(".custom li").eq(i).children(".lable").children("span").text();
            form.set('paramArray[' + i + '].name', title);
            form.set('paramArray[' + i + '].value', texts);
            /*paramArray.push({
                name:title,
                value:texts
            })*/
        }
//		form.append("paramArray",paramArray);
    }
    // console.log(paramArray)

    var treasureId = '';
    if (Istrue($.cookie("treasureId"))) {
        treasureId = $.cookie("treasureId")
    } else {
        treasureId = ''
    }

    //横向封面
    var horizontalFile = $(".horizontal_img #result img").attr("src");
    if (Istrue(horizontalFile)) {
        form.append('horizontalFile', base64toFile(horizontalFile));
    }

    //纵向封面
    var verticalFile = $("#resultImg2").attr("src");

    if (!verticalFile == 'img/addimg2.png') {
        form.append('verticalFile', base64toFile(verticalFile));
    }
    form.append("itemId", treasureId);
    form.append("title", $("#title").val());
    form.append("introduce", textareaTex());
    form.append("preservation", $(".save_case .lable span").attr('title'));
    form.append("cateId", $(".sify_select .lable span").attr("data-cateId"));
    form.append("themeId", $(".lable_select .lable").attr("data-themeid"));
    form.append("groupId", $(".addr_select .lable").attr("data-groupid"));
    form.append("status", 'TEMPORARY');
    form.append("requestId", 'd79b9efa995f');
    form.append("timestamp", new Date().getTime());
    $.ajax({
        url: link + "/goods/items",
        type: "POST",
        contentType: false,
        processData: false,
        data: form,
        dataType: "Json",
        success: function (res) {
            console.log(res);

            handler({
                res: res,
                fun1: function () {
                    if (postStatus == "TEMPORARY") {
                        window.location.href = "issue_auction.html"
                    } else {
                        send_create(postStatus)
                    }
                    var curPageData = res.data; // 接口返回的当前页数据列表
                    var totalSize = res.count; // 接口返回的总数据量(比如列表有26个数据,每页10条,共3页; 则totalSize值为26)
                }, fun2: function () {
                    $("#loading").remove()
                }, fun3: function () {
                    $("#loading").remove()
                }, fun4: function () {
                    $("#loading").remove()
                }, fun5: function () {
                    $("#loading").remove()
                }
            })
        },
        error: function (e) {
        }
    });
}


//发布藏品
function send_create(postStatus) {
    var treasureId = '';
    if (Istrue($.cookie("treasureId"))) {
        treasureId = $.cookie("treasureId")
    } else {
        treasureId = ''
    }
    console.log(postStatus);
    var form = new FormData();
    form.append("status", postStatus);
    form.append("treasureId", treasureId);
    form.append("timestamp", new Date().getTime());
    form.append("requestId", 'd79b9efa995f');
    $.ajax({
        url: link + "/goods/treasure/create",
        type: "POST",
        contentType: false,
        processData: false,
        data: form,
        dataType: "Json",
        success: function (res) {
            console.log(res);
            $("#loading").remove();
            handler({
                res: res,
                fun1: function () {
                    window.location.href = "myHome.html"
                }
            })
        },
        error: function (e) {
        }
    });
}


//发布拍品
function send_auction(postStatus) {

    if ($("#startPrice").val() == "") {
        hint("请输入起拍价格");
        return false;
    }
    if ($("#markupRange").val() == "") {
        hint("请输入加价幅度金额");
        return false;
    }
    if ($("#earnestMoney").val() == "") {
        hint("请输入保证金金额");
        return false;
    }
    if (!$("#buyoutPrice").val() == "") {
        if (Number($("#buyoutPrice").val()) <= Number($("#startPrice").val())) {
            hint("请输入正确的拍卖价格");
            return false;
        }
    }

    if ($("#address").val() == "") {
        hint('请填写发货地区');
        return false;
    }

    if ($(".pattern li.hover").length < 1) {
        hint('请区选择交易模式');
        return false;
    }


    if ($(".auction_protocol.hover").length < 1) {
        hint("请认真阅读并同意拍卖协议");
        return false;
    }

    $("body").append('<div id="loading"><div class="gif"><img src="img/timg.gif"></div></div>');
    var myDate = new Date();
    var year = myDate.getFullYear();
    var month = myDate.getMonth() + 1;
    var getDate = myDate.getDate();
    var gethours = myDate.getHours();
    var getMinutes = myDate.getMinutes();
    var getSeconds = myDate.getSeconds();
    console.log(month);
    if (month < 10) {
        month = "0" + month
    }
    if (getDate < 10) {
        getDate = "0" + getDate
    }
    if (gethours < 10) {
        gethours = "0" + gethours
    }
    if (getMinutes < 10) {
        getMinutes = "0" + getMinutes
    }
    if (getSeconds < 10) {
        getSeconds = "0" + getSeconds
    }
    var dataTime = '';
    console.log($("#demo1").val());

    if ($(".beginTime .hover").attr('date-begin') == "IMMEDIATELY") {
        dataTime = year + '-' + month + '-' + getDate + ' ' + gethours + ':' + getMinutes + ":00"
    } else {
        if ($("#demo1").val() == "") {
            hint('请选择日期');
            return false;
        }
        dataTime = $("#demo1").val() + ' ' + $("#hour").val() + ':' + $("#min").val() + ":00"
    }
    var tradingMode = [];
    for (var i = 0; i < $(".pattern .hover").length; i++) {
        tradingMode.push($(".pattern .hover").eq(i).attr('data-title'))
    }
    var toString = tradingMode.toString();
    var auctionId = "";


    if (Istrue($.cookie("treasureId"))) {
        auctionId = $.cookie("treasureId")
    } else {
        auctionId = ''
    }
    var data = {
        requestId: 'd79b9efa995f',
        timestamp: new Date().getTime(),
        startPrice: $("#startPrice").val(),
        markupRange: $("#markupRange").val(),
        earnestMoney: $("#earnestMoney").val(),
        buyoutPrice: $("#buyoutPrice").val(),
        bottomPrice: $("#basePrice").val(),
        startType: $(".beginTime .hover").attr('date-begin'),
        startTime: dataTime,
        auctionDuration: $("#timeSelect").val(),
        endedPrematurely: $("#aheadTime").val(),
        automaticDelay: $("#renewal").val(),
        praiseLimit: $("#user").val(),
        allowReturns: $("#salesReturn").val(),
        tradingMode: toString,
        textRemark: $("#textRemark").val(),
        placeOfDelivery: $("#address").val(),
        auctionStatus: postStatus,
        auctionId: auctionId,
    };
    $.ajax({
        url: link + "/goods/auctions",
        type: "POST",
        data: data,
        dataType: "Json",
        success: function (res) {
            console.log(res);
            $("#loading").remove();
            handler({
                res: res,
                fun1: function () {
                    console.log('存储');
                    if (postStatus == "TEMPORARY") {
                        window.location.href = "issue_auction.html"
                    } else {
                        window.location.href = "myHome.html"
                    }
                }
            })
        },
        error: function (e) {
        }
    });
}

//发布帖子
function send_post(postStatus) {
    $("body").append('<div id="loading"><div class="gif"><img src="img/timg.gif"></div></div>');
    var treasureId = '';
    var plate = '';
    if (Istrue($.cookie("treasureId"))) {
        treasureId = $.cookie("treasureId")
    } else {
        treasureId = ''
    }
    // alert(111)
    console.log(treasureId);
    if (Istrue($.cookie("plate"))) {
        plate = $.cookie("plate")
    } else {
        plate = ''
    }
    var form = new FormData();
    form.append("title", $("#title").val());
    form.append("text", textareaTex());
    if (videoArr.length) {
        form.append('videoFile', videoArr[0]);
    }
    // alert(dataArr[1].length)
    // alert(dataArr[1])
    // alert(3333)
    if (dataArr[1].length) {
        for (var i = 0; i < dataArr[1].length; i++) {
            // alert(dataArr[1].length)
            form.append('imageFiles', base64toFile(dataArr[1][i].base64,));
        }
    }
    form.append("themeId", $(".lable_select .lable").attr("data-themeId"));
    form.append("groupId", $(".addr_select .lable").attr("data-groupId"));
    form.append("postStatus", postStatus);
    form.append("postId", treasureId);
    form.append("plate", plate);
    form.append("requestId", 'd79b9efa995f');
    form.append("timestamp", new Date().getTime());
    // alert(444444)
    $.ajax({
        url: link + "/social/posts/addPost",
        type: "POST",
        contentType: false,
        processData: false,
        data: form,
        dataType: "Json",
        success: function (res) {
            console.log(res);
            $("#loading").remove();
            handler({
                res: res,
                fun1: function () {
                    window.location.href = "myHome.html"
                }
            })
        },
        error: function (e) {
        }
    });
}


//改写藏品帖子
function change(postStatus) {
    if ($("#title").val() == "") {
        hint("请输入商品标题");
        return false;
    }
    if ($(".mark_detail").val() == "") {
        hint('请输入商品介绍');
        console.log(22222);
        return false;
    }
    $("body").append('<div id="loading"><div class="gif"><img src="img/timg.gif"></div></div>');
    var form = new FormData();
    var paramArray = [];//存放自定义标题内容
    //获取自定义标题内容
    if ($(".custom li").length > 0) {
        for (var i = 0; i < $(".custom li").length; i++) {
            var title = $(".custom li").eq(i).children(".name").text();
            var texts = $(".custom li").eq(i).children(".lable").children("span").text();
            form.set('themeName[' + i + '].name', title);
            form.set('themeName[' + i + '].value', texts);
        }
    }

    var treasureId = '';
    if (Istrue($.cookie("treasureId"))) {
        treasureId = $.cookie("treasureId")
    } else {
        treasureId = ''
    }


    form.append("postId", treasureId);
    form.append("title", $("#title").val());
    form.append("text", textareaTex());
    form.append("themeId", $(".lable_select .lable").attr("data-themeid"));
    form.append("groupId", $(".addr_select .lable").attr("data-groupid"));
    form.append("postStatus", postStatus);
    // form.append("status", 'TEMPORARY');
    form.append("requestId", 'd79b9efa995f');
    form.append("timestamp", new Date().getTime());
    $.ajax({
        url: link + "/social/posts/modifyPost",
        type: "POST",
        contentType: false,
        processData: false,
        data: form,
        dataType: "Json",
        success: function (res) {
            console.log(res);
            $("#loading").remove();
            handler({
                res: res,
                fun1: function () {
                    window.location.href = "myhome.html"
                }
            })
        },
        error: function (e) {
        }
    });
}


//藏品转拍品
function transition(postStatus) {
    if ($("#startPrice").val() == "") {
        hint("请输入起拍价格");
        return false;
    }
    if ($("#markupRange").val() == "") {
        hint("请输入加价幅度金额");
        return false;
    }
    if ($("#earnestMoney").val() == "") {
        hint("请输入保证金金额");
        return false;
    }
    if (!$("#buyoutPrice").val() == "") {
        if (Number($("#buyoutPrice").val()) <= Number($("#startPrice").val())) {
            hint("请输入正确的拍卖价格");
            return false;
        }
    }
    if ($("#address").val() == "") {
        hint('请填写区域地址');
        return false;
    }
    if ($(".pattern li.hover").length < 1) {
        hint('请区选择交易模式');
        return false;
    }
    if ($(".auction_protocol.hover").length < 1) {
        hint("请认真阅读并同意拍卖协议");
        return false;
    }
    var myDate = new Date();
    var year = myDate.getFullYear();
    var month = myDate.getMonth() + 1;
    var getDate = myDate.getDate();
    var gethours = myDate.getHours();
    var getMinutes = myDate.getMinutes();
    var getSeconds = myDate.getSeconds();
    console.log(month);
    if (month < 10) {
        month = "0" + month
    }
    if (getDate < 10) {
        getDate = "0" + getDate
    }
    if (gethours < 10) {
        gethours = "0" + gethours
    }
    if (getMinutes < 10) {
        getMinutes = "0" + getMinutes
    }
    if (getSeconds < 10) {
        getSeconds = "0" + getSeconds
    }
    var dataTime = '';
    console.log($("#demo1").val());

    if ($(".beginTime .hover").attr('date-begin') == "IMMEDIATELY") {
        dataTime = year + '-' + month + '-' + getDate + ' ' + gethours + ':' + getMinutes + ":00"
    } else {
        if ($("#demo1").val() == "") {
            hint('请选择日期');
            return false;
        }
        dataTime = $("#demo1").val() + ' ' + $("#hour").val() + ':' + $("#min").val() + ":00"
    }
    var tradingMode = [];
    for (var i = 0; i < $(".pattern .hover").length; i++) {
        tradingMode.push($(".pattern .hover").eq(i).attr('data-title'))
    }
    var toString = tradingMode.toString();
    var data = {
        requestId: 'd79b9efa995f',
        timestamp: new Date().getTime(),
        startPrice: $("#startPrice").val(),
        auctionId: getQueryStringByName("auctionId"),
        markupRange: $("#markupRange").val(),
        earnestMoney: $("#earnestMoney").val(),
        buyoutPrice: $("#buyoutPrice").val(),
        bottomPrice: $("#basePrice").val(),
        startType: $(".beginTime .hover").attr('date-begin'),
        startTime: dataTime,
        auctionDuration: $("#timeSelect").val(),
        endedPrematurely: $("#aheadTime").val(),
        automaticDelay: $("#renewal").val(),
        praiseLimit: $("#user").val(),
        allowReturns: $("#salesReturn").val(),
        tradingMode: toString,
        textRemark: $("#textRemark").val(),
        placeOfDelivery: $("#address").val(),
        auctionStatus: postStatus,
    };
    $.ajax({
        url: link + "/goods/auctions/resale",
        type: "POST",
        data: data,
        dataType: "Json",
        success: function (res) {
            console.log(res);
            handler({
                res: res,
                fun1: function () {
                    console.log('存储');
                    window.location.href = "myHome.html"
                }
            })
        },
        error: function (e) {
        }
    });
}


function textareaTex() {
    let font_size = 28;
    var textareaValue = '';
    $(".textarea").each(function () {
        var value_text = $(this).val();
        console.log(value_text);
//		var width_length = $(this).width() - font_size;
        var valueList = value_text.split("\n");
        console.log(valueList);
        var length = valueList.length;
        if (length > 1) {
            for (var j = 0; j < length - 1; j++) {
                textareaValue += valueList[j] + "<br/>";
            }
            console.log(length);
            textareaValue += valueList[length - 1]
        } else {
            textareaValue += value_text
        }
        textareaValue = textareaValue.replace(/\n\n/g, "<br/>");
        console.log(textareaValue);
    });
    return textareaValue;
}


//获取所有话题列表
function getThemeList() {
    var paramObject = getParamObject();
    paramObject.timestamp = new Date().getTime(); // 请求时间戳
    paramObject.requestId = md5($.param(paramObject)); // 请求ID
    $.ajax({
        url: link + "/manager/theme/getThemeList?" + $.param(paramObject),
        dataType: "Json",
        success: function (res) {
            console.log(res);
            handler({
                res: res,
                fun1: function () {

                }
            })
        },
        error: function (e) {

        }
    });
}


// 获取所有帖子接口
function getPost(orderBy) {
    paramObject.orderBy = orderBy;
    paramObject.orderMode = 'DESC';
    $.ajax({
        url: link + "/social/posts/getPostList?" + $.param(paramObject),
        dataType: "Json",
        success: function (res) {
            console.log(res);
            handler({
                res: res,
                fun1: function () {
                    var curPageData = res.data; // 接口返回的当前页数据列表
                    var totalSize = res.count; // 接口返回的总数据量(比如列表有26个数据,每页10条,共3页; 则totalSize值为26)
                    mescroll.endBySize(curPageData.length, totalSize); // 联网成功的回调,隐藏下拉刷新和上拉加载的状态;
                    setListData(curPageData); // 设置列表数据
                }
            })
        },
        error: function (e) {
            mescroll.endErr()
        }
    });
}

//添加新话题
function addTheme() {
    var paramObject = getParamObject();
    paramObject.timestamp = new Date().getTime(); // 请求时间戳
    paramObject.requestId = md5($.param(paramObject)); // 请求ID
    $.ajax({
        url: link + "/manager/theme/addTheme?" + $.param(paramObject),
        dataType: "Json",
        success: function (res) {
            console.log(res);
            handler({
                res: res,
                fun1: function () {

                }
            })
        },
        error: function (e) {

        }
    });
}

//添加删除关注人
function plantGrassOrCancel(sellerId, num, total) {

    var paramObject = getParamObject();
    paramObject.timestamp = new Date().getTime(); // 请求时间戳
    paramObject.requestId = md5($.param(paramObject)); // 请求ID
    paramObject.sellerId = sellerId; // 关注人ID
    $.ajax({
        url: link + "/social/follow/plantGrassOrCancel",
        type: "POST",
        data: paramObject,
        dataType: "Json",
        success: function (res) {
            console.log(res);
            handler({
                res: res,
                fun1: function () {
                    $('.user_' + sellerId + ' .attention').attr("data-isFollow", res.data);
                    if (res.data == true) {
                        $('.user_' + sellerId + ' .attention').addClass("hover");
                        $('.user_' + sellerId + ' .attention').text("已关注");
                        $('.user_' + sellerId + ' .attention img').attr("")
                    } else {
                        $('.user_' + sellerId + ' .attention').removeClass("hover");
                        if (total == 0) {
                            $('.user_' + sellerId + ' .attention').text("回粉")
                        } else {
                            $('.user_' + sellerId + ' .attention').text("关注")
                        }
                    }
                    console.log(num);
                    if (num == 0) {
                        $(".fans p").text(res.count)
                    }
                }
            })
        },
        error: function (e) {

        }
    });
}

//点赞帖子取消点赞帖子
function postcollect(postId, num) {
    // alert(postId)
    var paramObject = getParamObject();
    paramObject.timestamp = new Date().getTime(); // 请求时间戳
    paramObject.requestId = md5($.param(paramObject)); // 请求ID
    paramObject.postId = postId; // 帖子ID
    $.ajax({
        url: link + "/social/postcollect",
        type: "POST",
        data: paramObject,
        dataType: "Json",
        success: function (res) {
            console.log(res);
            handler({
                res: res,
                fun1: function () {
                    // alert(1111)
                    if (num == 1) {
                        postcollectList();
                    }
                    $(".like" + postId).text(NumberHandle(res.count));
                    if (res.data == true) {
                        $(".like" + postId).addClass("hover")
                    } else {
                        $(".like" + postId).removeClass("hover")
                    }
                }
            })
        },
        error: function (e) {

        }
    });
}


//添加帖子评论
function addPostComment(content, postId) {
    var paramObject = getParamObject();
    paramObject.timestamp = new Date().getTime(); // 请求时间戳
    paramObject.content = content; // 评论内容
    paramObject.postId = postId; // 帖子ID
    paramObject.floorNo = '0'; // 帖子ID
    paramObject.requestId = md5($.param(paramObject)); // 请求ID
    $.ajax({
        url: link + "/social/postcomment/addPostComment",
        type: "POST",
        data: paramObject,
        dataType: "Json",
        success: function (res) {
            console.log(res);
            handler({
                res: res,
                fun1: function () {
                    history.go(0)
                }
            })
        },
        error: function (e) {

        }
    });
}


//获取圈子达人
function geGroupMaster(groupId) {
    var paramObject = getParamObject();
    paramObject.timestamp = new Date().getTime(); // 请求时间戳
    paramObject.groupId = groupId; // 圈子ID
    paramObject.requestId = md5($.param(paramObject)); // 请求ID
    $.ajax({
        url: link + "/rating/groupmaster/geGroupMaster?" + $.param(paramObject),
        success: function (res) {
            console.log(res);
            handler({
                res: res,
                fun1: function () {
                    var str = '';
                    if (res.data.length > 0) {
                        for (var i = 0; i < res.data.length; i++) {
                            var pd = res.data[i];
                            let username = "";
                            if (pd.username.length > 4) {
                                username = pd.username.substring(0, 4) + "...";
                            } else {
                                username = pd.username
                            }
                            str += '<li onclick="window.location.href=\'myHome.html?userId=' + pd.userId + '\'">' +
                                '<div class="head_img">' + headImgHtml(pd.avatarPath) + '</div>' +
                                '<p class="name">' + username + '</p>' +
                                '</li>';
                            $("#expert ul").html(str)
                        }
                        var length = res.data.length;
                        $("#expert ul").css({
                            "width": length * 160
                        })
                    } else {
                        $(".expertList").hide();
                    }

                }
            })
        },
        error: function (e) {

        }
    });
}


//获取圈子详情
function getOneGroupCase(groupCaseId) {
    var paramObject = getParamObject();
    paramObject.timestamp = new Date().getTime(); // 请求时间戳
    paramObject.groupCaseId = groupCaseId; // 圈子ID
    paramObject.requestId = md5($.param(paramObject)); // 请求ID
    $.ajax({
        url: link + "/manager/groupcase/getOneGroupCase?" + $.param(paramObject),
        dataType: "Json",
        success: function (res) {
            console.log(res);
            handler({
                res: res,
                fun1: function () {
                    var pd = res.data;
                    var str = '';
                    // $('title').text(pd.groupName)
                    $("title").text(pd.groupName);
                    $("#fixation_menu .title").text(pd.groupName);
                    $("#fixation_menu .userNum").text(pd.userNum);
                    $("#fixation_menu .postsNum").text(pd.postsNum);
                    str = '<img src="' + link + pd.largeImage + '">' +
                        '<div class="flot_box"></div>' +
                        '<div class="title">' +
                        '<p class="name">' + pd.groupName + '</p>' +
                        '<div class="number">' +
                        '<p class="collect">圈粉:<span>' + pd.userNum + '</span></p>' +
                        '<p class="post">内容:<span>' + pd.postsNum + '</span></p>' +
                        '</div>' +
                        '<p class="text">' + pd.groupText + '</p>';
                    if (res.count) {
                        str += '<div class="addCircle" onclick="addGroupForUser(\'' + pd.groupId + '\')">退出圈子</div>'
                    } else {
                        str += '<div class="addCircle" onclick="addGroupForUser(\'' + pd.groupId + '\')">加入圈子</div>'
                    }
                    str += '</div>';
                    $("#circleMsg").html(str)
                }
            })
        },
        error: function (e) {

        }
    });
}

//进出圈子
function addGroupForUser(groupId) {
    let paramObject = getParamObject();
    console.log(paramObject);
    console.log(groupId);
    paramObject.timestamp = new Date().getTime(); // 请求时间戳
    paramObject.groupId = groupId; // 圈子ID
    paramObject.requestId = md5($.param(paramObject)); // 请求ID
    $.ajax({
        url: link + "/rating/groupmaster/changeGroupForUser",
        type: "POST",
        data: paramObject,
        dataType: "Json",
        success: function (res) {
            console.log(res);
            handler({
                res: res,
                fun1: function () {
                    if (res.data == false) {
                        $(".addCircle").text('加入圈子')
                    } else {
                        $(".addCircle").text('退出圈子')
                    }
                }
            })
        },
        error: function (e) {

        }
    });
}


//获取单个部落
function getOneHorde(hordeId) {
    var paramObject = getParamObject();
    paramObject.timestamp = new Date().getTime(); // 请求时间戳
    paramObject.hordeId = hordeId; // 圈子ID
    paramObject.requestId = md5($.param(paramObject)); // 请求ID
    $.ajax({
        url: link + "/manager/horde/getOneHorde?" + $.param(paramObject),
        dataType: "Json",
        success: function (res) {
            console.log(res);
            handler({
                res: res,
                fun1: function () {
                    var pd = res.data;
                    var str = '';
                    var btHtml = '';
                    $("#fixation_menu .title").text(pd.hordeName);
                    $("#fixation_menu .userNum").text(pd.userNum);
                    $("#fixation_menu .postsNum").text(pd.postsNum);
                    str = '<img src="' + link + pd.largeImage + '">' +
                        '<div class="flot_box"></div>' +
                        '<div class="title">' +
                        '<p class="name">' + pd.hordeName + '</p>' +
                        '<div class="number">' +
                        '<p class="collect">成员:<span>' + pd.userNum + '</span></p>' +
                        '<p class="post">内容:<span>' + pd.postsNum + '</span></p>' +
                        '</div>';
                    '<p class="text">' + pd.hordeText + '</p>';
                    if (res.count) {
                        str += '<div class="addCircle" data-hordeId="\'' + pd.hordeId + '\'">已关注</div>'
                    } else {
                        str += '<div class="addCircle" data-hordeId="\'' + pd.hordeId + '\'">关注</div>'
                    }
                    str += '</div>';
                    $("#circleMsg").html(str);

                    btHtml = '<button onclick="basicOperation(\'' + pd.hordeId + '\',\'JOIN\')"><span class="joinbt">申请加入</span></button>' +
                        '<button><span class="chatbt">群聊</span></button>';
                    $(".button_box").html(btHtml);
                    plantHorde()
                }
            })
        },
        error: function (e) {

        }
    });
}

//添加或删除关注人点击事件
function plantHorde() {
    $(".addCircle").click(function () {
        var isfollow = $(this).attr('data-isfollow');
        hordeId = $(this).data('hordeId');
        if (isfollow == 'false') {
            basicOperation(hordeId, 'COLLECT')//添加或删除关注人
        } else {
            let name = $(this).siblings('.name').text();
            $(".hint_box3 .text span").text(name);
            $(".hint_box3").fadeIn(200)
        }
    });

    $(".close").click(function () {
        $(".hint_box3").fadeOut(200);
    });
    $(".affirm").click(function () {
        $(".hint_box3").fadeOut(200);
        basicOperation(hordeId, 'UNCOLLECT')//添加或删除关注人
    })

}

//获取部落成员列表
function getHordeMaster(hordeId) {
    var paramObject = getParamObject();
    paramObject.timestamp = new Date().getTime(); // 请求时间戳
    paramObject.hordeId = hordeId; // 圈子ID
    paramObject.requestId = md5($.param(paramObject)); // 请求ID
    $.ajax({
        url: link + "/rating/hordemaster/getHordeMaster?" + $.param(paramObject),
        success: function (res) {
            console.log(res);
            handler({
                res: res,
                fun1: function () {
                    var str = '';
                    var strList = '';
                    if (res.data.length > 0) {
                        for (var i = 0; i < res.data.length; i++) {
                            var pd = res.data[i];
                            let username = "";
                            if (pd.username.length > 4) {
                                username = pd.username.substring(0, 4) + "...";
                            } else {
                                username = pd.username
                            }
                            if (pd.masterLevel == '4' || pd.masterLevel == '3') {
                                str += '<li onclick="window.location.href=\'myHome.html?userId=' + pd.userId + '\'">' +
                                    '<div class="head_img">' + headImgHtml(pd.avatarPath) + '</div>' +
                                    '<p class="name">' + username + '</p>' +
                                    '</li>'
                            } else if (pd.masterLevel == '2') {
                                strList += '<li onclick="window.location.href=\'myHome.html?userId=' + pd.userId + '\'">' +
                                    '<div class="head_img">' + headImgHtml(pd.avatarPath) + '</div>' +
                                    '<p class="name">' + username + '</p>' +
                                    '</li>'
                            }


                        }
                        $("#expert ul").html(str);
                        $("#expert ul").html(str);
                        var length = res.data.length;
                        $("#expert ul").css({
                            "width": length * 160
                        })
                    } else {
                        $(".expertList").hide();
                    }

                }
            })
        },
        error: function (e) {

        }
    });
}


//获取话题详情
function getOneTheme(themeId) {
    var paramObject = getParamObject();
    paramObject.timestamp = new Date().getTime(); // 请求时间戳
    paramObject.themeId = themeId; // 圈子ID
    paramObject.requestId = md5($.param(paramObject)); // 请求ID
    $.ajax({
        url: link + "/manager/theme/getOneTheme?" + $.param(paramObject),
        dataType: "Json",
        success: function (res) {
            console.log(res);
            handler({
                res: res,
                fun1: function () {
                    var pd = res.data;
                    var str = '';
//	            	var imgArr = pd.image.split(";");
//	            	str ='<img src="'+link+imgArr[1]+'">'+

                    $('title').text(pd.themeName);
                    str = '<img src="img/association/img.jpg">' +

                        '<div class="flot_box"></div>' +
                        '<div class="title">' +
                        '<p class="name">' + pd.themeName + '</p>' +
                        '<div class="number">' +
                        '<p class="post">内容:<span>' + pd.postsNum + '</span></p>' +
                        '</div>' +
                        '</div>';
                    $("#themeMsg").html(str);
                    $("#fixation_menu .title").text(pd.themeName);
                    $("#fixation_menu .txt").html(pd.postsNum + '内容')
                }

            })
        },
        error: function (e) {

        }
    });
}


//获取分类列表
function getSify() {
    var paramObject = getParamObject();
    paramObject.timestamp = new Date().getTime(); // 请求时间戳
    paramObject.requestId = md5($.param(paramObject)); // 请求ID
    $.ajax({
        url: link + "/goods/cates?" + $.param(paramObject),
        dataType: "Json",
        success: function (res) {
            console.log(res);
            handler({
                res: res, fun1: function () {
                    console.log(res);
                    var parentResult = "";
                    var result = "";
                    for (var i = 0; i < res.data.length; i++) {
                        parentResult += '<li>' + res.data[i].cateName + '</li>';
                        result += '<ul class="part' + i + '"><p class="title">' + res.data[i].cateName + '</p>';
                        for (var j = 0; j < res.data[i].childList.length; j++) {
                            result += '<li onclick="productList(\'' + res.data[i].childList[j].cateId + '\')">' +
                                '<img src="' + link + res.data[i].childList[j].cateImg + '" alt="" />' +
                                '<p>' + res.data[i].childList[j].cateName + '</p>' +
                                '</li>'
                        }
                        result += '</ul>'
                    }
                    $("#sidebar_menr ul").append(parentResult);
                    $("#container").append(result);
                    $("#sidebar_menr ul li:nth-child(1)").addClass("hover");
                    scroll();
                }
            })
        },
        error: function (e) {
        }
    })
}

//获取分类列表
function cates() {
    var jsonList = [];
    $.ajax({
        url: link + "/goods/cates",
        type: "GET",
        data: {
            requestId: 'd79b9efa995f',
            timestamp: new Date().getTime(),
        },
        dataType: "Json",
        success: function (res) {
            console.log(res);
            handler({
                res: res, fun1: function () {
                    console.log(res);
                    var parentResult = "";
                    for (var i = 1; i < res.data.length; i++) {
                        var pd = res.data[i];
                        parentResult += '<li data-cateId1="' + pd.cateId + '">' + pd.cateName + '</li>';
                        jsonList.push(pd.childList);
                    }
                    $(".oneList ul").html(parentResult);
//     				$(".twoList ul").html(listHtml(jsonList[0]));
                    $(".oneList li").click(function () {
                        var index = $(this).index();
                        $(".oneList li").removeClass("hover");
                        $(this).addClass("hover");

                        $(".twoList ul").html(listHtml(jsonList[index]));
                        sifySelect()
                    });
                    sifySelect()
                }
            })
        },
        error: function (e) {
        }
    })
}


//获取用户信息
function getOneSocial(userId) {
    var paramObject = getParamObject();
    paramObject.timestamp = new Date().getTime(); // 请求时间戳
    paramObject.checkUserId = userId; // 用户ID
    paramObject.requestId = md5($.param(paramObject)); // 请求ID
    $.ajax({
        url: link + "/user/user/getOneSocial?" + $.param(paramObject),
        dataType: "Json",
        success: function (res) {
            console.log(res);
            handler({
                res: res,
                fun1: function () {
                    let pd = res.data;
                    let count = res.count;
                    inhtml(pd, count)

                }

            })
        },
        error: function (e) {

        }
    });
}


function sifySelect() {
    $(".twoList ul li").click(function () {
        let txt = "";
        $(this).siblings("li").removeClass("hover");
        $(this).addClass("hover");
        txt = $(".oneList .hover").text() + $(this).text();
        console.log(txt);
        var cateId = $(this).attr("data-cateId");
        $(".sify_select .lable span").text(txt);
        $(".sify_select .lable span").attr("data-cateId", cateId);
        $(".sify_box").hide();
    });
}

function listHtml(arr) {
    var str = "";
    for (var i = 0; i < arr.length; i++) {
        var pd = arr[i];
        str += '<li data-cateId="' + pd.cateId + '">' + pd.cateName + '</li>'
    }
    return str
}


/*function circleImg(img,f){
	var imgArr = img.split(";");
	return imgArr[f];
}*/


//获取推荐分类帖子只显示一张图片
function postImgHtml(video, img, id, postType, plates) {
    // console.log(video,img,id,postType,plates)
    var inhtml = "";
    if (Istrue(video)) {
        if (!Istrue(img)) {
            if (postType == 'AUCTION') {
                inhtml = '<dd><img onclick="videoPlay(\'' + video + '\')" src="img/videoImgbig.jpg"></dd>'
            } else {
                inhtml = '<dd><img onclick="videoPlay(\'' + video + '\')" src="img/videoImgbig.jpg"></dd>'
            }
        }
    }
    if (Istrue(img)) {
        var imgArrList = [];//存放分割图片后的list数组
        var imgArr = img.split(";"); //原图list数组

        for (var m = 0; m < imgArr.length; m++) {
            var imgList = imgArr[m].split('.');
            // console.log(imgList);
            imgArrList.push(imgList);
        }

        for (var j = 0; j < plates; j++) {
            let src = '';
            if (plates == 2) {
                src = link + imgArrList[j][0] + '-thumbnail.' + imgArrList[j][1];
            } else {
                if (j == 0) {
                    src = link + imgArrList[j][0] + '-thumbnail.' + imgArrList[j][1];
                } else {
                    src = link + imgArrList[j][0] + '.thumbnail.' + imgArrList[j][1];
                }
            }

            inhtml += '<dd onclick="img_big(\'' + imgArr[j] + '\')"><img onload="imgHeight(this)" class="thumbnail" src="' + src + '" onerror="src=\'img/img_default.png\'"></dd>'
            /* if(plates > 3){
                inhtml +='<dd onclick="img_big(\''+imgArr[j]+'\')"><img class="thumbnail" src="'+link+imgArrList[j][0]+'.thumbnail.'+imgArrList[j][1]+'"></dd>'
            }else{
                inhtml +='<dd onclick="img_big(\''+imgArr[j]+'\')"><img class="thumbnail" src="'+link+imgArrList[j][0]+'.thumbnail.'+imgArrList[j][1]+'"></dd>'
            } */
            // imgHeight('thumbnail')

        }
    }

    return inhtml;
}

// 单张图片缩略图显示
function compressPicture(smoll, img) {
    var inhtml = "";
    if (img) {
        console.log(img);
        var imgArr = img.split("."); //原图list数组
        if (smoll == 1) {
            // inhtml +='<img onload="imgHeight(this)" src="'+link+imgArr[0]+'-thumbnail.'+imgArr[1]+'" onerror="src=\''+link+imgArr[0]+'.'+imgArr[1]+'\'">'//大图
            inhtml += '<img onload="imgHeight(this)" src="' + link + imgArr[0] + '-thumbnail.' + imgArr[1] + '" onerror="src=\'img/img_default.png\'">'//大图
        } else {
            // inhtml +='<img onload="imgHeight(this)" src="'+link+imgArr[0]+'-thumbnail.'+imgArr[1]+'" onerror="src=\''+link+imgArr[0]+'.'+imgArr[1]+'\'">'//小图
            inhtml += '<img onload="imgHeight(this)" src="' + link + imgArr[0] + '.thumbnail.' + imgArr[1] + '" onerror="src=\'img/img_default.png\'">'//小图
        }
    } else {
        inhtml += '<img class="imgwidth" src="img/img.png">'
    }
    return inhtml;
}

//获取推荐分类帖子只显示一张图片
function headImgHtml(img) {
    var inhtml = "";
    // console.log(img)
    let type = checkUrl(img);
    // console.log(type);
    if (type == false) {
        var imgArr = img.split("."); //原图list数组
        inhtml += '<img class="imgwidth" src="' + link + imgArr[0] + '.thumbnail.' + imgArr[1] + '" onerror="src=\'' + link + imgArr[0] + '.' + imgArr[1] + '\'">'
    } else {
        inhtml += '<img class="imgwidth" src="' + img + '">'
    }

    return inhtml;
}

//列表图片高度
function imgHeight(ts) {
    let ddWidth = $(ts).parent().width();
    let ddHeidth = $(ts).parent().height();
    let imgWidth = $(ts).width();
    let imgHeidth = $(ts).height();
    // console.log(imgWidth,imgHeidth,ddWidth,ddHeidth)
    if (imgWidth / imgHeidth > ddWidth / ddHeidth) {
        $(ts).css({'height': '100%'})
    } else {
        $(ts).css({'width': '100%'})
    }

}


/* //列表图片高度
function imgHeight(){
	let length = $("#dataList dd").length;
	for(var i = 0 ; i < length ; i ++){
		let ddWidth = $("#dataList dd").eq(i).width();
		let ddHeidth = $("#dataList dd").eq(i).height();
		let imgWidth = $("#dataList dd").eq(i).children('img').width();
		let imgHeidth = $("#dataList dd").eq(i).children('img').height();
		console.log(imgWidth,imgHeidth,ddWidth,ddHeidth)
		if(imgWidth/imgHeidth > ddWidth/ddHeidth){
			$("#dataList dd").eq(i).children('img').css({'height':'100%'})
		}else{
			$("#dataList dd").eq(i).children('img').css({'width':'100%'})
		}
	}
	
} */


//获取帖子显示多张图片
function postDetailHtml(video, img) {
    var inhtml = "";
    if (!video == "") {
        inhtml += '<video class="showimg" x5-video-player-type="h5" x5-video-player-fullscreen="true" x-webkit-airplay="allow" webkit-playsinline playsinline src="' + link + video + '" controls preload></video>'
    }
    if (!img == "") {
        var imgArr = img.split(";");
        for (var i = 0; i < imgArr.length; i++) {
            var imgList = imgArr[i].split('.');
            // inhtml += '<img  src="' + link + imgArr[i] + '">'
            inhtml += '<img onclick="img_big(\'' + imgArr[i] + '\')"  src="' + link + imgList[0] + '-thumbnail.' + imgList[1] + '" onerror="src=\'img/img_default.png\'">'
        }
    }
    return inhtml;
}

function videoPlay(src) {
    let str = '';
    str = '<div id="video_box">' +
        '<video src="' + link + src + '" controls="controls" id="video" width="100%"></video>' +
        '<div class="close_back" onclick="closeback(this)"></div>' +
        '</div>';
    $('body').append(str);
    var video = document.getElementById('video');
    video.play();
}

function img_big(src) {

    let str = '';
    str = '<div id="big_img" style="">' +
        '<div class="img pinch-zoom" style="height:100%;"><span style="width:100%;text-align:center;position: absolute;left:50%;top: 50%;transform:translate(-50%,-50%)"><img style="" onload="autoSizeImg(this)" class="autoSizeImg" src="' + link + src + '"></span></div>' +
        '<div class="close_back" onclick="closeback(this)"></div>' +
        '</div>';
    $('body').append(str);
    // autoSizeImg('.autoSizeImg')
    $('div.pinch-zoom').each(function () {
        new RTP.PinchZoom($(this), {});
    });
    // $("#big_img").click(funciton)

}

function autoSizeImg(obj) {
    let height = $(obj).parent().parent(".img").height();
    let width = $(obj).parent().parent(".img").width();
    if ($(obj).width() / $(obj).height() < width / height) {
        if ($(obj).height() > height) {
            $(obj).parent('span').css({'height': '100%'});
            $(obj).css({'height': '100%'});
        } else {
            $(obj).css({'width': '100%'});
        }
    } else {
        $(obj).css({'width': '100%'});
    }


}

function closeback(that) {
    $(that).parent().remove()
}


function texHtml(tex) {
    let texHtml = "";
    if (tex.length > 120) {
        texHtml = "<span>" + cutOut(tex) + "</span><i style='display:none'>" + tex + "</i><a onclick='textShow(this)' style='color:#6eafff'>...全文</a>"
    } else {
        texHtml = tex
    }
    return texHtml;
}

function cutOut(texbox) {
    let textbox = '';
    if (texbox[120] == "<") {
        textbox = texbox.substring(0, 119)
    } else if (texbox[120] == "b" || texbox[119] == "<") {
        textbox = texbox.substring(0, 118)
    } else if (texbox[120] == "r" || texbox[118] == "<") {
        textbox = texbox.substring(0, 117)
    } else if (texbox[120] == "/" || texbox[117] == "<") {
        textbox = texbox.substring(0, 116)
    } else if (texbox[120] == ">" || texbox[116] == "<") {
        textbox = texbox.substring(0, 115)
    } else if (texbox.substring(115, 40) == "<br/>") {
        textbox = texbox.substring(0, 114)
    } else {
        textbox = texbox.substring(0, 120)
    }
    return textbox;
}

function textShow(that) {
    var tex = that.previousSibling.innerHTML;
    console.log(that);
    if (that.innerHTML == "...全文") {
        that.innerHTML = ' 收起';
        that.parentNode.children[0].innerHTML = tex
    } else {
        that.innerHTML = '...全文';
        console.log(cutOut(tex));
        that.parentNode.children[0].innerHTML = cutOut(tex);
    }
}


//获取用户职业
function getUserCareer(userId) {
    var paramObject = getParamObject();
    paramObject.requestUserId = userId; // 用户Id
    paramObject.timestamp = new Date().getTime(); // 请求时间戳
    paramObject.requestId = md5($.param(paramObject)); // 请求ID
    $.ajax({
        url: link + "/user/user/getUserCareer?" + $.param(paramObject),
        success: function (res) {
            console.log(res);
            handler({
                res: res,
                fun1: function () {
                    let resTex = "";
                    let pd = res.data;
                    let result = '';
                    if (Istrue(pd.careerFirstId)) {
                        resTex += pd.careerFirstName;
                        // result += "<li class='li"+pd.careerFirstId+"' data_id='"+pd.careerFirstId+"'>"+pd.careerFirstName+"</li>";
                    }
                    if (Istrue(pd.careerSecondName)) {
                        resTex += ' | ' + pd.careerSecondName
                        // result += "<li class='li"+pd.careerSecondId+"' data_id='"+pd.careerSecondId+"'>"+pd.careerSecondName+"</li>";
                    }
                    if (Istrue(pd.careerThirdName)) {
                        resTex += ' | ' + pd.careerThirdName
                        // result += "<li class='li"+pd.careerThirdId+"' data_id='"+pd.careerThirdId+"'>"+pd.careerThirdName+"</li>";
                    }
                    // $(".pitch .lable").append(result)
                    $(".selectID").text(resTex)
                }
            })
        },
        error: function (e) {

        }
    });
}


//删除草稿
function deletePosts(targetType, targetId, that) {
    var paramObject = getParamObject();
    paramObject.timestamp = new Date().getTime(); // 请求时间戳
    paramObject.requestId = md5($.param(paramObject)); // 请求ID
    paramObject.targetType = targetType; // POSTS帖子 TREASURE藏品
    paramObject.targetId = targetId; // 请求ID *必填
    $.ajax({
        url: link + "/social/posts/deletePosts",
        type: "delete",
        data: paramObject,
        dataType: "Json",
        success: function (res) {
            console.log(res);
            handler({
                res: res,
                fun1: function () {
                    $(that).parent().parent().remove()
                }
            })
        },
        error: function (e) {

        }
    });
}


//获取未读系统消息数量
function getUnReadMsgNum(msgSort) {
    var paramObject = getParamObject();
    paramObject.msgSort = msgSort; // 请求类型 *必填： ALL所有总数 SORT分类数量
    paramObject.timestamp = new Date().getTime(); // 请求时间戳
    paramObject.requestId = md5($.param(paramObject)); // 请求ID
    $.ajax({
        url: link + "/manager/systemmsg/getUnReadMsgNum?" + $.param(paramObject),
        success: function (res) {
            console.log(res);
            handler({
                res: res,
                fun1: function () {

                    if (msgSort == 'SORT') {
                        for (var i = 0; i < res.data.length; i++) {
                            if (res.data[i] > 0) {
                                $(".head_menu ul li").eq(i).find('.dot').show();
                                $("#foot_menu .dot").show();
                            }
                        }
                    } else if (res.data > 0) {
                        $("#foot_menu .dot").show();
                    }
                }
            })
        },
        error: function (e) {

        }
    });
}


/** ==================== 支付 start ==================== **/
if (location.hostname === '2501p.com') {
    location.hostname = 'www.2501p.com';
}
var tradeNo, tradeObj;
initPayment();

function initPayment() {
    orderId = getQueryStringByName('orderId');
    if (orderId !== '') {
        tradeNo = orderId;
        tradeObj = 'ORDER';
    }

    let productId = getQueryStringByName('productId');
    if (productId !== '') {
        tradeNo = productId;
        tradeObj = 'DEPOSIT';
    }
}

function payment(paymentMethod) {
    // 微信支付
    if ('WeChat' === paymentMethod) {
        // 是否微信浏览器
        let userAgent = navigator.userAgent.toLowerCase();
        if (userAgent.indexOf('micromessenger') !== -1) {
            location.replace(link + '/order/payments/authorize?trade_no=' + tradeNo + '&trade_obj=' + tradeObj);
        }
        // 微信H5支付
        else {
            $.ajax({
                'url': link + '/order/payments/wechat',
                'method': 'POST',
                'data': {
                    'trade_no': tradeNo,
                    'trade_obj': tradeObj,
                    'trade_type': 'MWEB'
                }
            }).done(function (response) {
                console.log(response);

                handler({
                    res: response,
                    fun1: function () {
                        location.href = response.data;
                    }
                });
            });
        }
    }
    // 支付宝
    if ('Alipay' === paymentMethod) {
        $.ajax({
            'url': link + '/order/payments/alipay',
            'method': 'POST',
            'data': {
                'trade_no': tradeNo,
                'trade_obj': tradeObj,
                'trade_type': 'wap'
            }
        }).done(function (response) {
            console.log(response);
            let data = response.data;

            data = data.replace('<script>document.forms[0].submit();</script>', '<script type="text/javascript" src="ap.js"></script>\n' +
                '<script type="text/javascript">\n' +
                'let gotoUrl = $(\'[name="punchout_form"]\').attr(\'action\');\n' +
                'const bizContent = $(\'[name="biz_content"]\').val();\n' +
                'gotoUrl += \'&biz_content=\' + encodeURIComponent(bizContent);\n' +
                '_AP.pay(gotoUrl);\n' +
                '</script>');

            $('body').prepend(data);
        });
    }
}

if (typeof WeixinJSBridge == 'undefined') {
    if (document.addEventListener) {
        document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
    } else if (document.attachEvent) {
        document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
        document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
    }
} else {
    onBridgeReady();
}

/**
 * 请求微信JSAPI支付
 */
function onBridgeReady() {
    if ('#wechat_pay' !== location.hash) return;

    $.ajax({
        'url': link + '/order/payments/wechat',
        'method': 'POST',
        'data': {
            'trade_no': tradeNo,
            'trade_obj': tradeObj,
            'trade_type': 'JSAPI'
        }
    }).done(function (response) {
        console.log(response);

        handler({
            res: response,
            fun1: function () {
                getBrandWCPayRequest(response.data);
            }
        });
    });
}

/**
 * 微信内H5调起支付
 */
function getBrandWCPayRequest(reqData) {
    WeixinJSBridge.invoke('getBrandWCPayRequest', reqData, function (res) {
        if (res.err_msg === 'get_brand_wcpay_request:ok') {
            // 使用以上方式判断前端返回,微信团队郑重提示：
            //res.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。
            alert('支付成功');
            // 刷新页面
        } else if (res.err_msg === 'get_brand_wcpay_request:fail') {
            alert('支付失败');
        } else if (res.err_msg === 'get_brand_wcpay_request:cancel') {
            alert('支付取消');
        }
        // 订单
        if ('ORDER' === tradeObj) {
            location.replace('/buyer_noPayment.html?orderId=' + tradeNo);
        }
        // 保证金
        else if ('DEPOSIT' === tradeObj) {
            location.replace('/productDetail.html?productId=' + tradeNo);
        }
    });
}


$.fn.extend({
    toggleWin: function (winBox, boolean) {
        //this 点击显示winBox 点击除了this的 其他地方则隐藏winBox
        //boolean赋值 且 == true 点击除了this和winBox 的 其他地方则隐藏winBox
        $(this).click(function (ev) {
            $(winBox).show();
            ev.stopPropagation();
        });
        $(document).click(function (e) {
            $(winBox).hide();
        });
        if (boolean && boolean == true) {
            $(winBox).click(function (ev) {
                ev.stopPropagation();
            });
        }
    },
    toggSpecify: function (winBox, closeBar) {
        //this 被点击显示winBox
        //closeBar被点击则关闭 winBox,默认 closeBar=#mb
        var winBoxBg = '<div id=\"mb\"></div>';
        $(this).click(function () {
            if (!$("#mb")) {
                $("body").append(winBoxBg);
            }
            $("#mb," + winBox).show();
        });
        closeBar ? closeBar : closeBar = "#mb";
        $(closeBar).click(function () {
            $("#mb," + winBox).hide();
        });
    }

});


//帖子发布统计
function addRecord(postsType, index) {
    var paramObject = getParamObject();
    paramObject.postsType = postsType; // 统计类型 *必填 POSTS帖子 TREASURE藏品 AUCTION拍品
    paramObject.action = 'ATTEMPT'; // 统计动作 *必填 ATTEMPT尝试发布 SAVE保存草稿 RELEASED正式发布
    paramObject.timestamp = new Date().getTime(); // 请求时间戳
    paramObject.requestId = md5($.param(paramObject)); // 请求ID
    $.ajax({
        url: link + "/stat/releaserecord/addRecord",
        type: "POST",
        data: paramObject,
        dataType: "Json",
        success: function (res) {
            console.log(res);
            handler({
                res: res,
                fun1: function () {
                    console.log(index);
                    switch (index) {
                        case '1':
                            window.location.href = 'issue_post.html';
                            break;
                        case '2':
                            window.location.href = 'issue_object.html?id=1';
                            break;
                        case '3':
                            window.location.href = 'issue_object.html?id=2';
                            break;
                    }
                }
            })
        },
        error: function (e) {
        }
    });
}

//判断是否是http或者https开头
function checkUrl(src) {
    var Expression = /http(s)?:\/\/([\w-]+\.)+[\w-]+(\/[\w- .\/?%&=]*)?/;
    var objExp = new RegExp(Expression);
    return objExp.test(src);
    /* if(objExp.test(src==true){

    }else{
        alert("您输入的链接地址有误")
    } */
}


//访问统计
function visiAddRecord(visitType, id) {
    var paramObject = getParamObject();
    paramObject.timestamp = new Date().getTime(); // 请求时间戳
    paramObject.requestId = md5($.param(paramObject)); // 请求ID
    paramObject.visitType = visitType; // 帖子ID
    $.ajax({
        url: link + "/stat/visitrecord/addRecord",
        type: "POST",
        data: paramObject,
        dataType: "Json",
        success: function (res) {
            console.log(res);
            handler({
                res: res,
                fun1: function () {
                    if (visitType == 'INTO') {
                        window.location.href = "post_detail.html?postId=" + id
                    }
                }
            })
        },
        error: function (e) {

        }
    });
}


//用户名超长处理
function userName(name, numbers) {
    let userName = '';
    let number = '';
    if (Istrue(numbers)) {
        number = numbers
    } else {
        number = 10;
    }
    if (name.length > number) {
        userName = name.substring(0, 10) + '...'
    } else {
        userName = name
    }
    return userName;
}

function MsgLink(msgType, itemId) {
    let msgT = Number(msgType);
    switch (msgT) {
        case 1:

            break;
        case 2:

            break;
        case 3:

            break;
        case 4:

            break;
        case 5:

            break;
        case 6:

            break;
        case 7:

            break;
        case 8:

            break;
        case 9:

            break;
        case 10:

            break;
        case 11:
            window.location.href = 'productDetail.html?productId=' + itemId;
            break;
        case 12:
            window.location.href = 'productDetail.html?productId=' + itemId;
            break;
        case 13:
            window.location.href = 'productDetail.html?productId=' + itemId;
            break;
        case 14:
            window.location.href = 'productDetail.html?productId=' + itemId;
            break;
        case 15:
            window.location.href = 'questionList.html?productId=' + itemId;
            break;
        case 16:

            break;
        case 17:

            break;
        case 18:
            window.location.href = 'productDetail.html?productId=' + itemId;
            break;
        case 19:
            window.location.href = 'questionList.html?productId=' + itemId;
            break;
        case 20:
            window.location.href = 'buyer_noPayment.html?orderId=' + itemId;
            break;
        case 21:
            window.location.href = 'buyer_noPayment.html?orderId=' + itemId;
            break;
        case 22:
            window.location.href = 'buyer_noPayment.html?orderId=' + itemId;
            break;
        case 23:
            window.location.href = 'buyer_noPayment.html?orderId=' + itemId;
            break;
        case 24:
            window.location.href = 'buyer_noPayment.html?orderId=' + itemId;
            break;
        case 25:
            window.location.href = 'buyer_noPayment.html?orderId=' + itemId;
            break;
        case 26:
            window.location.href = 'buyer_noPayment.html?orderId=' + itemId;
            break;
        case 27:
            window.location.href = 'buyer_noPayment.html?orderId=' + itemId;
            break;
        case 28:
            window.location.href = 'buyer_noPayment.html?orderId=' + itemId;
            break;
        case 29:
            window.location.href = 'buyer_noPayment.html?orderId=' + itemId;
            break;
        case 30:
            window.location.href = 'post_detail.html?postId=' + itemId;
            break;
        case 31:
            window.location.href = 'post_detail.html?postId=' + itemId;
            break;
        case 32:

            break;
        case 33:

            break;
        case 34:

            break;
        case 35:

            break;
        case 36:

            break;
        case 37:

            break;
        case 38:

            break;
        case 39:

            break;
        case 40:

            break;
    }
}

/* var overscroll = function(el) {
    el.addEventListener('touchstart', function() {
        var top = el.scrollTop
        ,totalScroll = el.scrollHeight
        ,currentScroll = top + el.offsetHeight;
        if(top === 0) {
            el.scrollTop = 1;
        }else if(currentScroll === totalScroll) {
            el.scrollTop = top - 1;
        }
    });
 
    el.addEventListener('touchmove', function(evt) {
    if(el.offsetHeight < el.scrollHeight)
        evt._isScroller = true;
    });
}
        
overscroll(document.querySelector('.scroll'));
document.body.addEventListener('touchmove', function(evt) {
    if(!evt._isScroller) {
        evt.preventDefault();
    }
}); */
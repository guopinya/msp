var Fuse = Fuse || {};
Fuse.Event = ('ontouchstart' in window) ? {
    START: 'touchstart',
    MOVE: 'touchmove',
    END: 'touchend',
    MOVE_END: 'webkitAnimationEnd animationend'
} : {START: 'mousedown', MOVE: 'mousemove', END: 'mouseup'};

//正则式
var reg = /^(((13[0-9]{1})|(18[0-9]{1})|(15[0-9]{1})|(14[0-9]{1})|(17[0-9]{1}))+\d{8})$/;

//接口前缀连接
var link = "/api";

//小于10时往前补0
function checkTime(i) {
    if (i < 10) {
        i = "0" + i;
    }
    return i;
}

//带天数的倒计时
countDown(10);

function countDown(times) {
    var timer = null;
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
    }, 1000);
    if (times <= 0) {
        clearInterval(timer);
    }
}


//接口通用处理方法
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
    window.location.href = "productDetail.html?id=" + id
}

//点击跳转至分类列表
function sifyMenu(id) {
    window.location.href = "sify_menu.html?id=" + id
}

//点击跳转至产品详情
function productList(name) {
    window.location.href = "productList.html?name" + name
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
    return {'status': pass, 'msg': tip};
    return pass;
}


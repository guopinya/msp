$(".head_title li").click(function () {
    $(".head_title li").removeClass("hover");
    $(this).addClass("hover");
    paramObject.requestType = $(this).data('title');
    mescroll.resetUpScroll();
});
var paramObject = getParamObject();
paramObject.requestType = 'ALL';
// 创建mescroll对象
var mescroll = new MeScroll("mescroll", {
    up: {
        callback: upCallback, //上拉回调,此处可简写; 相当于 callback: function (page) { getListData(page); }
        isBounce: false, //此处禁止ios回弹,解析(务必认真阅读,特别是最后一点): http://www.mescroll.com/qa.html#q10
        noMoreSize: 1,
        clearEmptyId: "dataList", //1.下拉刷新时会自动先清空此列表,再加入数据; 2.无任何数据时会在此列表自动提示空

        toTop: { //配置回到顶部按钮
            src: "../res/img/mescroll-totop.png" //默认滚动到1000px显示,可配置offset修改
        }
    }
});

//上拉加载的回调 page = {num:1, size:10}; num:当前页 默认从1开始, size:每页数据条数,默认10
function upCallback(page) {
    paramObject.pageNumber = page.num; // 页码, 默认从1开始 如何修改从0开始 ?
    paramObject.pageSize = page.size; // 页长, 默认每页10条
    paramObject.requestId = md5($.param(paramObject)); // 请求ID
    paramObject.timestamp = new Date().getTime(); // 请求时间戳
    $.ajax({
        url: link + "/manager/groupcase/getGroupCaseList?" + $.param(paramObject),
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

// 设置列表数据
function setListData(curPageData) {
    var str = "";
    for (var i = 0; i < curPageData.length; i++) {
        var pd = curPageData[i];

        str += '<li data-groupId="' + pd.groupId + '">' +
            '<div class="img"><img src="' + link + pd.smallImage + '"></div>' +
            '<div class="circleMsg">' +
            '<p class="name">' + pd.groupName + '</p>' +
            '<div class="detail">' +
            '<p class="people"><span>' + pd.userNum + '</span>人</p>' +
            '<p class="number"><span>' + pd.postsNum + '</span>帖子</p>' +
            '</div>' +
            '</div>' +
            '</li>'
    }

    $("#dataList").append(str);
    $("#circleSelect li").click(function () {
        let tex = $(this).find(".name").text();
        let groupId = $(this).attr('data-groupId');
        $("#circleSelect").hide();
        $(".addr_select .name").hide();
        $(".addr_select .lable").show();
        $(".addr_select .lable span").html('#' + tex + ' <i style="font-style: inherit;color:black">X</i>');
        $(".addr_select .lable").attr("data-groupId", groupId)
    })
}


//获取所有话题列表
var paramObjects = getParamObject();
paramObjects.timestamp = new Date().getTime(); // 请求时间戳
paramObjects.requestId = md5($.param(paramObjects)); // 请求ID
$.ajax({
    url: link + "/manager/theme/getThemeList?" + $.param(paramObjects),
    success: function (res) {
        console.log(res);
        handler({
            res: res,
            fun1: function () {
                var str = '';
                console.log(res.data.length);
                var curPageData = res.data;
                var themeData = [];
                for (var i = 0; i < curPageData.length; i++) {
                    var pd = curPageData[i];
                    str += '<li data-themeId="' + pd.themeId + '">' + pd.themeName + '</li>';
                    themeData.push(pd.childList);
                    for (var j = 0; j < pd.childList.length; j++) {

//              		res += '<li data-themeId="'+curPageData[0]themeHtml[i].themeId+'">'+themeHtml[i].themeName+'</li>'
                    }
                }
                $(".hot_search ul").html(str);
                $(".hot_search ul li:nth-child(1)").addClass("hover");
                console.log(themeData);
                themeSelect(themeData)
            }
        })
    },
    error: function (e) {

    }
});

function themeSelect(themeHtml) {
    var res = '';
    for (var i = 0; i < themeHtml[0].length; i++) {
        res += '<li data-themeId="' + themeHtml[0][i].themeId + '">' + themeHtml[0][i].themeName + '</li>'
    }
    $(".search_history ul").html(res);

    $(".hot_search li").click(function () {
        res = '';
        $(".hot_search li").removeClass("hover");
        $(this).addClass("hover");
        var index = $(this).index();
        for (var i = 0; i < themeHtml[index].length; i++) {
            res += '<li data-themeId="' + themeHtml[index][i].themeId + '">' + themeHtml[index][i].themeName + '</li>'
        }
        $(".search_history ul").html(res);
        selecttheme()
    });
    selecttheme()
}

function selecttheme() {
    $("#themeSelect .submenu li").click(function () {
        let tex = $(this).text();
        let themeid = $(this).attr('data-themeid');
        $("#themeSelect").hide();
        $(".lable_select .name").hide();
        $(".lable_select .lable").show();
        $(".lable_select .lable span").html('#' + tex + ' <i style="font-style: inherit;color:black">X</i>');
        $(".lable_select .lable").attr("data-themeId", themeid)
    })
}


$(".lable_box .lable").click(function () {
    $(this).hide();
    $(this).prev().show();
});
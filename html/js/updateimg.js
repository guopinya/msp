var pass = false;
var Orientation;
var OrientationList = [];
var index = 0;
//	window.onload = function() {
var input = document.getElementById("upgteimg");
var showui = document.getElementById("showui");
var result, video;
var videoArr = [];
var dataArr = [[], []]; // 储存所选图片的结果(文件名和base64数据)
var imageArr = [[], []]; // 储存所选图片的结果(文件名和base64数据)
var fd; //FormData方式发送请求
var showinput = document.getElementById("showinput");
var oSubmit = document.getElementById("submit");
var dateli, dateinput;
var srcList = []; //存放图片源

var form = new FormData();

function randomString(len) { //id随机码
    len = len || 32;
    var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
    /****默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1****/
    var maxPos = $chars.length;
    var pwd = '';
    for (i = 0; i < len; i++) {
        pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
    }
    return pwd;
}

// alert(11111)
if (typeof FileReader === 'undefined') {
    alert("抱歉，你的浏览器不支持 FileReader");
    input.setAttribute('disabled', 'disabled');
} else {
    // alert(3333)
    input.addEventListener('change', readFile, false);
    // alert(55555)
}

function readFile() {
    // alert(2222)
//			fd = new  ();
    form = new FormData();
    var iLen = this.files.length;

    var currentReViewImgIndex = 0;
    // alert(this.files.length)
    for (var i = 0; i < iLen; i++) {
        var totalLength = parseInt(dataArr[1].length) + parseInt(iLen);
        if (totalLength > 9) {
            alert('最多只能上传9张图片哦');
            return false
        }
        console.log(fd);
        var reader = new FileReader();
        reader.index = i;
//				fd.append(i, this.files[i]);

        console.log(fd);
        console.log(this.files[i].name);
        console.log(this.files[i]);
        // alert(this.files[i])
        console.log(this.files[i]);
        console.log(this.files[i].type);

        if (this.files[i].type.match(/video/i)) {
//					form.append('videoFile', this.files[i]);
            if (videoArr.length < 1) {
                videoArr.push(this.files[i])
            }
        }
        console.log(imageArr);
        reader.readAsDataURL(this.files[i]); //转成base64
        reader.fileName = this.files[i].name;
        reader.files = this.files[i];
        console.log(this.files[i]);
        var file = this.files[i];

        /* EXIF.getData(file, function() {
            EXIF.getAllTags(this);
            console.log(EXIF.pretty(this));
            Orientation = EXIF.getTag(this, 'Orientation');
            console.log(EXIF.getTag(this, 'Orientation'));
            // OrientationList.push
            //return;
        }); */


        reader.onload = function (e) {
            // var img = new Image();
            console.log(this);
            var imgMsg = {
                name: randomString(5), //获取文件名
                base64: this.result, //reader.readAsDataURL方法执行完后，base64数据储存在reader.result里
            };
            /* EXIF.getData(file, function() {
                EXIF.getAllTags(this);
                console.log(EXIF.pretty(this));
                Orientation = EXIF.getTag(this, 'Orientation');
                console.log(EXIF.getTag(this, 'Orientation'));
                OrientationList.push(Orientation)
                //return;
                addImg(imgMsg,OrientationList)
            }); */

            console.log(OrientationList);


            if (imgMsg.base64.match(/image/i)) {

                dataArr[1].push(imgMsg);
                // alert(dataArr[1])
            } else if (dataArr[0].length < 1) {
                dataArr[0].push([imgMsg]);
            }

            console.log(dataArr);
            for (var j = 0; j < dataArr[1].length; j++) {
                currentReViewImgIndex = j
            }
            var li = document.createElement('li');
            var div = document.createElement('div');
            console.log($("#showui video").length);
            // alert($("#showui video").length)


            if (imgMsg.base64.match(/image/i)) {　　 //判断上传文件是否为图片
                console.log(Orientation);
                /* if(Orientation == 6){
                    result = '<div class="showdiv"><img class="left" src="img/Arrow_left.svg" /><img class="center" src="img/delete.svg" /><img class="right" src="img/Arrow_right.svg" /></div><img style="transform:rotate(90deg) translate(-50%, 50%);height:100%" id="img' + currentReViewImgIndex + randomString(1) + randomString(2) + randomString(5) + '" class="showimg" src="' + imgMsg.base64 + '" />';
                }else{ */
                result = '<div class="showdiv"><img class="left" src="img/Arrow_left.svg" /><img class="center" src="img/delete.svg" /><img class="right" src="img/Arrow_right.svg" /></div><img id="img' + currentReViewImgIndex + randomString(1) + randomString(2) + randomString(5) + '" class="showimg" src="' + imgMsg.base64 + '" />';
                // }
                li.innerHTML = result;
                showui.appendChild(li);
            } else if ($("#showui video").length == 0) {
                video = '<div id="video"><video id="img' + currentReViewImgIndex + randomString(1) + randomString(2) + randomString(5) + '" class="showimg" src="' + dataArr[0][0][0].base64 + '" ></video></div>';
                div.innerHTML = video;
                showui.prepend(div);
                accept = "video/*";
                $("#upgteimg").attr("accept", "image/*")
            }
            index++;


        }
    }
}

function addImg(imgMsg, Orientation) {
    console.log(OrientationList);


    if (imgMsg.base64.match(/image/i)) {

        dataArr[1].push(imgMsg);
        // alert(dataArr[1])
    } else if (dataArr[0].length < 1) {
        dataArr[0].push([imgMsg]);
    }

    console.log(dataArr);
    for (var j = 0; j < dataArr[1].length; j++) {
        currentReViewImgIndex = j
    }
    var li = document.createElement('li');
    var div = document.createElement('div');
    console.log($("#showui video").length);
    // alert($("#showui video").length)


    if (imgMsg.base64.match(/image/i)) {　　 //判断上传文件是否为图片
        console.log(Orientation);
        if (Orientation == 6) {
            result = '<div class="showdiv"><img class="left" src="img/Arrow_left.svg" /><img class="center" src="img/delete.svg" /><img class="right" src="img/Arrow_right.svg" /></div><img style="transform:rotate(90deg) translate(-50%, 50%);height:100%" id="img' + currentReViewImgIndex + randomString(1) + randomString(2) + randomString(5) + '" class="showimg" src="' + imgMsg.base64 + '" />';
        } else {
            result = '<div class="showdiv"><img class="left" src="img/Arrow_left.svg" /><img class="center" src="img/delete.svg" /><img class="right" src="img/Arrow_right.svg" /></div><img id="img' + currentReViewImgIndex + randomString(1) + randomString(2) + randomString(5) + '" class="showimg" src="' + imgMsg.base64 + '" />';
        }
        li.innerHTML = result;
        showui.appendChild(li);
    } else if ($("#showui video").length == 0) {
        video = '<div id="video"><video id="img' + currentReViewImgIndex + randomString(1) + randomString(2) + randomString(5) + '" class="showimg" src="' + dataArr[0][0][0].base64 + '" ></video></div>';
        div.innerHTML = video;
        showui.prepend(div);
        accept = "video/*";
        $("#upgteimg").attr("accept", "image/*")
    }
    index++;
}

//				点击操作事件
function onclickimg() {
    var dataArrlist = dataArr[1].length;
    console.log(dataArrlist);
    console.log(dataArr[1]);
    var lilength = document.querySelectorAll('ul#showui li');
    for (var i = 0; i < lilength.length; i++) {
        lilength[i].getElementsByTagName('img')[0].onclick = function (num) {
            return function () {
                if (num == 0) {
                } else {
                    var list = 0;
                    for (var j = 0; j < dataArr[1].length; j++) {
                        list = j
                    }
                    var up = num - 1;
                    dataArr[1].splice(up, 0, dataArr[1][num]);
                    dataArr[1].splice(num + 1, 1);
                    var lists = $("ul#showui li").length;
                    for (var j = 0; j < lists; j++) {
                        var usid = $("ul#showui li")[j].getElementsByTagName('img')[3];
                        $("#" + usid.id + "").attr("src", dataArr[1][j].base64)
                    }
                }
            }
        }(i);
        //删除图标
        lilength[i].getElementsByTagName('img')[1].onclick = function (num) {
            return function () {
//						if(dataArr[1].length == 1) {
//							dataArr[1] = [];
////							$("ul#showui").html("")
//						} else {
                $("ul#showui li:eq(" + num + ")").remove();
                dataArr[1].splice(num, 1)
//						}

            }
        }(i);
        //右箭头图标
        lilength[i].getElementsByTagName('img')[2].onclick = function (num) {
            return function () {
                var list = 0;
                for (var j = 0; j < dataArr[1].length; j++) {
                    list = j
                }
                var datalist = list + 1;
                dataArr[1].splice(datalist, 0, dataArr[1][num]);
                dataArr[1].splice(num, 1);
                var lists = $("ul#showui li").length;
                for (var j = 0; j < lists; j++) {
                    var usid = $("ul#showui li")[j].getElementsByTagName('img')[3];
                    $("#" + usid.id + "").attr("src", dataArr[1][j].base64)
                }

            }
        }(i)

    }
}

showui.addEventListener("click", function () {
    onclickimg();
}, true);

function send(postStatus) {
    // if(pass == true) {
    if (!dataArr[0].length && !dataArr[1].length) {
        text = "请先上传图片";
        hint(text);
        return false;
    }
    // }

    pass_yz(postStatus); //通用验证方法

}


//}
//	



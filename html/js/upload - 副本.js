function addGesture(init) {
    var el = init.el;
    var isGesture = false;
    var startDis = 0;
    var startDeg = 0;
    el.addEventListener('touchstart', function (e) {
        if (e.touches.length >= 2) {
            isGesture = true;
            startDis = getDis(e.touches[0], e.touches[1]);
            startDeg = getDeg(e.touches[0], e.touches[1]);
            init.start && init.start.call(el, e);
        }
    });
    el.addEventListener('touchmove', function (e) {
        if (e.touches.length >= 2 && isGesture) {
            var nowDis = getDis(e.touches[0], e.touches[1]);
            var nowDeg = getDeg(e.touches[0], e.touches[1]);
            /*
                e.scale = 0;
                计算缩放值:
                    change时，两根手指之间的距离 和 start时 两根手指之间的距离的比值
            */
            e.scale = nowDis / startDis;
            e.rotation = startDeg - nowDeg;
            init.change && init.change.call(el, e);
        }
    });
    el.addEventListener('touchend', function (e) {
        if (isGesture) {
            isGesture = false;
            init.end && init.end.call(el, e);
        }
    });

    function getDis(point, point2) {
        var x = point2.pageX - point.pageX;
        var y = point2.pageY - point.pageY;
        return Math.sqrt(x * x + y * y);
    }

    function getDeg(point, point2) {
        var x = point2.pageX - point.pageX;
        var y = point2.pageY - point.pageY;
        return Math.atan2(x, y) * 180 / Math.PI;
    }
}

document.addEventListener('touchstart', function (e) {
    e.preventDefault();
});
(function () {
    var pageShow = document.querySelectorAll('.page');
    var page = document.querySelector('#modified');
    var file = document.querySelector('#file');
    var c = document.querySelector('#c');
    var cxt = c.getContext("2d");
    var saveBtn = document.querySelector('#saveBtn');
    var maxSize = 10 * 1024 * 1024;
    var label = document.querySelector('#label_1');
    var select = document.querySelector('#select');
    var selectW = css(select, "width");
    var selectH = css(select, "height");
    var img = document.querySelector('#resultImg');
    label.addEventListener('touchstart', function (e) {
        e.stopPropagation();
    });
    img.addEventListener('touchstart', function (e) {
        e.stopPropagation();
    });
    c.width = document.body.clientWidth;
    c.height = document.body.clientHeight - 60;
    c.fillStyle = "green";
    file.onchange = function (e) {
        var files = e.target.files[0];
        if (files.size > maxSize) {
            alert("对不起不能上传10M以上的文件");
        } else {
            var reader = new FileReader();
            reader.onload = function (e) {
                var img = new Image();
                img.onload = function () {
                    var width = img.width;
                    var height = img.height;
                    var scale = 1;
                    if (width > c.width || height > c.height) {
                        if (width > height) {
                            scale = c.width / width;
                        } else {
                            scale = c.height / height;
                        }
                    } else if (width < selectW || height < selectH) {
                        if (height > width) {
                            scale = selectW / width;
                        } else {
                            scale = selectH / height;
                        }
                    }
                    width *= scale;
                    height *= scale;
                    setImage(img, width, height);
                };
                EXIF.getData(files, function () {
                    EXIF.getAllTags(this);
                    Orientation = EXIF.getTag(this, 'Orientation');
                });
                img.src = e.target.result;
                // pageShow.className = "page";
                $(".page").show()
            };
            reader.readAsDataURL(files);
        }
    };

    function setImage(img, width, height) {
        var left = (c.width - width) / 2;
        var top = (c.height - height) / 2;
        var con = document.querySelector('.con');
        var rotate = 0;
        var scale = 1;
        var startScale = 1;
        var startWidth = width;
        var startHeight = height;
        var startLeft = left;
        var startTop = top;
        var isDrag = true;
        var isResize = true;
        var htmlClient = {
            w: con.clientWidth,
            h: con.clientHeight
        };
        cxt.drawImage(img, left, top, width, height);
        addGesture({
            el: con,
            start: function () {
                startWidth = width;
                startHeight = height;
                startLeft = left;
                startTop = top;
            },
            change: function (e) {
                isDrag = false;
                isResize = false;
                cxt.clearRect(0, 0, c.width, c.height);
                startWidth = width * e.scale;
                startHeight = height * e.scale;
                startLeft = left - (startWidth - width) / 2;
                startTop = top - (startHeight - height) / 2;
                cxt.drawImage(img, startLeft, startTop, startWidth, startHeight);
            },
            end: function () {
                width = startWidth;
                height = startHeight;
                left = startLeft;
                top = startTop;
            }
        });
        /* 拖拽 canvas */
        drag({
            el: c,
            start: function (e) {
                isDrag = true;
                startTop = top;
                startLeft = left;
            },
            move: function (e) {
                if (isDrag) {
                    top = startTop + e.pointDis.y;
                    left = startLeft + e.pointDis.x;
                    cxt.clearRect(0, 0, c.width, c.height);
                    cxt.drawImage(img, left, top, width, height);
                }
            }
        });

        /*拖拽选框*/
        var startSelect = {};
        var selectRect = select.getBoundingClientRect();
        css(select, "translateX", (htmlClient.w - selectRect.width) / 2);
        css(select, "translateY", (htmlClient.h - selectRect.height) / 2);
        drag({
            el: select,
            start: function (e) {
                if (e.target.tagName == "SPAN") {
                    return;
                }
                isDrag = true;
                isResize = false;
                startSelect.x = css(this, "translateX");
                startSelect.y = css(this, "translateY");
            },
            move: function (e) {
                if (isResize) {
                    return;
                }
                if (isDrag) {
                    var now = {
                        x: startSelect.x + e.pointDis.x,
                        y: startSelect.y + e.pointDis.y
                    };
                    var rect = this.getBoundingClientRect();
                    var maxX = (htmlClient.w - rect.width);
                    var maxY = (htmlClient.h - rect.height);
                    if (now.x > maxX) {
                        now.x = maxX;
                    } else if (now.x < 0) {
                        now.x = 0;
                    }
                    if (now.y > maxY) {
                        now.y = maxY;
                    } else if (now.y < 0) {
                        now.y = 0;
                    }
                    css(this, "translateX", now.x);
                    css(this, "translateY", now.y);
                }
            }
        });


        saveBtn.addEventListener('touchend', function (e) {
            var rect = select.getBoundingClientRect();
            var cReac = c.getBoundingClientRect();

            var imgData = cxt.getImageData(rect.left - cReac.left, rect.top - cReac.top, rect.width, rect.height);
            // page[2].className = "page";
            // $("#result").show();
            $(".page").hide();
            setTimeout(function () {
                var img = document.querySelector('#resultImg');
                cxt.clearRect(0, 0, c.width, c.height);
                c.width = rect.width;
                c.height = rect.height;
                cxt.putImageData(imgData, 0, 0);
                var src = c.toDataURL("image/png");
                var newImage = new Image();
                newImage.src = src;
                newImage.onload = function () {
                    /* img.style.width = newImage.width + "100%";
                    img.style.height = newImage.height + "px";
                    img.style.margin = "-"+newImage.height/2+"px 0 0 -"+newImage.width/2 + "px"; */
                    img.style.width = "100%";
                    img.style.height = "100%";
                    // img.style.margin = "-"+newImage.height/2+"px 0 0 -"+newImage.width/2 + "px";
                    img.src = src;
                };
                sumitImageFile(src)
            }, 500);
        });
    }
})();

function drag(init) {
    var startPoint = {};
    var el = init.el;
    var isDrag = true;
    el.addEventListener('touchstart', function (e) {
        if (e.touches.length < 2) {
            init.start && init.start.call(el, e);
            var touch = e.changedTouches[0];
            startPoint.x = Math.round(touch.pageX);
            startPoint.y = Math.round(touch.pageY);
            isDrag = true;
        } else {
            isDrag = false;
        }
    });
    el.addEventListener('touchmove', function (e) {
        if (!isDrag || e.touches.length >= 2) {
            isDrag = false;
            return;
        }
        var touch = e.changedTouches[0];
        var nowPoint = {
            x: Math.round(touch.pageX),
            y: Math.round(touch.pageY)
        };
        e.pointDis = {
            x: nowPoint.x - startPoint.x,
            y: nowPoint.y - startPoint.y
        };
        init.move && init.move.call(el, e);
        e.preventDefault();
    });
    el.addEventListener('touchend', function (e) {
        if (isDrag) {
            init.end && init.end.call(el, e);
        } else {
            isDrag = true;
        }

    });

}


//对图片旋转处理 added by lzk
function rotateImg(img, direction, canvas) {
    //alert(img);
    //最小与最大旋转方向，图片旋转4次后回到原方向
    var min_step = 0;
    var max_step = 3;
    //var img = document.getElementById(pid);
    if (img == null) return;
    //img的高度和宽度不能在img元素隐藏后获取，否则会出错
    var height = img.height;
    var width = img.width;
    //var step = img.getAttribute('step');
    var step = 2;
    if (step == null) {
        step = min_step;
    }
    if (direction == 'right') {
        step++;
        //旋转到原位置，即超过最大值
        step > max_step && (step = min_step);
    } else {
        step--;
        step < min_step && (step = max_step);
    }
    //img.setAttribute('step', step);
    /*var canvas = document.getElementById('pic_' + pid);
    if (canvas == null) {
        img.style.display = 'none';
        canvas = document.createElement('canvas');
        canvas.setAttribute('id', 'pic_' + pid);
        img.parentNode.appendChild(canvas);
    }  */
    //旋转角度以弧度值为参数
    var degree = step * 90 * Math.PI / 180;
    var ctx = canvas.getContext('2d');
    switch (step) {
        case 0:
            canvas.width = width;
            canvas.height = height;
            ctx.drawImage(img, 0, 0);
            break;
        case 1:
            canvas.width = height;
            canvas.height = width;
            ctx.rotate(degree);
            ctx.drawImage(img, 0, -height);
            break;
        case 2:
            canvas.width = width;
            canvas.height = height;
            ctx.rotate(degree);
            ctx.drawImage(img, -width, -height);
            break;
        case 3:
            canvas.width = height;
            canvas.height = width;
            ctx.rotate(degree);
            ctx.drawImage(img, -width, 0);
            break;
    }
}
			
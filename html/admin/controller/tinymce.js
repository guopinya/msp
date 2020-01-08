/**
 * 菜单显示异常修改tinymce/skins/ui/oxide/skin.min.css:96 .tox-silver-sink的z-index值
 * http://tinymce.ax-z.cn/   中文文档
 */
layui.define(['jquery'], function (exports) {
    let $ = layui.$;

    let modFile = layui.cache.modules['tinymce'];

    let modPath = modFile.substr(0, modFile.lastIndexOf('.'));

    let setter = layui.setter || {};

    let response = setter.response || {};

    let settings = {
        base_url: modPath,
        images_upload_url: '/api/admin/upload',// 图片上传接口
        language: 'zh_CN',
        response: {
            statusName: response.statusName || 'code', // 返回状态字段
            msgName: response.msgName || 'msg', // 返回消息字段
            dataName: response.dataName || 'data', // 返回的数据
            statusCode: response.statusCode || {
                ok: 200 // 数据正常
            }
        },
        success: function (res, succFun, failFun) {//上传完成回调
            if (res[this.response.statusName] === this.response.statusCode.ok) {
                //succFun('http://192.168.18.14/api/' + res.data);
                succFun('http://localhost/api/' + res.data);
            } else {
                failFun(res[this.response.msgName]);
            }
        }
    };

    let t = {};

    t.render = function (option) {

        let admin = layui.admin || {};

        option.base_url = option.base_url ? option.base_url : settings.base_url;

        option.language = option.language ? option.language : settings.language;

        option.selector = option.selector ? option.selector : option.elem;

        option.quickbars_selection_toolbar = option.quickbars_selection_toolbar ? option.quickbars_selection_toolbar : 'cut copy | bold italic underline strikethrough ';

        option.plugins = option.plugins ? option.plugins : 'quickbars print preview searchreplace autolink fullscreen image link media codesample table charmap hr advlist lists wordcount imagetools indent2em';

        option.toolbar = option.toolbar ? option.toolbar : 'undo redo | forecolor backcolor bold italic underline strikethrough | indent2em alignleft aligncenter alignright alignjustify outdent indent | link bullist numlist image table codesample | formatselect fontselect fontsizeselect';

        option.resize = false;

        option.elementpath = false;

        option.branding = false;

        option.contextmenu_never_use_native = true;

        option.menubar = option.menubar ? option.menubar : 'file edit insert format table';

        option.images_upload_handler = function (blobInfo, succFun, failFun) {
            let formData = new FormData();
            formData.append('file', blobInfo.blob());

            admin.req({
                url: '/api/admin/upload',
                type: 'post',
                data: formData,
                contentType: false,
                processData: false,
                success: function (res) {
                    //succFun('http://192.168.18.14/api' + res.data);
                    succFun('http://localhost/api' + res.data);
                },
                error: function (res) {
                    failFun("网络错误：" + res.status);
                }
            });
        };

        option.menu = option.menu ? option.menu : {
            file: {title: '文件', items: 'newdocument | print preview fullscreen | wordcount'},
            edit: {title: '编辑', items: 'undo redo | cut copy paste pastetext selectall | searchreplace'},
            format: {
                title: '格式',
                items: 'bold italic underline strikethrough superscript subscript | formats | forecolor backcolor | removeformat'
            },
            table: {title: '表格', items: 'inserttable tableprops deletetable | cell row column'},
        };

        // 获取插件
        $.ajax({
            url: option.base_url + '/tinymce.min.js',
            dataType: 'script',
            cache: true,
            async: false
        });

        option.suffix = '.min';
        option.toolbar_drawer = false;
        tinymce.init(option);

        t.tinymce = tinymce;

        return tinymce.activeEditor;
    };
    exports('tinymce', t);
});
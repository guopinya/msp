var where = new Array(31);

function comefrom(loca, locauCities) {
    this.loca = loca;
    this.locauCities = locauCities;
}

where[0] = new comefrom("省份", "城市");
where[1] = new comefrom("北京", "东城|西城|崇文|宣武|朝阳|丰台|石景山|海淀|门头沟|房山|通州|顺义|昌平|大兴|平谷|怀柔|密云|延庆");
where[2] = new comefrom("天津", "和平|东丽|河东|西青|河西|津南|南开|北辰|河北|武清|红挢|塘沽|汉沽|大港|宁河|静海|宝坻|蓟县");
where[3] = new comefrom("上海", "黄浦|卢湾|徐汇|长宁|静安|普陀|闸北|虹口|杨浦|闵行|宝山|嘉定|浦东|金山|松江|青浦|南汇|奉贤|崇明");
where[4] = new comefrom("重庆", "万州|涪陵|渝中|大渡口|江北|沙坪坝|九龙坡|南岸|北碚|万盛|双挢|渝北|巴南|黔江|长寿|綦江|潼南|铜梁|大足|荣昌|壁山|梁平|城口|丰都|垫江|武隆|忠县|开县|云阳|奉节|巫山|巫溪|石柱|秀山|酉阳|彭水|江津|合川|永川|南川");
where[5] = new comefrom("河北", "石家庄|张家口|承德|秦皇岛|唐山|廊坊|保定|衡水|沧州|邢台|邯郸");
where[6] = new comefrom("山西", "太原|朔州|大同|阳泉|长治|晋城|忻州|晋中|临汾|吕梁|运城");
where[7] = new comefrom("内蒙古", "呼和浩特|包头|乌海|赤峰|通辽|呼伦贝尔|鄂尔多斯|乌兰察布|巴彦淖尔|兴安盟|锡林郭勒盟|阿拉善盟");
where[8] = new comefrom("辽宁", "沈阳|朝阳|阜新|铁岭|抚顺|本溪|辽阳|鞍山|丹东|大连|营口|盘锦|锦州|葫芦岛");
where[9] = new comefrom("吉林", "长春|白城|松原|吉林|四平|辽源|通化|白山|延边州");
where[10] = new comefrom("和龙江", "哈尔滨|齐齐哈尔|七台河|黑河|大庆|鹤岗|伊春|佳木斯|双鸭山|鸡西|牡丹江|绥化|大兴安岭");
where[11] = new comefrom("江苏", "南京|徐州|连云港|宿迁|淮安|盐城|扬州|泰州|南通|镇江|常州|无锡|苏州");
where[12] = new comefrom("浙江", "杭州|湖州|嘉兴|舟山|宁波|绍兴|衢州|金华|台州|温州|丽水");
where[13] = new comefrom("安徽", "合肥|宿州|淮北|亳州|阜阳|蚌埠|淮南|滁州|马鞍山|芜湖|铜陵|安庆|黄山|六安|巢湖|池州|宣城");
where[14] = new comefrom("福建", "福州|南平|莆田|三明|泉州|厦门|漳州|龙岩|宁德");
where[15] = new comefrom("江西", "南昌|九江|景德镇|鹰潭|新余|萍乡|赣州|上饶|抚州|宜春|吉安");
where[16] = new comefrom("山东", "济南|青岛|聊城|德州|东营|淄博|潍坊|烟台|威海|日照|临沂|枣庄|济宁|泰安|莱芜|滨州|菏泽");
where[17] = new comefrom("河南", "郑州|开封|三门峡|洛阳|焦作|新乡|鹤壁|安阳|濮阳|商丘|许昌|漯河|平顶山|南阳|信阳|周口|驻马店|济源");
where[18] = new comefrom("湖北", "武汉|十堰|襄阳|荆门|孝感|黄冈|鄂州|黄石|咸宁|荆州|宜昌|随州|仙桃|潜江|天门|神农架林区|恩施州");
where[19] = new comefrom("湖南", "长沙|张家界|常德|益阳|岳阳|株洲|湘潭|衡阳|郴州|永州|邵阳|怀化|娄底|湘西州");
where[20] = new comefrom("广东", "广州|深圳|清远|韶关|河源|梅州|潮州|汕头|揭阳|汕尾|惠州|东莞|珠海|中山|江门|佛山|肇庆|云浮|阳江|茂名|湛江");
where[21] = new comefrom("广西", "南宁|桂林|柳州|梧州|贵港|玉林|钦州|北海|防城港|崇左|百色|河池|来宾|贺州");
where[22] = new comefrom("海南", "海口|三亚|文昌|琼海|万宁|五指山|东方|儋州|三沙");
where[23] = new comefrom("四川", "成都|广元|绵阳|德阳|南充|广安|遂宁|内江|乐山|自贡|泸州|宜宾|攀枝花|巴中|达州|资阳|眉山|雅安|阿坝州|甘孜州|凉山州");
where[24] = new comefrom("贵州", "贵阳|六盘水|遵义|安顺|毕节|铜仁|黔东南州|黔南州|黔西南州");
where[25] = new comefrom("云南", "昆明|曲靖|玉溪|保山|昭通|丽江|思茅|临沧|德宏州|怒江州|迪庆州|大理州|楚雄州|红河州|文山州|西双版纳");
where[26] = new comefrom("西藏", "拉萨|那曲|昌都|林芝|山南|日喀则|阿里");
where[27] = new comefrom("陕西", "西安|延安|铜川|渭南|咸阳|宝鸡|汉中|榆林|安康|商洛");
where[28] = new comefrom("甘肃", "兰州|嘉峪关|白银|天水|武威|酒泉|张掖|庆阳|平凉|定西|陇南|临夏州|甘南州");
where[29] = new comefrom("青海", "西宁|海东|海北州|海南州|黄南州|果洛州|玉树州|海西州");
where[30] = new comefrom("宁夏", "银川|石嘴山|吴忠|固原|中卫");
where[31] = new comefrom("新疆", "乌鲁木齐|克拉玛依|喀什|阿克苏|和田|吐鲁番|哈密|克孜勒苏柯州|博尔塔拉州|昌吉州|巴音郭楞州|伊犁州|塔城|阿勒泰");
where[32] = new comefrom("香港", "香港");
where[33] = new comefrom("澳门", "澳门");
where[34] = new comefrom("台湾", "台北|高雄|台中|花莲|基隆|嘉义|金门|连江|苗栗|南投|澎湖|屏东|台东|台南|桃园|新竹|宜兰|云林|彰化");

var uProvinces_obj;
var uCities_obj;

function init(uProvinces, uCities) {
    uProvinces_obj = document.getElementById(uProvinces);
    uCities_obj = document.getElementById(uCities);

    with (uProvinces_obj) {
        length = where.length;
        for (k = 0; k < where.length; k++) {
            options[k].text = where[k].loca;
            options[k].value = where[k].loca;
        }

        options[0].selected = true;
    }
    with (uCities_obj) {
        loca3 = (where[0].locauCities).split("|");
        length = loca3.length;
        for (l = 0; l < length; l++) {
            options[l].text = loca3[l];
            options[l].value = loca3[l];
        }

        options[0].selected = true;
    }

}

function select() {
    with (uProvinces_obj) {
        var loca2 = options[selectedIndex].value;
        uProvincesID = selectedIndex;
    }
    for (i = 0; i < where.length; i++) {
        if (where[i].loca == loca2) {
            var loca3 = (where[i].locauCities).split("|");
            var loca4 = "";
            if (loca3 == "") {
                break;
            } else {
            }

            for (j = 0; j < loca3.length; j++) {
                if (loca3[j].replace(/(^\s*)|(\s*$)/g, "") != "") {
                    with (uCities_obj) {
                        length = loca3.length;
                        options[j].text = loca3[j];
                        options[j].value = loca3[j];
                        loca4 = options[selectedIndex].value;
                        uCitiesID = selectedIndex;
                    }
                }
            }
            break;
        }
    }

}
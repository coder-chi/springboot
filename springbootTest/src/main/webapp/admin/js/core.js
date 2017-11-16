// var KT_DOMAIN = "http://123.59.57.102:60334";
var KT_DOMAIN = "";

function generateURL(path) {
    return KT_DOMAIN + path;
}

function getLoginCookie() {
    var requestHeader = {
        "managerId": $.cookie('managerId'),
        "ticket": $.cookie('ticket')
    };
    return requestHeader;
}

function doConfirmDialog(options) {
    var _class, _class2;
    var _text = "";
    if (options.flag == 'success') {
        _class = "glyphicon-green";
        _class2 = "";
    } else if (options.flag == 'error') {
        _class = "glyphicon-red";
        _class2 = "";
    }
    if (options.text) _text = options.text;
    var _buttons = {};
    if (options.buttons) {
        if (options.buttons.ok) {
            _buttons.ok = {text: '确定', btnClass: 'btn btn-primary'};
            if (options.buttons.ok.action) {
                var _func = options.buttons.ok.action;
                _buttons.ok.action = eval(_func);
            }
        }
        if (options.buttons.cancel) {
            _buttons.cancel = {text: '取消', btnClass: 'btn btn-primary btn-default'};
            if (options.buttons.cancel.action) {
                var _func = options.buttons.cancel.action;
                _buttons.cancel.action = eval(_func);
            }
        }
    }
    $.confirm({
        title: false,
        content: '<div class="' + _class + ' text-align-center font16"><div class="glyphicon ' + _class2 + '">' + _text + '</div></div>',
        buttons: _buttons
    });
}

function doAlertDialog(options) {
    var _text = "";
    var _class, _class2;
    if (options.text) _text = options.text;
    if (options.flag == 'success') {
        _class = "glyphicon-green";
        _class2 = "";
    } else if (options.flag == 'error') {
        _class = "glyphicon-red";
        _class2 = "";
    }
    $.alert({
        title: false,
        content: '<div class="' + _class + ' text-align-center font16"><div class="glyphicon ' + _class2 + '">' + _text + '</div></div>',
        buttons: {
            ok: {
                "text": "确定",
                "btnClass": 'btn btn-primary'
            }
        }
    });
}

function getScrollTop() {
    var scrollTop = 0, bodyScrollTop = 0, documentScrollTop = 0;
    if (document.body) {
        bodyScrollTop = document.body.scrollTop;
    }
    if (document.documentElement) {
        documentScrollTop = document.documentElement.scrollTop;
    }
    scrollTop = (bodyScrollTop - documentScrollTop > 0) ? bodyScrollTop : documentScrollTop;
    return scrollTop;
}

function getScrollHeight() {
    var scrollHeight = 0, bodyScrollHeight = 0, documentScrollHeight = 0;
    if (document.body) {
        bodyScrollHeight = document.body.scrollHeight;
    }
    if (document.documentElement) {
        documentScrollHeight = document.documentElement.scrollHeight;
    }
    scrollHeight = (bodyScrollHeight - documentScrollHeight > 0) ? bodyScrollHeight : documentScrollHeight;
    return scrollHeight;
}

function getWindowHeight() {
    var windowHeight = 0;
    if (!window.top) {
        if (document.compatMode == "CSS1Compat") {
            windowHeight = document.documentElement.clientHeight;
        } else {
            windowHeight = document.body.clientHeight;
        }
    } else {
        windowHeight = $(parent.document).height() - $(parent.document).find("#header").height() - $(parent.document).find(".content_tab").height();
    }
    return windowHeight;
}

var areaList = {};

function formatArea(list) {
    if (list.length > 0) {
        for (var i in list) {
            if (list[i].level == 0) {
                areaList[list[i].provinceCode] = {"code": list[i].districtId, "name": list[i].name, "list": []};
            } else if (list[i].level == 1) {
                if (areaList[list[i].provinceCode]) {
                    areaList[list[i].provinceCode]['list'].push({"code": list[i].districtId, "name": list[i].name});
                } else {
                    areaList[list[i].provinceCode] = {"code": list[i].districtId, "name": list[i].name, "list": []};
                    areaList[list[i].provinceCode]['list'].push({"code": list[i].districtId, "name": list[i].name});
                }
            }
        }
        for (var j in areaList) {
            $("select[name='province']").append('<option value="' + areaList[j].code + '" data-code="' + j + '">' + areaList[j].name + '</option>');
        }
    }
}

function getPrivince() {
    $.ajax({
        url: generateURL('/a/district/list'),
        type: 'GET',
        //data:{"r":JSON.stringify({"nameLike":"北京"})},
        headers: getLoginCookie(),
        datatype: 'json',
        success: function (data) {
            console.log(data);
            if (data.code == 200) {
                formatArea(data.obj);
            }
        }
    });
}


// 获取文章列表
function getArticleList(obj) {
    $.ajax({
        url: generateURL('/p/article/list'),
        type: 'get',
        datatype: 'json',
        success: function (data) {
            if (data.code == 200) {
                var _list = data.obj;
                var _html = "<option value>选择H5页面</option>";
                if (_list.length > 0) {
                    for (var i in _list) {
                        _html += '<option value="' + encodeURI(_list[i].articleUrl) + '">' + _list[i].title + '</option>';
                    }
                }
                obj.html(_html);
            }
        }
    });
}

//when select province
$(document).on("change", "select[name='province']", function () {
    var obj = $(this).find("option:selected");
    var _code = obj.data('code');
    var _list = areaList[_code].list;
    var _options = "<option value=''>城市</option>";
    if (_list.length > 0) {
        for (var i in _list) {
            _options += '<option value="' + _list[i].code + '">' + _list[i].name + '</option>';
        }
    }
    var that = $($(this).data("target"));
    that.html(_options);
});

/* 获取经代公司 */
function getAgentList(obj) {
    $.ajax({
        url: generateURL('/a/agent/list'),
        type: 'GET',
        //data:{"r":JSON.stringify({"nameLike":"北京"})},
        headers: getLoginCookie(),
        datatype: 'json',
        success: function (data) {
            if (data.code == 200) {
                var _list = data.obj;
                var _html = "<option value>选择经代公司</option>";
                if (_list.length > 0) {
                    for (var i in _list) {
                        _html += '<option value="' + _list[i].agentId + '">' + _list[i].name + '</option>';
                    }
                }
                obj.html(_html);
            }
        }
    });
}

/* 获取保险公司 */
function getInsurerList(obj) {
    $.ajax({
        url: generateURL('/a/insurer/list'),
        type: 'GET',
        //data:{"r":JSON.stringify({"nameLike":"北京"})},
        headers: getLoginCookie(),
        datatype: 'json',
        success: function (data) {
            if (data.code == 200) {
                var _list = data.obj;
                var _html = "<option value>选择保险公司</option>";
                if (_list.length > 0) {
                    for (var i in _list) {
                        _html += '<option value="' + _list[i].insurerId + '">' + _list[i].name + '</option>';
                    }
                }
                obj.html(_html);
            }
        }
    });
}


//
function checkMobile(str) {
    var re = /^1[3|5|7|8]\d{9}$/;
    if (re.test(str)) {
        return true
    }
    return false;
}

function checkEmail(str) {
    var re = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/
    if (re.test(str)) {
        return true;
    }
    return false;
}
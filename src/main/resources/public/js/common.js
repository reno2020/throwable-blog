/**
 * 请求
 */
function request(method, url, params, callback, async) {
    $.ajax({
        type: method || "GET",
        url: url,
        cache: false,
        data: params,
        async: async || "false",
        success: function (response) {
            if(response && response.data){
                callback(response.data);
            }
        },
        error: function (response) {
            console.log(response);
        }
    })
}


function callbackJson(method, url, params, callback, async) {
    $.ajax({
        type: method || "GET",
        url: url,
        cache: false,
        data: params,
        async: async || "false",
        dataType: 'json',
        success: function (response) {
            if(response && response.data){
                callback(response.data);
            }
        },
        error: function (response) {
            console.log(response);
        }
    })
}

function getCurrentYear() {
    return new Date().getFullYear();
}

/**
 * 加载copyRight
 */
$(function () {
    var copyRight = '&copy; 2016-' + getCurrentYear() + ' Throwable. All Rights Reserved.Contact me:739805340@qq.com';
    $('#copyRight').html(copyRight);
});


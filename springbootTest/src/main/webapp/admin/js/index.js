function article(){
    $.ajax({
            url: '/p/user/article',
            type: 'POST',
            data:"{}",
            dataType:"JSON",
            // headers:getLoginCookie(),
            success: function () {
                alert("success");
            },
            error: function () {
                alert("error");
            }
        }
    )
}
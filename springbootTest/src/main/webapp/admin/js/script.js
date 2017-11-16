$(function(){
	
	// Checking for CSS 3D transformation support
	$.support.css3d = supportsCSS3D();
	
	var formContainer = $('#formContainer');
	
	// Listening for clicks on the ribbon links
	$('.flipLink').click(function(e){
		
		// Flipping the forms
		formContainer.toggleClass('flipped');
		
		// If there is no CSS3 3D support, simply
		// hide the login form (exposing the recover one)
		if(!$.support.css3d){
			$('#login').toggle();
		}
		e.preventDefault();
	});
	
	formContainer.find('form').submit(function(e){
		// Preventing form submissions. If you implement
		// a backend, you might want to remove this code
		e.preventDefault();
	});
	
	
	// A helper function that checks for the 
	// support of the 3D CSS3 transformations.
	function supportsCSS3D() {
		var props = [
			'perspectiveProperty', 'WebkitPerspective', 'MozPerspective'
		], testDom = document.createElement('a');
		  
		for(var i=0; i<props.length; i++){
			if(props[i] in testDom.style){
				return true;
			}
		}
		
		return false;
	}
});
function login_bt(){
    var name = $('#userName').val();
    var password = $('#password').val();
    login(name,password);
}
function login(u,p){
    $.ajax({
            url: '/p/user/login',
            type: 'POST',
            data: {"r": JSON.stringify({"userName": u, "password": p})},
            dataType: 'JSON',
            success: function (data) {
                if (data.code == 200) {
                    window.location = '/admin/index.html';
                    // save managerId and ticket, and get the role id
                    var test = data.obj;
                    console.log(test);
                    $.cookie('managerId', data.obj.managerId);
                    $.cookie('ticket', data.obj.ticket);
                    $.cookie('roleId', data.obj.roleId);
                    $.cookie('managerName', data.obj.name);

                } else {
                	alert("账号密码有误");
                    $('#username').focus();
                    $('#password-help').text(data.msg);
                }
            },
            error: function (data) {
                $('#username').focus();
                // $('#password-help').text('系统错误，请稍后尝试！')
            }
        }
    )
}
$(function(){
	//重置所有input
	$(':input').attr('autocomplete','off');
	
	//添加管理员
	var add_manager_name_obj = $('#add_manager_name');
	var add_manager_nick_obj = $('#add_manager_nick');
	var add_manager_pass1_obj = $('#add_manager_pass1');
	var add_manager_pass2_obj = $('#add_manager_pass2');
	$('#add_manager_submit').click(function(){
		var name = add_manager_name_obj.val();
		if(!name){
			add_red(add_manager_name_obj);
			alert('请填写登录名！');
			return false;
		}
		var nick = add_manager_nick_obj.val();
		if(!nick){
			add_red(add_manager_nick_obj);
			alert('请填写昵称！');
			return false;
		}
		var pass1 = add_manager_pass1_obj.val();
		if(!pass1){
			add_red(add_manager_pass1_obj);
			alert('请填写密码！');
			return false;
		}
		var pass2 = add_manager_pass2_obj.val();
		if(!pass2){
			add_red(add_manager_pass2_obj);
			alert('请再一次填写密码！');
			return false;
		}
		if(pass1 != pass2){
			add_red(add_manager_pass1_obj);
			add_red(add_manager_pass2_obj);
			alert('两次密码不一致！');
			return false;
		}
		if($('.rank .checkbox:checked').length<=0){
			alert('请选择权限！');
			return false;
		}
	});
	add_manager_name_obj.click(function(){
		remove_red(this);
	});
	add_manager_name_obj.blur(function(){
		remove_red(this);
	});
	add_manager_nick_obj.click(function(){
		remove_red(this);
	});
	add_manager_nick_obj.blur(function(){
		remove_red(this);
	});
	add_manager_pass1_obj.click(function(){
		remove_red(this);
	});
	add_manager_pass1_obj.blur(function(){
		remove_red(this);
	});
	add_manager_pass2_obj.click(function(){
		remove_red(this);
	});
	add_manager_pass2_obj.blur(function(){
		remove_red(this);
	});
})


/**
 * 给元素加红色框
 * @param e html元素(id or class)
 */
function add_red(e){
	$(e).addClass('red');
}

/**
 * 撤销所有加红色框的元素
 * @param e html元素(id or class)
 */
function remove_red(e){
	$(e).removeClass('red');
}
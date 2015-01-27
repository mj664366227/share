/*
支付密码
*/

//加载安全控件

var pass1 = {id:"originalPass",state:1,test:[["#==''","支付密码不能为空"]]};//原支付密码
var pass2 = {id:"newPayPass",state:1,info:"由6-20个英文字母、数字或符号组成，建议使用大小写字母与数字混合设置密码。",test:[["#==''","支付密码不能为空"],["#.length>20||#.length<6","支付密码要6-20位字符"],["passwordUtil.isSimplePassword(#) || passwordUtil.isAllSameChar(#)","支付密码过于简单"],["numRep.test(#)","支付密码不能为纯数字"]]};//新支付密码
var pass3 = {id:"rePayPass",state:1,info:"重新输入支付密码。",test:[["#==''","确认支付密码不能为空"]]};

//给控件加md5
function md5Pass(){
	if($("originalPass")){//原支付密码
		$("originalPassHidden").value = hex_md5($("originalPass").value).toLowerCase();
	}
	if($("newPayPass")){//新支付密码
		$("newPayPassHidden").value = hex_md5($("newPayPass").value).toLowerCase();
	}
	if($("rePayPass")){//确认新支付密码
		$("rePayPassHidden").value = hex_md5($("rePayPass").value).toLowerCase();
	}	
	if($("otppass")){//将军令
		$("otppass").value = $("OTPCtl").value;
	}	
};

//不允许使用的简单密码
var passwordUtil = {
simplePassword:['123456',
'123456789',
'12345678',
'123123',
'5201314',
'1234567',
'7758521',
'654321',
'1314520',
'123321',
'1234567890',
'147258369',
'123654',
'5211314',
'woaini',
'1230123',
'987654321',
'147258',
'123123123',
'7758258',
'520520',
'789456',
'456789',
'159357',
'112233',
'1314521',
'456123',
'110110',
'521521',
'zxcvbnm',
'789456123',
'0123456789',
'0123456',
'123465',
'159753',
'qwertyuiop',
'987654',
'115415',
'1234560',
'123000',
'123789',
'100200',
'963852741',
'121212',
'111222',
'123654789',
'12301230',
'456456',
'741852963',
'asdasd',
'asdfghjkl',
'369258',
'863786',
'258369',
'8718693',
'666888',
'5845201314',
'741852',
'168168',
'iloveyou',
'852963',
'4655321',
'102030',
'147852369',
'321321'],
	isSimplePassword:function(password){
		var isSimplePassword = false;
		for(var i = 0,size = this.simplePassword.length; i < size; i++ ){
			if(this.simplePassword[i] == password){
				isSimplePassword = true;
				break;
			}
		}
		return isSimplePassword;
	},
	isAllSameChar:function(str){
		var c = str.charAt(0);
		var isAllSame = true;
		for(var i = 1,size = str.length; i < size; ++i){
			if(c != str.charAt(i)){
				isAllSame = false;
				break;
			}
		}
		return isAllSame;
	}
};
var passRegs = [/\d/,/[a-z]/,/[A-Z]/,/\W/];
var numRep = /^\d+$/;//纯数字

//检测新支付密码强度
if($("newPayPass")){		
	ev.addEvent($("newPayPass"),"blur",getGrade);				
};

//得到密码等级
function getGrade(){	
	var v = $("newPayPass").value;	
	var len = v.length;
	if(v == "" || len < 6 || len > 20){
		$("passSafe").style.display = "none";
		return;	
	}
	if(passwordUtil.isSimplePassword(v) || passwordUtil.isAllSameChar(v)|| numRep.test(v)){$("passSafe").style.display = "none";return;}
	var s = "弱" ,color = "red";
	var n = 0;
	$("passSafe").style.display = "block";
	
	for(var i = 0; i < passRegs.length ; i ++){			
		if(passRegs[i].test(this.value)){n ++}
	}			
	if(n >= 1 && len >= 6){s = "中";color = "blue"}
	if(n > 2 && len > 10){s = "强";color = "green"}	
	$("passSafe").innerHTML = "密码强度：<strong style='color:"+color+"'>" + s + "</strong>";	
};
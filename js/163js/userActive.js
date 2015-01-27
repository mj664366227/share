/*
用户激活
2012-4-26
*/


function checkDate(s){
	var year = Number(s.substring(0,4));
	var month = Number(s.substring(4,6));
	var day = Number(s.substring(6,8));		
	 var day_num = new Date(year,month,0).getDate();	
	if(day > day_num){return false}
	return true;
};

/*--------激活  开始-------*/

var UserActive = {};

UserActive.init = function(){
	this.bindCheck();
	this.setSafeQue();
};


// 支付密码
if(epay.$("safeCtrlBox")){
	epay.safeCtrl({
	  box:epay.$("safeCtrlBox"),
	  hiddenInputName:"payPassword"	  
 });
};
if(epay.$("OTPCtl2")){
	epay.$("OTPCtl2").className = "formInput"
};

$("activeBtn").disabled = "";
var ele=[];
//绑定需要验证的元素
UserActive.bindCheck = function(){	
	//信息提示
	var info = {
		c1:"请填写身份证上真实信息，设置完成后不可随意修改。",
		c2:"网易宝绝不会泄露您的证件号码，请放心填写。",
		c3:"由6-20位字符组成，试试大小写字母数字符号混搭。",
		c4:"重新输入支付密码。",
		c5:"",
		c6:"",
		c7:"",
		c8:"请填写对应坐标的密码，<font color='red'>注意：密码间不能有空格</span>",
		c9:'请填入您的<a href="http://ekey.163.com" target="_blank" title="作为当前市场上安全级别最高的防盗措施，确保您账户资金的安全。">将军令</a>动态密码。',		
		c10:"请输入您已设置的支付密码。若遗忘可<a href='https://mima.163.com/nie/ts_epay_index.aspx' target='_blank'>申请修复账号</a>。"
	};
	
	var c1 = {id:"trueName",state:1,info:info.c1,test:[["#==''","真实姓名不能为空"],["!checkReg.TrueName.test(#)","真实姓名格式不正确"]]};
	var c2 = {id:"identityNo",state:1,info:info.c2,test:[["#==''","证件号码不能为空"],["!isIDno($('identityNo'))","证件号码格式不正确"]]};
	var c3 = {id:"newPayPass",state:1,info:info.c3,test:[["#==''","支付密码不能为空"],["#.length>20||#.length<6","支付密码要6-20位字符"],["passwordUtil.isSimplePassword(#) || passwordUtil.isAllSameChar(#)","支付密码过于简单"],["numRep.test(#)","支付密码不能为纯数字"]]};//新支付密码
var c4 = {id:"rePayPass",state:1,info:info.c4,test:[["#==''","确认支付密码不能为空"],["#!=$('newPayPass').value","确认支付密码与新支付密码不一致"]]};
	var c5 = {id:"que_0",state:1,info:info.c5,test:[["#==''","问题答案不能为空"]]};
	var c6 = {id:"que_1",state:1,info:info.c6,test:[["#==''","问题答案不能为空"]]};
	var c7 = {id:"que_2",state:1,info:info.c7,test:[["#==''","问题答案不能为空"]]};
	var c8 = {id:"mbk",state:1,info:info.c8,test:[["#==''","密保卡认证码不能为空"]]};
	var c9 = {id:"OTPCtl",state:1,info:info.c9,test:[["#==''","将军令动态密码不能为空"]]};
	var c10 = {id:"OTPCtl2",state:1,info:info.c10,test:[["#==''","支付密码不能为空"]]};
	
	ele = [c1,c2,c3,c4,c5,c6,c7,c8,c9,c10];
	var ck = new CheckForm($("activeForm"),ele);	
	ck.addCase = function(){		
		if(ck.form.isSubmit){			
			$("activeBtnBox").className = "btn1Box btn1Disbled";
			$("activeBtn").disabled = "disabled";
			$("loadingIcon").style.display = "";
			if($("newPayPass")){//新支付密码
				$("newPayPass").value = hex_md5($("newPayPass").value).toLowerCase();
			}
			if($("rePayPass")){//确认新支付密码
				$("rePayPass").value = hex_md5($("rePayPass").value).toLowerCase();
			}	
			if($("OTPCtl2")){//原支付密码
				$("payPassword").value = hex_md5($("OTPCtl2").value).toLowerCase();
			}	
			if($("otppass")){//将军令
				$("otppass").value = $("OTPCtl").value;
			}	
			//不经过ajax校验直接提交了
			ck.form.submit();			
		}
		
		this.form.isSubmit = false;			
	};
	//AJAX验证错误信息处理
	function backCheck(e){	
		for(var i in e){
			if($(i+"_err")){
				$(i).style.border = "1px solid #ff0000";
				var err = $(i +"_err");			
				err.className = "err";						
				err.innerHTML = e[i];	
				err.style.display='';
			};				
		};	
	};
};
//安全问题选择
UserActive.setSafeQue = function(){		
	var checkQueDate = [["#==''","问题答案不能为空"],["!checkReg.MDate.test(#)","日期格式不正确"],["!checkDate(#)","日期格式不正确"]];
	var checkQue = [["#==''","问题答案不能为空"],["#.length>32||#.length<2","长度应为2-32个字符"],["!checkReg.Code.test(#)","答案格式不正确"]];
	var nb = this;	
	//初始化机选问题
	this.safeQue = ["你的生日","你的出生地","你父亲的名字","你母亲的名字","你的小学校名","你的中学校名","你最好的朋友的名字","你最喜欢的职业","你喜欢的运动","你最喜欢的人的名字"];//所有问题列表
	this.safeQueInfo = ["你的生日,格式为YYYYMMDD;如：19800615","你的出生地","你父亲的名字","你母亲的名字","你的小学校名","你的中学校名","你最好的朋友的名字","你最喜欢的职业","你喜欢的运动","你最喜欢的人的名字"];//所有问题的信息提示
	this.safeQueReg = [checkQueDate,checkQue,checkQue,checkQue,checkQue,checkQue,checkQue,checkQue,checkQue,checkQue];//所有问题的验证规则
	var len = this.safeQue.length;
	this.selQue = [];//当前已选择的
	this.disSelQue = cArr(0,len - 1);//末选择的	
	this.disSelQue.aSort(2);
	
	//返回的问题
	if($("que_0_hidden").value != ""){
		var backQue = [];
		for(var i = 0; i < 3 ; i ++){
			var v = $("que_"+i+"_hidden").value;
			var k = this.safeQue.indexof(v);
			if(k || k==0){backQue.push(this.safeQue.indexof(v));}			
		};
		if(backQue.length == 3){this.disSelQue = backQue;}		
	};	
	
	for(var i = 0; i < 3 ; i ++){
		var n = this.disSelQue[i];
		$("safeQus_" + i).innerHTML = this.safeQue[n];	
		$("que_" + i + "_hidden").value = this.safeQue[n];
		$("que_" + i).info = "请输入：" + this.safeQueInfo[n];
		$("que_" + i).test = this.safeQueReg[n];
		this.safeQueReg
		this.selQue.push(this.disSelQue[i]);
		
		//绑定切换问题
		var btn = $("chSafeQus_" + i);
		btn.index = i; //索引
		btn.q = this.disSelQue[i];//问题索引
		
		btn.onclick = function(){
			var n = this.index;
			var k = Math.floor(Math.random() * (len-3));//机选索引号
			var newQue = nb.disSelQue[k]//换的新问题			
			getSelQue(this.q,newQue);			
			getAbleQue();			
			this.q = newQue;			
			$("safeQus_" + n).innerHTML = nb.safeQue[newQue];
			$("que_" + n + "_hidden").value = nb.safeQue[newQue];
			$("que_" + n).info = "请输入：" + nb.safeQueInfo[newQue];
			$("que_" + n).test = nb.safeQueReg[newQue];
			$("que_" + n).value = "";
		};
	};	 
	 
	getAbleQue();

	//得到还可以选择的问题
	function getAbleQue(){
		var bb = [];
		for(var i = 0; i < len ; i ++){		
			var k = indexof(nb.selQue,i);			
			if(k == "mm" ){	
				bb.push(i);
			}
		};	
		nb.disSelQue = bb;	
	};
	
	//得到已选择的问题
	function getSelQue(a,b){
		for(var i = 0; i < 3 ; i ++){	
			if(nb.selQue[i] == a){nb.selQue[i] = b}	
		}
	};
	
	//后成数字数组
	function cArr(n,n1){
		var arr = [];
		for(var i = n; i < n1 + 1 ; i ++){arr.push(i);}
		return arr;
	};
	
	function indexof(o,str){	
		for(var q = 0;q < o.length;q++){
			if(o[q] == str){return q;}
		}
		return "mm";
	};
};

UserActive.init();

/*--------激活  结束-------*/


/*--------生僻字库  开始-------*/
var unname = {};
unname.words = {a:"奡靉叆",c:"旵玚棽琤翀珵楮偲赪瑒篪珹捵茝鷐铖宬査嶒",b:"仌昺竝霦犇愊贲琲礴埗別骉錶",d:"耑昳菂頔遆珰龘俤叇槙璗惇",g:"玍冮芶姏堽粿筦嘏釭",f:"仹汎沨昉璠雰峯洑茀渢棻棻頫",e:"峩",h:"郃浛訸嗃瓛翃隺鋐滈翚翯竑姮葓皜袆淏皞翙銲鉷澒澔閤婳黃峘鸻鈜褘锽谹嫮",k:"凱堃蒯鹍崑焜姱衎鵾愷鎧",j:"冏泂劼莙濬暕珒椈珺璟競煚傑玦鑑瑨瑨琎勣寯烱浕斚倢瑴畯雋傢峤",m:"劢忞旻旼濛嫚媺铓鋩洺媌媔祃牻慜霂楙媄瑂",l:"玏呂俍冧倞琍綝壘孋瓅璘粦琍麗樑秝鍊崚链镠皊箖菻竻鸰琭瓈騄浬瑠嶺稜欐昽",n:"婻寗嫟秾迺柟薿枏",q:"玘佺耹踆骎啟蒨慬勍嵚婍璆碏焌駸綪锜荍釥嶔啓",p:"芃玭玶罴毰珮蘋慿弸掽逄砯",s:"屾昇妽珅姼甡湦骦塽挻甦鉥燊遂陞莦湜奭佀聖骕琡",r:"汭瑈瑢讱镕婼叡蒻羢瀼",t:"沺凃禔慆弢颋譚曈榃湉珽瑱橦镋渟黇頲畑媞鰧",w:"卍彣炆溦娬韡暐偉湋妏硙珷娒",y:"乂冘弌贠伝伃杙沄旸玙玥垚訚堯溁嫈澐颺熤儀赟祎瑀湧燚嬿鋆嫄愔贇彧崟韻龑颙晹媖顒禕羕炀弇湲霙嫕浥飏峣曣億雲愔洢暘钖垟詠燿鹓歈貟瑩燏暎畇娫矞祐溳崯颍煬靷谳異軏繄",x:"仚旴忺炘昍烜爔斅豨勲敩虓鈃禤燮瑄晞賢翾譞諕璿琇晛焮珣晅郤禼皛哓肸谞迿咲婞昫缐姁猇欻箮翛暁",z:"烝梽喆禛誌曌衠淽枬詟炤昝珘赒"};

unname.init = function(){
	var creatTlink = false;
	$("unname_box").onclick = function(){
		var evt = ev.getEvent();
		ev.formatEvent(evt);
		evt.eStopBubble();
	};
	$("unname_btn").onclick = function(){	
		var evt = ev.getEvent();
		ev.formatEvent(evt);
		evt.eStopBubble();
		$("unname_box").style.display = "";		
		if(!creatTlink){
			var w = ["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"];
			var s = "";
			for(var i = 0 ; i < w.length; i++){	
				var wo = w[i];
				if(w[i] == "c"){wo = "c[ch]"}	
				if(w[i] == "s"){wo = "s[sh]"}
				if(w[i] == "z"){wo = "z[zh]"}	
				s += '<a title="' + w[i] + '" href="#">' + wo + '</a>';			
			}
			$("unname_title").innerHTML = s;
			var titles = $("unname_title").getElementsByTagName("a");
			for(var i = 0 ; i < titles.length; i++){
				titles[i].onmouseover = function(){
					if(eval("unname.words." + this.title)){
						var str =  eval("unname.words." + this.title).split("");					
						var links = "";
						for(var j = 0 ; j < str.length; j++){
							links += '<a href="javascript:void(0)">' + str[j] + '</a>';
						}		
						$("unname_con").innerHTML = links;	
						
						var l = $("unname_con").getElementsByTagName("a");
						for(var j = 0 ; j < l.length; j++){
							l[j].onclick = function(){
								$("trueName").value = $("trueName").value + this.innerHTML;
								$("unname_box").style.display = "none";
							
							};
						}
					}
					else{$("unname_con").innerHTML = ""}
				}
			}
			titles[0].onmouseover();
			creatTlink = true;
		}
	};
	
	ev.addEvent(document,"click",function(){$("unname_box").style.display = "none";});	
};
if($("unname_box")){unname.init();}

/*--------生僻字库  结束-------*/

function focusInput(){
	var form = document.forms[0];	
	var e  = form.getElementsByTagName("*");	
	for(var i = 0 ;  i < e.length ; i ++){
		var tag = e[i].tagName.toLowerCase();		
		if(tag == "object" || tag == "input"){	
			if(!isHidden(e[i])){	
				if(tag == "object"){e[i].focus();break;}
				if(tag == "input"){				
					var t = e[i].type;
					if(t == "text" || t == "password"){						
						e[i].focus();							
						break;
					}
				}
			}
		}
	}	
};

//判断元素是否隐藏
function isHidden(o){
	while(o.parentNode){		
		if(o.style.display == "none"){
			return true;		
		}
		o = o.parentNode;	
	} 
	return false;	
};
//setTimeout(focusInput,500);


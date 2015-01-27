epay.safeCtrl = function(o){	
	var isIE = (navigator.userAgent.indexOf("MSIE") != -1);	
	var box = o.box;//密码输入框包裹元素
	var inputId = o.id ? o.id : "OTPCtl2";//密码输入框id
	var hiddenInputId = o.hiddenInputId ? o.hiddenInputId : "payPassword"; //隐藏密码框（真实要提交的密码框）的id
	var hiddenInputName = o.hiddenInputName ? o.hiddenInputName : "payPassword";//隐藏密码框（真实要提交的密码框）的name
	var inputStyle =  o.inputStyle ? o.inputStyle : ""; //输入框的样式
	var tipStyle =  o.tipStyle ? o.tipStyle : ""; //提示的样式
	var errValue = box.getAttribute("errText");
	var errText = errValue != "" ? errValue : "&nbsp;"; //初始的错误信息
	
	//提示
	var ctrlTip = '<input type="hidden" name="'+hiddenInputName+'" id="'+hiddenInputId+'" ><span class="err" id="'+inputId+'_err">'+errText+'</span>';
	
	//普通密码输入框
	var ordinaryStr = '<input type="password" id="'+inputId+'" class="input" style="'+inputStyle+'" />' + ctrlTip;
	
	//安全控件密码输入框
	var ctrlStr = "";

	if(!isIE){
		ctrlStr = '<span key="safaCtrlBox"><embed pluginspage="/download/nEdit.exe"  type="application/npnedit-netease-edit-plugin" id="'+inputId+'" class="input" style="'+inputStyle+'" chllng="12345678" isencryption="0" enctype="SHA1" onlynum="1"></embed></span>' + ctrlTip;;
	}
	else{
		ctrlStr = '<span key="safaCtrlBox"><object id="'+inputId+'" style="'+inputStyle+'"  class="input ieSafeCtrl" classid="clsid:32D72994-45B9-42B5-8980-FB561D1BE2D0" viewastext></object></span><span key="updateBox"></span>'+ ctrlTip;	
	}
	
	//var tipText = "控件可保护您输入信息的安全";
	var tipText1 = "安全控件升级了，可更全面保护您<br />输入信息的安全 <a href='javascript:void(0)' key='safeClose1'>下次再说</a>";
		
	//x64无法安装提示
	var strX64 = '<span class="safeClass1" key="safeClass1" style="'+tipStyle+'"><span class="safeClass1Tip safeClassInstall"  style="height:40px;top:-50px"><span class="safeClass1TipArr"></span>暂不支持64位的浏览器，请更换32位浏览器后重新访问网易宝。<a href="#" target="_blank">如何更换？</a></span><span style="color:#999">无法安装安全控件</span></span></span>' + ctrlTip;;	
		
	//安装安全控件提示
	var installStr = '<span class="safeClass1" key="safeClass1" style="'+tipStyle+'"><a href="#" onclick="epay.safeCtrl.installNedit();return false;">请点此安装安全控件</a></span>' + ctrlTip;;
	
	//升级安全控件提示
	var updateStr = '<span class="safeClass1 safe0therClass" key="safeClass1" style="'+tipStyle+'"><span class="safeClose" key="safeClose"></span><span class="safeClass1Tip" key="safeClass1Tip"><span class="safeClass1TipArr"></span>'+tipText1+'</span><a href="#" onclick="epay.safeCtrl.installNedit();return false;">请点此升级安全控件</a></span>';

		
	//安全控件不能输入密码的处理
	function show_ordinary_input(){
		document.cookie = "OTPCtl2=no";
		box.innerHTML = ordinaryStr;	
		epay.$(inputId).focus();
	};	
	var otpctl = cookie.getCookie(inputId);
	
	//如果安全控件不能正常输入就显示普通密码输入框
	if(otpctl == "no"){
		box.innerHTML = ordinaryStr;
		return;
	}	
	//ie下的处理
	if(isIE){		
		if(epay.safeCtrl.getCPU() == "win64"){
			box.innerHTML = strX64;
		}
		else{
			box.innerHTML = ctrlStr;					
		}
		var safeCtrl = epay.$(inputId);		
		
		if(!safeCtrl.MaxLngth){box.innerHTML = installStr; return}
			//如果已安装过
		  try{
			  safeCtrl.OnlyNum = false;			 
			  safeCtrl.TestEdit();
			  safeCtrl.MaxLngth=20;
			  var version = epay.$(inputId).nEditVersion;//获取版本号	
			  if(!version){
				  var updateBox =  epay.getAttribute(box,"*","key","updateBox")[0];
				  updateBox.innerHTML = updateStr;  
				  var safaCtrlBox =  epay.getAttribute(box,"*","key","safaCtrlBox")[0];
				  var safeClass1 =  epay.getAttribute(box,"*","key","safeClass1")[0];
				  var safeClass1Tip =  epay.getAttribute(box,"*","key","safeClass1Tip")[0];
				  var safeClose =  epay.getAttribute(box,"*","key","safeClose")[0];
				  var safeClose1 =  epay.getAttribute(box,"*","key","safeClose1")[0];
				  safaCtrlBox.style.display = "none";
				  safeClass1.onmouseover = function(){
					 safeClass1Tip.style.zIndex = 1000;
					 safeClass1Tip.style.display = "block";
				  };
				  safeClass1.onmouseout = function(){
					 safeClass1Tip.style.zIndex = 1;
					 safeClass1Tip.style.display = "none";
				  };
				  function closeUpdata(){
					  safeClass1.style.display = "none";
				 	  safaCtrlBox.style.display = "inline";
				  };
				  //关闭控件升级
				  safeClose.onclick = closeUpdata;
				  safeClose1.onclick = closeUpdata;
			  }
		  } 
		  catch (exception){//没有安装过的时候
				box.innerHTML = installStr; 				
		  }
	 
	}
	//非ie下的处理
	else{		
		box.innerHTML = ordinaryStr;		
		/*
		if(epay.$("OTPCtl2").value){	//通过判断有没有password这个属性来得到是否安装过控件		
			box.innerHTML = installStr; 
		};			
		*/
	}
	// var payPassTipBox =  epay.getAttribute(box,"*","key","payPassTipBox")[0];
	// var payPassTip =  epay.getAttribute(box,"*","key","payPassTip")[0];
	 var replaceInput =  epay.getAttribute(box,"*","key","replaceInput")[0];
	 function Tips(o,o1){		
		o.tips = o1;	
		o.onmouseover = function(){		
			this.tips.style.display = "block";		
		};
		o.onmouseout = function(){
			this.tips.style.display = "none";		
		};	
	};	
	//Tips(payPassTipBox,payPassTip);	
	if(!epay.$(inputId)){replaceInput.id = inputId;}
};

//安装安全控件按钮
epay.safeCtrl.installNedit = function(){	
	epay.safeCtrl.installPop();
	location.href="http://epay.163.com/download/nEdit.exe";
};

//密码安装安全控件前的提示
epay.safeCtrl.installPop = function(){		
	var o  = {		
		title:"安全控件提示",
		style:"width:550px;height:250px;",
		content:'<div class="safePopCon">安装控件，可对您输入的信息(密码、金额等)进行加密保护，提高账户安全。<div class="safePopText2"><strong class="c_FD8300"><span class="safePoint">■</span>&nbsp;安装成功后&nbsp;<a class="reDown" href="javascript:location.reload()">点此刷新</a>&nbsp;，如刷新无效请重启浏览器。 </strong><br><span class="safePoint">■</span>&nbsp;如果浏览器还没有开始下载控件文件，请 <a href="http://epay.163.com/download/nEdit.exe">点此重新下载</a></div><div class="dashedLine"></div><div class="safePopQue"><a href="http://epay.gm.163.com/load_kind.html?2011">安装遇到问题？</a></div></div>'
	};	
	setTimeout(function(){epay.popup(o)},100);	
};

epay.safeCtrl.getCPU = function(){
       return window.navigator.platform.toLowerCase();
 };

var cookie = {
    //设置cookie
    setCookie:function(sName,sValue,oExpires,sPath,sDomain,bSecure)	{
	    var sCookie=sName + "=" + encodeURIComponent(sValue);
	    if(oExpires){sCookie += "; expires=" + oExpires.toUTCString();}
	    if(sPath){sCookie += "; path="+sPath;}
	    if(sDomain){sCookie += "; domain="+sDomain;}	
	    if(bSecure){sCookie += "; scure";}
	    document.cookie=sCookie;
    },
	
    //读取cookie
    getCookie:function(sName){
	    var sRE="(?:; )?" + sName + "=([^;]*);?";
	    var oRE=new RegExp(sRE);
	    if(oRE.test(document.cookie)){
		    return decodeURIComponent(RegExp["$1"]);
		}
		else{return null;}	
    },
	
    //删除cookie
    delCookie:function(sName,sPath,sDomain){
	    setCookie(sName,"",new Date(0),sPath,sDomain);	
    }
};
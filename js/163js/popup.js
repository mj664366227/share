//---------------------------------弹窗类-----------------------------------
function popup(popId,bgcolor,alpha){//
    this.popWindow=document.getElementById(popId);
    this.bgcolor=bgcolor;
    this.alpha=alpha;   
	this.mybg=null; 
}

//创建弹窗
popup.prototype.create=function(){    
    if(!this.mybg){	
		
	//设置窗口属性	
    this.popWindow.style.display = "block";
	this.popWindow.style.position = "absolute";
	this.popWindow.style.zIndex = 1000;	
	this.popWindow.style.top = "50%";
	this.popWindow.style.left = "50%";
	this.popWindow.style.marginTop = -(this.popWindow.clientHeight/2)+"px";
	this.popWindow.style.marginLeft = -(this.popWindow.clientWidth/2)+"px";	
	
	//创建遮盖层	
	var h = document.body.clientHeight;
	var h1 = document.documentElement.clientHeight;
	h > h1 ? h = h : h = h1;
	mybg = document.createElement("div");	
	mybg.style.background = this.bgcolor;
	mybg.style.width = "100%";
	mybg.style.height = h + "px";
	mybg.style.position = "absolute";
	mybg.style.top = "0";
	mybg.style.left = "0";
	mybg.style.zIndex = "999";
	mybg.style.opacity = (this.alpha/100);
	mybg.style.filter = "Alpha(opacity="+this.alpha+")";
	document.body.appendChild(mybg);	
	this.mybg=mybg;
	//document.body.style.overflow = "hidden";	
	}
	
	this.mybg.style.display="block";
	this.popWindow.style.display = "block";
	//针对ie6.0隐藏select
	if(navigator.userAgent.indexOf("MSIE 6.0")>0){
	document.body.style.height="100%";//针对ie6.0不定义body高度遮盖层高度无效
	this.hideSelects();
	}	
	this.hideObject();
	
	 ev.addEvent(window,"resize",this.changSize);
}


//弹窗拖动
popup.prototype.drag=function(handId){//
    $(handId).onselectstart=function(){return false};
    var s_left=0;
    var s_top=0;
	var popup=this.popWindow;
    ev.addEvent($(handId),"mousedown",mDown);
	//按下时
	function mDown(){
	    var evn=ev.getEvent();        
        //拖动修正值
        f_left=evn.clientX-popup.offsetLeft;
        f_top=evn.clientY-popup.offsetTop;       
        s_left=evn.clientX-f_left+"px";
        s_top=evn.clientY-f_top+"px";	
		ev.addEvent(document,"mousemove",mMove);
		ev.addEvent(document,"mouseup",mUp);
	}
	//拖动时
	function mMove(){
        var evn=ev.getEvent();        
        popup.style.left=evn.clientX-f_left+(popup.clientWidth/2)+"px";
        popup.style.top=evn.clientY-f_top+(popup.clientHeight/2)+"px";
   }
   //放下时
   function mUp(){
        ev.removeEvent(document,"mousemove",mMove);
        ev.removeEvent(document,"mouseup",mUp);
   }
}


//针对ie6.0显示select
popup.prototype.showSelects=function(){
   var elements = document.getElementsByTagName("select");
   for (i=0;i< elements.length;i++){
      elements[i].style.visibility='visible';
   }
}

//针对ie6.0隐藏select
popup.prototype.hideSelects=function(){
   var elements = document.getElementsByTagName("select");
   for (i=0;i< elements.length;i++){
   elements[i].style.visibility='hidden';
   }
}

//关闭弹窗
popup.prototype.closePopup=function(){    
    this.popWindow.style.display="none";	
	this.mybg.style.display="none";			
	//针对ie6.0显示select
   	if(navigator.userAgent.indexOf("MSIE 6.0")>0){	
	this.showSelects();
	}
	this.showObject();
}

//显示弹窗
popup.prototype.openPopupAndBg=function(){    
    this.popWindow.style.display="";	
	this.mybg.style.display="";			
	//针对ie6.0显示select
   	if(navigator.userAgent.indexOf("MSIE 6.0")>0){	
	this.hideSelects();
	}
	this.hideObject();
}

//显示弹窗
popup.prototype.openPopup=function(){    
    this.popWindow.style.display="";	
	this.mybg.style.display="none";			
	//针对ie6.0显示select
   	if(navigator.userAgent.indexOf("MSIE 6.0")>0){	
	this.hideSelects();
	}
	this.hideObject();
}
//隐藏OBJECT
popup.prototype.hideObject=function(){	
   var elements = document.getElementsByTagName("object");
   for (i=0;i< elements.length;i++){	
  	 elements[i].style.visibility='hidden';
   }   
    var elements1 = this.popWindow.getElementsByTagName("object");	
	for (i=0;i< elements1.length;i++){	
  	 elements1[i].style.visibility='visible';
   }
};

//显示OBJECT
popup.prototype.showObject=function(){
   var elements = document.getElementsByTagName("object");
   for (i=0;i< elements.length;i++){
   elements[i].style.visibility='visible';
   }
};
popup.prototype.changSize = function(){
	if(this.mybg.style.display != "none"){		
		var h = document.body.clientHeight;
		var h1 = document.documentElement.clientHeight;
		h > h1 ? h = h : h = h1;
		this.mybg.style.height = h + "px";
	}
};

 

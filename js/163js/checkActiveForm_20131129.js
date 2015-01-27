
//-----------------------event---------------------------


var ev={
          //添加事件监听
          addEvent:function(obj,evt,fun){
              if(obj.addEventListener){//for dom
                    obj.addEventListener(evt,fun,false);
              }
              else if(obj.attachEvent){//for ie
			        // obj.attachEvent("on"+evt,fun);
                    obj.attachEvent("on"+evt,function(){fun.call(obj)});//解决IE attachEvent this指向window的问题
			  }
              else{obj["on"+evt] = fun}//for other
          },
		  
          //删除事件监听
          removeEvent:function(obj,evt,fun){
              if(obj.removeEventListener){//for dom
                    obj.removeEventListener(evt,fun,false);
              }
              else if(obj.detachEvent){//for ie
                    obj.detachEvent("on"+evt,fun);
              }
              else{obj["on"+evt] = null;
			  } //for other
           },
	
          //捕获事件		
           getEvent:function(){
                    if(window.event){return window.event}
                    else{return ev.getEvent.caller.arguments[0];}	
           },
		   
		   formatEvent:function(evt){
                    evt.eTarget = evt.target ? evt.target:evt.srcElement;//事件目标对象
                    evt.eX = evt.pagex ? evt.pagex:evt.clientX + document.body.scrollLeft;//页面鼠标X坐标
                    evt.eY = evt.pagey ? evt.pagex:evt.clientY + document.body.scrollTop;//页面鼠标Y坐标
                    evt.eStopDefault = function(){this.preventDefault ? this.preventDefault():this.returnValue = false;};//取消默认动作
                    evt.eStopBubble = function(){this.stopPropagation ? this.stopPropagation():this.cancelBubble = true;};//取消冒泡
           }
};
var submitEvt = false;

function CheckForm(form,ele){
	this.form = form;//表单
	this.ele = ele;//需验证的元素数组	
	this.eleBlur();//onblur时验证
	this.submitForm();//提交表单时验证
	this.gotoErr = true;
};
var finishIds="";
//onblur时验证
CheckForm.prototype.eleBlur = function(){	
	var ele = this.ele;
	var nb = this;
	for(var i=0; i <ele.length;i++){
		if($(ele[i].id)){
			var e = $(ele[i].id);
			e.state = ele[i].state;
			e.test = ele[i].test;
			e.info = ele[i].info;
			
			//元素得到焦点时
			e.onfocus = function(){							
				nb.focusMode(this);	
			};			
			
			//元素失去焦点时
			e.onblur = function(){
				var ids=this.id+",";
				var reg=new RegExp("\\"+ids,"g");
				finishIds=finishIds.replace(reg,"");
				nb.blurMode(this);								
				
				this.r = true;//元素是否通过验证
				//不需要验证
				if(this.state == 3){
					nb.nocheckMode(this);
					return;
				} 
				
				//不是必填项且值为空不做验证
				if(this.value == "" &&  this.state == 2){
					nb.nocheckMode(this);	
					return;
				}
				
				//值为空且不是提交时不做验证
				if(this.value == "" && !submitEvt){
					nb.nocheckMode(this);	
					return;
				}
				
				//必填项值不为空进行验证			
				for(var j=0; j < this.test.length;j++){
					var f = this.test[j][0].replace(/#/g,"this.value");//转换#为this.value	
					if(eval(f)){
						this.r = false;
						nb.errMode(this,this.test[j][1]);
						break;
					}
				}
				if(this.r){
					if(finishIds.indexOf(this.id+",")==-1){finishIds+=this.id+",";};
					nb.rightMode(this);					
				}
				
			};
		}		
	}	
};
function refinishNum(){return finishIds}


//提交表单时验证
CheckForm.prototype.submitForm = function(){	
	var nb = this;	
	var form = nb.form;	
	this.form.onsubmit = function(){
		var firstErr = null;
		submitEvt = true;
		var ele = nb.ele;
		form.isSubmit = true;
		for(var i = 0; i < ele.length;i++){	
			if($(ele[i].id)){
				var e = $(ele[i].id);
				e.onblur();				
				if(!e.r){
					if(!firstErr){
						firstErr = e;						
					}					
					form.isSubmit = false;
					if(nb.gotoErr){firstErr.scrollIntoView();}
				};
			}			
		};	
		submitEvt = false;	
	
		//其他
		nb.addCase();		
		
		return form.isSubmit;
	};		
};


//元素得到焦点时的处理
CheckForm.prototype.focusMode = function(obj){		
	if($(obj.id + "_err")){$(obj.id + "_err").innerHTML = '&nbsp;';}//错误信息隐藏
	if(obj.info){//提示信息出现		
		obj.parentNode.style.zIndex = 500;		
		var info = $(obj.id + "_info");
		info.innerHTML = obj.info;
		info.style.display = "block";			
		//jQuery("#"+info.id).fadeTo("slow",1);; 
		var w = obj.clientWidth;			
		$(obj.id + "_info").style.left = (w+30) + "px";
	}
	obj.className = "formInputFocus";
	//obj.style.border = "1px solid #5B849E";	
	//obj.style.background = "#fff";
};


//元素失去焦点时的处理
CheckForm.prototype.blurMode = function(obj){
	//obj.style.border = "1px solid #BECED8";	
	//obj.style.background = "#fff";
	obj.className = "formInput";
	if(obj.info){//提示信息隐藏
		obj.parentNode.zIndex = 100;
		jQuery("#"+obj.id + "_info").fadeOut("slow"); 
		setTimeout(function(){$(obj.id + "_info").style.display = "none";},300);			
	}
};


//错误时的处理
CheckForm.prototype.errMode = function(obj,text){
	
	//obj.style.border = "1px solid #ff0000";
	//obj.style.background = "#FFE2E2";
	obj.className = "formInputError";
	if($(obj.id+"_err")){
		var err = 	$(obj.id+"_err");		
		if(text){
			err.innerHTML = text;
		}		
	}
};


//正确时的处理
CheckForm.prototype.rightMode = function(obj){
	if($(obj.id+"_err")){
		var err = 	$(obj.id+"_err");		
		err.innerHTML = '&nbsp;';			
	}
};
//不要验证时的处理
CheckForm.prototype.nocheckMode = function(obj,t){	
	if($(obj.id+"_err")){
		var err = 	$(obj.id+"_err");
		err.innerHTML = "&nbsp;";					
	}
};

//附加验证的出错信息提示

//非输入类型及其他附加提交验证接口
CheckForm.prototype.addCase = function(){};

/* 精确校验身份证 */

function onCheckRegIDno(id_card) {  
    if (!isIDno(id_card)) {
        return 1;
    }
    else { 
        return 0;
    }
}

function isIDno(obj) {
	if($("cardType")){
		if($("cardType").value==1){
			return true;
		}
	}
    var aCity = "11,12,13,14,15,21,22,23,31,32,33,34,35,36,37,41,42,43,44,45,46,50,51,52,53,54,61,62,63,64,65,71,81,82,91";
    var iSum = 0;
    var info = "";
    var idCardLength = obj.value.length;
    if(!/^\d{17}(\d|x)$/i.test(obj.value) && !/^\d{15}$/i.test(obj.value) && !/^\d{8}$/i.test(obj.value)) {     
        return false;
    }
    //在后面的运算中x相当于数字10,所以转换成a
    var objvalue = obj.value.replace(/x$/i,"a");

    var curCity = objvalue.substr(0,2);

    if(!(aCity.indexOf(curCity) >= 0) ) {      
        return false;
    }
    if (idCardLength==18) {
        sBirthday=objvalue.substr(6,4)+"-"+Number(objvalue.substr(10,2))+"-"+Number(objvalue.substr(12,2));
        var d = new Date(sBirthday.replace(/-/g,"/"))
        if(sBirthday!=(d.getFullYear()+"-"+ (d.getMonth()+1) + "-" + d.getDate())) {       
            return false;
        }
        for(var i = 17;i>=0;i --)
            iSum += (Math.pow(2,i) % 11) * parseInt(objvalue.charAt(17 - i),11);

        if(iSum%11!=1) {        
            return false;
        }

    }
    else if (idCardLength==15) {
        sBirthday = "19" + objvalue.substr(6,2) + "-" + Number(objvalue.substr(8,2)) + "-" + Number(objvalue.substr(10,2));
        var d = new Date(sBirthday.replace(/-/g,"/"))
        var dd = d.getFullYear().toString() + "-" + (d.getMonth()+1) + "-" + d.getDate();

        if(sBirthday != dd) {       
            return false;
        }
    }
    else if (idCardLength==8) {

    }
    return true;
};


//常用验证正则
var checkReg = {
	Require : /.+/,
	Email : /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
	MDate:/^((?:19|20)\d\d)(0[1-9]|1[012])(0[1-9]|[12][0-9]|3[01])$/,
	Phone : /^((\(\d{3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}$/,
	Mobile : /^(13|14|15|18)\d{9}$/,
	Name:/^[a-zA-Z0-9_\u4e00-\u9fa5]+$/,
	TrueName : /^([\u0391-\uFFE5]|[A-Za-z]|[\u00B7])([\u0391-\uFFE5A-Za-z\u00B7\\*\\?\\)\\(]){0,39}$/,
	ChineseName:/^[\u0391-\uFFE5]{2,10}$/,
	Code : /^([\u0391-\uFFE5]|[A-Za-z]|\d|[\u00B7]){2,40}$/,
	Money : /^(?![0\.]+$)(?:(?:[1-9]\d*?(?:\.\d{1,2})?)|(?:0\.\d{1,2}))$/,
	Pinyin:/^[a-zA-Z]*$/,
	Url : /^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/,
	IdCard : /^\d{15}(\d{2}[A-Za-z0-9])?$/,
	Currency : /^\d+(\.\d+)?$/,
	Number : /^\d+$/,
	Zip : /^[1-9]\d{5}$/,
	QQ : /^[1-9]\d{4,8}$/,
	Integer : /^[-\+]?\d+$/,
	Double : /^[-\+]?\d+(\.\d+)?$/,
	English : /^[A-Za-z]+$/,
	Chinese :  /^[\u0391-\uFFE5]+$/
};

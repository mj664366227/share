

/* 表单验证 */

//输入类型的验证
/*
var c = {id:"",state,1,test:[]}
id//需要验证的元素
state//验证元素状态，1：必填项，2：非必填项,3:不需验证项（用于取消对元素的验证）
test//验证元素的验证条件,数组形式，[[验证条件1,出错提示文本1],[验证条件2,出错提示文本2]....],其中验证条件为字符串形式，#号代表该元素的值(this.value)
*/


var submitEvt = false;

function CheckForm(form,ele){
	this.form = form;//表单
	this.ele = ele;//需验证的元素数组	
	this.eleBlur();//onblur时验证
	this.submitForm();//提交表单时验证
};

//onblur时验证
CheckForm.prototype.eleBlur = function(){	
	var ele = this.ele;
	var nb = this;
	for(var i=0; i <ele.length;i++){
		if($(ele[i].id)){
			var e = $(ele[i].id);
			e.state = ele[i].state;
			e.test = ele[i].test;
			
			e.onblur = function(){		
				this.r = true;//元素是否通过验证
				var err = $(this.id + "_err");//出错提示元素id为元素id+ "_err";
			
				if(this.state == 3){return} //不需要验证
				if(this.value == "" &&  this.state == 2){err.className = "err_info";err.innerHTML = "";return;}//值为空且不是必填项
				
				if(submitEvt){
					//必填项值为空
					if(this.value == ""){
						this.r = false;					
						err.className = "err_info";
						err.innerHTML = "不能为空";	
						return;
					};
				}
				if(this.value == ""){
					err.innerHTML = "";
					err.className = "";
					return;
					}
				
				//必填项值不为空			
				for(var j=0; j < this.test.length;j++){
					var f = this.test[j][0].replace(/#/g,"this.value");//转换#为this.value				
					if(eval(f)){					
						this.r = false;					
						err.className = "err_info";
						err.innerHTML = this.test[j][1];
					}
				}
				if(this.r){
					err.innerHTML = "";					
					err.className = "succ_info";
				}
				
			};
		}		
	}	
};


//提交表单时验证
CheckForm.prototype.submitForm = function(){	
	var nb = this;	
	var form = nb.form;	
	this.form.onsubmit = function(){
		submitEvt = true;
		var ele = nb.ele;
		form.isSubmit = true;
		for(var i = 0; i < ele.length;i++){	
			if($(ele[i].id)){
				var e = $(ele[i].id);
				e.onblur();
				if(!e.r){form.isSubmit = false};
			}			
		};	
		submitEvt = false;	
		
		//其他
		nb.addCase();			
		return form.isSubmit;
	};		
};

//非输入类型及其他附加提交验证接口
CheckForm.prototype.addCase = function(){};


//精确身份证验证
function isIDno(obj) {
    var aCity = "11,12,13,14,15,21,22,23,31,32,33,34,35,36,37,41,42,43,44,45,46,50,51,52,53,54,61,62,63,64,65,71,81,82,91";

    var iSum = 0;
    var info = "";
    var idCardLength = obj.value.length;
    if(!/^\d{17}(\d|x)$/i.test(obj.value)&&!/^\d{15}$/i.test(obj.value)&&!/^\d{8}$/i.test(obj.value)) {      
        return false;
    };
    //在后面的运算中x相当于数字10,所以转换成a
    var objvalue = obj.value.replace(/x$/i,"a");
    var curCity = objvalue.substr(0,2);
    if(!(aCity.indexOf(curCity) >= 0) ) {  return false;};
    if (idCardLength==18) {
        sBirthday=objvalue.substr(6,4)+"-"+Number(objvalue.substr(10,2))+"-"+Number(objvalue.substr(12,2));
        var d = new Date(sBirthday.replace(/-/g,"/"));
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
        var d = new Date(sBirthday.replace(/-/g,"/"));
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
	Phone : /^((\(\d{3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}$/,
	Mobile : /^(13|14|15|18)\d{9}$/,
	Name:/^[a-zA-Z0-9_\u4e00-\u9fa5]+$/,
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





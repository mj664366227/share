###说明
######使用每一种后端语言实现一个可立刻上手编程的框架。目前只实现了php和java。
######把常用的linux服务器软件的安装过程编辑成shell脚本，实现一键傻瓜式配置生产环境。

---

###已实现项目
######shareTools
一些在日常开发过程中小工具的集合，例如：md5计算，urlencode等等。

######monitor
一个类似监控宝的服务器监控程序，基于snmp v3实现。


```javascript
function test(){
	console.log("Hello world!");
}
 
(function(){
    var box = function(){
        return box.fn.init();
    };

    box.prototype = box.fn = {
        init : function(){
            console.log('box.init()');

			return this;
        },

		add : function(str){
			alert("add", str);

			return this;
		},

		remove : function(str){
			alert("remove", str);

			return this;
		}
    };
    
    box.fn.init.prototype = box.fn;
    
    window.box =box;
})();

var testBox = box();
testBox.add("jQuery").remove("jQuery");
```
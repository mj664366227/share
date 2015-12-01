/**
 * Created by gongzuoshi_gatherup on 15/11/16.
 */
//公用JS

//窗口大小
var winWidth,winHeight;
var posX = 0;
var pos = "";
var timer1,timer2,timer3;
var showIndex = 0;
window.onload = function(){
    checkWinSize();
    unlock();
}
$(function(){
    //$("html,body,img,#page1,#page2,#page3,#page4,#page4 img,#page4 li,#page5,#page6,.container,.content").on("dblclick",function(){
    //    return false;
    //});
   $("#page1").on("swiperight",function(){
        pageSwiper();
    });

    function pageSwiper(){
        $("#page1").animate({left:"640px"},400,"linear",function(){
            $("#page1").hide();
        });
        $("#page2").animate({left:"0px"},400,"linear",function(){
        });
    };
    $("#direct01").click(function(){
        $("#page2").hide();
        $("#page3").fadeIn();
        $(document).attr("title","邮箱1111");
    });
    $("#direct02").click(function(){
        $("#page3").hide();
        $("#page4").fadeIn();
        $(".container").css("overflow","inherit");
    });
    $("#page4").click(function(){
        var i = 0;
        $("#page4 dl dd").each(function(){
            $(this).delay(1000).css("backgroundImage",'url("/mobile/default/groupTalk/images/checked.png")');
        });
        $("#page4").unbind("click");
        $('html,body').animate({scrollTop: $('.footer').offset().top}, 300);
    });
    $("#SelectAll a").click(function(){
        $("#page4").hide();
        $("#page5").fadeIn(300,function(){
            $("body").css("background-color","#ebebeb");
        });
        $("#page5").queue(function(){
            inOrderShow();
        });
    });
    $("#page6Case").click(function(){
        $("#page5").hide();
        $("#page6").fadeIn();
        $("body").css("background-color","#fff");
        $('html,body').animate({scrollTop: $('.top').offset().top}, 100);
    });
    function inOrderShow(){
        if(showIndex < 3){
            $('html,body').animate({scrollTop: $('.top').offset().top}, 300);
        }
        $("#page5 .s2 .list:eq(" + showIndex + ")").fadeIn();
        if(showIndex > 3){
            $('html,body').animate({scrollTop: $('.footer').offset().top}, 300);
        }
        showIndex++;
        if(showIndex == 8){
            clearTimeout(timer3);
            return false;
        }
        timer3 = setTimeout(inOrderShow,1000);

    }
    $(".alertBox").click(function(){
    	if(is_weixin()){
            $(".guide").fadeIn();
        }
    });
    $(".guide").click(function(){
    	$(this).fadeOut();
    });
    function is_weixin() {
        var ua = navigator.userAgent.toLowerCase();
        if (ua.match(/MicroMessenger/i) == "micromessenger") {
            return true;
        } else {
            return false;
        }
    }

});

    function getWindowSize(){
        //获取窗口的宽度
        if(window.innerWidth){
            winWidth = window.innerWidth;
        }else if((document.body) && (document.body.clientWidth)){
            winWidth = document.body.clientWidth;
        }
        //获取窗口的高度
        if(window.innerHeight){
            winHeight = window.innerHeight;
        }else if((document.body) && (document.body.clientHeight)){
            winHeight = document.body.clientHeight;
        }

        if(document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth){
            winWidth = document.documentElement.clientWidth;
            winHeight = document.documentElement.clientHeight;
        }
    };

    function checkWinSize(){
        getWindowSize();
        document.getElementById("container").style.width = 640 + "px";
        document.getElementById("container").style.height = winHeight/winWidth * 640 + "px";
        document.getElementById("content").style.width = winWidth + "px";
        document.getElementById("content").style.height = winHeight/winWidth * 640 + "px";
        if(winWidth > winHeight){//横屏
            console.log("横屏");
        }
        loadBarFun();
    }
    function loadBarFun(){
        var loadBar = document.getElementById("loadBar");
        var loadTotal = document.getElementById("loadTotal");
        var loadTips = document.getElementById("loadTips");
        loadBar.style.width = parseInt(loadBar.style.width) + 1 + "%";
        loadTotal.innerHTML = loadBar.style.width;
        if(loadBar.style.width == "100%"){
            loadTips.innerHTML = "Complate.";
            $("#loading").hide();
            document.body.style.backgroundColor = "#000";
            $("#page1").show();
            $("#page2").show();
            window.clearTimeout(timer1);
            return;
        }
        timer1 = window.setTimeout("loadBarFun()",30);
    }
    function unlock(){
        pos = posX + "px 0";
        $("#unlock").css("background-position",pos);
        posX -= 222;
        if(posX == -6660){
            posX = 0;
            window.clearTimeout(timer2);
        }
        timer2 = window.setTimeout("unlock()",36);
    }

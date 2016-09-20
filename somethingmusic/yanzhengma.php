<?php 
session_start();
//验证码生成代码
$image_width=60;                      //设置图像宽度
$image_height=20;              //设置图像高度
$num_image=imagecreate($image_width,$image_height);  //创建一个画布
$red=rand(190,255);//设置画布的颜色
$green=rand(120,210);
$blue=rand(130,150);
imagecolorallocate($num_image,$red,$green,$blue);     	 
for($i=0;$i<4;$i++) //循环读取SESSION变量中的验证码
{
	$string=rand(0,9);
	$_SESSION['yzm_check'].=$string;
	$str[$i]=$string;
	$font=mt_rand(38,490);                            //设置随机的字体
	$x=$image_width*$i/4+5;               //设置随机字符所在位置的X坐标
	$y=$image_height/2-8;                   //设置随机字符所在位置的Y坐标
    $color=imagecolorallocate($num_image,mt_rand(130,180),mt_rand(10,130),mt_rand(130,180));  	 //设置字符的颜色
    imagestring($num_image,$font,$x,$y,$str[$i],$color);				     //水平输出字符
}
imagepng($num_image);      			//生成PNG格式的图
imagedestroy($num_image);  			//释放图像资源

/*
检测方法
substr($yzm_check,-4,strlen($yzm_check));
*/
?>
<?php
session_start();
/**
 * PHP SDK for QQ登录 OpenAPI
 *
 * @version 1.2
 * @author connect@qq.com
 * @copyright © 2011, Tencent Corporation. All rights reserved.
 */

/**
 * @brief 本文件作为demo的配置文件。
 */

/**
 * 正式运营环境请关闭错误信息
 * ini_set("error_reporting", E_ALL);
 * ini_set("display_errors", TRUE);
 * QQDEBUG = true  开启错误提示
 * QQDEBUG = false 禁止错误提示
 * 默认禁止错误信息
 */

define("QQDEBUG", true);
if (defined("QQDEBUG") && QQDEBUG)
{
    //@ini_set("error_reporting", E_ALL);
    @ini_set("display_errors", TRUE);
}

/**
 * session
 */
//include_once("session.php");


/**
 * 在你运行本demo之前请到 http://connect.opensns.qq.com/申请appid, appkey, 并注册callback地址
 */
//申请到的appid
//$_SESSION["appid"]    = yourappid; 
$_SESSION["appid"]    = 100261747; 

//申请到的appkey
//$_SESSION["appkey"]   = "yourappkey"; 
$_SESSION["appkey"]   = "2eaa8d62823a4a24836d123cdd74cf8e"; 

//QQ登录成功后跳转的地址,请确保地址真实可用，否则会导致登录失败。
//$_SESSION["callback"] = "http://your domain/oauth/qq_callback.php"; 
$_SESSION["callback"] = "http://music.libmill.com/qq/oauth/qq_callback.php";

//QQ授权api接口.按需调用
$_SESSION["scope"] = 'get_user_info,add_share,check_page_fans,add_t,del_t,add_pic_t,re_list_t,get_info,get_other_info,get_fanslist,get_idolist,add_friend,de_friend,add_share,list_album,add_album,upload_pic,add_topic,add_one_blog,add_weibo';

//print_r ($_SESSION);
?>

package com.gu.core.common;

import com.gu.core.util.SystemUtil;

/**
 * url定义
 * @author ruan
 */
public final class URLCommand {
	/**
	 * 发表吐槽
	 */
	public final static String flow_pub = "/flow/pub";
	/**
	 * 吐槽列表
	 */
	public final static String flow_list = "/flow/list";
	/**
	 * 用户登录
	 */
	public final static String user_login = "/user/login";
	/**
	 * 用户微信登录
	 */
	public final static String user_login_wechat = "/user/login/wechat";
	/**
	 * 更新个人资料
	 */
	public final static String user_updateinfo = "/user/updateinfo";
	/**
	 * 个人资料
	 */
	public final static String user_selfinfo = "/user/selfinfo";
	/**
	 * 他人资料
	 */
	public final static String user_otherinfo = "/user/otherinfo";
	/**
	 * 普通用户注册(手机注册)
	 */
	public final static String user_reg = "/user/reg";
	/**
	 * 普通用户注册(微信+手机注册)
	 */
	public final static String user_reg_wechat = "/user/reg/wechat";
	/**
	 * 验证手机是否被注册
	 */
	public final static String user_reg_mobileonly = "/user/reg/mobileonly";
	/**
	 * 已注册用户绑定微信
	 */
	public final static String user_bind_wechat = "/user/bind/wechat";
	/**
	 * 已注册用户解除绑定微信
	 */
	public final static String user_unbind_wechat = "/user/unbind/wechat";
	/**
	 * app版本
	 */
	public final static String app_version = "/app/version";
	/**
	 * 修复接口
	 */
	public final static String repair = "/repair";
	/**
	 * 重置密码
	 */
	public final static String user_reset_password = "/user/reset/password";
	/**
	 * 设置支付密码
	 */
	public final static String user_set_payment_password = "/user/set/payment/password";
	/**
	 * 用户关注企业
	 */
	public final static String user_focus_company = "/user/focus/company";
	/**
	 * 用户取消关注企业
	 */
	public final static String user_unfocus_company = "/user/unfocus/company";
	/**
	 * 用户关注用户
	 */
	public final static String user_focus = "/user/focus";
	/**
	 * 用户取消关注用户
	 */
	public final static String user_unfocus = "/user/unfocus";
	/**
	 * 找回密码
	 */
	public final static String user_modify_pass = "/user/modify/pass";
	/**
	 * 我参与过的专案
	 */
	public final static String user_take_part_in_list = "/user/takepartin/list";
	/**
	 * 我的关注人列表
	 */
	public final static String user_focus_list = "/user/focus/list";
	/**
	 * 用户关注的专案列表
	 */
	public final static String user_focus_case_list = "/user/focus/case/list";
	/**
	 * 用户关注的专案列表(v2)
	 */
	public final static String user_focus_case_list_v2 = "/user/focus/case/list/v2";
	/**
	 * 我的粉丝列表
	 */
	public final static String user_fans_list = "/user/fans/list";
	/**
	 * 我的钱包
	 */
	public final static String user_wallet = "/user/wallet";
	/**
	 * 用户反馈
	 */
	public final static String user_feedback = "/user/feedback";
	/**
	 * 用户举报
	 */
	public final static String user_accusation = "/user/accusation";
	/**
	 * 随机返回一句话亮身份例子
	 */
	public final static String user_identity = "/user/identity";
	/**
	 * 更新一句话亮身份
	 */
	public final static String user_update_identity = "/user/update/identity";
	/**
	 * 用户实名认证
	 */
	public final static String user_prove = "/user/prove";
	/**
	 * 用户设置
	 */
	public final static String user_setting_save = "/user/setting/save";
	/**
	 * 我关注的企业列表
	 */
	public final static String user_focus_company_list = "/user/focus/company/list";
	/**
	 * 用户消息
	 */
	public final static String user_msg = "/user";
	/**
	 * 用户消息数量
	 */
	public final static String user_msg_num = "/user/num";
	/**
	 * 用户消息删除
	 */
	public final static String user_msg_del = "/user/del";
	/**
	 * 发送普通短信
	 */
	public final static String sms = "/sms";
	/**
	 * 获取验证码
	 */
	public final static String sms_get_code = "/sms/get/code";
	/**
	 * 验证短信验证码
	 */
	public final static String sms_verify_code = "/sms/verify/code";
	/**
	 * 上传图片
	 */
	public final static String upload_images = "/upload";
	/**
	 * 上传视频
	 */
	public final static String upload_video = "/upload/video";
	/**
	 * 新建专案
	 */
	public final static String case_new = "/case/new";
	/**
	 * 点子消息
	 */
	public final static String idea_msg = "/idea/msg";
	/**
	 * 编辑专案
	 */
	public final static String case_edit = "/case/edit/{caseId}";
	/**
	 * 金额排序
	 */
	public final static String case_bonus = "/case/bonus/{caseId}";
	/**
	 * 新增关注
	 */
	public final static String case_attent = "/case/attent/{caseId}";
	/**
	 * 建议留言
	 */
	public final static String case_advice = "/case/advice/{caseId}";
	/**
	 * 吐槽留言
	 */
	public final static String case_flow = "/case/flow/{caseId}";
	/**
	 * 企业注册
	 */
	public final static String company_reg = "/company/reg";
	/**
	 * 企业展示
	 */
	public final static String company_show = "/company/show/{companyId}";
	/**
	 * 后台首页
	 */
	public final static String company_index = "/company/index";
	/**
	 * 新建企业
	 */
	public final static String company_new = "/company/new/{companyId}";
	/**
	 * 企业登录
	 */
	public final static String company_login = "/company/login";
	/**
	 * 企业登出
	 */
	public final static String company_logout = "/company/logout";
	/**
	 * 企业邮箱验证
	 */
	public final static String company_email = "/company/email";
	/**
	 * 邮箱认证提示页面
	 */
	public final static String company_verify = "/company/verify/{companyId}";
	/**
	 * 专案简介
	 */
	public final static String case_info = "/case/info";
	/**
	 * 全部专案列表
	 */
	public final static String case_list = "/case/list";
	/**
	 * 分类专案列表
	 */
	public final static String case_list_type = "/case/list/type";
	/**
	 * 最新的专案列表
	 */
	public final static String case_list_new = "/case/list/new";
	/**
	 * 企业后台专案搜索
	 */
	public final static String case_search = "/case/search";
	/**
	 * 专案详情
	 */
	public final static String case_show = "/case/show/{caseId}";
	/**
	 * 企业专案清单
	 */
	public final static String company_case_list = "/company/case/list";
	/**
	 * 企业粉丝列表
	 */
	public final static String company_fans_list = "/company/fans/list";
	/**
	 * 企业简介
	 */
	public final static String company_info = "/company/info";
	/**
	 * 关注专案
	 */
	public final static String case_focus = "/case/focus";
	/**
	 * 取消关注专案
	 */
	public final static String case_unfocus = "/case/unfocus";
	/**
	 * 专案类型配置
	 */
	public final static String case_type_config = "/case/type/config";
	/**
	 * 发布创意(点子)
	 */
	public final static String idea_pub = "/idea/pub";
	/**
	 * 创意详情页
	 */
	public final static String idea_info = "/idea/info";
	/**
	 * mobile工程专案idea列表
	 */
	public final static String case_idea_list = "/list/{data}/*";
	/**
	 * mobile工程专案我分享的idea
	 */
	public final static String case_idea_share = "/share/{data}/*";
	/**
	 * 输入手机号送G点
	 */
	public final static String store = "/store";
	/**
	 * 充值
	 */
	public final static String charge = "/charge";
	/**
	 * 登录群聊分享页
	 */
	public final static String wechat_group_talk = "/wechat/group/talk";
	/**
	 * 下载app
	 */
	public final static String downApp = "/downapp";
	/**
	 * 创意列表
	 */
	public final static String idea_list = "/idea/list";
	/**
	 * 创意前n
	 */
	public final static String idea_top = "/idea/top";
	/**
	 * 编辑点子
	 */
	public final static String idea_edit = "/idea/edit";
	/**
	 * 发表创意评论
	 */
	public final static String idea_comment_pub = "/idea/comment/pub";
	/**
	 * 删除创意评论
	 */
	public final static String idea_comment_del = "/idea/comment/del";
	/**
	 * 发表创意评论
	 */
	public final static String idea_reply_company = "/idea/reply/company";
	/**
	 * 创意评论列表
	 */
	public final static String idea_comment_list = "/idea/comment/list";
	/**
	 * 企业创意评论列表
	 */
	public final static String idea_comment_company_list = "/idea/comment/company/list";
	/**
	 * 创意点赞
	 */
	public final static String idea_praise_up = "/idea/praise/up";
	/**
	 * 创意点赞取消
	 */
	public final static String idea_praise_down = "/idea/praise/down";
	/**
	 * 微信扫码支付回调地址
	 */
	public final static String wechat_qrcode_callback = "/wechat/qrcode/callback/{orderId}";
	/**
	 * 判断订单状态
	 */
	public final static String check_order = "/check/order/{orderId}";
	/**
	 * 生成二维码
	 */
	public final static String qrcode = "/qrcode/{qrcode}/{width}/{height}";
	/**
	 * 提交建议
	 */
	public final static String set_advice = "/set/advice";
	/**
	 * 修改密码
	 */
	public final static String set_psw = "/set/psw/{companyId}";
	/**
	 * 更换新密码
	 */
	public final static String set_psw_find = "/set/psw/find";
	/**
	 * 重置密码
	 */
	public final static String set_psw_reset = "/set/psw/reset";
	/**
	 * 设置界面
	 */
	public final static String set = "/set";
	/**
	 * 粉丝数据统计
	 */
	public final static String count_fans = "/count/fans";
	/**
	 * 专案数据统计
	 */
	public final static String count_case = "/count/case/{caseId}";
	/**
	 * 专案数据统计列表
	 */
	public final static String count_case_list = "/count/case/list";
	/**
	 * 首页
	 */
	public final static String index = "/index";
	/**
	 * 粉丝管理	
	 */
	public final static String contact_fans = "/contact/fans";
	/**
	 * G点管理	
	 */
	public final static String contact_gpoint = "/contact/gpoint";
	/**
	 * 点子收藏
	 */
	public final static String collect_idea = "/collect/idea";
	/**
	 * 关注用户管理
	 */
	public final static String contact_care = "/contact/care";
	/**
	 * 语言提示
	 */
	public final static String contact_status = "/contact/status/{tips}";
	/**
	 * ajax点子收藏
	 */
	public final static String ajax_collect_idea = "/ajax/collect/idea";
	/**
	 * 404页面
	 */
	public final static String error_404 = SystemUtil.isDEV() ? "https://192.168.1.110/404" : "https://gatherup.cc/404";
	/**
	 * 验证码
	 */
	public final static String yzm = "/yzm";
	/**
	 * ajax检查企业邮箱
	 */
	public final static String ajax_company_check_email = "/ajax/company/check/email";
	/**
	 * ajax检查企业名字
	 */
	public final static String ajax_company_check_name = "/ajax/company/check/name";
	/**
	 * ajax检查企业全名
	 */
	public final static String ajax_company_check_fullname = "/ajax/company/check/fullname";
	/**
	 * ajax企业关注用户
	 */
	public final static String ajax_company_focus_user = "/ajax/company/focus/user";
	/**
	 * ajax企业回复用户
	 */
	public final static String ajax_company_reply_user = "/ajax/company/reply/user";
	/**
	 * ajax检查验证码	
	 */
	public final static String ajax_check_yzm = "/ajax/check/yzm";
	/**
	 * ajax专案性别统计
	 */
	public final static String ajax_count_case_sex = "/ajax/count/case/sex";
	/**
	 * ajax专案实名认证统计
	 */
	public final static String ajax_count_case_prove = "/ajax/count/case/prove";
	/**
	 * ajax专案年龄段统计
	 */
	public final static String ajax_count_case_age = "/ajax/count/case/age";
	/**
	 * ajax专案省份统计
	 */
	public final static String ajax_count_case_province = "/ajax/count/case/province";
	/**
	 * ajax专案省份统计(翻页)
	 */
	public final static String ajax_count_case_province_page = "/ajax/count/case/province/page";
	/**
	 * ajax专案省份城市统计
	 */
	public final static String ajax_count_case_province_city = "/ajax/count/case/province/city";
	/**
	 * ajax新增关注走势
	 */
	public final static String ajax_count_case_focus_new = "/ajax/count/case/focus/new";
	/**
	 * ajax关注总数走势
	 */
	public final static String ajax_count_case_focus_total = "/ajax/count/case/focus/total";
	/**
	 * ajax取消关注走势
	 */
	public final static String ajax_count_case_unfocus = "/ajax/count/case/unfocus";
	/**
	 * ajax点子数走势
	 */
	public final static String ajax_count_case_idea = "/ajax/count/case/idea";
	/**
	 * ajax点子总数走势
	 */
	public final static String ajax_count_case_idea_total = "/ajax/count/case/idea/total";
	/**
	 * ajax获取消息数量
	 */
	public final static String ajax_message_num = "/ajax/message/num";
	/**
	 * 记录闪退日志
	 */
	public final static String crash = "/crash";
	/**
	 * 分享统计
	 */
	public final static String share = "/share";
	/**
	 * 用户app使用时长统计
	 */
	public final static String duration = "/duration";
	/**
	 * 分享点击统计
	 */
	public final static String share_click = "/share/click";
	/**
	 * 分享下载统计
	 */
	public final static String share_download = "/share/download";
	/**
	 * 记录启动次数
	 */
	public final static String start = "/start";
	/**
	 * 搜索用户
	 */
	public final static String search_user = "/search/user";
	/**
	 * 搜索专案
	 */
	public final static String search_case = "/search/case";
	/**
	 * 搜索企业
	 */
	public final static String search_company = "/search/company";
	/**
	 * 临时接口，生成帐号
	 */
	public final static String tmp_make_account = "/tmp/make/account";
	/**
	 * 用户提现信息
	 */
	public final static String checkout_info = "/checkout/info";
	/**
	 * 微信提现
	 */
	public final static String checkout_wechat = "/checkout/wechat";
	/**
	 * 支付宝提现
	 */
	public final static String checkout_alipay = "/checkout/alipay";
	/**
	 * 系统消息
	 */
	public final static String msg_system = "/system";
	/**
	 * web端消息页面
	 */
	public final static String message = "/company/message";
	/**
	 * 企业专案消息
	 */
	public final static String company_message_case = "/message/case";
	/**
	 * 企业粉丝消息
	 */
	public final static String company_message_fans = "/message/fans";
	/**
	 * 企业系统消息
	 */
	public final static String company_message_system = "/message/system";
	/**
	 * 企业用户回复消息
	 */
	public final static String company_message_userreply = "/message/userreply";
	/**
	 * ajax关注企业性别统计
	 */
	public final static String ajax_count_company_sex = "/ajax/count/company/sex";
	/**
	 * ajax关注企业实名认证统计
	 */
	public final static String ajax_count_company_prove = "/ajax/count/company/prove";
	/**
	 * ajax关注企业年龄段统计
	 */
	public final static String ajax_count_company_age = "/ajax/count/company/age";
	/**
	 * ajax关注企业省份统计
	 */
	public final static String ajax_count_company_province = "/ajax/count/company/province";
	/**
	 * ajax关注企业省份统计(翻页)
	 */
	public final static String ajax_count_company_province_page = "/ajax/count/company/province/page";
	/**
	 * ajax关注企业省份城市统计
	 */
	public final static String ajax_count_company_province_city = "/ajax/count/company/province/city";
	/**
	 * ajax企业新增关注走势
	 */
	public final static String ajax_count_company_focus_new = "/ajax/count/company/focus/new";
	/**
	 * ajax企业关注总数走势
	 */
	public final static String ajax_count_company_focus_total = "/ajax/count/company/focus/total";
	/**
	 * ajax企业关注净增走势
	 */
	public final static String ajax_count_company_growth = "/ajax/count/company/growth";
	/**
	 * ajax企业取消关注走势
	 */
	public final static String ajax_count_company_unfocus = "/ajax/count/company/unfocus";
	/**
	 * ajax企业点子数走势
	 */
	public final static String ajax_count_company_idea = "/ajax/count/company/idea";
	/**
	 * 发表创意圈_作品
	 */
	public final static String market_opus_pub = "/market/opus/pub";
	/**
	 * 发表创意圈_作品评论
	 */
	public final static String market_opus_comment_pub = "/market/opus/comment/pub";
	/**
	 * 发表创意圈_作品列表(个人)
	 */
	public final static String market_opus_list_user = "/market/opus/list/user";
	/**
	 * 发表创意圈_作品列表(全部)
	 */
	public final static String market_opus_list_all = "/market/opus/list/all";
	/**
	 * 发表创意圈_作品评论列表
	 */
	public final static String market_opus_comment_list = "/market/opus/comment/list";
	/**
	 * 发表创意圈_作品点赞或取消
	 */
	public final static String market_opus_praise_updown = "/market/opus/praise/updown";
	/**
	 * 创意圈_作品详细信息
	 */
	public final static String market_opus_info = "/market/opus/info";

}
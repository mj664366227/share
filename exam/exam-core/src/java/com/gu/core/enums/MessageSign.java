package com.gu.core.enums;

import java.util.HashMap;

import com.gu.core.msgbody.company.AdminSendPointsMessageBody;
import com.gu.core.msgbody.company.CaseFinishMessageBody;
import com.gu.core.msgbody.company.FocusCaseMessageBody;
import com.gu.core.msgbody.company.IdeaPubMessageBody;
import com.gu.core.msgbody.company.UserFocusCompanyMessageBody;
import com.gu.core.msgbody.company.UserReplyCompanyMessageBody;
import com.gu.core.msgbody.user.CaseFinishBonusMessageBody;
import com.gu.core.msgbody.user.CaseNewMessageBody;
import com.gu.core.msgbody.user.CompanyReplyUserMessageBody;
import com.gu.core.msgbody.user.DelIdeaMessageBody;
import com.gu.core.msgbody.user.IdeaCommentPubMessageBody;
import com.gu.core.msgbody.user.IdeaCommentReplyMessageBody;
import com.gu.core.msgbody.user.IdeaPraiseUpMessageBody;
import com.gu.core.msgbody.user.MarketOpusCommentPubMessageBody;
import com.gu.core.msgbody.user.MarketOpusCommentReplyMessageBody;
import com.gu.core.msgbody.user.UserFocusMessageBody;
import com.gu.core.msgbody.user.UserInvitePubIdeaMessageBody;

/**
 * 消息代号
 * @author lou
 */
public enum MessageSign {

	/** 发送给管理员消息分类 *************************************************************************************/
	/*100100x*/

	/** 发送给用户的消息分类*************************************************************************************/
	/*200100x,发送者为系统****************************************/
	//删除点子
	delIdea(2001001, "删除点子", DelIdeaMessageBody.class),
	//专案结束分红
	caseFinishBonus(2001002, "专案结束分红", CaseFinishBonusMessageBody.class),

	/*200200x,发送者为用户****************************************/
	//新增粉丝消息
	userFocus(2002001, "新增粉丝消息", UserFocusMessageBody.class),
	//创意评论消息
	ideaCommentPub(2002002, "创意评论消息", IdeaCommentPubMessageBody.class),
	//创意获赞消息
	ideaPraiseUp(2002003, "创意获赞消息", IdeaPraiseUpMessageBody.class),
	//发布专案邀请消息
	invitePubIdea(2002004, "发布专案邀请消息", UserInvitePubIdeaMessageBody.class),
	//创意评论被回复消息
	ideaCommentReply(2002005, "创意评论被回复消息", IdeaCommentReplyMessageBody.class),
	//创意圈_作品评论消息
	marketOpusCommentPub(2002006, "创意圈作品评论消息", MarketOpusCommentPubMessageBody.class),
	//创意圈_作品评论被回复消息
	marketOpusCommentReply(2002007, "创意圈作品评论被回复消息", MarketOpusCommentReplyMessageBody.class),

	/*200300x,发送者为企业****************************************/
	//企业回复创意
	companyReplyUser(2003001, "企业回复创意", CompanyReplyUserMessageBody.class),
	//企业新发布专案
	caseNew(2003002, "企业新发布专案", CaseNewMessageBody.class),

	/** 发送给企业的消息分类 *************************************************************************************/
	/*300100x,发送者为系统/管理员****************************************/
	//后台赠送G点
	adminSendPoints(3001001, "后台赠送G点", AdminSendPointsMessageBody.class),
	//专案结束通知
	caseFinish(3001002, "专案结算", CaseFinishMessageBody.class),

	/*300200x,消息通知****************************************/
	//用户回复企业
	userReplyCompany(3002001, "用户回复企业", UserReplyCompanyMessageBody.class),

	/*300300x,粉丝通知****************************************/
	//用户关注企业
	userFocusCompany(3003001, "用户关注企业", UserFocusCompanyMessageBody.class),

	/*300400x,专案通知****************************************/
	//用户提交创意
	ideaPub(3004001, "用户提交创意", IdeaPubMessageBody.class),
	//用户关注专案
	focusCase(3004002, "用户关注专案", FocusCaseMessageBody.class);

	private int sign;
	private String description;
	private Class<?> clazz;

	private static HashMap<Integer, MessageSign> map = new HashMap<Integer, MessageSign>(MessageSign.values().length);

	static {
		for (MessageSign sex : MessageSign.values()) {
			map.put(sex.getSign(), sex);
		}
	}

	private MessageSign(int sign, String description, Class<?> clazz) {
		this.sign = sign;
		this.description = description;
		this.clazz = clazz;
	}

	/**
	 * 获取值
	 */
	public int getSign() {
		return sign;
	}

	/**
	 * 获取描述
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 获取反序列化的Class
	 */
	public Class<?> getClazz() {
		return clazz;
	}

	public static MessageSign signOf(int sign) {
		return map.get(sign);
	}
}

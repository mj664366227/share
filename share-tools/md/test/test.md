# 作品接口说明
### 2.玩家未登陆获取作品信息 v02 遗留接口  GET  /json/v2/cdn/opus/get_opusinfo/v02
#### 请求参数
参数        |必选   |类型    	|说明
---         |---    |---    	|---
opusId    	|true   |long  		|作品ID
playerId    |true   |int        |玩家ID
fromType    |true   |int    	|1.人气最高  2.附近  3.最新  0.默认

#### 响应数据
```json
"data":{
	"baseInfo":{
		"opusId":709394,//作品id
		"songHash":"JOUHU2626DDDF5555fffff",//歌曲hash
		"opusName":"中国人",//作品名称
		"opusHash":"JOUHU2626DDDF5555fffff",//作品hash
		"createTime":1409823246000,//上传时间
		"status":1,//1:正常 0：作者删除 -1：官方删除
		"descr":"好好听的",//作品描述
		"player":{
			"playerId":323444,//玩家ID
			"nickname":"asqsummer",//用户昵称
			"sex":1,//性别
			"headimg ":"/sing_img/344333.jpg",//用户头像
			"isStar":1,//是否明星认证，0-否 1-是
			"isFx":0,  //是否繁星主播 0-否 1-是
			"showFxIcon":0,是否公开繁星主播认证 0-保密 1-公开
			"authExplain":"我是明星" //认证说明
		},
		"opusParentId":95454545,//作品父id
		"type":1,  	//作品类型 1-上传  2-转发(默认是1)
		"songId:52959659,//伴奏id
	},//作品的基本信息 
	"commentNum":2,// 评论数
	"praiseNum":3,//点赞数
	"giftNum":4,//送礼人数
	"forwardNum":5,//转发数
	"listenNum":6,//试听数
	"playerinfo":{
		[
			{
				"playerId":323444,//玩家ID
				"nickname":"asqsummer",//用户昵称
				"sex":1,//性别
				"headimg ":"/sing_img/344333.jpg",//用户头像
				"isStar":1,//是否明星认证，0-否 1-是
				"isFx":0,  //是否繁星主播 0-否 1-是
				"showFxIcon":0,是否公开繁星主播认证 0-保密 1-公开
				"authExplain":"我是明星" //认证说明
			}
		],[...]
	}// 贵宾席
	"isPraise":true,//是否点赞
	"isFocus":1//是否关注 0-未关注 1-已关注 2-互相关注
}
```

### 3.作品转发统计  POST  /json/v2/opus/forward_count
#### 请求参数
参数        |必选   |类型    	|说明
---         |---    |---    	|---
opusId    	|true   |long  		|作品ID
opusHash    |true   |string     |作品哈希

#### 响应数据
```json
"data":{
	//空,以code、msg为准 返回成功或失败
}
```

### 6.分享作品  POST  /json/v2/opus/share
#### 请求参数
参数        |必选   |类型    	|说明
---         |---    |---    	|---
songHash    |true   |string  	|伴奏哈希
opusName    |true   |string     |作品名字
opusHash    |true   |string     |作品哈希
opusDesc    |true   |string  	|作品描述
songId    	|true   |string     |伴奏id
fromType    |true   |string  	|分享到哪里  0: 唱空间	 1：第三方
longitude   |true   |string     |经度
latitude    |true   |string  	|纬度
opustime    |true   |string     |作品播放时间  单位s

#### 响应数据
```json
"data":{
	"opusId":2233 // 返回的作品id，若失败则opusId为0.
}
```


### 7.根据玩家id获取玩家作品列表 v02 GET  /json/v2/cdn/opus/get_player_opuslist/v02
#### 请求参数
参数        |必选   |类型    	|说明
---         |---    |---    	|---
opusType    |true   |int  		|获取周期类型: 0.原创作品 1.转发作品
pageSize    |true   |int        |获取条数,默认是20
page    	|true   |int    	|第几次,页码(1.开始)
playerId    |true   |int        |查看 其他人的作品列表

#### 响应数据
```json
"data":{
	"opusInfo":{
		[
			{
				"baseInfo":{
					"opusId":1,//作品id
					"songHash":"JLJHKIJ2562HJHHJHJ",//歌曲hash
					"opusName":"中国人",//作品名称
					"opusHash":"JLJHKIJ2562HJHHJHJ",//作品hash
					"createTime":124553655555,//上传时间
					"status:1,//1:正常 0：作者删除 -1：官方删除
					"descr":"很好听啊",//作品描述
					"player":{
						"playerId":323444,//玩家ID
						"nickname":"asqsummer",//用户昵称
						"sex":1,//性别
						"headimg ":"/sing_img/344333.jpg",//用户头像
						"isStar":1,//是否明星认证，0-否 1-是
						"isFx":0,  //是否繁星主播 0-否 1-是
						"showFxIcon":0,是否公开繁星主播认证 0-保密 1-公开
						"authExplain":"我是明星" //认证说明
					},//作品的玩家信息
					"opusParentId":9945515,//作品父id
					"type":1,//作品类型 1-上传  2-转发
					"songId":112434//伴奏id
				},
				"commentNum":2,//评论数
				"praiseNum":3,//点赞数
				"giftNum":4,//送礼人数
				"forwardNum":5,//转发数
				"listenNum":6//试听数
			}
		],[...]
	}//作品信息列表,
	"count":200//当请求的page为1时，返回作品列表总条数
}
```

### 8.根据玩家id获取自己的作品列表 POST  /json/v2/opus/get_my_opuslist
#### 请求参数
参数        |必选   |类型    	|说明
---         |---    |---    	|---
opusType    |true   |int  		|获取周期类型: 0.原创作品 1.转发作品
pageSize    |true   |int        |获取条数,默认是20
page    	|true   |int    	|第几次,页码(1.开始)
playerId    |true   |int        |查看 其他人的作品列表

#### 响应数据
```json
"data":{
	"opusInfo":{
		[
			{
				"baseInfo":{
					"opusId":1,//作品id
					"songHash":"JLJHKIJ2562HJHHJHJ",//歌曲hash
					"opusName":"中国人",//作品名称
					"opusHash":"JLJHKIJ2562HJHHJHJ",//作品hash
					"createTime":124553655555,//上传时间
					"status:1,//1:正常 0：作者删除 -1：官方删除
					"descr":"很好听啊",//作品描述
					"player":{
						"playerId":323444,//玩家ID
						"nickname":"asqsummer",//用户昵称
						"sex":1,//性别
						"headimg ":"/sing_img/344333.jpg",//用户头像
						"isStar":1,//是否明星认证，0-否 1-是
						"isFx":0,  //是否繁星主播 0-否 1-是
						"showFxIcon":0,是否公开繁星主播认证 0-保密 1-公开
						"authExplain":"我是明星" //认证说明
					},//作品的玩家信息
					"opusParentId":9945515,//作品父id
					"type":1,//作品类型 1-上传  2-转发
					"songId":112434//伴奏id
				},
				"commentNum":2,//评论数
				"praiseNum":3,//点赞数
				"giftNum":4,//送礼人数
				"forwardNum":5,//转发数
				"listenNum":6//试听数
			}
		],[...]
	}//作品信息列表,
	"count":200//当请求的page为1时，返回作品列表总条数
}
```

### 9.转发作品  POST  /json/v2/opus/forward
#### 请求参数
参数        |必选   |类型    	|说明
---         |---    |---    	|---
opusId      |true   |long  	    |作品ID
opusDesc    |true   |string     |作品描述

#### 响应数据
```json
"data":{
	//空 //返回成功或者失败
}
```

### 10.作品试听统计  POST  /json/v2/opus/recordListen
#### 请求参数
参数        |必选   |类型    	|说明
---         |---    |---    	|---
opusId      |true   |long  	    |作品ID
songHash    |true   |string     |伴奏hash
songId	    |true   |int     	|伴奏ID

#### 响应数据
```json
"data":{
	//空 //返回成功或者失败
}
```

### 11.删除作品  POST  /json/v2/opus/del_opus  
#### 请求参数
参数        |必选   |类型    	|说明
---         |---    |---    	|---
opusId      |true   |long  	    |作品ID

#### 响应数据
```json
"data":{
	//空 //返回成功或者失败
}
```

### 12.举报作品  POST  /json/v2/opus/report_opus 
#### 请求参数
参数        |必选   |类型    	|说明
---         |---    |---    	|---
opusId      |true   |long  	    |作品ID
contents    |true   |string  	|举报说明
reportType  |true   |int  	    |被举报类型
contact     |true   |string  	|联系方式

#### 响应数据
```json
"data":{
	//空 //返回成功或者失败
}
```

### 13.获取作品是否被删  POST  /json/v2/opus/getOpusStatus
#### 请求参数
参数        |必选   |类型    	|说明
---         |---    |---    	|---
opusId      |true   |long  	    |作品ID

#### 响应数据
```json
"data":{
	"isDel":1,//作品是否被删
	"contents":"xxx"//作品被删提示语
}
```

### 14.获取作品信息 v03  GET  /json/v2/cdn/opus/get_opusinfo/v03
#### 请求参数
参数        |必选   |类型    	|说明
---         |---    |---    	|---
opusId      |true   |long  	    |作品ID
fromType	|true	|int		|1.人气最高  2.附近  3.最新  0.默认

#### 响应数据
```json
"data":{
	"baseInfo":{
		"opusId":709394,//作品id
		"songHash":"JOUHU2626DDDF5555fffff",//歌曲hash
		"opusName":"中国人",//作品名称
		"opusHash":"JOUHU2626DDDF5555fffff",//作品hash
		"createTime":1409823246000,//上传时间
		"status":1,//1:正常 0：作者删除 -1：官方删除
		"descr":"好好听的",//作品描述
		"player":{
			"playerId":323444,//玩家ID
			"nickname":"asqsummer",//用户昵称
			"sex":1,//性别
			"headimg ":"/sing_img/344333.jpg",//用户头像
			"isStar":1,//是否明星认证，0-否 1-是
			"isFx":0,  //是否繁星主播 0-否 1-是
			"showFxIcon":0,是否公开繁星主播认证 0-保密 1-公开
			"authExplain":"我是明星" //认证说明
		},
		"opusParentId":95454545,//作品父id
		"type":1,  	//作品类型 1-上传  2-转发(默认是1)
		"songId:52959659,//伴奏id
	},//作品的基本信息 
	"commentNum":2,// 评论数
	"praiseNum":3,//点赞数
	"giftNum":4,//送礼人数
	"forwardNum":5,//转发数
	"listenNum":6,//试听数
	"playerinfo":{
		[
			{
				"playerId":323444,//玩家ID
				"nickname":"asqsummer",//用户昵称
				"sex":1,//性别
				"headimg ":"/sing_img/344333.jpg",//用户头像
				"isStar":1,//是否明星认证，0-否 1-是
				"isFx":0,  //是否繁星主播 0-否 1-是
				"showFxIcon":0,是否公开繁星主播认证 0-保密 1-公开
				"authExplain":"我是明星" //认证说明
			}
		],[...]
	}// 贵宾席
	"isPraise":true,//不用关心此值
	"isFocus":1//不用关心此值
}
```

### 15.获取用户是否对作品点赞及是否关注作者的信息   POST  /json/v2/opus/get_privateinfo
#### 请求参数
参数        |必选   |类型    	|说明
---         |---    |---    	|---
opusId      |true   |long  	    |作品ID
opusPlayerId|true	|int		|作品所属ID

#### 响应数据
```json
"data":{
	"isPraise":true,// 是否点赞
	"isFocus":2// 是否关注 0-未关注 1-已关注 2-互相关注  
}
```
#share库字典表
###gift (礼物表)
字段名|数据类型|是否为空|额外|注释
---|---|---|---|---
id|int(11)|否|auto_increment|礼物id
img|varchar(500)|否||礼物图片
name|varchar(100)|否||礼物名称
price|int(11)|否||礼物价格
property|int(11)|否||礼物属性
status|tinyint(4)|否||是否上架(0-否 1-是)
hot|int(11)|否||热度值
hot_duration|int(11)|否||热度值持续时间(单位：秒)
score|int(11)|否||排序得分

#####索引

索引名|是否唯一|索引类型|字段
---|---|---|---
PRIMARY|是|BTREE|id
property_2|是|BTREE|property<br>status<br>hot<br>hot_duration<br>score
property|否|BTREE|property
hot|否|BTREE|hot
status|否|BTREE|status<br>hot<br>hot_duration

<br><br><br>###gift_free (免费礼物)
字段名|数据类型|是否为空|额外|注释
---|---|---|---|---
player_id|int(11)|否||玩家id
gift_id|int(11)|否||礼物id
num|int(11)|否||当天已赠送数量
record_time|date|否||记录时间

#####索引

索引名|是否唯一|索引类型|字段
---|---|---|---
player_id|是|BTREE|player_id<br>gift_id<br>record_time

<br><br><br>###gift_property (礼物属性图片)
字段名|数据类型|是否为空|额外|注释
---|---|---|---|---
property_id|int(11)|否||属性id
img|varchar(500)|否||属性图片

#####索引

索引名|是否唯一|索引类型|字段
---|---|---|---
PRIMARY|是|BTREE|property_id

<br><br><br>###opus_gift (单用户给作品送礼的礼物列表)
字段名|数据类型|是否为空|额外|注释
---|---|---|---|---
opus_id|bigint(20)|否||作品id
player_id|int(11)|否||送礼人的玩家id
gift_id|int(11)|否||礼物id
num|int(11)|否||数量

#####索引

索引名|是否唯一|索引类型|字段
---|---|---|---
opus_id|是|BTREE|opus_id<br>player_id<br>gift_id

<br><br><br>###opus_gift_price (作品礼物价值排行榜)
字段名|数据类型|是否为空|额外|注释
---|---|---|---|---
opus_id|bigint(20)|否||作品id
player_id|int(11)|否||送礼人玩家id
price|int(11)|否||赠送总价值

#####索引

索引名|是否唯一|索引类型|字段
---|---|---|---
opus_id|是|BTREE|opus_id<br>player_id

<br><br><br>###player_consumed_record (消费流水表)
字段名|数据类型|是否为空|额外|注释
---|---|---|---|---
id|bigint(20)|否|auto_increment|流水id
player_id|int(11)|否||玩家id
order_id|bigint(20)|否||订单id
money|int(11)|否||消费金额
create_time|bigint(20)|否||创建时间
consumed_type|int(2)|否||消费类型：1:作品赠礼 2:赠送K币

#####索引

索引名|是否唯一|索引类型|字段
---|---|---|---
comsumed_id_idx|是|BTREE|id

<br><br><br>###player_money (玩家金钱)
字段名|数据类型|是否为空|额外|注释
---|---|---|---|---
player_id|int(11)|否||
total_kb|int(11)|是||k币总额
update_time|bigint(20)|否||最后修改时间

#####索引

索引名|是否唯一|索引类型|字段
---|---|---|---
PRIMARY|是|BTREE|player_id

<br><br><br>###player_money_change (玩家金钱变动表)
字段名|数据类型|是否为空|额外|注释
---|---|---|---|---
player_id|int(11)|否||主键
total|int(11)|是||K币变动数量
change_type|int(1)|是||K币变动类型:1:增加,2:减少
insert_time|bigint(20)|是||变动时间
consumed_record_id|bigint(20)|是||消费流水id
consumed_type|int(2)|是||消费类型：1:作品赠礼 2:赠送K币

#####索引

索引名|是否唯一|索引类型|字段
---|---|---|---
PRIMARY|是|BTREE|player_id

<br><br><br>###player_order (订单表)
字段名|数据类型|是否为空|额外|注释
---|---|---|---|---
id|bigint(20)|否|auto_increment|订单id
player_id|int(11)|否||玩家id
goods_id|int(11)|是||物品id
goods_num|int(11)|是||物品数量
money|int(11)|是||物品金额
sender_id|int(11)|否||发送玩家id
order_type|tinyint(1)|是||订单类型  1:作品赠礼 
status|tinyint(1)|否||订单状态 0:未产生流水 1:产生流水
create_time|bigint(20)|否||创建时间
modify_time|bigint(20)|是||修改时间

#####索引

索引名|是否唯一|索引类型|字段
---|---|---|---
order_id_idx|是|BTREE|id

<br><br><br>
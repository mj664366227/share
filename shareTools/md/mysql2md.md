#share库字典表
###gift (礼物表)
字段名|数据类型|是否为空|额外|注释
---|---|---|---|---
id|int(11)|否|auto_increment|礼物idsdhkjh
img|varchar(500)|否||礼物图片sdhkjh
name|varchar(100)|否||礼物名称sdhkjh
price|int(11)|否||礼物价格sdhkjh
property|int(11)|否||礼物属性sdhkjh
status|tinyint(4)|否||是否上架(0-否 1-是)sdhkjh
hot|int(11)|否||热度值sdhkjh
hot_duration|int(11)|否||热度值持续时间(单位：秒)sdhkjh
score|int(11)|否||排序得分sdhkjh


###gift_free (免费礼物)
字段名|数据类型|是否为空|额外|注释
---|---|---|---|---
player_id|int(11)|否||玩家idsdhkjh
gift_id|int(11)|否||礼物idsdhkjh
num|int(11)|否||当天已赠送数量sdhkjh
record_time|date|否||记录时间sdhkjh


###gift_property (礼物属性图片)
字段名|数据类型|是否为空|额外|注释
---|---|---|---|---
property_id|int(11)|否||属性idsdhkjh
img|varchar(500)|否||属性图片sdhkjh


###opus_gift (单用户给作品送礼的礼物列表)
字段名|数据类型|是否为空|额外|注释
---|---|---|---|---
opus_id|bigint(20)|否||作品idsdhkjh
player_id|int(11)|否||送礼人的玩家idsdhkjh
gift_id|int(11)|否||礼物idsdhkjh
num|int(11)|否||数量sdhkjh


###opus_gift_price (作品礼物价值排行榜)
字段名|数据类型|是否为空|额外|注释
---|---|---|---|---
opus_id|bigint(20)|否||作品idsdhkjh
player_id|int(11)|否||送礼人玩家idsdhkjh
price|int(11)|否||赠送总价值sdhkjh


###player_consumed_record (消费流水表)
字段名|数据类型|是否为空|额外|注释
---|---|---|---|---
id|bigint(20)|否|auto_increment|流水idsdhkjh
player_id|int(11)|否||玩家idsdhkjh
order_id|bigint(20)|否||订单idsdhkjh
money|int(11)|否||消费金额sdhkjh
create_time|bigint(20)|否||创建时间sdhkjh
consumed_type|int(2)|否||消费类型：1:作品赠礼 2:赠送K币sdhkjh


###player_money (玩家金钱)
字段名|数据类型|是否为空|额外|注释
---|---|---|---|---
player_id|int(11)|否||sdhkjh
total_kb|int(11)|是||k币总额sdhkjh
update_time|bigint(20)|否||最后修改时间sdhkjh


###player_money_change (玩家金钱变动表)
字段名|数据类型|是否为空|额外|注释
---|---|---|---|---
player_id|int(11)|否||主键sdhkjh
total|int(11)|是||K币变动数量sdhkjh
change_type|int(1)|是||K币变动类型:1:增加,2:减少sdhkjh
insert_time|bigint(20)|是||变动时间sdhkjh
consumed_record_id|bigint(20)|是||消费流水idsdhkjh
consumed_type|int(2)|是||消费类型：1:作品赠礼 2:赠送K币sdhkjh


###player_order (订单表)
字段名|数据类型|是否为空|额外|注释
---|---|---|---|---
id|bigint(20)|否|auto_increment|订单idsdhkjh
player_id|int(11)|否||玩家idsdhkjh
goods_id|int(11)|是||物品idsdhkjh
goods_num|int(11)|是||物品数量sdhkjh
money|int(11)|是||物品金额sdhkjh
sender_id|int(11)|否||发送玩家idsdhkjh
order_type|tinyint(1)|是||订单类型  1:作品赠礼 sdhkjh
status|tinyint(1)|否||订单状态 0:未产生流水 1:产生流水sdhkjh
create_time|bigint(20)|否||创建时间sdhkjh
modify_time|bigint(20)|是||修改时间sdhkjh


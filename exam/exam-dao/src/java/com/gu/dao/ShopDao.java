package com.gu.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gu.core.util.Time;
import com.gu.dao.db.GuConfigDbService;
import com.gu.dao.db.GuMasterDbService;
import com.gu.dao.db.GuSlaveDbService;
import com.gu.dao.model.DShopGoods;

/**
 * 商城dao类
 * @author luo
 */
@Component
public class ShopDao {
	@Autowired
	private GuMasterDbService guMasterDbService;
	@Autowired
	private GuSlaveDbService guSlaveDbService;
	@Autowired
	private GuConfigDbService guConfigbService;

	/**
	 * 获取商品类型配置
	 */
	public List<Map<String, Object>> getShopGoodsTypeConfig() {
		String sql = "SELECT * FROM  `shop_goods_type` order by id desc";
		return guConfigbService.queryList(sql);
	}

	/**
	 * 新增商品类型设置
	 * @param type 类型名字
	 */
	public void addShopGoodsTypeConfig(String type) {
		String sql = "INSERT INTO `shop_goods_type`(`name`) VALUES (?)";
		guConfigbService.update(sql, type);
	}

	/**
	 * 判断商品类型是否重复
	 * @param type 类型名字
	 */
	public boolean existsShopGoodsTypeConfig(String type) {
		String sql = "select `name` from `shop_goods_type` where `name`=?";
		return !guConfigbService.queryList(sql, type).isEmpty();
	}

	/**
	 * 删除商品类型设置
	 * @param id 类型id
	 */
	public void deleteShopGoodsTypeConfig(int id) {
		String sql = "DELETE FROM `shop_goods_type` WHERE `id`=?";
		guConfigbService.update(sql, id);
	}

	/**
	 * 添加积分兑换商品
	 * @param name 商品名称
	 * @param introduce 商品介绍
	 * @param score 兑换积分
	 * @param num 数量
	 * @param type 分类
	 * @param state 是的上架(0=否,1=是)
	 * @param companyId 企业id
	 * @param image1 图片1
	 * @return
	 */
	public DShopGoods addShopGoods(String name, String introduce, int score, int num, String type, String state, long companyId, String image1) {
		DShopGoods shopGoods = new DShopGoods();
		shopGoods.setName(name);
		shopGoods.setIntroduce(introduce);
		shopGoods.setScore(score);
		shopGoods.setNum(num);
		shopGoods.setType(type);
		shopGoods.setState(0);
		shopGoods.setCompanyId(companyId);
		shopGoods.setCreateTime(Time.now());
		shopGoods.setImage1(image1);
		return guMasterDbService.save(shopGoods);
	}

	/**
	 * 更新商品
	 * @param shopGoods 商品
	 * @return
	 */
	public boolean updateShopGoods(DShopGoods shopGoods) {
		return guMasterDbService.update(shopGoods);
	}

	/**
	 * 查询商品
	 * @param shopGoodsId 商品id
	 * @return
	 */
	public DShopGoods getShopGoodsById(long shopGoodsId) {
		String sql = "SELECT * FROM `shop_goods` WHERE `id`=?";
		return guSlaveDbService.queryT(sql, DShopGoods.class, shopGoodsId);
	}

	/**
	 * 批量获取商品
	 * @param caseIdSet 商品id集合
	 */
	public List<DShopGoods> getCase(Set<Long> shopGoodsIdSet) {
		return guSlaveDbService.multiGetT(shopGoodsIdSet, DShopGoods.class);
	}
}

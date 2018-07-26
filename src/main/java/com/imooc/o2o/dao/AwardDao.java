package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Award;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: REX
 * @Date: Create in 14:37 2018/7/24
 */
public interface AwardDao {
	/**
	 * 根据传入的查询条件，分页显示奖品信息列表
	 * @param awardCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<Award> queryAwardList(@Param("awardCondition") Award awardCondition,
							   @Param("rowIndex")Integer rowIndex,@Param("pageSize")Integer pageSize);

	/**
	 * 配合queryAwardList，返回系统查询条件下的奖品数目
	 * @param awardCondition
	 * @return
	 */
	int queryAwardCount(@Param("awardCondition")Award awardCondition);

	/**
	 * 通过ID查询奖品信息
	 * @param awardId
	 * @return
	 */
	Award queryAwardByAwardId(Long awardId);

	int insertAward(Award award);
	int updateAward(Award award);
	int deleteAward(@Param("awardId")Long awardId,@Param("shopId")Long shopId);

}

package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Area;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * @Author:REX
 * @Date: Create in 10:31 2018/3/17
 */
public interface AreaDao {
	/**
	 * 列出区域列表
	 * @return areaList
	 */
	List<Area> queryArea();
}

package com.zeroq6.spring_boot.dao;

import com.zeroq6.spring_boot.base.BaseDao;
import com.zeroq6.spring_boot.domain.DictDomain;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author icgeass@hotmail.com
 * @date 2017-11-10
 */
@Mapper
public interface DictDao extends BaseDao<DictDomain, Long> {


}

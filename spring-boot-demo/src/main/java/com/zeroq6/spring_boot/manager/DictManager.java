package com.zeroq6.spring_boot.manager;

import com.zeroq6.spring_boot.base.BaseDao;
import com.zeroq6.spring_boot.base.BaseManager;
import com.zeroq6.spring_boot.dao.DictDao;
import com.zeroq6.spring_boot.domain.DictDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author icgeass@hotmail.com
 * @date 2017-07-08
 */
@Service
public class DictManager extends BaseManager<DictDomain, Long>{

    @Autowired
    private DictDao dictDao;


    @Override
    public BaseDao<DictDomain, Long> getDao() {
        return dictDao;
    }



}

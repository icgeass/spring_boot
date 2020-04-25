package com.zeroq6.spring_boot.service;

import com.zeroq6.spring_boot.base.BaseManager;
import com.zeroq6.spring_boot.base.BaseService;
import com.zeroq6.spring_boot.domain.DictDomain;
import com.zeroq6.spring_boot.manager.DictManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author icgeass@hotmail.com
 * @date 2017-05-17
 */
@Service
public class DictService extends BaseService<DictDomain, Long> {

    @Autowired
    private DictManager dictManager;


    @Override
    public BaseManager<DictDomain, Long> getManager() {
        return dictManager;
    }


}

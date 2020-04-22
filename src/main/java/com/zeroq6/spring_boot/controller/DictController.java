package com.zeroq6.spring_boot.controller;

import com.zeroq6.spring_boot.base.BaseController;
import com.zeroq6.spring_boot.base.BaseService;
import com.zeroq6.spring_boot.base.Page;
import com.zeroq6.spring_boot.domain.DictDomain;
import com.zeroq6.spring_boot.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dict")
public class DictController extends BaseController<DictDomain, Long> {
    @Autowired
    private DictService dictService;

    @Override
    public BaseService<DictDomain, Long> getService() {
        return dictService;
    }

    @Override
    public String getModelName() {
        return "dict";
    }

    @Override
    public String getExportCsvTitle() {
        return "null";
    }

    @Override
    public String getExportCsvRow(DictDomain dictDomain) {
        return "null";
    }


    /**
     * 列表展示
     *
     * @param dictDomain 实体对象
     * @param page                分页对象
     * @return
     */
    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String list(DictDomain dictDomain, Page<DictDomain> page, Model view) throws Exception {
        try {
            return baseList(dictDomain, page, view);
        } catch (Exception e) {
            logger.error("获取列表异常, " + dictDomain.getClass().getSimpleName(), e);
            throw e;
        }
    }

    /**
     * 响应新增(修改)页面
     *
     * @param id 对象编号
     * @return
     */
    @RequestMapping(value = "/edit/{id}")
    public String edit(@PathVariable Long id, Model view) throws Exception {
        try {
            return baseEdit(id, view);
        } catch (Exception e) {
            logger.error("编辑异常, " + id, e);
            throw e;
        }
    }

    /**
     * 通过编号删除对象
     *
     * @param id 对象编号
     * @return
     */
    @RequestMapping(value = "/delete/{id}")
    public String delete(@PathVariable Long id, Model view) throws Exception {
        try {
            return baseDelete(id, view);
        } catch (Exception e) {
            logger.error("删除异常, " + id, e);
            throw e;
        }
    }

    /**
     * 通过编号查看对象
     *
     * @param id 对象编号
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String view(@PathVariable Long id, Model view) throws Exception {
        try {
            return baseView(id, view);
        } catch (Exception e) {
            logger.error("查看异常, " + id, e);
            throw e;
        }
    }

    /**
     * 保存方法
     *
     * @param dictDomain 实体对象
     * @return
     */
    @RequestMapping(value = "/save", method = {RequestMethod.POST, RequestMethod.GET})
    public String save(DictDomain dictDomain, Model view) throws Exception {
        try {
            if(null == dictDomain.getId() || dictDomain.getId() <= 0){
                return baseSave(dictDomain, view);
            }else{
                return baseUpdate(dictDomain, view);
            }
        } catch (Exception e) {
            logger.error("保存异常, " + dictDomain.getClass().getSimpleName() + ", " + dictDomain.getId(), e);
            throw e;
        }
    }
}

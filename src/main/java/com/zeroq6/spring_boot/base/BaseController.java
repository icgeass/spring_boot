package com.zeroq6.spring_boot.base;

import com.alibaba.fastjson.JSON;
import com.zeroq6.spring_boot.util.web.CustomDateEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BaseController<T extends BaseDomain, KEY extends Serializable> {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected String baseDir = "modules";

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(true));
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }


    protected void outJson(HttpServletResponse response, Object object) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Cache-Control", "no-store, max-age=0, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        String json = object instanceof String ? String.valueOf(object) : JSON.toJSONString(object);
        try {
            PrintWriter out = response.getWriter();
            out.print(json);
            out.close();
        } catch (IOException e) {
            logger.error("向页面输出json发生错误, " + json, e);
        }
    }

    /**
     * 获取Service操作类
     */
    public abstract BaseService<T, KEY> getService();

    /**
     * 获取视图基本路径 t
     */
    public abstract String getModelName();


    /**
     * 获取视图路径 t/t
     */
    public String getViewPathPrefix() {
        return baseDir + "/" + getModelName() + "/" + getModelName();
    }

    /**
     * 获取导出csv的表头
     *
     * @return
     */
    public abstract String getExportCsvTitle();

    /**
     * 获取导出csv的数据明细
     *
     * @param t
     * @return
     */
    public abstract String getExportCsvRow(T t);

    /**
     * 列表展示
     *
     * @param t    实体对象
     * @param page 分页对象
     * @return
     */
    public String baseList(T t, Page<T> page, Model view) throws Exception {
        view.addAttribute(getModelName(), t);
        view.addAttribute("page", getService().selectPage(t, page));
        return getViewPathPrefix() + "List";
    }

    /**
     * 响应新增(修改)页面
     *
     * @param key 对象编号
     * @return
     */
    public String baseEdit(KEY key, Model view) throws Exception {
        T t = getService().selectByKey(key);
        view.addAttribute(getModelName(), t);
        return getViewPathPrefix() + "Edit";
    }

    /**
     * 通过编号查看对象
     *
     * @param key 对象编号
     * @return
     */
    public String baseView(KEY key, Model view) throws Exception {
        T t = getService().selectByKey(key);
        view.addAttribute(getModelName(), t);
        return getViewPathPrefix() + "View";
    }

    /**
     * 通过编号删除对象
     *
     * @param key 对象编号
     * @return
     */
    public String baseDelete(KEY key, Model view) throws Exception {
        getService().disableByKey(key);
        return "redirect:/" + getModelName();
    }

    /**
     * 新增方法
     *
     * @param t 新增对象
     * @return
     */
    public String baseSave(T t, Model view) throws Exception {
        getService().insert(t);
        return "redirect:/" + getModelName();
    }

    /**
     * 更新方法
     *
     * @return
     * @throws Exception
     */
    public String baseUpdate(T t, Model view) throws Exception {
        getService().updateByKey(t);
        return "redirect:/" + getModelName();
    }


    /**
     * 导出CSV
     *
     * @param t
     * @return
     * @throws Exception
     */
    public void baseExportCsv(T t, Page<T> page, HttpServletResponse response) throws Exception {
        String csvFileName = getModelName() + "_" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".csv";

        response.setHeader("Content-type", "text/csv;charset=gbk");
        response.setCharacterEncoding("gbk");

        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", csvFileName));
        PrintWriter writer = null;
        boolean begin = false;
        try {
            writer = response.getWriter();
            writer.write(getExportCsvTitle());
            writer.write("\r\n");
            while (!begin || page.getCurrentPage() <= page.getPageCount()) {
                begin = true;
                long t3 = System.currentTimeMillis();
                getService().selectPage(t, page);
                for (T result : page.getData()) {
                    writer.write(getExportCsvRow(result));
                }
                writer.flush();
                long t4 = System.currentTimeMillis();
                logger.info(String.format("%s 导出csv中, 第%s页, 耗时%s毫秒", getModelName(), page.getCurrentPage(), t4 - t3));
                page.setCurrentPage(page.getCurrentPage() + 1);
            }
            writer.flush();
            writer.close();
            logger.info(String.format("%s 导出csv完成", getModelName()));
        } catch (Exception e) {
            logger.error("导出CSV列表失败: ", e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

}
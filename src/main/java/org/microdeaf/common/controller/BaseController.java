package org.microdeaf.common.controller;

import org.microdeaf.hbm.model.BaseEntity;
import org.microdeaf.common.service.IGenericService;
import org.microdeaf.common.utility.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

public abstract class BaseController<T extends BaseEntity, PK extends Serializable> {

    @Autowired
    public abstract IGenericService getService();

    /*@RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public T save(@RequestBody T entity) {
        return (T) getService().save(entity);
    }*/

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public T update(@RequestBody T entity) {
        return (T) getService().update(entity);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public void delete(@PathVariable("id") PK id) {
        getService().delete(id);
    }

    @RequestMapping(value = "/find/{id}", method = RequestMethod.GET)
    @ResponseBody
    public T find(@PathVariable("id") PK id) {
        return (T) getService().findOne(id);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @ResponseBody
    public List<T> findAll() {
        return getService().findAll();
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
//    @ApiImplicitParams({ @ApiImplicitParam(name = "searchFilter", paramType = "query"),
//            @ApiImplicitParam(name = "pageNumber", paramType = "query"),
//            @ApiImplicitParam(name = "pageSize", paramType = "query") })
    public PageResult<T> findAll(@RequestParam String filter, @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return getService().findAll(filter, pageNumber, pageSize);
    }

}

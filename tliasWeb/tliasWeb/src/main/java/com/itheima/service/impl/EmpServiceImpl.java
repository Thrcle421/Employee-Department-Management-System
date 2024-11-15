package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.Emp;
import com.itheima.pojo.PageBean;
import com.itheima.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;


//    @Override
//    public PageBean getByPage(Integer page, Integer pagesize) {
//        Long Total=empMapper.count();
//        Integer start=(page-1)*pagesize;
//        List<Emp> emps=empMapper.getByPage(start,pagesize);
//        PageBean pageBean=new PageBean();
//        pageBean.setTotal(Total);
//        pageBean.setRows(emps);
//        return pageBean;
//    }

    @Override
    public PageBean getByPage(Integer page, Integer pagesize,
                              String name, Short gender, LocalDate start, LocalDate end) {
        PageHelper.startPage(page, pagesize);
        List<Emp> empList=empMapper.list(name,gender,start,end);
        Page<Emp> p=(Page<Emp>) empList;
        PageBean pageBean=new PageBean(p.getTotal(),p.getResult());
        return pageBean;
    }

    @Override
    public void delete(List<Integer> ids) {
        empMapper.delete(ids);
    }

    @Override
    public void add(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.add(emp);
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {
        return empMapper.getByusernameandPassword(emp);
    }


}

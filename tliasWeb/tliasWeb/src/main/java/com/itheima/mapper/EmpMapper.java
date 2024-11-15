package com.itheima.mapper;

import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EmpMapper {

//    @Select("select count(*) from emp")
//    Long count();
//
//    @Select("select * from emp limit #{start},#{pagesize}")
//    List<Emp> getByPage(Integer start,Integer pagesize);

//    @Select("select * from emp where name like concat('%',#{name},'%') and gender = #{gender} " +
//            "and entrydate between #{begin} and #{end} order by update_time desc")
    public List<Emp> list(String name, Short gender, LocalDate begin, LocalDate end);

//    @Delete("delete from emp where id=#{id}")
    public void delete(List<Integer> ids);

    @Insert("insert into mybatis.emp(username,name,gender,image,dept_id,entrydate,job,update_time,create_time) " +
            "values(#{username},#{name},#{gender},#{image},#{deptId},#{entrydate},#{job},#{updateTime},#{createTime})")
    void add(Emp emp);

    @Select("select * from emp where id=#{id}")
    Emp getById(Integer id);

//    @Update("update emp set name = #{name}, username=#{username}, " +
//            "gender=#{gender}, image=#{image}, dept_id=#{deptId}, entrydate=#{entrydate}, job=#{job}," +
//            "update_time = #{updateTime} where id = #{id}")
    void update(Emp emp);

    @Select("select * from emp where username=#{username} and password=#{password}")
    Emp getByusernameandPassword(Emp emp);

    @Delete("delete from emp where dept_id=#{deptId}")
    void deleteByDeptId(Integer deptId);
}

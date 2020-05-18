package com.lagou.sqlSession;

import com.lagou.pojo.Configuration;
import com.lagou.pojo.MappedStatement;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class DefaultSqlSession implements SqlSession {
    private Configuration configuration;


    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statementid, Object... params) throws SQLException, IllegalAccessException, IntrospectionException, InstantiationException, ClassNotFoundException, InvocationTargetException, NoSuchFieldException {
        List<Object> objects = selectList(statementid,params);
        System.out.println("size:"+objects.size());
        if(objects.size()==1){
            return (T) objects.get(0);
        }else {
            throw  new RuntimeException("查询结果为空或者查询结果过多！");
        }

    }

    //真正的查询方法，提取到Excetor类中实现
    @Override
    public <E> List<E> selectList(String statementid, Object... params) throws SQLException, IllegalAccessException, IntrospectionException, InstantiationException, NoSuchFieldException, InvocationTargetException, ClassNotFoundException {
        //将要完成的操作提取的Executor现实类SimpleExecutor中的query方法中实现
        Executor executor = new SimpleExecutor();
        MappedStatement mappedStatement = configuration.getMappedStatementMap().get(statementid);
        System.out.println("statementid:"+statementid);
        System.out.println("mappedStatement:"+mappedStatement);
        return executor.query(configuration,mappedStatement,params);
    }
}

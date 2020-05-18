package com.lagou;

import com.lagou.io.Resources;
import com.lagou.sqlSession.SqlSession;
import com.lagou.sqlSession.SqlSessionFactory;
import com.lagou.sqlSession.SqlSessionFactoryBuilder;
import com.pojo.Animal;
import org.dom4j.DocumentException;

import java.beans.IntrospectionException;
import java.beans.PropertyVetoException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws DocumentException, PropertyVetoException, IllegalAccessException, IntrospectionException, InstantiationException, NoSuchFieldException, SQLException, InvocationTargetException, ClassNotFoundException {
        //1.使用端，步骤一：导入自定义持久层框架
        //2.使用其API实现对配置文件的加载
        InputStream resourceAsStream = Resources.getResourceAsStream("SqlMapConfig.xml");
        System.out.println(resourceAsStream);
        //3.获取会话工厂对象sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        //获取会话对象
        SqlSession session = sqlSessionFactory.openSession();


        //查询
        Animal queryAnimal = new Animal();
        queryAnimal.setName("zhangan1");
        queryAnimal.setId(1);
        Animal reusltAnimal = session.selectOne("animal.selectOne",queryAnimal);
        System.out.println("over:"+reusltAnimal);

        System.out.println("------------------------------");
        List<Animal> as = session.selectList("animal.selectList");
        for (Animal a : as) {
            System.out.println(a);
        }
    }
}

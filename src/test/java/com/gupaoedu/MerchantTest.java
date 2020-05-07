package com.gupaoedu;

import com.gupaoedu.domain.Blog;
import com.gupaoedu.domain.Merchant;
import com.gupaoedu.domain.associate.AuthorAndBlog;
import com.gupaoedu.domain.associate.BlogAndAuthor;
import com.gupaoedu.domain.associate.BlogAndComment;
import com.gupaoedu.mapper.BlogMapper;
import com.gupaoedu.mapper.BlogMapperExt;
import com.gupaoedu.mapper.MerchantMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tony Yan
 * MyBatis Maven 作业演示工程
 */
public class MerchantTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void prepare() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 通过 SqlSession.getMapper(XXXMapper.class)  接口方式
     * @throws IOException
     */
    @Test
    public void testInsert() throws IOException {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            MerchantMapper mapper = session.getMapper(MerchantMapper.class);
            Merchant merchant = new Merchant();
            merchant.setMerchantId(1008);
            merchant.setMerchantName("天下第一家");
            List<Integer> scope= new ArrayList<Integer>();
            scope.add(3);
            scope.add(2);
            scope.add(1);
            scope.add(4);
            merchant.setScope(scope);
            mapper.insert(merchant);
            session.commit();
        } finally {
            session.close();
        }
    }


    /**
     * 通过 SqlSession.getMapper(XXXMapper.class)  接口方式
     * test select
     * @throws IOException
     */
    @Test
    public void testSelect() throws IOException {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            MerchantMapper mapper = session.getMapper(MerchantMapper.class);
            Merchant merchant = mapper.selectByPrimaryKey(1008);
            System.out.println("name->"+merchant.getMerchantName());
            System.out.println("scope->"+merchant.getScope());
        } finally {
            session.close();
        }
    }
}

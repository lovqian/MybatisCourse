package com.gupaoedu;

import com.gupaoedu.domain.Merchant;
import com.gupaoedu.domain.associate.FeeDate;
import com.gupaoedu.mapper.FeeDateMapper;
import com.gupaoedu.mapper.MerchantMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: tony Yan
 * MyBatis Maven 作业演示工程
 */
public class FeeDateTest {

    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void prepare() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    /**
     * 通过 SqlSession.getMapper(XXXMapper.class)  接口方式
     * test select
     * @throws IOException
     */
    @Test
    public void testSelectByDate() throws IOException {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            FeeDateMapper mapper = session.getMapper(FeeDateMapper.class);
            DateFormat format =  new SimpleDateFormat("yyyy-MM-dd");


            FeeDate feeDate = new FeeDate(1,format.parse("2020-02-21"));

            List<FeeDate> feeDateList1 = mapper.selectByDate(format.parse("2020-02-21"));

           // List<FeeDate> feeDateList = mapper.selectByDateOrId(feeDate);
           // List<FeeDate> feeDateList = mapper.selectByDate(format.parse("2020-02-21"));
            System.out.println(feeDateList1);
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}

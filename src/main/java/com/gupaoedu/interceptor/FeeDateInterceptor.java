package com.gupaoedu.interceptor;

import com.gupaoedu.domain.associate.FeeDate;
import org.apache.ibatis.builder.StaticSqlSource;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Intercepts({@Signature(type = Executor.class,method = "query",args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class FeeDateInterceptor implements Interceptor {

    public static final String FEE_DATE_FILED = "feeDate";
    public static final String FEE_DATE_TABLE = "fee_date";
    public static final String FEE_DATE_REGEX = "from\\s*fee_date";
    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        BoundSql boundSql = ms.getBoundSql(args[1]);
        //获取执行的sql
        String sql = boundSql.getSql();
        //获取参数
        Object obj = boundSql.getParameterObject();
        //获取执行参数
        List<ParameterMapping> params = boundSql.getParameterMappings();
        //如果不需要查询fee_date表，直接返回
        if(!sql.contains(FEE_DATE_TABLE)){
            return invocation.proceed();
        }
        //获取需要查询的表名并替换(这里防止表名和字段名重复，用正则匹配)
        sql = sql.replaceAll(FEE_DATE_REGEX,"from "+getDetailTableName(obj));
        // 自定义sqlSource
        SqlSource sqlSource = new StaticSqlSource(ms.getConfiguration(), sql, boundSql.getParameterMappings());
        // 修改原来的sqlSource
        Field field = MappedStatement.class.getDeclaredField("sqlSource");
        field.setAccessible(true);
        field.set(ms, sqlSource);
        return invocation.proceed();
    }

    /**
     * 根据传入参数拼接分表的表名，返回分表表名
     * @param obj
     * @return
     */
    private String getDetailTableName(Object obj){
        //给一个默认的分表，防止查询报table not exist异常
        String feeDateTable = "fee_date_202001";
        DateFormat format = new SimpleDateFormat("yyyyMM");
        if(obj instanceof FeeDate){
            FeeDate feeDate = (FeeDate)obj;
            if(feeDate.getFeeDate()!=null){
                feeDateTable = "fee_date_"+format.format(feeDate.getFeeDate());
            }
        }else if(obj instanceof Map){
            Map map = (Map)obj;
            if(map.containsKey(FeeDateInterceptor.FEE_DATE_FILED)){
                feeDateTable = "fee_date_"+format.format(map.get(FeeDateInterceptor.FEE_DATE_FILED));
            }
        }else if(obj instanceof Date){
            feeDateTable = "fee_date_"+format.format(obj);
        }
        return  feeDateTable;
    }


    @Override
    public Object plugin(Object target) {
       return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

}

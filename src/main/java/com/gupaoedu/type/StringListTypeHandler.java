package com.gupaoedu.type;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: tony Yan
 *
 */
public class StringListTypeHandler extends BaseTypeHandler<List<Integer>> {
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType)
            throws SQLException {

    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Integer> parameter, JdbcType jdbcType) throws SQLException {
        // 设置 String 类型的参数的时候调用，Java类型到JDBC类型
        // 注意只有在字段上添加typeHandler属性才会生效
        ps.setString(i, StringListTypeHandler.listToString(parameter));
    }

    public List<Integer> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        // 根据列名获取 String 类型的参数的时候调用，JDBC类型到java类型
        // 注意只有在字段上添加typeHandler属性才会生效
        System.out.println("---------------getNullableResult1："+columnName);
        return StringListTypeHandler.stringToList(rs.getString(columnName));
    }

    public List<Integer> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        // 根据下标获取 String 类型的参数的时候调用
        System.out.println("---------------getNullableResult2："+columnIndex);
        return StringListTypeHandler.stringToList(rs.getString(columnIndex));
    }

    public List<Integer> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        System.out.println("---------------getNullableResult3：");
        return StringListTypeHandler.stringToList(cs.getString(columnIndex));
    }

    /**
     * list covert to String , element connected by comma
     * @param li
     * @return
     */
    public static String listToString(List li){
        if(li==null||li.size()==0){
            return "";
        }
        Collections.sort(li);
        StringBuffer res = new StringBuffer();
        for(Iterator<Integer> itor = li.iterator();itor.hasNext();res.append(itor.next().toString())){
            if (res.length() != 0) {
                res.append(",");
            }
        }
        return res.toString();
    }

    /**
     * string convert to list,split by comma
     * @param str
     * @return
     */
    public static List<Integer> stringToList(String str){
        if(str==null||str.length()==0){
            return null;
        }
        String[] strArr = str.split(",");
        List<Integer> res = new ArrayList<>();
        for(int i=0;i<strArr.length;i++){
            res.add(Integer.parseInt(strArr[i]));
        }
        return res;
    }

    public static void main(String[] args) {
        List<Integer> li= new ArrayList<Integer>();
        li.add(3);
        li.add(2);
        li.add(1);
        li.add(4);

        System.out.println(listToString(li));
    }
}
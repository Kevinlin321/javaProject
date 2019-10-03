package cn.yongjie.dao;


import cn.yongjie.pojo.Formula;

import java.sql.Connection;
import java.util.ArrayList;

public interface FormulaDao {

    /**
     *
     * @param connection
     * @param formula
     */
    void insertSingleObjectIntoFormulaTable(Connection connection, Formula formula);

    ArrayList<Integer> getFormulaParamByFormulaName(Connection connection, String name);
}


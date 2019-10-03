package cn.yongjie.dao.impl;

import cn.yongjie.dao.FormulaDao;
import cn.yongjie.pojo.Formula;
import cn.yongjie.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class FormulaDaoImpl implements FormulaDao{

    public void insertSingleObjectIntoFormulaTable(Connection connection, Formula formula) {

        //需判断是否已经存在 ON DUPLICATE KEY UPDATE
        String sqlText = "INSERT INTO" +
                "    tb_formula_test(name, type, unit, a, b, c, d, formulaUnfold, reversedCol01, reversedCol02, reversedCol03) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) " +
                "ON DUPLICATE KEY UPDATE " +
                "    type=?, unit=?, a=?, b=?, c=?, d=?, formulaUnfold=?, reversedCol01=?, reversedCol02=?, reversedCol03=?";
        ArrayList<Object> arrayList = new ArrayList<Object>();
        arrayList.add(formula.getName());
        for (int i = 0; i < 2; i++) {
           arrayList.add(formula.getType());
           arrayList.add(formula.getUnit());
           arrayList.add(formula.getA());
           arrayList.add(formula.getB());
           arrayList.add(formula.getC());
           arrayList.add(formula.getD());
           arrayList.add(formula.getFormulaUnfold());
           arrayList.add(formula.getReversedCol01());
           arrayList.add(formula.getReversedCol02());
           arrayList.add(formula.getReversedCol03());
        }

        try {
            JDBCUtils.getInstance().execute(connection, sqlText, arrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> getFormulaParamByFormulaName(Connection connection, String name) {

        String querySQL = "SELECT a, b, c, d FROM tb_formula_test WHERE name=?";
        ArrayList<Object> arrayList = new ArrayList<Object>();
        arrayList.add(name);

        ArrayList<Integer> paramList = new ArrayList<Integer>();

        ResultSet resultSet = JDBCUtils.getInstance().executeQuery(connection, querySQL, arrayList);
        try {
            while (resultSet.next()) {
                paramList.add(resultSet.getInt("a"));
                paramList.add(resultSet.getInt("b"));
                paramList.add(resultSet.getInt("c"));
                paramList.add(resultSet.getInt("d"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return paramList;
    }
}

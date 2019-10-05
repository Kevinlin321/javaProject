package cn.yongjie.dao.impl;

import cn.yongjie.dao.VariableDao;
import cn.yongjie.pojo.Varibale;
import cn.yongjie.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VariableDaoImpl implements VariableDao {

    public void insertSingleObjectIntoVariableTable(Connection connection, Varibale varibale) {

        String sqlText = "INSERT INTO " +
                "    tb_variable_test(shortName, belongModule, category, swAddrMethonRef, swBaseTypeRef, swCalibritionAcess," +
                "     swCodeSyntaxRef, swCompuMethodRef, swDataConstrRef, swImplPolicy, swVariableAccessImplPolicy, " +
                "     description, minHexValue, maxHexValue, minPhyValue, maxPhyValue, mimiAccuracy, swSyscond, " +
                "     reservedCol01, reservedCol02, reservedCol03)" +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)" +
                "ON DUPLICATE KEY UPDATE " +
                "    belongModule=?, category=?, swAddrMethonRef=?, swBaseTypeRef=?, swCalibritionAcess=?," +
                "     swCodeSyntaxRef=?, swCompuMethodRef=?, swDataConstrRef=?, swImplPolicy=?, swVariableAccessImplPolicy=?, " +
                "     description=?, minHexValue=?, maxHexValue=?, minPhyValue=?, maxPhyValue=?, mimiAccuracy=?, swSyscond=?, " +
                "     reservedCol01=?, reservedCol02=?, reservedCol03=?";

        ArrayList<Object> arrayList = new ArrayList<Object>();
        arrayList.add(varibale.getShortName());
        for (int i = 0; i < 2; i++) {
            arrayList.add(varibale.getBelongModule());
            arrayList.add(varibale.getCategory());
            arrayList.add(varibale.getSwAddrMethonRef());
            arrayList.add(varibale.getSwBaseTypeRef());
            arrayList.add(varibale.getSwCalibritionAcess());
            arrayList.add(varibale.getSwCodeSyntaxRef());
            arrayList.add(varibale.getSwCompuMethodRef());
            arrayList.add(varibale.getSwDataConstrRef());
            arrayList.add(varibale.getSwImplPolicy());
            arrayList.add(varibale.getSwVariableAccessImplPolicy());
            arrayList.add(varibale.getDescription());
            arrayList.add(varibale.getMinHexValue());
            arrayList.add(varibale.getMaxHexValue());
            arrayList.add(varibale.getMinPhyValue());
            arrayList.add(varibale.getMaxHexValue());
            arrayList.add(varibale.getMimiAccuracy());
            arrayList.add(varibale.getSwSyscond());
            arrayList.add(varibale.getReservedCol01());
            arrayList.add(varibale.getReservedCol02());
            arrayList.add(varibale.getReservedCol03());
        }

        try {
            JDBCUtils.getInstance().execute(connection, sqlText, arrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> queryAllVariableShortName(Connection connection) {

        ArrayList<String> nameList = new ArrayList<String>();

        String querySQL = "SELECT shortName FROM tb_variable_test";
        ResultSet resultSet = JDBCUtils.getInstance().executeQuery(connection, querySQL, null);
        try {
            while (resultSet.next()) {
                nameList.add(resultSet.getString("shortName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nameList;
    }

    public void updateFields(Connection connection, String shortName,
                             String description, Double minPhyValue, Double maxPhyValue, Double mimiAccuracy) {

        String updateSQL = "UPDATE tb_variable_test SET " +
                "description=?, minPhyValue=?, maxPhyValue=?, mimiAccuracy=?" +
                "WHERE shortName=?";

        ArrayList<Object> arrayList = new ArrayList<Object>();
        arrayList.add(description);
        arrayList.add(minPhyValue);
        arrayList.add(maxPhyValue);
        arrayList.add(mimiAccuracy);
        arrayList.add(shortName);

        try {
            JDBCUtils.getInstance().execute(connection, updateSQL, arrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Varibale> queryVariableMaxMinByShortName(Connection connection, String shortName) {

        ArrayList<Varibale> variableList = new ArrayList<Varibale>();

        String querySQL = String.format("SELECT shortName, minPhyValue, maxPhyValue, mimiAccuracy " +
                "FROM tb_variable_test WHERE shortName in (%s)", shortName);


        ResultSet resultSet = JDBCUtils.getInstance().executeQuery(connection, querySQL, null);

        try {
            while (resultSet.next()) {
                Varibale varibale = new Varibale();
                varibale.setShortName(resultSet.getString("shortName"));
                varibale.setMinPhyValue(resultSet.getDouble("minPhyValue"));
                varibale.setMaxPhyValue(resultSet.getDouble("maxPhyValue"));
                varibale.setMimiAccuracy(resultSet.getDouble("mimiAccuracy"));
                variableList.add(varibale);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return variableList;
    }
}

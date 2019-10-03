package cn.yongjie.dao.impl;

import cn.yongjie.dao.UnitDao;
import cn.yongjie.pojo.Unit;
import cn.yongjie.utils.JDBCUtils;

import java.sql.Connection;
import java.util.ArrayList;

public class UnitDaoImpl implements UnitDao{

    public void insertSingleObjectIntoUnitTable(Connection connection, Unit unit) {

        String sqlText = "INSERT INTO " +
                "    tb_unit_test(markSymbol, realSymbol)" +
                "VALUES(?, ?)" +
                "ON DUPLICATE KEY UPDATE " +
                "    realSymbol=?";

        ArrayList<Object> arrayList = new ArrayList<Object>();
        arrayList.add(unit.getMarkSymbol());
        for (int i = 0; i < 2; i++) {
            arrayList.add(unit.getRealSymbol());
        }

        try {
            JDBCUtils.getInstance().execute(connection, sqlText, arrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

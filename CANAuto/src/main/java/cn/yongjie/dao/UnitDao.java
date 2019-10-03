package cn.yongjie.dao;

import cn.yongjie.pojo.Unit;

import java.sql.Connection;

public interface UnitDao {

    /**
     *
     * @param connection
     * @param unit
     */
    void insertSingleObjectIntoUnitTable(Connection connection, Unit unit);
}

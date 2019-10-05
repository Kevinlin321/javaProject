package cn.yongjie.dao;

import cn.yongjie.pojo.Varibale;

import java.sql.Connection;
import java.util.ArrayList;

public interface VariableDao {

    /**
     *
     * @param connection
     * @param varibale
     */
    void insertSingleObjectIntoVariableTable(Connection connection, Varibale varibale);

    /**
     *
     * @param connection
     * @return
     */
    ArrayList<String> queryAllVariableShortName(Connection connection);

    /**
     *
     * @param connection
     * @param description
     * @param minPhyValue
     * @param maxPhyValue
     */
    void updateFields(Connection connection, String shortName,
                      String description, Double minPhyValue, Double maxPhyValue, Double mimiAccuracy);

    /**
     *
     * @param connection
     * @param shortNameStr
     * @return
     */
    ArrayList<Varibale> queryVariableMaxMinByShortName(Connection connection, String shortNameStr);
}

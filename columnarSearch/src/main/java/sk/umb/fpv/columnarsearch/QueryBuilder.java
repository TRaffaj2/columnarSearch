/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sk.umb.fpv.columnarsearch;

/**
 *
 * @author tomraffaj
 */
import java.util.Map;

public class QueryBuilder {
       
    public String buildSelectQuery(Map<String, Integer> columnValues, String table) {
        StringBuilder query = new StringBuilder("SELECT id FROM ");
        query.append(table).append(" WHERE ");

        int conditionsAdded = 0;
        for (Map.Entry<String, Integer> entry : columnValues.entrySet()) {
            String columnName = entry.getKey();
            int columnValue = entry.getValue();

            if (conditionsAdded > 0) {
                query.append(" AND ");
            }

            query.append(columnName).append(" = ").append(columnValue);
            conditionsAdded++;
        }

        return query.toString();
    }
    
    public String buildSubQuery(Map<String, Integer> columnValues, String table, long id) {
        StringBuilder query = new StringBuilder("SELECT id FROM ");
        query.append(table).append(" WHERE ");
        query.append("id =").append(id);
        int conditionsAdded = 1;
        for (Map.Entry<String, Integer> entry : columnValues.entrySet()) {
            String columnName = entry.getKey();
            int columnValue = entry.getValue();

            if (conditionsAdded > 0) {
                query.append(" AND ");
            }

            query.append(columnName).append(" = ").append(columnValue);
            conditionsAdded++;
        }

        return query.toString();
    }
}

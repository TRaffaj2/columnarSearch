/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sk.umb.fpv.columnarsearch;

import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tomraffaj
 */
public class SequentialSearch {

    public List<Integer> seqSearch(CassandraQueryExecutor queryExecutor, int[][] subMatrix, int matrixSize, String table) {
        List<Integer> resultList = new ArrayList<>();
        QueryBuilder queryBuilder = new QueryBuilder();
        for (int[] dimension : subMatrix) {
            int startingIndex = 1;
            for (int i = 0; i < matrixSize; i++) {
                Map<String, Integer> map = new HashMap<>();

                for (int j = 0; j < dimension.length; j++) {
                    String columnName = "column" + (startingIndex + j);
                    int value = dimension[j];
                    map.put(columnName, value);
                }
                startingIndex++;
                String query = queryBuilder.buildSelectQuery(map, table);
                ResultSet result = queryExecutor.executeQuery(query);
                for(Row row : result){
                    resultList.add(row.getInt(0));
                }
            }
        }
        Collections.sort(resultList);
        int counter = 0;
        Integer lastValue = null;
        List<Integer> results = new ArrayList<>();
        for (Integer i : resultList){
            if (counter == subMatrix.length){
                results.add(i-counter);
                counter = 0;
            }
            if(counter == 0){
                counter++;
                lastValue = i; 
                continue;
            } else {
                if (lastValue-1 == i){
                    counter++;
                } else {
                    counter = 0;
                }
            }
        }
        return results;
    }
}

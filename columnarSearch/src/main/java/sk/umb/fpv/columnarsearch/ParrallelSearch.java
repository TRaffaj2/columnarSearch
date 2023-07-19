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
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 *
 * @author tomraffaj
 */
class ParrallelSearch {

    List<Integer> parSearch(CassandraQueryExecutor queryExecutor, int[][] subMatrix, int matrixSize, String table) {
        QueryBuilder queryBuilder = new QueryBuilder();
        ConcurrentLinkedQueue<Integer> resultList = new ConcurrentLinkedQueue<>();
        ExecutorService executorService = Executors.newWorkStealingPool(); // Create a thread pool

        for (int[] dimension : subMatrix) {
            executorService.execute(() -> {
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
                    result.forEach(row -> resultList.add(row.getInt(0)));
                }
            });
            List<Integer> sortedList = resultList.stream().sorted().collect(Collectors.toList());
            int counter = 0;
            Integer lastValue = null;
            List<Integer> results = new ArrayList<>();
            for (Integer i : sortedList) {
                if (counter == subMatrix.length) {
                    results.add(i - counter);
                    counter = 0;
                }
                if (counter == 0) {
                    counter++;
                    lastValue = i;
                    continue;
                } else {
                    if (lastValue - 1 == i) {
                        counter++;
                    } else {
                        counter = 0;
                    }
                }
            }
            return results;
        }
        return null;
    }
}

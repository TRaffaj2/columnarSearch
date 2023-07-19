/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sk.umb.fpv.columnarsearch;

import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;
import java.util.List;

/**
 *
 * @author tomraffaj
 */
public class Main {

    private static boolean MULTI_THREAD = false;
    private static int[][] SUB_MATRIX = null;
    private static String TABLE_NAME = "cql36";
    private static int MATRIX_SIZE = 36;

    public static void main(String[] args) {
        String contactPoint = "127.0.0.1";
        int port = 9042;
        String username = "usr";
        String password = "usr";
        String keyspace = "casdb";
        List<Integer> results = null;
        CassandraQueryExecutor queryExecutor = new CassandraQueryExecutor(contactPoint, port, username, password, keyspace);
        if (MULTI_THREAD == false) {
            SequentialSearch seq = new SequentialSearch();
            results = seq.seqSearch(queryExecutor, SUB_MATRIX, MATRIX_SIZE, TABLE_NAME);
        } else {
            ParrallelSearch par = new ParrallelSearch();
            results = par.parSearch(queryExecutor, SUB_MATRIX, MATRIX_SIZE, TABLE_NAME);
        }
        if (results == null || results.isEmpty()) {
            System.out.println("Nenajdeny ziadny vysledok");
            return;
        }
        ResultSet result = queryExecutor.executeQuery("SELECT * FROM your_table_name WHERE ID >= " + results.get(0) + " AND <" + (results.get(0) + MATRIX_SIZE));
        int[][] resultToPrint = null;
        int rowNum = 0;
        for (Row row : result) {
            for (int i = 0; i < MATRIX_SIZE; i++) {
                resultToPrint [rowNum][i] = row.getInt(i);
            }
        }
        PrintPrep p = new PrintPrep();
        List toPrint = p.isSubMatrixPresent(resultToPrint, SUB_MATRIX);
        int sx = (int) toPrint.get(0);
        int sy = (int) toPrint.get(1);
        int ex = (int) toPrint.get(2);
        int ey = (int) toPrint.get(3);
        ArrayDrawer drawer = new ArrayDrawer(resultToPrint,sx, sy, ex, ey);
        drawer.paintOut(sx, sy, ex, ey,resultToPrint);
        queryExecutor.closeConnection();
    }
}

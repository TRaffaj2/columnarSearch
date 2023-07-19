/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sk.umb.fpv.columnarsearch;

import com.datastax.oss.driver.api.core.cql.Row;

/**
 *
 * @author tomraffaj
 */
public class RowTranslator {

    public int[] convertRowToIntArray(Row row) {
        int[] array = new int[row.size()];
        for (int i = 0; i < row.size(); i++) {
            array[i] = row.getInt(i);
        }
        return array;
    }

}

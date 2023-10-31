/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PraktikumPemrogramanII_B_2023.StudiKasus;

import javax.swing.table.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ahmad
 */

public class MyTableModel extends AbstractTableModel {
    private String[] columnNames = {"Nama", "No.HP", "Jenis Kelamin", "Alamat"};
    private ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
    
    public int getColumnCount(){
        return columnNames.length;
    }
    
    public int getRowCount(){
        return data.size();
    }
    
    public String getColumnName(int col){
        return columnNames[col];
    }
    
    public Object getValueAt(int row, int col){
        List<String> rowItem = data.get(row);
        return rowItem.get(col);
    }
    
    public void setValueAt(Object value, int row, int col) {
        ArrayList<String> rowItem = data.get(row);
        if (value instanceof String) {
            rowItem.set(col, (String) value);
            fireTableCellUpdated(row, col);
        } else {
            // Tindakan atau pesan kesalahan jika tipe data tidak sesuai.
        }
    }
    
    public boolean isCellEditable(int row, int col){
        return true;
    }
    
    public void updateRow(int row, String updatedNama, String updatedNoHp, String updatedJenisKelamin, String updatedAlamat) {
        ArrayList<String> updatedData = new ArrayList<>();
        updatedData.add(updatedNama);
        updatedData.add(updatedNoHp);
        updatedData.add(updatedJenisKelamin);
        updatedData.add(updatedAlamat);
        data.set(row, updatedData);
        fireTableRowsUpdated(row, row);
    }
    
    public void removeRow(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < data.size()) {
            data.remove(rowIndex); // Menghapus baris dari ArrayList data
            fireTableRowsDeleted(rowIndex, rowIndex); // Memberi tahu tabel bahwa baris telah dihapus
        }
    }
    
    public void add(ArrayList<String> value)
    {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }
}

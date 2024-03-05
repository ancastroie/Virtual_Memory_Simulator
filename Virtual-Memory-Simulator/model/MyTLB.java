package org.example.model;

import javax.swing.table.AbstractTableModel;

public class MyTLB extends AbstractTableModel {
    int index=0;
    String[][] table;
    public MyTLB() {
        int rowCount = 50;
        int columnCount = 3;
        table = new String[rowCount][columnCount];


        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                table[i][j] = "";
            }
        }
    }
    @Override
    public int getRowCount() {
        return table.length;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(rowIndex<table.length&&columnIndex<getColumnCount())
        {
            return table[rowIndex][columnIndex];
        }
        return null;
    }
    public void setValue(Object s,int r,int c)
    {
        if(r< table.length&&c<getColumnCount()){
            table[r][c]=s.toString();
        }
        fireTableDataChanged();
    }

    public String getColumnName(int c)
    {
        if(c==0)return "Index";
        if(c==1)return "Virtual Page";
        if(c==2)return "Physical Page";
        return null;

    }
    public void setSize(int rowCount) {
        int columnCount = 3;
        table = new String[rowCount][columnCount];

        for (int i = 0; i < rowCount; i++) {
            setValue((Integer.toHexString(i)).toUpperCase(), i, 0);
            for (int j = 1; j < columnCount; j++) {

                table[i][j] = "";
            }
        }
        fireTableDataChanged();
    }
    public int searchTLB(String s){
        for (int i = 0; i < getRowCount(); i++) {
            if (table[i][1].equals(s)) {
                return i;
            }
        }
        return -1;
    }

    public void addValue(String s,String index1){

        table[index][1]=s;
        setValue(index1,index,2);
        if(index==getRowCount()-1)index=0;
        else index++;
        fireTableDataChanged();
    }

}

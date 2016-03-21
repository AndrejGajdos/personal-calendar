package cz.muni.fi.pv168.swing;

import java.util.Calendar;
import javax.swing.table.AbstractTableModel;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author Andrej Gajdos
 */
public class TableCalendarModel extends AbstractTableModel {

private Calendar cal = Calendar.getInstance();

private String[][] parsedData = new String[6][7];

public TableCalendarModel() {

        calcDataInTable();
}

@Override
public int getRowCount() {
        return 6;
}

@Override
public int getColumnCount() {
        return 7;
}

@Override
public Object getValueAt(int rowIndex, int columnIndex) {



        return parsedData[rowIndex][columnIndex];


}

@Override
public String getColumnName(int columnIndex) {
        switch (columnIndex) {
        case 0:
                return "Monday";
        case 1:
                return "Tuesday";
        case 2:
                return "Wednesday";
        case 3:
                return "Thursday";
        case 4:
                return "Friday";
        case 5:
                return "Saturday";
        case 6:
                return "Sunday";
        default:
                throw new IllegalArgumentException("columnIndex");
        }
}


/**
 * executes algorithm that fills table with correct days based on current month
 */
private void calcDataInTable() {
        Calendar tempCal = (Calendar) cal.clone();

        tempCal.set(Calendar.DAY_OF_MONTH, 1);
        int dayOfWeek;

        if (tempCal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                dayOfWeek = 7;
        } else {
                dayOfWeek = tempCal.get(Calendar.DAY_OF_WEEK) - 1;
        }

        int dayOfMonth = tempCal.get(Calendar.DAY_OF_MONTH);

        for (int j = 0; j < 6; j++) {

                for (int i = 0; i < 7; i++) {

                        if (j != 0) {
                                if (dayOfMonth < 32) {
                                        parsedData[j][i] = Integer.toString(dayOfMonth);
                                }

                                if (dayOfMonth > cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
                                        parsedData[j][i] = "";
                                }

                                dayOfMonth++;

                        } else {

                                if ((j == 0) && (i >= dayOfWeek - 1)) {
                                        parsedData[j][i] = Integer.toString(dayOfMonth);
                                        dayOfMonth++;
                                } else {
                                        parsedData[j][i] = "";
                                }


                        }

                }

        }
}

/**
 * sets calendar and calls refresh method
 * @param calend
 */
public void setCalendarForModel(Calendar calend) {
        this.cal = calend;
        //fireTableDataChanged();
        refresh();
}

/**
 * uses calDataInTable and executes fireTableDataChanged()
 */
public void refresh() {
        calcDataInTable();
        fireTableDataChanged();

}
}

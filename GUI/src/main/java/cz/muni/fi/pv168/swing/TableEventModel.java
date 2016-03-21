package cz.muni.fi.pv168.swing;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import pv168.mydates.Event;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrej Gajdos
 */
public class TableEventModel extends AbstractTableModel {

private List<Event> events = new ArrayList<Event>();

@Override
public int getRowCount() {
        return events.size();
}

@Override
public int getColumnCount() {
        return 5;
}

@Override
public Object getValueAt(int rowIndex, int columnIndex) {
        Event evn = events.get(rowIndex);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String formattedDate;

        switch (columnIndex) {
        case 0:
                return evn.getname();
        case 1:
                return evn.getplace();
        case 2:
                return evn.getlabel();
        case 3:
                formattedDate = formatter.format(evn.getTimeFrom().getTime());
                return formattedDate;
        case 4:
                formattedDate = formatter.format(evn.getTimeFrom().getTime());
                return formattedDate;
        default:
                throw new IllegalArgumentException("columnIndex");
        }
}

public Event getEventAtRow(int rowIndex){

        return events.get(rowIndex);


}

public List<Event> getEvents(){

        return this.events;
}

@Override
public String getColumnName(int columnIndex) {
        switch (columnIndex) {
        case 0:
                return "Name";
        case 1:
                return "Place";
        case 2:
                return "Label";
        case 3:
                return "Time From";
        case 4:
                return "Time To";
        default:
                throw new IllegalArgumentException("columnIndex");
        }
}

public void addEvents(List<Event> l){
        this.events =l;
        fireTableDataChanged();

}

public void addEvent(Event event) {
        events.add(event);
        int lastRow = events.size() - 1;
        fireTableRowsInserted(lastRow, lastRow);
}

public void refresh(){
        fireTableDataChanged();
}

}

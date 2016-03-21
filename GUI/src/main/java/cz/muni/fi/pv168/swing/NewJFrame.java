package cz.muni.fi.pv168.swing;


import cz.muni.fi.pv168.workers.RemoveEventWorker;
import cz.muni.fi.pv168.workers.TableEventWorker;
import cz.muni.fi.pv168.workers.TableEventWorkerWeek;
import java.beans.PropertyVetoException;
import java.util.Calendar;
import javax.sql.DataSource;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import pv168.mydates.CalendarManagerImpl;
import pv168.mydates.Event;
import pv168.mydates.EventManagerImpl;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andrej Gajdos
 */
public class NewJFrame extends javax.swing.JFrame {

private final static Logger logger = Logger.getLogger(NewJFrame.class);

Calendar cal = Calendar.getInstance();
DataSource ds = Tools.getDS();

private EventManagerImpl emi = new EventManagerImpl();
private CalendarManagerImpl cmi = new CalendarManagerImpl();

String[] monthName = {"January", "February",
                      "March", "April", "May", "June", "July",
                      "August", "September", "October", "November",
                      "December"};

/**
 * Creates new form NewJFrame
 */
public NewJFrame() {

        initComponents();

        emi.setDataSource(ds);
        cmi.setDataSource(ds);

        //<editor-fold defaultstate="collapsed" desc="comment">
        this.setTitle("My Dates");
        //</editor-fold>

        jTable1.setShowHorizontalLines(true);
        jTable1.setShowVerticalLines(true);

        myInit();

}

/**
 * This method is called from within the constructor to initialize the form.
 * WARNING: Do NOT modify this code. The content of this method is always
 * regenerated by the Form Editor.
 */
@SuppressWarnings("unchecked")
// <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        monthLabel = new javax.swing.JLabel();
        yearLabel = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));

        monthLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        monthLabel.setText("month");

        yearLabel.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        yearLabel.setText("year");

        jButton2.setText("previous");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                clickPreviousMonth(evt);
                        }
                });

        jButton3.setLabel("next");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                clickNextMonth(evt);
                        }
                });

        jTable1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTable1.setModel(new TableCalendarModel());
        jTable1.setGridColor(new java.awt.Color(102, 102, 102));
        jTable1.setRowHeight(41);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable1);

        jButton1.setLabel("Add Event");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                clickAddEvent(evt);
                        }
                });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("ToDo list:");

        jButton4.setText("Edit Event");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                editEvent(evt);
                        }
                });

        jButton5.setText("Remove Event");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                removeEvent(evt);
                        }
                });

        jTable2.setModel(new TableEventModel());
        jScrollPane3.setViewportView(jTable2);

        jButton6.setText("clear");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButton6ActionPerformed(evt);
                        }
                });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 517, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createSequentialGroup()
                          .addContainerGap()
                          .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                              .addComponent(monthLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                              .addComponent(yearLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                              .addComponent(jButton6)
                                              .addGap(44, 44, 44)
                                              .addComponent(jButton2)
                                              .addGap(18, 18, 18)
                                              .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                              .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                              .addComponent(jButton1)
                                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                              .addComponent(jButton4)
                                              .addGap(18, 18, 18)
                                              .addComponent(jButton5)))
                          .addContainerGap())
                );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                          .addContainerGap()
                          .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                              .addComponent(jButton2)
                                              .addComponent(jButton3)
                                              .addComponent(jButton6))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                              .addComponent(monthLabel)
                                              .addComponent(yearLabel)))
                          .addGap(18, 18, 18)
                          .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                          .addGap(18, 18, 18)
                          .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                              .addComponent(jButton1)
                                              .addComponent(jButton4)
                                              .addComponent(jButton5))
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING))
                          .addGap(18, 18, 18)
                          .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                          .addContainerGap())
                );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                          .addContainerGap()
                          .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                          .addContainerGap())
                );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                          .addContainerGap()
                          .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                          .addGap(0, 0, Short.MAX_VALUE))
                );

        pack();
}    // </editor-fold>//GEN-END:initComponents

//---------------------------NEXT_MONTH_CLICK-------------------------------

private void clickNextMonth(java.awt.event.ActionEvent evt) {    //GEN-FIRST:event_clickNextMonth

        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)+1);


        if(jTable1.getSelectedRow() == -1) {        // if row isn't selected in calendar

                setCalendarLabels();

                TableEventWorker tew = new TableEventWorker((TableEventModel) jTable2.getModel(), cmi, cal);
                tew.execute();

                TableCalendarModel model2 = (TableCalendarModel) jTable1.getModel();
                model2.setCalendarForModel(cal);

        }

        else{
                setWeekCalendar();

        }

}    //GEN-LAST:event_clickNextMonth


//---------------------------ADD_EVENT_CLICK-------------------------------

private void clickAddEvent(java.awt.event.ActionEvent evt) {    //GEN-FIRST:event_clickAddEvent

        AddJDialog addDialog = new AddJDialog(this, rootPaneCheckingEnabled);

        addDialog.setRequiredElements((TableEventModel) jTable2.getModel(), cal, cmi, emi);

        addDialog.setVisible(true);


}    //GEN-LAST:event_clickAddEvent


//---------------------------EDIT_EVENT_CLICK-------------------------------

private void editEvent(java.awt.event.ActionEvent evt) {    //GEN-FIRST:event_editEvent

        if (jTable2.getSelectedRow() != -1) {    // if event is selected

                EditJDialog editDialog = new EditJDialog(this, rootPaneCheckingEnabled);

                TableEventModel model = (TableEventModel) jTable2.getModel();

                try {
                        editDialog.setEvent(model.getEventAtRow(jTable2.getSelectedRow()), model);
                } catch (PropertyVetoException ex) {
                        logger.error( "Edit event exception ", ex);
                }

                editDialog.setVisible(true);

        }
        else
                JOptionPane.showMessageDialog(null, "Zvoľte udalosť.");

}    //GEN-LAST:event_editEvent

//---------------------------REMOVE_EVENT_CLICK-------------------------------

private void removeEvent(java.awt.event.ActionEvent evt) {    //GEN-FIRST:event_removeEvent


        if (jTable2.getSelectedRow() != -1) {    // if event is selected

                TableEventModel model = (TableEventModel) jTable2.getModel();
                Event event = model.getEventAtRow(jTable2.getSelectedRow());

                RemoveEventWorker worker = new RemoveEventWorker(model, event,cal,emi, cmi);
                worker.execute();

        }
        else{

                JOptionPane.showMessageDialog(null, "Zvoľte udalosť.");
        }

}    //GEN-LAST:event_removeEvent

//---------------------------PREVOIUS_MONTH_CLICK-------------------------------

private void clickPreviousMonth(java.awt.event.ActionEvent evt) {    //GEN-FIRST:event_clickPreviousMonth

        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)-1);

        if(jTable1.getSelectedRow() == -1) {        // if row isn't selected in calendar

                setCalendarLabels();
                TableEventWorker tew = new TableEventWorker((TableEventModel) jTable2.getModel(), cmi, cal);
                tew.execute();

                TableCalendarModel model2 = (TableCalendarModel) jTable1.getModel();
                model2.setCalendarForModel(cal);
        } else{

                setWeekCalendar();
        }

}    //GEN-LAST:event_clickPreviousMonth

//----------------------------CLEAR_TABLE--------------------------------

private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {    //GEN-FIRST:event_jButton6ActionPerformed
        jTable1.getSelectionModel().clearSelection();
}    //GEN-LAST:event_jButton6ActionPerformed

//---------------------------SET_MONTH_YEAR-------------------------------

private void setCalendarLabels(){

        monthLabel.setText(monthName[cal.get(Calendar.MONTH)]);
        yearLabel.setText(Integer.toString(cal.get(Calendar.YEAR)));

}

// get events in week in table

private void setWeekCalendar(){

        Calendar tempcal = cal;
        int selectedRow = jTable1.getSelectedRow();

        setCalendarLabels();

        for(int j = 0; j<7; j++) {

                if( jTable1.getValueAt(selectedRow, j) != "") {

                        int index = Integer.parseInt((String)jTable1.getValueAt(selectedRow,j));

                        tempcal.set(Calendar.DAY_OF_MONTH, index);

                        TableEventWorkerWeek tew = new TableEventWorkerWeek((TableEventModel) jTable2.getModel(), cmi, tempcal);
                        tew.execute();

                        break;
                } else

                if(j ==6) {

                        // do something

                }

        }

}

//---------------------------INITIALIZE_COMPONENTS-------------------------------

private void myInit(){

        setCalendarLabels();


        TableEventWorker tew = new TableEventWorker((TableEventModel) jTable2.getModel(), cmi, cal);
        tew.execute();


        // Listener (row in dates is selected)

        jTable1.getSelectionModel().addListSelectionListener(
                new javax.swing.event.ListSelectionListener() {
                        public void valueChanged(ListSelectionEvent evt) {
                                customRowSelectionEventHandler(evt);
                        }
                        int sele = -1;
                        private void customRowSelectionEventHandler(ListSelectionEvent evt) {

                                if(jTable1.getSelectedRow() != -1)

                                {

                                        setWeekCalendar();

                                }

                                else{
                                        TableEventWorker tew = new TableEventWorker((TableEventModel) jTable2.getModel(), cmi, cal);
                                        tew.execute();
                                }
                        }
                }
                );


}


/**
 * @param args the command line arguments
 */
public static void main(String args[]) {

        PropertyConfigurator.configure("log4j.properties");
        logger.info("Log was initialized");
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Motif".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
                javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

                        public void run() {
                                new NewJFrame().setVisible(true);
                        }
                });
}

// Variables declaration - do not modify//GEN-BEGIN:variables
private javax.swing.JButton jButton1;
private javax.swing.JButton jButton2;
private javax.swing.JButton jButton3;
private javax.swing.JButton jButton4;
private javax.swing.JButton jButton5;
private javax.swing.JButton jButton6;
private javax.swing.JLabel jLabel8;
private javax.swing.JPanel jPanel1;
private javax.swing.JScrollPane jScrollPane2;
private javax.swing.JScrollPane jScrollPane3;
private javax.swing.JTable jTable1;
private javax.swing.JTable jTable2;
private javax.swing.JLabel monthLabel;
private javax.swing.JLabel yearLabel;
// End of variables declaration//GEN-END:variables


}
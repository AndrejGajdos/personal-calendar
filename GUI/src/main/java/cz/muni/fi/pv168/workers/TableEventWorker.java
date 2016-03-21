/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.workers;

import cz.muni.fi.pv168.swing.TableEventModel;
import cz.muni.fi.pv168.swing.Tools;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.sql.DataSource;
import javax.swing.SwingWorker;
import org.apache.log4j.Logger;
import pv168.mydates.CalendarManagerImpl;
import pv168.mydates.Event;


/**
 *
 * @author Andrej Gajdos
 */
public class TableEventWorker extends SwingWorker<List<Event>, Void>
    {
    private final static Logger logger = Logger.getLogger(TableEventWorker.class);
    
        private TableEventModel tem;
        private CalendarManagerImpl cmi;
        private Calendar cal;
        DataSource ds = Tools.getDS();
        
        public TableEventWorker(TableEventModel tem, CalendarManagerImpl cmi, Calendar cal)
        {
            this.tem = tem;
            this.cmi = cmi;
            this.cal = cal;
            cmi.setDataSource(ds);
        }
        
        
        @Override
        protected List<Event> doInBackground() throws Exception {
            List<Event> lists = cmi.showEventsByMonth(cal);
            return lists;
        }

        @Override
        protected void done() {
            try {
                tem.addEvents(get());
                tem.refresh();
            } catch (InterruptedException ex) {
                logger.error(ex);
            } catch (ExecutionException ex) {
                logger.error(ex);
            }
        }
        
        
        
    }

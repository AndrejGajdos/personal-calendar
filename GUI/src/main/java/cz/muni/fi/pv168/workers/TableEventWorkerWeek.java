/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.workers;

import cz.muni.fi.pv168.swing.TableEventModel;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;
import org.apache.log4j.Logger;
import pv168.mydates.CalendarManagerImpl;
import pv168.mydates.Event;

/**
 *
 * @author Andrej Gajdos
 */
public class TableEventWorkerWeek extends SwingWorker<List<Event>, Void>{
    
    private final static Logger logger = Logger.getLogger(TableEventWorkerWeek.class);
    
    
        private TableEventModel tem;
        private CalendarManagerImpl cmi;
        private Calendar cal;
        
        public TableEventWorkerWeek(TableEventModel tem, CalendarManagerImpl cmi, Calendar cal)
        {
            this.tem = tem;
            this.cmi = cmi;
            this.cal = cal;
        }
        
        
        @Override
        protected List<Event> doInBackground() throws Exception {
            List<Event> lists = cmi.showEventsByWeek(cal);
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

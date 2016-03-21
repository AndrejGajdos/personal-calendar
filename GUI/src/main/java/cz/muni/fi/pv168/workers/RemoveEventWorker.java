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
import pv168.mydates.EventManagerImpl;

/**
 *
 * @author Andrej Gajdos
 */
public class RemoveEventWorker extends SwingWorker<List<Event>, Void>{
    
    private final static Logger logger = Logger.getLogger(RemoveEventWorker.class);

        private EventManagerImpl emp;
        private CalendarManagerImpl cmi;
        private Event ev;
        private TableEventModel tabev;
        private Calendar cal;
        DataSource ds = Tools.getDS();
        
        public RemoveEventWorker(TableEventModel tab, Event e,Calendar cal,EventManagerImpl emi, CalendarManagerImpl cmi) {
            this.ev = e;
            this.tabev = tab;
            this.emp = emi;
            this.cmi = cmi;
            this.cal = cal;
            
            emp = new EventManagerImpl();
            emp.setDataSource(ds);
        }

       
        @Override
        protected List<Event> doInBackground() throws Exception {
            emp.deleteEvent(ev);
            List<Event> l = cmi.showEventsByMonth(cal);
            tabev.refresh();
            return l;
        }

        @Override
        protected void done() {
           
            try {
                tabev.addEvents(get());
            } catch (ExecutionException ex) {
                logger.error(ex);
            } catch (InterruptedException ex) {
                logger.error(ex);
            }

        }
    
}

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
import pv168.mydates.EventManagerImpl;

/**
 *
 * @author Andrej Gajdos
 */
public class AddEventWorker extends SwingWorker<List<Event>, Void> {
    
        private final static Logger logger = Logger.getLogger(AddEventWorker.class);

        private EventManagerImpl emi;
        private CalendarManagerImpl cmi;
        private Event ev;
        private TableEventModel tem;
        private Calendar cal;


        public AddEventWorker(TableEventModel tab, Event e,Calendar cal,EventManagerImpl emi, CalendarManagerImpl cmi) {
            this.ev = e;
            this.tem = tab;
            this.emi = emi;
            this.cmi = cmi;
            this.cal = cal;

        }

        @Override
        protected List<Event> doInBackground() throws Exception {
            emi.createEvent(ev);
            List<Event> l = cmi.showEventsByMonth(cal);
            tem.refresh();
            return l;
        }

        @Override
        protected void done() {
           
            try {
                tem.addEvents(get());
            } catch (ExecutionException ex) {
                logger.error(ex);
            } catch (InterruptedException ex) {
                logger.error(ex);
            }
            
        }
    
    
}

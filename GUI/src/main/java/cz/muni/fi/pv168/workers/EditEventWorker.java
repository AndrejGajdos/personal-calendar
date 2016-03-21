/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pv168.workers;

import cz.muni.fi.pv168.swing.Tools;
import java.util.concurrent.ExecutionException;
import javax.sql.DataSource;
import javax.swing.SwingWorker;
import org.apache.log4j.Logger;
import pv168.mydates.Event;
import pv168.mydates.EventManagerImpl;

/**
 *
 * @author Andrej Gajdos
 */
public class EditEventWorker extends SwingWorker<Integer, Integer>{
    
    private final static Logger logger = Logger.getLogger(EditEventWorker.class);

        private EventManagerImpl emp;
        private Event ev;
        DataSource ds = Tools.getDS();

        public EditEventWorker(Event e) {
            this.ev = e;
            emp = new EventManagerImpl();
            emp.setDataSource(ds);
        }

        @Override
        protected Integer doInBackground() throws Exception {
            emp.updateEvent(ev);
            return 1;
        }

        @Override
        protected void done() {

            try {
                get();
            } catch (ExecutionException ex) {
                logger.error(ex);
            } catch (InterruptedException ex) {
                logger.error(ex);
            }

        }
    
    
}

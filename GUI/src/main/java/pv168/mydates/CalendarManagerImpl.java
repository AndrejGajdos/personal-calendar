package pv168.mydates;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import pv168.common.DBUtils;
import pv168.common.ServiceFailureException;



public class CalendarManagerImpl implements CalendarManager {

private final static Logger logger = Logger.getLogger(CalendarManagerImpl.class);

private DataSource dataSource;

public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
}

private void checkDataSource() {
        if (dataSource == null) {
                logger.error("DataSource is not set");
                throw new IllegalStateException("DataSource is not set");
        }
}

@Override
public List<Event> showEventsByMonth(Calendar cal) throws ServiceFailureException {

        if(cal == null)
        {
                logger.error("calendar is null");
                throw new IllegalArgumentException("calendar is null");
        }

        checkDataSource();
        Connection conn = null;
        PreparedStatement st = null;
        try {
                conn = dataSource.getConnection();

                st = conn.prepareStatement("SELECT * FROM event WHERE (MONTH(timefrom) = MONTH(?) AND YEAR(timefrom) = YEAR(?))");

                st.setTimestamp(1, new java.sql.Timestamp(cal.getTimeInMillis()));
                st.setTimestamp(2, new java.sql.Timestamp(cal.getTimeInMillis()));

                if (st != null)
                        logger.info("Events from month were selected");

                return executeQueryForMultipleEvents(st);

        } catch (SQLException ex) {
                String msg = "Error when getting all bodies from DB";
                logger.error(msg, ex);
                throw new ServiceFailureException(msg, ex);
        } finally {
                DBUtils.closeQuietly(conn, st);
        }

}

@Override
public List<Event> showEventsByWeek(Calendar cal) throws ServiceFailureException {

        if(cal == null)
        {
                logger.error("calendar is null, showEventsByWeek method");
                throw new IllegalArgumentException("calendar is null");
        }

        checkDataSource();
        Connection conn = null;
        PreparedStatement st = null;
        try {
                conn = dataSource.getConnection();

                st = conn.prepareStatement("SELECT * FROM event WHERE (WEEK(timefrom) = WEEK(?) AND YEAR(timefrom) = YEAR(?))");

                st.setTimestamp(1, new java.sql.Timestamp(cal.getTimeInMillis()));
                st.setTimestamp(2, new java.sql.Timestamp(cal.getTimeInMillis()));

                if (st != null)
                        logger.info("Events from week were selected");

                return executeQueryForMultipleEvents(st);
        } catch (SQLException ex) {
                String msg = "Error when getting all bodies from DB";
                logger.error(msg, ex);
                throw new ServiceFailureException(msg, ex);
        } finally {
                DBUtils.closeQuietly(conn, st);
        }

}

static List<Event> executeQueryForMultipleEvents(PreparedStatement st) throws SQLException {
        ResultSet rs = st.executeQuery();
        List<Event> result = new ArrayList<Event>();
        while (rs.next()) {
                result.add(rowToEvent(rs));
        }
        return result;
}

static private Event rowToEvent(ResultSet rs) throws SQLException {
        Event event = new Event();


        Calendar tempCalendarFrom = Calendar.getInstance();
        tempCalendarFrom.setTimeInMillis(rs.getTimestamp("timeFrom").getTime());

        Calendar tempCalendarTo = Calendar.getInstance();
        tempCalendarTo.setTimeInMillis(rs.getTimestamp("timeTo").getTime());

        event.setId(rs.getLong("id"));
        event.setname(rs.getString("name"));
        event.setplace(rs.getString("place"));
        event.setlabel(rs.getString("label"));
        event.setTimeFrom(tempCalendarFrom);
        event.setTimeTo(tempCalendarTo);

        return event;
}

}

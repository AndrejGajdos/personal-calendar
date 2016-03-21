package pv168.mydates;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import pv168.common.DBUtils;
import pv168.common.IllegalEntityException;
import pv168.common.ServiceFailureException;
import pv168.common.ValidationException;


public class EventManagerImpl implements EventManager {

private final static Logger logger = Logger.getLogger(EventManagerImpl.class);

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
public void createEvent(Event event) throws ServiceFailureException {

        checkDataSource();
        validate(event);

        if (event.getId() != null) {
                logger.error("event id is already set");
                throw new IllegalArgumentException("event id is already set");
        }

        Connection conn = null;
        PreparedStatement st = null;

        try {
                conn = dataSource.getConnection();
                // Temporary turn autocommit mode off. It is turned back on in
                // method DBUtils.closeQuietly(...)
                conn.setAutoCommit(false);
                st = conn.prepareStatement(
                        "INSERT INTO event (name,place,label,timeFrom,timeTo) VALUES (?,?,?,?,?)",
                        Statement.RETURN_GENERATED_KEYS);

                java.sql.Timestamp dateFrom = new java.sql.Timestamp(event.getTimeFrom().getTimeInMillis());
                java.sql.Timestamp dateTo = new java.sql.Timestamp(event.getTimeTo().getTimeInMillis());
                dateFrom.setNanos(0);
                dateTo.setNanos(0);

                st.setString(1, event.getname());
                st.setString(2, event.getplace());
                st.setString(3, event.getlabel());
                st.setTimestamp(4, dateFrom);
                st.setTimestamp(5, dateTo);

                int count = st.executeUpdate();
                DBUtils.checkUpdatesCount(count, event, true);

                Long id = DBUtils.getId(st.getGeneratedKeys());
                event.setId(id);
                conn.commit();

                logger.info("Event was created");

        } catch (SQLException ex) {
                String msg = "Error when inserting event into db";
                logger.error(msg, ex);
                throw new ServiceFailureException(msg, ex);
        } finally {
                DBUtils.doRollbackQuietly(conn);
                DBUtils.closeQuietly(conn, st);
        }
}

@Override
public Event getEvent(Long id) throws ServiceFailureException {

        checkDataSource();

        if (id == null) {
                logger.error("id is null");
                throw new IllegalArgumentException("id is null");
        }

        Connection conn = null;
        PreparedStatement st = null;
        try {
                conn = dataSource.getConnection();
                st = conn.prepareStatement(
                        "SELECT id,name,place,label,timefrom,timeto FROM event WHERE id = ?");
                st.setLong(1, id);

                if(st!= null)
                        logger.info("Event was selected");

                return executeQueryForSingleEvent(st);

        } catch (SQLException ex) {
                String msg = "Error when getting body with id = " + id + " from DB";
                logger.error(msg, ex);
                throw new ServiceFailureException(msg, ex);
        } finally {
                DBUtils.closeQuietly(conn, st);
        }
}

@Override
public void updateEvent(Event event) throws ServiceFailureException {
        checkDataSource();
        validate(event);

        if (event.getId() == null) {
                logger.error("event id is null");
                throw new IllegalEntityException("event id is null");
        }

        Connection conn = null;
        PreparedStatement st = null;

        try {
                conn = dataSource.getConnection();
                // Temporary turn autocommit mode off. It is turned back on in
                // method DBUtils.closeQuietly(...)
                conn.setAutoCommit(false);
                st = conn.prepareStatement(
                        "UPDATE event SET name = ?,place = ?,label = ?,timefrom = ?,timeto = ? WHERE id=?",
                        Statement.RETURN_GENERATED_KEYS);

                java.sql.Timestamp dateFrom = new java.sql.Timestamp(event.getTimeFrom().getTimeInMillis());
                java.sql.Timestamp dateTo = new java.sql.Timestamp(event.getTimeTo().getTimeInMillis());
                dateFrom.setNanos(0);
                dateTo.setNanos(0);

                st.setString(1, event.getname());
                st.setString(2, event.getplace());
                st.setString(3, event.getlabel());
                st.setTimestamp(4, dateFrom);
                st.setTimestamp(5, dateTo);
                st.setLong(6, event.getId());

                int count = st.executeUpdate();
                DBUtils.checkUpdatesCount(count, event, false);
                conn.commit();

                logger.info("Event was updated");

        } catch (SQLException ex) {
                String msg = "Error when updating event in the db";
                logger.error(msg, ex);
                throw new ServiceFailureException(msg, ex);
        } catch (NullPointerException ex) {
                logger.error(st);
                logger.error(conn);
        } finally {
                DBUtils.doRollbackQuietly(conn);
                DBUtils.closeQuietly(conn, st);
        }
}

@Override
public void deleteEvent(Event event) throws ServiceFailureException {

        checkDataSource();
        if (event == null) {
                logger.error("event is null");
                throw new IllegalArgumentException("event is null");
        }
        if (event.getId() == null) {
                logger.error("event id is null");
                throw new IllegalEntityException("event id is null");
        }

        Connection conn = null;
        PreparedStatement st = null;

        try {
                conn = dataSource.getConnection();
                // Temporary turn autocommit mode off. It is turned back on in
                // method DBUtils.closeQuietly(...)
                conn.setAutoCommit(false);
                st = conn.prepareStatement(
                        "DELETE FROM event WHERE id = ?",
                        Statement.RETURN_GENERATED_KEYS);
                st.setLong(1, event.getId());

                int count = st.executeUpdate();
                DBUtils.checkUpdatesCount(count, event, false);
                conn.commit();

                logger.info("Event was deleted");

        } catch (SQLException ex) {
                String msg = "Error when deleting event from the db";
                logger.error(msg, ex);
                throw new ServiceFailureException(msg, ex);
        } finally {
                DBUtils.doRollbackQuietly(conn);
                DBUtils.closeQuietly(conn, st);
        }
}

@Override
public List<Event> findAllEvents() throws ServiceFailureException {
        checkDataSource();
        Connection conn = null;
        PreparedStatement st = null;
        try {
                conn = dataSource.getConnection();
                st = conn.prepareStatement(
                        "SELECT id, name, place, label, timefrom, timeto FROM event");

                if (st != null)
                        logger.info("All events were selected");

                return executeQueryForMultipleEvents(st);
        } catch (SQLException ex) {
                String msg = "Error when getting all events from DB";
                logger.error(msg, ex);
                throw new ServiceFailureException(msg, ex);
        } finally {
                DBUtils.closeQuietly(conn, st);
        }
}

static Event executeQueryForSingleEvent(PreparedStatement st) throws SQLException, ServiceFailureException {
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
                Event result = rowToEvent(rs);
                if (rs.next()) {
                        logger.error("Internal integrity error: more events with the same id found!");
                        throw new ServiceFailureException(
                                      "Internal integrity error: more events with the same id found!");
                }
                return result;
        } else {
                return null;
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

static private void validate(Event event) {
        if (event == null) {
                logger.error("event is null");
                throw new IllegalArgumentException("event is null");
        }
        if (event.getname() == null) {
                logger.error("event name is null");
                throw new ValidationException("event name is null");
        }
        if (event.getTimeFrom() == null) {
                logger.error("event timefrom is null");
                throw new ValidationException("event timefrom is null");
        }

        if (event.getTimeTo() == null) {
                logger.error("event timeto is null");
                throw new ValidationException("event timeto is null");
        }
}



}

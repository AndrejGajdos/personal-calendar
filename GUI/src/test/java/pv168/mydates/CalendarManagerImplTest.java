package pv168.mydates;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

//import pv168.common.DBUtils;


public class CalendarManagerImplTest {

private EventManagerImpl eventManager;
private CalendarManagerImpl calendarManager;
private DataSource ds;

private static DataSource prepareDataSource() throws SQLException {
        BasicDataSource ds = new BasicDataSource();
        //we will use in memory database
        ds.setUrl("jdbc:mysql://mysql51.websupport.sk:3309/bookstoreSpring");
        ds.setUsername("booksStorePA165");
        ds.setPassword("kolovratok8");
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        return ds;
}

@Before
public void setUp() throws SQLException {
        ds = prepareDataSource();
        eventManager = new EventManagerImpl();
        eventManager.setDataSource(ds);

        calendarManager = new CalendarManagerImpl();
        calendarManager.setDataSource(ds);
}

@After
public void tearDown() throws SQLException {
        PreparedStatement st = ds.getConnection().prepareStatement("TRUNCATE `event`");
        st.execute();
}

@Test
public void getAllEventsByMonth() {

        Timestamp timestamp;

        Calendar timeFrom = Calendar.getInstance();
        timestamp = Timestamp.valueOf("2012-09-24 13:30:00.0");
        timeFrom.setTimeInMillis(timestamp.getTime());

        Calendar timeTo = Calendar.getInstance();
        timestamp = Timestamp.valueOf("2012-09-24 14:30:00.0");
        timeTo.setTimeInMillis(timestamp.getTime());

        Calendar timeFrom2 = Calendar.getInstance();
        timestamp = Timestamp.valueOf("2012-09-25 13:30:00.0");
        timeFrom2.setTimeInMillis(timestamp.getTime());

        Calendar timeTo2 = Calendar.getInstance();
        timestamp = Timestamp.valueOf("2012-09-25 14:30:00.0");
        timeTo2.setTimeInMillis(timestamp.getTime());

        Calendar timeFrom3 = Calendar.getInstance();
        timestamp = Timestamp.valueOf("2012-10-24 13:30:00.0");
        timeFrom3.setTimeInMillis(timestamp.getTime());

        Calendar timeTo3 = Calendar.getInstance();
        timestamp = Timestamp.valueOf("2012-10-24 14:30:00.0");
        timeTo3.setTimeInMillis(timestamp.getTime());


        Event event = newEvent("uloha","kolej","label", timeFrom, timeTo);
        Event event2 = newEvent("vyniest smeti","doma","label", timeFrom2, timeTo2);
        Event event3 = newEvent("ist na postu","ceska","label", timeFrom3, timeTo3);

        eventManager.createEvent(event);
        eventManager.createEvent(event2);
        eventManager.createEvent(event3);

        List<Event> expected = Arrays.asList(event, event2);
        List<Event> actual = calendarManager.showEventsByMonth(timeFrom);

        Collections.sort(actual,idComparator);
        Collections.sort(expected,idComparator);

        assertEquals(expected, actual);
        assertDeepEquals(expected, actual);

}

@Test
public void getAllEventsByWeek() {

        Timestamp timestamp;

        Calendar timeFrom = Calendar.getInstance();
        timestamp = Timestamp.valueOf("2012-09-24 13:30:00.0");
        timeFrom.setTimeInMillis(timestamp.getTime());

        Calendar timeTo = Calendar.getInstance();
        timestamp = Timestamp.valueOf("2012-09-24 14:30:00.0");
        timeTo.setTimeInMillis(timestamp.getTime());

        Calendar timeFrom2 = Calendar.getInstance();
        timestamp = Timestamp.valueOf("2012-09-25 13:30:00.0");
        timeFrom2.setTimeInMillis(timestamp.getTime());

        Calendar timeTo2 = Calendar.getInstance();
        timestamp = Timestamp.valueOf("2012-09-25 14:30:00.0");
        timeTo2.setTimeInMillis(timestamp.getTime());

        Calendar timeFrom3 = Calendar.getInstance();
        timestamp = Timestamp.valueOf("2012-10-24 13:30:00.0");
        timeFrom3.setTimeInMillis(timestamp.getTime());

        Calendar timeTo3 = Calendar.getInstance();
        timestamp = Timestamp.valueOf("2012-10-24 14:30:00.0");
        timeTo3.setTimeInMillis(timestamp.getTime());

        Event event = newEvent("uloha","kolej","label", timeFrom, timeTo);
        Event event2 = newEvent("vyniest smeti","doma","label", timeFrom2, timeTo2);
        Event event3 = newEvent("ist na postu","ceska","label", timeFrom3, timeTo3);

        eventManager.createEvent(event);
        eventManager.createEvent(event2);
        eventManager.createEvent(event3);

        List<Event> expected = Arrays.asList(event, event2);
        List<Event> actual = calendarManager.showEventsByWeek(timeFrom);

        Collections.sort(actual,idComparator);
        Collections.sort(expected,idComparator);

        assertEquals(expected, actual);
        assertDeepEquals(expected, actual);

}


private static Event newEvent(String name, String place, String label, Calendar timeTo, Calendar timeFrom) {
        Event Event = new Event();
        Event.setname(name);
        Event.setplace(place);
        Event.setlabel(label);
        Event.setTimeFrom(timeTo);
        Event.setTimeTo(timeFrom);
        return Event;
}

private void assertDeepEquals(List<Event> expectedList, List<Event> actualList) {
        for (int i = 0; i < expectedList.size(); i++) {
                Event expected = expectedList.get(i);
                Event actual = actualList.get(i);
                assertDeepEquals(expected, actual);
        }
}

private void assertDeepEquals(Event expected, Event actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getname(), actual.getname());
        assertEquals(expected.getplace(), actual.getplace());
        assertEquals(expected.getlabel(), actual.getlabel());
        assertEquals(expected.getTimeFrom(), actual.getTimeFrom());
        assertEquals(expected.getTimeTo(), actual.getTimeTo());
}

private static Comparator<Event> idComparator = new Comparator<Event>() {

        @Override
        public int compare(Event o1, Event o2) {
                return Long.valueOf(o1.getId()).compareTo(Long.valueOf(o2.getId()));
        }
};

}

package pv168.mydates;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;
import javax.sql.DataSource;
import static junit.framework.Assert.*;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.After;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import pv168.common.IllegalEntityException;
import pv168.common.ValidationException;


public class EventManagerImplTest {

private EventManagerImpl manager;
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
        manager = new EventManagerImpl();
        manager.setDataSource(ds);
}

@After
public void tearDown() throws SQLException {
        PreparedStatement st = ds.getConnection().prepareStatement("TRUNCATE `event`");
        st.execute();
}

@Test
public void createEvent() {

        Timestamp timestamp;

        Calendar timeFrom = Calendar.getInstance();
        timestamp = Timestamp.valueOf("2012-09-24 13:30:00.0");
        timeFrom.setTimeInMillis(timestamp.getTime());

        Calendar timeTo = Calendar.getInstance();
        timestamp = Timestamp.valueOf("2012-09-24 14:30:00.0");
        timeTo.setTimeInMillis(timestamp.getTime());

        Event event = newEvent("uloha","kolej","label", timeFrom, timeTo);

        manager.createEvent(event);

        Long eventId = event.getId();
        assertNotNull(eventId);
        Event result = manager.getEvent(eventId);
        assertEquals(event, result);
        assertNotSame(event, result);
        assertEventDeepEquals(event, result);
}

@Test
public void getEvent() {

        assertNull(manager.getEvent(1l));

        Timestamp timestamp;

        Calendar timeFrom = Calendar.getInstance();
        timestamp = Timestamp.valueOf("2012-09-24 13:30:00.0");
        timeFrom.setTimeInMillis(timestamp.getTime());

        Calendar timeTo = Calendar.getInstance();
        timestamp = Timestamp.valueOf("2012-09-24 14:30:00.0");
        timeTo.setTimeInMillis(timestamp.getTime());

        Event event = newEvent("uloha","kolej","label", timeFrom, timeTo);

        manager.createEvent(event);
        Long eventId = event.getId();

        Event result = manager.getEvent(eventId);
        assertEquals(event, result);
        assertEventDeepEquals(event, result);
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

@Test
public void createEventWithWrongAttributes() {

        Timestamp timestamp;

        Calendar timeFrom = Calendar.getInstance();
        timestamp = Timestamp.valueOf("2012-09-24 13:30:00.0");
        timeFrom.setTimeInMillis(timestamp.getTime());

        Calendar timeTo = Calendar.getInstance();
        timestamp = Timestamp.valueOf("2012-09-24 14:30:00.0");
        timeTo.setTimeInMillis(timestamp.getTime());

        try {
                manager.createEvent(null);
                fail();
        } catch (IllegalArgumentException ex) {
                System.out.println("Create null event exception");
        }

        Event event = newEvent(null,"kolej","label", timeFrom, timeTo);
        try {
                manager.createEvent(event);
                fail();
        } catch (ValidationException ex) {
                System.out.println("Event null name exception");
        }

        event = newEvent("uloha","kolej","label", null, timeTo);
        try {
                manager.createEvent(event);
                fail();
        } catch (ValidationException ex) {
                System.out.println("Event null timeFrom exception");
        }

        event = newEvent("uloha","kolej","label", timeFrom, null);
        try {
                manager.createEvent(event);
                fail();
        } catch (ValidationException ex) {
                System.out.println("Event null timeTo exception");
        }

        // these variants should be ok
        event = newEvent("uloha",null,"label", timeFrom, timeTo);
        manager.createEvent(event);
        Event result = manager.getEvent(event.getId());
        assertNotNull(result);
        assertEventDeepEquals(event, result);

        event = newEvent("uloha","kolej",null, timeFrom, timeTo);
        manager.createEvent(event);
        result = manager.getEvent(event.getId());
        assertNotNull(result);
        assertEventDeepEquals(event, result);

}

@Test
public void updateEvent() {

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

        Event event = newEvent("uloha","kolej","label", timeFrom, timeTo);
        Event event2 = newEvent("vyniest smeti","doma","label", timeFrom2, timeTo2);

        manager.createEvent(event);
        manager.createEvent(event2);
        Long eventId = event.getId();
        Event result;

        event = manager.getEvent(eventId);
        event.setname("Pepik");
        manager.updateEvent(event);
        result = manager.getEvent(eventId);
        assertEventDeepEquals(event, result);

        event = manager.getEvent(eventId);
        event.setplace("skola");
        manager.updateEvent(event);
        result = manager.getEvent(eventId);
        assertEventDeepEquals(event, result);

        event = manager.getEvent(eventId);
        event.setlabel("poznamka");
        manager.updateEvent(event);
        result = manager.getEvent(eventId);
        assertEventDeepEquals(event, result);

        event = manager.getEvent(eventId);
        event.setTimeFrom(timeFrom);
        manager.updateEvent(event);
        result = manager.getEvent(eventId);
        assertEventDeepEquals(event, result);

        event = manager.getEvent(eventId);
        event.setTimeTo(timeTo);
        manager.updateEvent(event);
        result = manager.getEvent(eventId);
        assertEventDeepEquals(event, result);

        // Check if updates didn't affected other records
        assertEventDeepEquals(event2, manager.getEvent(event2.getId()));
}

@Test
public void updateEventWithWrongAttributes() {

        Timestamp timestamp;

        Calendar timeFrom = Calendar.getInstance();
        timestamp = Timestamp.valueOf("2012-09-24 13:30:00.0");
        timeFrom.setTimeInMillis(timestamp.getTime());

        Calendar timeTo = Calendar.getInstance();
        timestamp = Timestamp.valueOf("2012-09-24 14:30:00.0");
        timeTo.setTimeInMillis(timestamp.getTime());

        Event event = newEvent("uloha","kolej","label", timeFrom, timeTo);

        manager.createEvent(event);

        try {
                manager.updateEvent(null);
                fail();
        } catch (IllegalArgumentException ex) {
                System.out.println("updateEvent null parameter exception");
        }

        try {
                event.setId(null);
                manager.updateEvent(event);
                fail();
        } catch (IllegalEntityException ex) {
                System.out.println("setId null parameter exception");
        }

        try {
                event.setname(null);
                manager.updateEvent(event);
                fail();
        } catch (ValidationException ex) {
                System.out.println("setname null parameter exception");
        }

        try {
                event.setTimeFrom(null);
                manager.updateEvent(event);
                fail();
        } catch (ValidationException ex) {
                System.out.println("setTimeFrom null parameter exception");
        }

        try {
                event.setTimeTo(null);
                manager.updateEvent(event);
                fail();
        } catch (ValidationException ex) {
                System.out.println("setTimeTo null parameter exception");
        }

        try {
                event.setTimeTo(timeTo);
                event.setTimeFrom(timeFrom);
                manager.updateEvent(event);
                fail();
        } catch (ValidationException ex) {
                System.out.println("setTimeFrom is later than setTimeTo parameter exception");
        }



}

@Test
public void deleteEvent() {

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

        Event event = newEvent("uloha","kolej","label", timeFrom, timeTo);
        Event event2 = newEvent("vyniest smeti","doma","label", timeFrom2, timeTo2);

        manager.createEvent(event);
        manager.createEvent(event2);

        assertNotNull(manager.getEvent(event.getId()));
        assertNotNull(manager.getEvent(event2.getId()));

        manager.deleteEvent(event);

        assertNull(manager.getEvent(event.getId()));
        assertNotNull(manager.getEvent(event2.getId()));

}

@Test
public void deleteEventWithWrongAttributes() {

        Timestamp timestamp;

        Calendar timeFrom = Calendar.getInstance();
        timestamp = Timestamp.valueOf("2012-09-24 13:30:00.0");
        timeFrom.setTimeInMillis(timestamp.getTime());

        Calendar timeTo = Calendar.getInstance();
        timestamp = Timestamp.valueOf("2012-09-24 14:30:00.0");
        timeTo.setTimeInMillis(timestamp.getTime());

        Event event = newEvent("uloha","kolej","label", timeFrom, timeTo);

        try {
                manager.deleteEvent(null);
                fail();
        } catch (IllegalArgumentException ex) {
                System.out.println("deleteEvent null event exception");
        }

        try {
                event.setId(null);
                manager.deleteEvent(event);
                fail();
        } catch (IllegalEntityException ex) {
                System.out.println("deleteEvent with null id exception");
        }

}

@Test
public void findAllEvents() {

        assertTrue(manager.findAllEvents().isEmpty());

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

        Event event = newEvent("uloha","kolej","label", timeFrom, timeTo);
        Event event2 = newEvent("vyniest smeti","doma","label", timeFrom2, timeTo2);

        manager.createEvent(event);
        manager.createEvent(event2);


        List<Event> expected = Arrays.asList(event,event2);
        List<Event> actual = manager.findAllEvents();

        assertEventCollectionDeepEquals(expected, actual);
}


static void assertEventDeepEquals(Event expected, Event actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getname(), actual.getname());
        assertEquals(expected.getplace(), actual.getplace());
        assertEquals(expected.getlabel(), actual.getlabel());
        assertEquals(expected.getTimeFrom(), actual.getTimeFrom());
        assertEquals(expected.getTimeTo(), actual.getTimeTo());
}

private static Comparator<Event> eventKeyComparator = new Comparator<Event>() {

        @Override
        public int compare(Event o1, Event o2) {
                Long k1 = o1.getId();
                Long k2 = o2.getId();
                if (k1 == null && k2 == null) {
                        return 0;
                } else if (k1 == null && k2 != null) {
                        return -1;
                } else if (k1 != null && k2 == null) {
                        return 1;
                } else {
                        return k1.compareTo(k2);
                }
        }
};

static void assertEventCollectionDeepEquals(List<Event> expected, List<Event> actual) {

        assertEquals(expected.size(), actual.size());
        List<Event> expectedSortedList = new ArrayList<Event>(expected);
        List<Event> actualSortedList = new ArrayList<Event>(actual);
        Collections.sort(expectedSortedList,eventKeyComparator);
        Collections.sort(actualSortedList,eventKeyComparator);
        for (int i = 0; i < expectedSortedList.size(); i++) {
                assertEventDeepEquals(expectedSortedList.get(i), actualSortedList.get(i));
        }
}
}

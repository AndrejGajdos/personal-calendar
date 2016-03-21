package pv168.mydates;

import java.util.Calendar;
import java.util.List;
import pv168.common.ServiceFailureException;

public interface CalendarManager {

/**
 * Returns list of all events by month.
 *
 * @return list of all events in database.
 * @throws  ServiceFailureException when db operation fails.
 */
List<Event> showEventsByMonth(Calendar cal) throws ServiceFailureException;

/**
 * Returns list of all events by week.
 *
 * @return list of all events in database.
 * @throws  ServiceFailureException when db operation fails.
 */
List<Event> showEventsByWeek(Calendar cal) throws ServiceFailureException;

}

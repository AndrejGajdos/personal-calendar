package pv168.mydates;

import java.util.List;
import pv168.common.ServiceFailureException;

public interface EventManager {


/**
 * Stores new Event into database. Id for the new Event is automatically
 * generated and stored into id attribute.
 *
 * @param Event Event to be created.
 * @throws IllegalArgumentException when Event is null, or Event has already
 * assigned id.
 * @throws  ServiceFailureException when db operation fails.
 */
void createEvent(Event Event) throws ServiceFailureException;

/**
 * Updates Event in database.
 *
 * @param Event updated Event to be stored into database.
 * @throws IllegalArgumentException when Event is null, or Event has null id.
 * @throws  ServiceFailureException when db operation fails.
 */
void updateEvent(Event Event) throws ServiceFailureException;

/**
 * Deletes Event from database.
 *
 * @param Event Event to be deleted from db.
 * @throws IllegalArgumentException when Event is null, or Event has null id.
 * @throws  ServiceFailureException when db operation fails.
 */
void deleteEvent(Event Event) throws ServiceFailureException;

/**
 * get Event from database.
 *
 * @param Long id find Event in db.
 * @throws IllegalArgumentException when Event is null, or Event has null id.
 * @throws  ServiceFailureException when db operation fails.
 */
Event getEvent(Long id) throws ServiceFailureException;

/**
 * Returns list of all events in the database.
 *
 * @return list of all bodies in database.
 * @throws ServiceFailureException when db operation fails.
 */
List<Event> findAllEvents() throws ServiceFailureException;

}

package pv168.mydates;

import java.io.IOException;
import java.sql.SQLException;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import pv168.common.DBUtils;

@SuppressWarnings("serial")
@WebServlet(
        name = "ActionServlet",
        urlPatterns = {
        ActionServlet.ACTION_LIST_EVENTS,
        ActionServlet.ACTION_ADD_EVENT
})
public class ActionServlet extends HttpServlet {

static final String ACTION_ADD_EVENT = "/AddEvent";
static final String ACTION_LIST_EVENTS = "/ListEvents";
static final String ATTRIBUTE_EVENTS = "events";
static final String ATTRIBUTE_EVENT_FORM = "eventForm";
static final String ATTRIBUTE_ERROR = "error";
static final String JSP_ADD_EVENT = "/WEB-INF/jsp/AddEvent.jsp";
static final String JSP_LIST_EVENTS = "/WEB-INF/jsp/ListEvents.jsp";

private EventManagerImpl eventManager = new EventManagerImpl();


@SuppressWarnings("unused")
@Resource(name="jdbc/EventManager")
private void setDataSource(DataSource ds) throws SQLException {
        DBUtils.tryCreateTables(ds,EventManager.class.getResource("createTables.sql"));
        eventManager.setDataSource(ds);
}

private void listEvents(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute(ATTRIBUTE_EVENTS, eventManager.findAllEvents());
        request.getRequestDispatcher(JSP_LIST_EVENTS).forward(request, response);
}


private void addEvent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getMethod().equals("POST")) {

                if (request.getParameter("cancel") != null) {
                        response.sendRedirect(request.getContextPath());
                        return;
                }

                EventForm eventForm = EventForm.extractFromRequest(request);

                StringBuilder errors = new StringBuilder();
                Event event = eventForm.validateAndToEvent(errors);

                if (event == null) {
                        request.setAttribute(ATTRIBUTE_ERROR, errors.toString());
                        request.setAttribute(ATTRIBUTE_EVENT_FORM, eventForm);
                        request.getRequestDispatcher(JSP_ADD_EVENT).forward(request, response);
                } else {
                        eventManager.createEvent(event);
                        response.sendRedirect(request.getContextPath());
                }

        } else {
                request.setAttribute(ATTRIBUTE_EVENT_FORM, new EventForm());
                request.getRequestDispatcher(JSP_ADD_EVENT).forward(request, response);
        }
}


protected void processRequest(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        if (request.getServletPath().equals(ACTION_LIST_EVENTS)) {
                listEvents(request, response);
        } else if (request.getServletPath().equals(ACTION_ADD_EVENT)) {
                addEvent(request, response);
        } else {
                throw new RuntimeException("Unknown operation: " + request.getServletPath());
        }
}

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
/**
 * Handles the HTTP
 * <code>GET</code> method.
 *
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
        processRequest(request, response);
}

/**
 * Handles the HTTP
 * <code>POST</code> method.
 *
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
throws ServletException, IOException {
        processRequest(request, response);
}

/**
 * Returns a short description of the servlet.
 *
 * @return a String containing servlet description
 */
@Override
public String getServletInfo() {
        return "Short description";
}    // </editor-fold>
}

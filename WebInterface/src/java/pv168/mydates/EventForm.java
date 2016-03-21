package pv168.mydates;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.http.HttpServletRequest;


public class EventForm {

private Long id;
private String name;
private String place;
private String label;
private String timeFrom;
private String timeTo;

public static EventForm extractFromRequest(HttpServletRequest request) {
        EventForm eventForm = new EventForm();
        String idString = request.getParameter("id");
        eventForm.setId(idString == null ? null : Long.parseLong(idString));
        eventForm.setname(request.getParameter("name"));
        eventForm.setplace(request.getParameter("place"));
        eventForm.setlabel(request.getParameter("label"));
        eventForm.setTimeFrom(request.getParameter("timeFrom"));
        eventForm.setTimeTo(request.getParameter("timeTo"));
        return eventForm;
}

private static Calendar stringToCalendar(String timeValue, String fieldName, StringBuilder errors){
        if (timeValue == null || timeValue.isEmpty()) {
                errors.append("Field '").append(fieldName).append("' is not filled").append(". <br />");
                return null;
        }

        try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                java.util.Date date;
                date = sdf.parse(timeValue);
                Calendar timest = new GregorianCalendar();
                timest.setTime(date);
                return timest;

        } catch (ParseException e) {
                errors.append("Field '").append(fieldName).append("' has wrong format: ").append(timeValue).append(". <br />");
                return null;
        }

}

public Event validateAndToEvent(StringBuilder errors) {
        Event event = new Event();

        if (getname() == null || getname().isEmpty()) {
                errors.append("Field '").append(" name ").append("' is not filled").append(". <br />");
                event.setname(null);
        }
        else
                event.setname(getname());


        if (getplace() == null || getplace().isEmpty())
                event.setplace(null);
        else
                event.setplace(getplace());


        if (getlabel() == null || getlabel().isEmpty())
                event.setlabel(null);
        else
                event.setlabel(getlabel());

        event.setTimeFrom(stringToCalendar(getTimeFrom(), "timeFrom", errors));
        event.setTimeTo(stringToCalendar(getTimeFrom(), "timeTo", errors));

        if (errors.length() > 0)
                return null;
        else
                return event;

}

public String getTimeTo() {
        return timeTo;
}

public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
}

public String getTimeFrom() {
        return timeFrom;
}

public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
}

public String getname() {
        return name;
}

public void setname(String name) {
        this.name = name;
}

public Long getId() {
        return id;
}

public void setId(Long id) {
        this.id = id;
}

public String getlabel() {
        return label;
}

public void setlabel(String label) {
        this.label = label;
}

public String getplace() {
        return place;
}

public void setplace(String place) {
        this.place = place;
}

}

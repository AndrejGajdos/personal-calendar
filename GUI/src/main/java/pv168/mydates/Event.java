package pv168.mydates;


import java.util.Calendar;

public class Event {

private Long id;
private String name;
private String place;
private String label;
private Calendar timeFrom;
private Calendar timeTo;

public Calendar getTimeTo() {
        return timeTo;
}

public void setTimeTo(Calendar timeTo) {
        this.timeTo = timeTo;
}

public Calendar getTimeFrom() {
        return timeFrom;
}

public void setTimeFrom(Calendar timeFrom) {
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

@Override
public String toString() {
        return "Event: " + name + " place= " + place + " label= " + label + " timeFrom= " + timeFrom.getTime() + " timeTo= " + timeTo.getTime();
}

@Override
public boolean equals(Object obj) {
        if (obj == null) {
                return false;
        }
        if (getClass() != obj.getClass()) {
                return false;
        }
        final Event other = (Event) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
                return false;
        }
        return true;
}

@Override
public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
}

}

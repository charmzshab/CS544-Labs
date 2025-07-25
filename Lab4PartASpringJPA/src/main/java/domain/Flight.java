package domain;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Flight() {
    }

    public Flight(String flightNumber, String to, String from) {
        this.flightNumber = flightNumber;
        this.to = to;
        this.from = from;
        this.date = new Date();
    }

    private String flightNumber;
    @Column(name = "destination", nullable = false, length = 30)
    private String to;
    @Column(name = "departure", nullable = false, length = 30)
    private String from;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "flight_date", nullable = false, length = 19)
    private Date date;

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", flightNumber='" + flightNumber + '\'' +
                ", to='" + to + '\'' +
                ", from='" + from + '\'' +
                ", date=" + date +
                '}';
    }
}

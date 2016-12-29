package bsr.history;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Calendar;
import java.util.Date;

public class Operation {
    private final SimpleStringProperty title;
    private final SimpleStringProperty type;
    private final SimpleDoubleProperty amount;
    private final SimpleDoubleProperty balance;
    private final SimpleStringProperty sourceNrb;
    private final ObjectProperty<Date> date;

    public Operation(String title, String type, Integer amount, Integer balance, String sourceNrb, Date date) {
        this.title = new SimpleStringProperty(title);
        this.type = new SimpleStringProperty(type);
        this.amount = new SimpleDoubleProperty(amount/100.0);
        this.balance = new SimpleDoubleProperty(balance/100.0);
        this.sourceNrb = new SimpleStringProperty(sourceNrb);
        this.date = new SimpleObjectProperty<>(this, "date", date);
    }

    public Operation(pl.bank.bsr.Operation responseOperation){
        this.title = new SimpleStringProperty(responseOperation.getTitle());
        this.type = new SimpleStringProperty(responseOperation.getType());
        this.amount = new SimpleDoubleProperty(responseOperation.getAmount()/100.0);
        this.balance = new SimpleDoubleProperty(responseOperation.getBalance()/100.0);
        this.sourceNrb = new SimpleStringProperty(responseOperation.getSourceNrb());
        XMLGregorianCalendar gregCal = responseOperation.getDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(gregCal.toGregorianCalendar().getTime());
        this.date = new SimpleObjectProperty<>(this, "date", cal.getTime());
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public double getAmount() {
        return amount.get();
    }

    public SimpleDoubleProperty amountProperty() {
        return amount;
    }

    public double getBalance() {
        return balance.get();
    }

    public SimpleDoubleProperty balanceProperty() {
        return balance;
    }

    public String getSourceNrb() {
        return sourceNrb.get();
    }

    public SimpleStringProperty sourceNrbProperty() {
        return sourceNrb;
    }

    public Date getDate() {
        return date.get();
    }

    public ObjectProperty<Date> dateProperty() {
        return date;
    }
}

package bsr.history;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by marcin on 01.01.17.
 */
public class MyDate extends Date {
    private static final DateFormat dateFormat = new SimpleDateFormat("HH:mm dd-MM-yyyy");

    public MyDate(long date) {
        super(date);
    }

    public String toString(){
        return dateFormat.format(this);
    }
}

package service.android.google.com.accessibility.util.date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * @author Created by trijckaert
 */
@RunWith(MockitoJUnitRunner.class)
public class DateFormatterTest {

    private DateFormatter dateFormatter;

    private Calendar calendar;

    @Before
    public void setUp() throws Exception {
        dateFormatter = new DateFormatter();
        calendar = Calendar.getInstance();
    }

    @Test
    public void test_dateToString() throws Exception {
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 1);
        assertThat(dateFormatter.dateToString(calendar.getTime()), is(new SimpleDateFormat("dd/MM/yyyy HH:mm", new Locale("nl", "BE")).format(calendar.getTime())));
    }
}
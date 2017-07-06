package com.example.hp4740.testapp.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp4740.testapp.api.Ticket;
import com.example.hp4740.testapp.R;
import com.example.hp4740.testapp.tickets.Tickets;
import com.example.hp4740.testapp.tickets.TicketsCallback;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    static private String TAG = "MainActivity";
    final static Calendar calendar = Calendar.getInstance();
    private static int CURRENT_YEAR = calendar.get(Calendar.YEAR);
    private static int CURRENT_MONTH = calendar.get(Calendar.MONTH);
    private static int CURRENT_DAY = calendar.get(Calendar.DAY_OF_MONTH);
    private static int YEAR_FROM = calendar.get(Calendar.YEAR);
    private static int MONTH_FROM = calendar.get(Calendar.MONTH);
    private static int DAY_FROM = calendar.get(Calendar.DAY_OF_MONTH);
    private static int YEAR_TO = calendar.get(Calendar.YEAR);
    private static int MONTH_TO = calendar.get(Calendar.MONTH);
    private static int DAY_TO = calendar.get(Calendar.DAY_OF_MONTH);
    private static int COUNTER = 0;
    List<DateTickets> dateTickets = new ArrayList<>();
    List<Ticket> resultList = new ArrayList<>();
    List<Ticket> resultListCopy = new ArrayList<>();
    String cityFrom;
    String cityTo;
    String dateFrom;
    String dateTo;
    TextView cityFromEditor;
    TextView cityToEditor;
    TextView dateFromEditor;
    TextView dateToEditor;
    Button search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cityFromEditor = (TextView) findViewById(R.id.cityFromEditor);
        cityToEditor = (TextView) findViewById(R.id.cityToEditor);
        dateFromEditor = (TextView) findViewById(R.id.dateFromEditor);
        dateToEditor = (TextView) findViewById(R.id.dateToEditor);
        search = (Button) findViewById(R.id.searchButton);
    }

    public void search(View v) {
        search.setEnabled(false);
        resultList.clear();
        resultListCopy.clear();
        dateTickets.clear();
        COUNTER = 0;
        cityFrom = cityFromEditor.getText().toString();
        cityTo = cityToEditor.getText().toString();
        dateFrom = dateFromEditor.getText().toString();
        dateTo = dateToEditor.getText().toString();
        if (cityFrom.equals("")) {
            search.setEnabled(true);
            Toast toast = Toast.makeText(getApplicationContext(), "Enter the city you want to fly from!", Toast.LENGTH_SHORT);
            toast.show();
        } else if (cityTo.equals("")) {
            search.setEnabled(true);
            Toast toast = Toast.makeText(getApplicationContext(), "Enter the city you want to fly to!", Toast.LENGTH_SHORT);
            toast.show();
        } else if (dateFrom.equals("")) {
            search.setEnabled(true);
            Toast toast = Toast.makeText(getApplicationContext(), "Enter first flight date!", Toast.LENGTH_SHORT);
            toast.show();
        } else if (dateTo.equals("")) {
            Tickets.get(cityFrom, cityTo, dateFrom, new TicketsCallback() {
                @Override
                public void handleTickets(List<Ticket> tickets) {
                    findFastestAndCheapestTickets(tickets);
                    sortByPriceMultiplyDurationAndSetBest(tickets);
                    final ListView listView = (ListView) findViewById(R.id.listView);
                    final TicketsAdapter appsAdapter = new TicketsAdapter(MainActivity.this);
                    appsAdapter.setData(tickets);
                    listView.setAdapter(appsAdapter);
                    search.setEnabled(true);
                }
            });
        } else {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.US);
                Date from = sdf.parse(dateFrom);
                Date to = sdf.parse(dateTo);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(from);
                sdf.applyPattern("yyyy-MM-dd");

                while (calendar.getTime().before(to) || calendar.getTime().equals(to)) {
                    dateTickets.add(new DateTickets(sdf.format(calendar.getTime())));
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                }

                sdf.applyPattern("yyyyMMdd");
                calendar.setTime(from);

                while (calendar.getTime().before(to) || calendar.getTime().equals(to)) {
                    Tickets.get(cityFrom, cityTo, dateFrom, new TicketsCallback() {
                        @Override
                        public void handleTickets(final List<Ticket> tickets) {
                            findFastestAndCheapestTickets(tickets);
                            sortByPriceMultiplyDurationAndSetBest(tickets);
                            for (DateTickets dt : dateTickets) {
                                if (dt.getDate().equals(tickets.get(0).getDepDate())) {
                                    dt.setTickets(tickets);
                                }
                            }
                            COUNTER++;
                            if (COUNTER == dateTickets.size()) {
                                for (DateTickets dt : dateTickets) {
                                    resultList.addAll(dt.getTickets());
                                }
                                resultListCopy = new ArrayList<Ticket>(resultList);
                                /*Collections.copy(resultListCopy, resultList);*/
                                final ListView listView = (ListView) findViewById(R.id.listView);
                                final TicketsAdapter appsAdapter = new TicketsAdapter(MainActivity.this);
                                appsAdapter.setData(resultListCopy);
                                listView.setAdapter(appsAdapter);
                                //TODO: copy here!
                                search.setEnabled(true);
                            }
                        }
                    });
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                    dateFrom = sdf.format(calendar.getTime());
                }
            } catch (ParseException e) {
                Log.e(TAG, "parsing failed!");
            }
        }
    }


    private static void sortByPriceMultiplyDurationAndSetBest(List<Ticket> tickets) {
        Collections.sort(tickets, Ticket.SORTING_BY_PRICE_MULTIPLY_DURATION);
        tickets.get(0).setBest(true);
    }

    private static void findFastestAndCheapestTickets(List<Ticket> tickets) {
        List<Integer> indexesOfCheapestNonStop = new ArrayList<>();
        indexesOfCheapestNonStop.add(-1);
        List<Integer> indexesOfCheapestWithStops = new ArrayList<>();
        indexesOfCheapestWithStops.add(-1);
        List<Integer> indexesOfFastest = new ArrayList<>();
        indexesOfFastest.add(0);

        for (int i = 0; i < tickets.size(); i++) {
            if (tickets.get(i).getAirlineCode2() == null && indexesOfCheapestNonStop.get(0) == -1) {
                indexesOfCheapestNonStop.set(0, i);
            } else if (tickets.get(i).getAirlineCode2() != null && indexesOfCheapestWithStops.get(0) == -1) {
                indexesOfCheapestWithStops.set(0, i);
            }
            if (indexesOfCheapestNonStop.get(0) != -1 && indexesOfCheapestWithStops.get(0) != -1) {
                break;
            }
        }

        for (int i = 1; i < tickets.size(); i++) {
            if (tickets.get(i).getDuration() < tickets.get(indexesOfFastest.get(0)).getDuration()) {
                indexesOfFastest.clear();
                indexesOfFastest.add(i);
            } else if (tickets.get(i).getDuration() == tickets.get(indexesOfFastest.get(0)).getDuration()) {
                indexesOfFastest.add(i);
            }
            if (tickets.get(i).getAirlineCode2() == null) {
                if (tickets.get(i).getPrice() < tickets.get(indexesOfCheapestNonStop.get(0)).getPrice()) {
                    indexesOfCheapestNonStop.clear();
                    indexesOfCheapestNonStop.add(i);
                } else if (tickets.get(i).getPrice() == tickets.get(indexesOfCheapestNonStop.get(0)).getPrice()) {
                    indexesOfCheapestNonStop.add(i);
                }
            } else {
                if (tickets.get(i).getPrice() < tickets.get(indexesOfCheapestWithStops.get(0)).getPrice()) {
                    indexesOfCheapestWithStops.clear();
                    indexesOfCheapestWithStops.add(i);
                } else if (tickets.get(i).getPrice() == tickets.get(indexesOfCheapestWithStops.get(0)).getPrice()) {
                    indexesOfCheapestWithStops.add(i);
                }
            }
        }

        for (int i : indexesOfCheapestNonStop) {
            tickets.get(i).setCheapestNonStop(true);
        }
        for (int i : indexesOfCheapestWithStops) {
            tickets.get(i).setCheapestWithStops(true);
        }
        for (int i : indexesOfFastest) {
            tickets.get(i).setFastest(true);
        }
    }

    private DatePickerDialog.OnDateSetListener onDateFromSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            Calendar calendar = Calendar.getInstance();
            YEAR_FROM = year;
            MONTH_FROM = month;
            DAY_FROM = day;
            calendar.set(year, month, day);
            search.setEnabled(true);
            setDateFrom(calendar);
        }
    };

    private DatePickerDialog.OnDateSetListener onDateToSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            Calendar calendar = Calendar.getInstance();
            YEAR_TO = year;
            MONTH_TO = month;
            DAY_TO = day;
            calendar.set(year, month, day);
            search.setEnabled(true);
            setDateTo(calendar);
        }
    };

    public void onCityFromClick(View view) {
        search.setEnabled(true);
    }

    public void onCityToClick(View view) {
        search.setEnabled(true);
    }

    public void onDateFromClick(View view) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setListener(onDateFromSetListener);
        fragment.setIdentifier("from");
        fragment.show(getSupportFragmentManager(), "date");
    }

    public void onDateToClick(View view) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setListener(onDateToSetListener);
        fragment.setIdentifier("to");
        fragment.show(getSupportFragmentManager(), "date");
    }

    private void setDateFrom(final Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.US);
        ((TextView) findViewById(R.id.dateFromEditor)).setText(sdf.format(calendar.getTime()));
    }

    private void setDateTo(final Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.US);
        ((TextView) findViewById(R.id.dateToEditor)).setText(sdf.format(calendar.getTime()));
    }

    private static class DateTickets {
        private String date;
        private List<Ticket> tickets;

        DateTickets(String date) {
            this.date = date;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public List<Ticket> getTickets() {
            return tickets;
        }

        public void setTickets(List<Ticket> tickets) {
            this.tickets = tickets;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            DateTickets that = (DateTickets) o;

            if (date != null ? !date.equals(that.date) : that.date != null) return false;
            return tickets != null ? tickets.equals(that.tickets) : that.tickets == null;

        }

        @Override
        public int hashCode() {
            int result = date != null ? date.hashCode() : 0;
            result = 31 * result + (tickets != null ? tickets.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "DateTickets{" +
                    "date='" + date + '\'' +
                    ", tickets=" + tickets +
                    '}';
        }
    }

    public static class DatePickerFragment extends DialogFragment {
        private DatePickerDialog.OnDateSetListener listener;
        private String identifier;

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            if (identifier.equals("from")) {
                calendar.set(CURRENT_YEAR, CURRENT_MONTH, CURRENT_DAY);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), listener, YEAR_FROM, MONTH_FROM, DAY_FROM);
                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis() - 1000);
                return datePickerDialog;
            } else {
                calendar.set(YEAR_FROM, MONTH_FROM, DAY_FROM);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), listener, YEAR_TO, MONTH_TO, DAY_TO);
                datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis() - 1000);
                return datePickerDialog;
            }
        }

        public void setListener(DatePickerDialog.OnDateSetListener listener) {
            this.listener = listener;
        }

        public void setIdentifier(String identifier) {
            this.identifier = identifier;
        }
    }
}
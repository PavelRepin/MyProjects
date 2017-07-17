package com.example.hp4740.testapp.activity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp4740.testapp.R;
import com.example.hp4740.testapp.api.TicketEntity;
import com.example.hp4740.testapp.domain.Ticket;
import com.example.hp4740.testapp.tickets.Cancellable;
import com.example.hp4740.testapp.tickets.TicketsCallback;
import com.example.hp4740.testapp.tickets.TicketsService;
import com.example.hp4740.testapp.utils.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.hp4740.testapp.R.id.listView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String DATE_FORMAT = "yyyyMMdd";


    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.US);

    private Map<Long, List<Ticket>> currentResponses = new HashMap<>();

    private final List<Cancellable> currentRequests = new ArrayList<>();

    @BindView(R.id.cityFromEditor)
    TextView cityFromEditor;
    @BindView(R.id.cityToEditor)
    TextView cityToEditor;
    @BindView(R.id.dateFromEditor)
    TextView dateFromEditor;
    @BindView(R.id.dateToEditor)
    TextView dateToEditor;
    @BindView(R.id.searchButton)
    Button search;

    @BindView(listView)
    ListView list;



    TicketsAdapter ticketsAdapter;

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://android-dev-tests.ru")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ticketsAdapter = new TicketsAdapter(this);
        list.setAdapter(ticketsAdapter);
    }

    @OnClick(R.id.searchButton)
    public void onSearchClicked(View view) {
        search.setEnabled(false);

        final String cityFromStr = cityFromEditor.getText().toString();
        final String cityToStr = cityToEditor.getText().toString();
        final String dateFromStr = dateFromEditor.getText().toString();
        final String dateToStr = dateToEditor.getText().toString();

        int errorMsg = validateInput(cityFromStr, cityToStr, dateFromStr);
        if (errorMsg != -1) {
            search.setEnabled(true);
            Toast.makeText(getApplicationContext(), errorMsg, Toast.LENGTH_SHORT)
                 .show();
            return;
        }

        final Date dateFrom = (Date) dateFromEditor.getTag();
        if (StringUtils.isEmptyOrBlank(dateToStr)) {
            searchTicketsForSingleDate(cityFromStr,
                                       cityToStr,
                                       dateFrom);
        } else {
            final Date dateTo = (Date) dateToEditor.getTag();
            searchTicketsForRange(cityFromStr, cityToStr, dateFrom, dateTo);
        }
    }

    private void searchTicketsForSingleDate(final String cityFromStr,
                                            final String cityToStr,
                                            final Date date) {

        searchTicketsForRange(cityFromStr, cityToStr, date, date);
    }

    private void searchTicketsForRange(final String cityFromStr,
                                       final String cityToStr,
                                       final Date dateFrom,
                                       final Date dateTo) {
        resetCurrentRequests();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateFrom);

        while (calendar.getTime().before(dateTo) || calendar.getTime().equals(dateTo)) {

            final TicketsCallback ticketsCallback = createCallbackForDate(calendar.getTime());

            currentRequests.add(new TicketsService(retrofit).get(cityFromStr,
                                                                 cityToStr,
                                                                 calendar.getTime(),
                                                                 ticketsCallback));

            calendar.add(Calendar.DAY_OF_MONTH, 1);
        }
    }

    private TicketsCallback createCallbackForDate(final Date date) {
        final long dateInMs = date.getTime();

        currentResponses.put(dateInMs, null);

        return new TicketsCallback() {
            @MainThread
            @Override
            public void handleTickets(final List<TicketEntity> ticketEntities) {
                validateMainThread();

                List<Ticket> tickets = mapToTicketsAndSort(ticketEntities);

                currentResponses.put(dateInMs, tickets);

                showDataIfLastResponse();
            }

            @MainThread
            @Override
            public void handleError(final Throwable error) {
                validateMainThread();

                Toast.makeText(MainActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_LONG)
                     .show();

                currentResponses.put(dateInMs, Collections.emptyList());

                showDataIfLastResponse();

            }

            @MainThread
            private void showDataIfLastResponse() {
                validateMainThread();

                final List<Ticket> resultList = new ArrayList<>();
                final Set<Long> sortedDates = new TreeSet<>(currentResponses.keySet());

                for (final Long date : sortedDates) {
                    List<Ticket> tickets = currentResponses.get(date);
                    if (tickets == null) return; // if response is not last - do nothing.


                    resultList.addAll(tickets);
                }

                ticketsAdapter.setData(resultList);
                search.setEnabled(true);
            }
        };
    }

    private static void validateMainThread() {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            throw new IllegalStateException("not main thread");
        }

    }

    private List<Ticket> mapToTicketsAndSort(final List<TicketEntity> ticketEntities) {
        final List<Ticket> tickets = mapToTickets(ticketEntities);

        Collections.sort(tickets, Ticket.SORTING_BY_PRICE_MULTIPLY_DURATION);

        if (!tickets.isEmpty()) {
            Ticket firstTicket = tickets.get(0);
            tickets.set(0, firstTicket.withIsBest(true));
        }

        return tickets;
    }

    private void resetCurrentRequests() {
        for (final Cancellable currentRequest : currentRequests) {
            currentRequest.cancel();
        }

        currentRequests.clear();
        currentResponses.clear();
    }

    @StringRes
    private int validateInput(final String cityFromStr,
                              final String cityToStr,
                              final String dateFromStr) {

        if (StringUtils.isEmptyOrBlank(cityFromStr)) {
            return R.string.msg_invalid_city_from;
        } else if (StringUtils.isEmptyOrBlank(cityToStr)) {
            return R.string.msg_invalid_city_to;
        } else if (StringUtils.isEmptyOrBlank(dateFromStr)) {
            return R.string.msg_invalid_date_from;
        } else {
            return  -1;
        }
    }

    private static List<Ticket> mapToTickets(List<TicketEntity> ticketEntities) {

        final double lowestPriceWithStop = findLowestPrice(ticketEntities, true);
        final double lowestPriceNonStop = findLowestPrice(ticketEntities, false);
        final double minDuration = findMinDuration(ticketEntities);

        List<Ticket> tickets = new ArrayList<>();

        for (final TicketEntity ticketEntity : ticketEntities) {
            boolean isCheapestNonStop = ticketEntity.getAirlineCode2() == null
                    && lowestPriceNonStop == ticketEntity.getPrice();

            boolean isCheapestWithStops = ticketEntity.getAirlineCode2() != null
                    && lowestPriceWithStop == ticketEntity.getPrice();

            boolean isFastest = ticketEntity.getDuration() == minDuration;

            tickets.add(Ticket.of(ticketEntity, isFastest, isCheapestNonStop, isCheapestWithStops));
        }

        return tickets;
    }

    private static long findMinDuration(final List<TicketEntity> tickets) {
        if (tickets.isEmpty()) return -1;


        long res = tickets.get(0).getDuration();

        for (final TicketEntity ticket : tickets) {
                long duration = ticket.getDuration();
                if (duration < res) {
                    res = duration;
                }
            }

        return res;
    }

    private static double findLowestPrice(final List<TicketEntity> tickets, boolean withStops) {
        double res = -1.0;
        for (final TicketEntity ticket : tickets) {
            final boolean hasStops = ticket.getAirlineCode2() != null;

            if (hasStops == withStops) {
                double price = ticket.getPrice();

                if (price < 0) throw new IllegalStateException("Invalid price");

                if (res < 0) {
                    res = price;
                } else if (price < res) {
                    res = price;
                }
            }
        }

        return res;

    }

    public void onCityFromClick(View view) {
        search.setEnabled(true);
    }

    public void onCityToClick(View view) {
        search.setEnabled(true);
    }

    @OnClick(R.id.dateFromEditor)
    public void onDateFromClick(View view) {
        final Date dateFrom = (Date) dateFromEditor.getTag();

        showDatePicker(dateFrom != null ? DateHolder.of(dateFrom) : null,
                       null,
                       (datePicker, year, month, day) -> {

                           search.setEnabled(true);

                           updateDateFrom(new DateHolder(year, month, day));
                       });
    }


    @OnClick(R.id.dateToEditor)
    public void onDateToClick(View view) {
        final Date dateTo = (Date) dateToEditor.getTag();
        final Date dateFrom = (Date) dateFromEditor.getTag();

        showDatePicker(dateTo != null ? DateHolder.of(dateTo) : null,
                       dateFrom != null ? DateHolder.of(dateFrom) : null,
                       (datePicker, year, month, day) -> {

                           search.setEnabled(true);

                           updateDateTo(new DateHolder(year, month, day));
                       });
    }

    private void showDatePicker(@Nullable final DateHolder date,
                                @Nullable final DateHolder dateMin,
                                final DatePickerDialog.OnDateSetListener onDateFromSetListener) {
        DatePickerFragment fragment = new DatePickerFragment();
        fragment.setListener(onDateFromSetListener);
        fragment.setDate(date);
        fragment.setDateMin(dateMin);
        fragment.show(getSupportFragmentManager(), "date");
    }

    private void updateDateFrom(@NonNull final DateHolder date) {
        updateDate(date, dateFromEditor);
    }

    private void updateDateTo(@NonNull final DateHolder date) {
        updateDate(date, dateToEditor);
    }

    private void updateDate(@NonNull final DateHolder dateHolder,
                            @NonNull final TextView tvDate) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(dateHolder.getYear(), dateHolder.getMonth(), dateHolder.getDay());

        Date date = calendar.getTime();
        tvDate.setText(simpleDateFormat.format(date));
        tvDate.setTag(date);
    }

}
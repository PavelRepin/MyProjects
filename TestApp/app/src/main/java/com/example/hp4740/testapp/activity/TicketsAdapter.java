package com.example.hp4740.testapp.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.hp4740.testapp.R;
import com.example.hp4740.testapp.domain.Ticket;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

class TicketsAdapter extends BaseAdapter {
    private Context context;
    private List<Ticket> tickets;

    private static final String DATE_PATTERN = "MMM d, ''yy";
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_PATTERN,
                                                                                    Locale.US);

    TicketsAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Ticket> tickets) {
        this.tickets = new ArrayList<>(tickets);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return tickets == null ? 0 : tickets.size();
    }

    @Override
    public Object getItem(int position) {
        return tickets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return tickets.get(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.header = (TextView) convertView.findViewById(R.id.header);
            holder.flightCode = (TextView) convertView.findViewById(R.id.flightCodeText);
            holder.flightDuration = (TextView) convertView.findViewById(R.id.flightDurationText);
            holder.flightPrice = (TextView) convertView.findViewById(R.id.flightPriceText);
            holder.bestTicket = (TextView) convertView.findViewById(R.id.bestTicket);
            holder.fastest = (TextView) convertView.findViewById(R.id.fastest);
            holder.cheapestWithStops = (TextView) convertView.findViewById(R.id.cheapestWithStops);
            holder.cheapestNonStop = (TextView) convertView.findViewById(R.id.cheapestNonStop);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Ticket ticket = tickets.get(position);
        holder.flightCode.setText(ticket.getCode());
        holder.flightDuration.setText(Long.toString(ticket.getDuration()));
        holder.flightPrice.setText(Double.toString(ticket.getPrice()));
        holder.bestTicket.setVisibility(ticket.isBest() ? View.VISIBLE : View.GONE);
        holder.header.setVisibility(ticket.isBest() ? View.VISIBLE : View.GONE);
        holder.header.setText(SIMPLE_DATE_FORMAT.format(ticket.getDepDate()));
        holder.fastest.setVisibility(ticket.isFastest() ? View.VISIBLE : View.GONE);
        holder.cheapestNonStop.setVisibility(ticket.isCheapestNonStop() ? View.VISIBLE
                                                                        : View.GONE);
        holder.cheapestWithStops.setVisibility(ticket.isCheapestWithStops() ? View.VISIBLE
                                                                            : View.GONE);
        return convertView;
    }

    private static class ViewHolder {
        TextView header;
        TextView flightCode;
        TextView flightDuration;
        TextView flightPrice;
        TextView bestTicket;
        TextView fastest;
        TextView cheapestWithStops;
        TextView cheapestNonStop;
    }
}

package com.lindar.thunderkick.util.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class NonIsoInstantTypeAdapter extends TypeAdapter<Instant> {
    private static final String DEF_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    @Override public void write(JsonWriter out, Instant value) throws IOException {
        SimpleDateFormat formatter = new SimpleDateFormat(DEF_DATE_FORMAT);
        out.value(formatter.format(Date.from(value)));
    }

    @Override public Instant read(JsonReader in) throws IOException {
        String dateString = in.nextString();
        if (StringUtils.isNotBlank(dateString)) {
            if (NumberUtils.isDigits(dateString) && dateString.length() > 4) {
                return Instant.ofEpochMilli(Long.parseLong(dateString));
            }
            SimpleDateFormat formatter = new SimpleDateFormat(DEF_DATE_FORMAT);
            try {
                return formatter.parse(dateString).toInstant();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

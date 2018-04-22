package com.lindar.thunderkick.util.adapters;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import org.apache.commons.lang3.math.NumberUtils;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;

public class NonIsoInstantSerializer implements JsonDeserializer<Instant> {
    private static final String DEF_DATE_FORMAT = "yyyy-MM-dd'T'hh:mm:ss.SSSZ";

    @Override
    public Instant deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
        String date = element.getAsString();
        if (NumberUtils.isDigits(date) && date.length() > 4) {
            return Instant.ofEpochMilli(Long.parseLong(date));
        }
        SimpleDateFormat formatter = new SimpleDateFormat(DEF_DATE_FORMAT);
        try {
            return formatter.parse(date).toInstant();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}

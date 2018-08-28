package com.lindar.thunderkick.util.adapters;

import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class NonIsoInstantDeserializer implements JsonSerializer<Instant> {
    private static final String DEF_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    @Override
    public JsonElement serialize(Instant src, Type arg1, JsonSerializationContext arg2) throws JsonParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(DEF_DATE_FORMAT);
        return new JsonPrimitive(formatter.format(Date.from(src)));
    }
}

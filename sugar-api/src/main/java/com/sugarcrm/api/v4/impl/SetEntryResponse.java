package com.sugarcrm.api.v4.impl;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.sugarcrm.api.SugarResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by seb on 28/07/16.
 */
public class SetEntryResponse implements SugarResponse {

    private final static Logger LOG = LoggerFactory.getLogger(SetEntryResponse.class);

    protected String id;

    @SerializedName("entry_list")
    protected SugarBean entry;

    public String getId() {
        return id;
    }

    public com.sugarcrm.api.SugarBean getEntry() {
        return entry;
    }

//    public static class SetEntryResponseDeserilizer implements JsonDeserializer<SetEntryResponse> {
//
//        @Override
//        public  deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//            Options options = new Gson().fromJson(json, Options.class);
//            JsonObject jsonObject = json.getAsJsonObject();
//
//            if (jsonObject.has("option_value")) {
//                JsonElement elem = jsonObject.get("option_value");
//                if (elem != null && !elem.isJsonNull()) {
//                    String valuesString = elem.getAsString();
//                    if (!TextUtils.isEmpty(valuesString)){
//                        List<OptionValue> values = new Gson().fromJson(valuesString, new TypeToken<ArrayList<OptionValue>>() {}.getType());
//                        options.setOptionValues(values);
//                    }
//                }
//            }
//            return options ;
//        }
//    }



}

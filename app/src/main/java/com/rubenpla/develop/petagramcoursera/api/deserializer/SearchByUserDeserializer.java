package com.rubenpla.develop.petagramcoursera.api.deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.rubenpla.develop.petagramcoursera.api.constants.JsonKeys;
import com.rubenpla.develop.petagramcoursera.mvp.model.SearchByUserModel;
import com.rubenpla.develop.petagramcoursera.mvp.model.SearchByUserModelResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SearchByUserDeserializer implements JsonDeserializer<SearchByUserModelResponse> {
    @Override
    public SearchByUserModelResponse deserialize(JsonElement json, Type typeOfT,
                                                JsonDeserializationContext context)
            throws JsonParseException {

        Gson gson = new Gson();
        SearchByUserModelResponse userInfoResponse = gson.fromJson(json, SearchByUserModelResponse.class);

        JsonArray searchByUserArrayObject = json.getAsJsonObject()
                .getAsJsonArray(JsonKeys.USER_RESPONSE_OBJECT);

        userInfoResponse.setUserResultsList(deserialize(searchByUserArrayObject));

        return userInfoResponse;
    }

    private ArrayList<SearchByUserModel> deserialize(JsonArray searchByUserResponseData) {
    //    JsonObject searchByUserResponseData = userProfileResponseData.getAsJsonObject();
        ArrayList<SearchByUserModel> userResultList = new ArrayList<>();

        for (int i = 0; i < searchByUserResponseData.size() ; i++) {
            JsonObject searchByUserResponseDataObject = searchByUserResponseData.get(i)
                    .getAsJsonObject();
            
            String fullName = searchByUserResponseDataObject.get(JsonKeys.USER_NAME).getAsString();
            String userId = searchByUserResponseDataObject.get(JsonKeys.USER_ID).getAsString();
            String profilePicture = searchByUserResponseDataObject.get(JsonKeys.USER_PROFILE_PICTURE).getAsString();

            SearchByUserModel profileInfo = new SearchByUserModel();
            profileInfo.setUserName(fullName);
            profileInfo.setUserId(userId);
            profileInfo.setUrlAvatar(profilePicture);

            userResultList .add(profileInfo);
        }

        return userResultList;
    }
}

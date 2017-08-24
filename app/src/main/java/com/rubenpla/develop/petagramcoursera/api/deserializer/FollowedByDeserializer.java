package com.rubenpla.develop.petagramcoursera.api.deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.rubenpla.develop.petagramcoursera.api.constants.JsonKeys;
import com.rubenpla.develop.petagramcoursera.mvp.model.FollowedByUser;
import com.rubenpla.develop.petagramcoursera.mvp.model.FollowedByUserModelResponse;
import com.rubenpla.develop.petagramcoursera.mvp.model.PetModel;
import com.rubenpla.develop.petagramcoursera.mvp.model.PetModelResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class FollowedByDeserializer implements JsonDeserializer {

    @Override
    public FollowedByUserModelResponse deserialize(JsonElement json, Type typeOfT,
                                        JsonDeserializationContext context)
            throws JsonParseException {

        Gson gson = new Gson();
        FollowedByUserModelResponse followedByUserModelResponse = gson.fromJson(json,
                FollowedByUserModelResponse.class);
        JsonArray followedByUserModelResponseData = json.getAsJsonObject()
                .getAsJsonArray(JsonKeys.FOLLOWED_BY_RESPONSE_ARRAY);

        followedByUserModelResponse.setFollowersList(deserialize(followedByUserModelResponseData));
        return followedByUserModelResponse;
    }

    private ArrayList<FollowedByUser> deserialize(JsonArray followedByResponseData){
        ArrayList<FollowedByUser> followers = new ArrayList<>();
        for (int i = 0; i < followedByResponseData.size() ; i++) {
            JsonObject followedByResponseDataObject = followedByResponseData.get(i).getAsJsonObject();

            String id               = followedByResponseDataObject.get(JsonKeys.USER_ID).getAsString();

            FollowedByUser currentFollower = new FollowedByUser();
            currentFollower.setUserId(id);

            followers.add(currentFollower);
        }

        return followers;
    }
}

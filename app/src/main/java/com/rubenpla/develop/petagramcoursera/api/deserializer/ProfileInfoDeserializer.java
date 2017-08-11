package com.rubenpla.develop.petagramcoursera.api.deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.rubenpla.develop.petagramcoursera.api.constants.JsonKeys;
import com.rubenpla.develop.petagramcoursera.mvp.model.UserProfileModelResponse;
import com.rubenpla.develop.petagramcoursera.mvp.model.UserProfile;

import java.lang.reflect.Type;

public class ProfileInfoDeserializer implements JsonDeserializer<UserProfileModelResponse> {
    @Override
    public UserProfileModelResponse deserialize(JsonElement json, Type typeOfT,
                                                JsonDeserializationContext context)
            throws JsonParseException {

        Gson gson = new Gson();
        UserProfileModelResponse userInfoResponse = gson.fromJson(json, UserProfileModelResponse.class);
        JsonObject petModelResponseData = json.getAsJsonObject()
                .getAsJsonObject(JsonKeys.USER_RESPONSE_OBJECT);

        userInfoResponse.setUser(deserialize(petModelResponseData));
        return userInfoResponse;
    }

    private UserProfile deserialize(JsonObject userProfileResponseData) {
        JsonObject userInfoResponseDataObject = userProfileResponseData.getAsJsonObject();

        String fullName = userInfoResponseDataObject.get(JsonKeys.USER_FULLNAME).getAsString();
        String profilePicture = userProfileResponseData.get(JsonKeys.USER_PROFILE_PICTURE).getAsString();

        UserProfile userProfile = new UserProfile();
        userProfile.setUserName(fullName);
        userProfile.setUrlAvatar(profilePicture);

        return userProfile;
    }
}
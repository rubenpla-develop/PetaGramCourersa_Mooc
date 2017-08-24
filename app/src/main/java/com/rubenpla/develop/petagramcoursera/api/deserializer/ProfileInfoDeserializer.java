package com.rubenpla.develop.petagramcoursera.api.deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.rubenpla.develop.petagramcoursera.api.constants.JsonKeys;
import com.rubenpla.develop.petagramcoursera.mvp.model.ProfileInfoModelResponse;
import com.rubenpla.develop.petagramcoursera.mvp.model.ProfileInfo;

import java.lang.reflect.Type;

public class ProfileInfoDeserializer implements JsonDeserializer<ProfileInfoModelResponse> {
    @Override
    public ProfileInfoModelResponse deserialize(JsonElement json, Type typeOfT,
                                                JsonDeserializationContext context)
            throws JsonParseException {

        Gson gson = new Gson();
        ProfileInfoModelResponse userInfoResponse = gson.fromJson(json, ProfileInfoModelResponse.class);
        JsonObject petModelResponseData = json.getAsJsonObject()
                .getAsJsonObject(JsonKeys.USER_RESPONSE_OBJECT);

        userInfoResponse.setUser(deserialize(petModelResponseData));
        return userInfoResponse;
    }

    private ProfileInfo deserialize(JsonObject userProfileResponseData) {
        JsonObject userInfoResponseDataObject = userProfileResponseData.getAsJsonObject();

        String fullName = userInfoResponseDataObject.get(JsonKeys.USER_FULLNAME).getAsString();
        String userId = userInfoResponseDataObject.get(JsonKeys.USER_ID).getAsString();
        String profilePicture = userProfileResponseData.get(JsonKeys.USER_PROFILE_PICTURE).getAsString();

        ProfileInfo profileInfo = new ProfileInfo();
        profileInfo.setUserName(fullName);
        profileInfo.setUserId(userId);
        profileInfo.setUrlAvatar(profilePicture);

        return profileInfo;
    }
}
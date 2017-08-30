package com.rubenpla.develop.petagramcoursera.api.deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.rubenpla.develop.petagramcoursera.api.constants.JsonKeys;
import com.rubenpla.develop.petagramcoursera.mvp.model.UserRegister;
import com.rubenpla.develop.petagramcoursera.mvp.model.UserRegisterModelResponse;

import java.lang.reflect.Type;

public class RegisterUserDeserializer implements JsonDeserializer<UserRegisterModelResponse> {
    @Override
    public UserRegisterModelResponse deserialize(JsonElement json, Type typeOfT,
                                                JsonDeserializationContext context)
            throws JsonParseException {

        Gson gson = new Gson();
        UserRegisterModelResponse userRegisterResponse = gson.fromJson(json, UserRegisterModelResponse.class);
        JsonObject useRegisterModelResponseData = json.getAsJsonObject();

        userRegisterResponse.setModel(deserialize(useRegisterModelResponseData));
        return userRegisterResponse;
    }

    private UserRegister deserialize(JsonObject userProfileResponseData) {
        JsonObject userInfoResponseDataObject = userProfileResponseData.getAsJsonObject();

        String userId = userInfoResponseDataObject.get(JsonKeys.FIREBASE_REGISTER_USER_ID_USER)
                .getAsString();
        String deviceId = userInfoResponseDataObject.get(JsonKeys.FIREBASE_REGISTER_USER_ID_DEVICE)
                .getAsString();

        UserRegister userRegister = new UserRegister();
        userRegister.setUserId(userId);
        userRegister.setDeviceId(deviceId);

        return userRegister;
    }
}
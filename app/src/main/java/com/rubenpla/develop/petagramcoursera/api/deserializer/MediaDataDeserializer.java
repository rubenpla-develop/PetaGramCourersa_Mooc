package com.rubenpla.develop.petagramcoursera.api.deserializer;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.rubenpla.develop.petagramcoursera.api.constants.JsonKeys;
import com.rubenpla.develop.petagramcoursera.mvp.model.PetModel;
import com.rubenpla.develop.petagramcoursera.mvp.model.PetModelResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MediaDataDeserializer implements JsonDeserializer<PetModelResponse> {
    @Override
    public PetModelResponse deserialize(JsonElement json, Type typeOfT,
                                        JsonDeserializationContext context)
            throws JsonParseException {

        Gson gson = new Gson();
        PetModelResponse petModelResponse = gson.fromJson(json, PetModelResponse.class);
        JsonArray petModelResponseData = json.getAsJsonObject()
                .getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        petModelResponse.setPetModels(deserialize(petModelResponseData));
        return petModelResponse;
    }

    private ArrayList<PetModel> deserialize(JsonArray petResponseData){
        ArrayList<PetModel> mediaList = new ArrayList<>();
        for (int i = 0; i < petResponseData.size() ; i++) {
            JsonObject petResponseDataObject = petResponseData.get(i).getAsJsonObject();

            String mediaId = petResponseDataObject.get(JsonKeys.MEDIA_ID).getAsString();

            JsonObject userJson     = petResponseDataObject.getAsJsonObject(JsonKeys.USER);
            String id               = userJson.get(JsonKeys.USER_ID).getAsString();
            String fullName   = userJson.get(JsonKeys.USER_FULLNAME).getAsString();

            JsonObject imageJson            = petResponseDataObject
                    .getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonObject stdResolutionJson    = imageJson
                    .getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
            String urlFoto                  = stdResolutionJson.get(JsonKeys.MEDIA_URL).getAsString();

            JsonObject likesJson = petResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likes = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            PetModel currentPet = new PetModel();
            currentPet.setId(id);
            currentPet.setMediaId(mediaId);
            currentPet.setfullName(fullName);
            currentPet.seturlPhoto(urlFoto);
            currentPet.setLikes(likes);

            mediaList.add(currentPet);

        }

        return mediaList;
    }
}

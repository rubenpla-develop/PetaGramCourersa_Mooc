package com.rubenpla.develop.petagramcoursera.mvp.model;

import java.util.ArrayList;

public class PetModelResponse {

    public PetModelResponse() {}

    ArrayList<PetModel> pets;

    public ArrayList<PetModel> getPetModels() {
        return pets;
    }

    public void setPetModels(ArrayList<PetModel> contactos) {
        this.pets = contactos;
    }
}

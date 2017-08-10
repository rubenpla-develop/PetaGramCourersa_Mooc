package com.rubenpla.develop.petagramcoursera.mvp.model;

import java.util.ArrayList;

/**
 * Created by anahisalgado on 25/05/16.
 */
public class PetModelResponse {

    ArrayList<PetModel> contactos;

    public ArrayList<PetModel> getPetModels() {
        return contactos;
    }

    public void setPetModels(ArrayList<PetModel> contactos) {
        this.contactos = contactos;
    }
}

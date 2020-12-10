package com.example.pertesncf.model.webservice;

import android.util.Log;

import com.example.pertesncf.model.beans.Fields;
import com.example.pertesncf.model.beans.Record;
import com.example.pertesncf.model.beans.Resultat;
import com.google.gson.Gson;

import java.util.ArrayList;

public class SncfDataWs {
    private static String URL1 = "https://data.sncf.com/api/records/1.0/search/?dataset=objets-trouves-restitution&q=&rows=20&start=";
    private static String URL2 = "&sort=date&facet=date&facet=gc_obo_date_heure_restitution_c&facet=gc_obo_gare_origine_r_name&facet=gc_obo_nature_c&facet=gc_obo_type_c&facet=gc_obo_nom_recordtype_sc_c";
    public final static int ROWS = 20;


    public static ArrayList<Fields> getFieldsDuServeur(int start) throws Exception {

        StringBuilder URL = new StringBuilder();
        URL.append(URL1).append(start).append(URL2);
        System.out.println( "PLUSPLUS : "  + String.valueOf(URL));
        String ReponseJson = OkHttpUtils.sendGetOkHttpUtils(String.valueOf(URL));
        System.out.println(ReponseJson);

        ArrayList<Fields> fields = new ArrayList<>();
        Gson gson = new Gson();

        Resultat resultat = gson.fromJson(ReponseJson,Resultat.class);

        if (resultat == null ) {
            throw  new Exception("Variable resultat est null");
        }
        else if (resultat.getRecords() != null) {
            for (Record record : resultat.getRecords()){
                if (record.getFields() != null ){
                    fields.add(record.getFields());
                }
            }
        }

        return fields;

    }

}

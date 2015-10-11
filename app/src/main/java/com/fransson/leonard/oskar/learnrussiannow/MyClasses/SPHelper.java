package com.fransson.leonard.oskar.learnrussiannow.MyClasses;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Oskar on 2015-06-29.
 */
public class SPHelper {

    private static final String SP_MODULE = "modules";
    public static final String SPK_MODULE_INTRODUCTION = "modules";


    public static void save_model_to_sp(Context context, Module module, String key){
        Gson gson = new Gson();
        SharedPreferences sp = context.getSharedPreferences(SP_MODULE, 0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, gson.toJson(module));
        editor.apply();
    }

    public static Module get_module(Context context, String key){
        SharedPreferences sp = context.getSharedPreferences(SP_MODULE, 0);
        Module bookShelfOne = new Gson().fromJson(sp.getString(key, null), Module.class);
        return bookShelfOne;
    }

    public static List<Module> get_all_modules(Context context){

        List<Module> allModules = new ArrayList<>();

        SharedPreferences sp = context.getSharedPreferences(SP_MODULE, 0);
        Map<String, ?> modulesAsStrings = sp.getAll();

        for (Map.Entry<String, ?> entry : modulesAsStrings.entrySet()) {
            Object gSonString = entry.getValue(); //Make sure not to change the dataset
            allModules.add(new Gson().fromJson(gSonString.toString(), Module.class));
        }

        return allModules;
    }


    public static void update_lession(Context context, Lession lession, String keyModule) {

        //Get the module to put the lession back in.
        Module module = get_module(context, keyModule);
        module.updateLession(lession);

        //Put back the module
        save_model_to_sp(context, module, keyModule);
    }



}

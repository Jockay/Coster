package com.jockay.apmt.controller;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {
    private SharedPreferences prefs;

    private static String BASE = "com.jockay.apmt";
    private static String PEOPLE_NUMBER = BASE + ".peoplenumber";
    private static String GAS_UNIT = BASE + ".gasunit";
    private static String ELECTRICITY_UNIT = BASE + ".electricityunit";
    private static String COLD_WATER_UNIT = BASE + ".coldwaterunit";
    private static String HOT_WATER_UNIT = BASE + ".hotwaterunit";


    public SharedPref(Context context) {
        prefs = context.getSharedPreferences(BASE, Context.MODE_PRIVATE);
    }

    public void storePeopleNumber(String peopleNumber) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(PEOPLE_NUMBER, Integer.valueOf(peopleNumber)).apply();
    }

    public void storePeopleNumber(Integer peopleNumber) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(PEOPLE_NUMBER, peopleNumber).apply();
    }

    public Integer getPeopleNumber() {
        return prefs.getInt(PEOPLE_NUMBER, 0);
    }

    public void storeGasUnitPrice(Integer gasUnit) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(GAS_UNIT, gasUnit).apply();
    }

    public Integer getGasUnitPrice() {
        return prefs.getInt(GAS_UNIT, 0);
    }

    public void storeElectricityUnitPrice(Integer gasUnit) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(ELECTRICITY_UNIT, gasUnit).apply();
    }

    public Integer getElectricityUnitPrice() {
        return prefs.getInt(ELECTRICITY_UNIT, 0);
    }

    public void storeColdWaterUnitPrice(Integer gasUnit) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(COLD_WATER_UNIT, gasUnit).apply();
    }

    public Integer getColdWaterUnitPrice() {
        return prefs.getInt(COLD_WATER_UNIT, 0);
    }

    public void storeHotWaterUnitPrice(Integer gasUnit) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(HOT_WATER_UNIT, gasUnit).apply();
    }

    public Integer getHotWaterUnitPrice() {
        return prefs.getInt(HOT_WATER_UNIT, 0);
    }

}

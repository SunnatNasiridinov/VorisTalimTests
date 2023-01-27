package com.isystem.tests.cache;

import android.content.Context;
import android.content.SharedPreferences;

import com.isystem.tests.UserData;

import java.util.ArrayList;

public class MemoryHelper {
    private static MemoryHelper helper;
    private SharedPreferences preferences;

    private MemoryHelper(Context context){
        preferences= context.getSharedPreferences("question", Context.MODE_PRIVATE);
    }
    public static void init(Context context){
        if(helper==null){
            helper=new MemoryHelper(context);
        }
    }

    public static MemoryHelper getHelper() {
        return helper;
    }

    public void saveData(UserData userData){
        preferences.edit().putString("name "+getLastIndex(), userData.getName()).apply();
        preferences.edit().putInt("trueAnswers "+getLastIndex(), userData.getTrueAnswers()).apply();
        preferences.edit().putInt("falseAnswers "+getLastIndex(), userData.getFalseAnswers()).apply();
        saveLastIndex();
}


    private void saveLastIndex(){
        preferences.edit().putInt("index",getLastIndex()+1).apply();
    }
    private int getLastIndex(){
        return preferences.getInt("index",0);
    }

public UserData getData(int index){
        UserData data=new UserData(
                preferences.getString("name "+index,""),
                preferences.getInt("trueAnswers "+index,0),
                preferences.getInt("falseAnswers "+index,0)
        );
        return data;
}

public ArrayList<UserData> getLastData(){
        ArrayList <UserData> list=new ArrayList<>();
        int n=getLastIndex();
    for (int i = 0; i < n; i++) {
        list.add(getData(i));
    }
    return list;
}
public void clearData(){
        preferences.edit().clear().apply();
}

}

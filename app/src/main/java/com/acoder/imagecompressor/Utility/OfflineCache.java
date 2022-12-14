package com.acoder.imagecompressor.Utility;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class OfflineCache {

    private static Context context;

    public static void initOfflineCache(Context con){
        context = con;
    }

    public static final String ALL_FILE = "ALL_FILE";
    public static final String USER_PROFILE_FILE = "USER_PROFILE_FILE";
    public static final String APP_VERSION = "APP_VERSION";
    public static final String RECENT_BUS_SEARCH = "RECENT_BUS_SEARCH";
    public static final String HOME_BANNER_IMAGES = "HOME_BANNER_IMAGES";
    public static final String BUS_TERMINALS = "BUS_TERMINALS";
    public static final String VISA_COUNTRY_LIST = "VISA_COUNTRY_LIST";
    public static final String VISA_TYPE_LIST = "VISA_TYPE_LIST";
    public static final String BUS_SEARCH = "BUS_SEARCH";
    public static final String NOTIFICATION_LIST = "NOTIFICATION_LIST";

    //<editor-fold desc="Handle Cache">


    public  static void deleteAllCacheFile() {
        ArrayList<String> allFile = getOfflineList(ALL_FILE);
        for (int i = 0; i < allFile.size(); i++) {
            //context.deleteFile(allFile.get(i));

            saveOffline(allFile.get(i), null);
        }
    }

    public  static void deleteCacheFile(String file) {
        context.deleteFile(file);
    }

    public  static <CLASS> void saveOffline(String file, CLASS data) {

        try {
            ArrayList<String> allFiles = getOfflineList(ALL_FILE);
            if (allFiles != null) {
                if (!allFiles.contains(file)) {
                    allFiles.add(file);
                    if (!file.equals(ALL_FILE)) {
                        saveOffline(ALL_FILE, allFiles);
                    }
                }
            } else {
                allFiles = new ArrayList<>();
                allFiles.add(ALL_FILE);
                saveOffline(ALL_FILE, allFiles);
            }

        } catch (Exception e) {
        }

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = context.openFileOutput(file, MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = null;
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(data);
            objectOutputStream.close();
        } catch (Exception e) {
            int i = 0;
//            Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show();
        }
    }

    public  static <CLASS> CLASS getOfflineSingle(String file) {

        CLASS items = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = context.openFileInput(file);
            ObjectInputStream objectInputStream = null;
            objectInputStream = new ObjectInputStream(fileInputStream);
            items = (CLASS) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
//            Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show();
        }
        return items;
    }

    public static  <CLASS> ArrayList<CLASS> getOfflineList(String file) {

        ArrayList<CLASS> items = new ArrayList<>();
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = context.openFileInput(file);
            ObjectInputStream objectInputStream = null;
            objectInputStream = new ObjectInputStream(fileInputStream);
            items = (ArrayList<CLASS>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
//            Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show();
        }
        if (items == null) {
            return new ArrayList<>();
        }
        return items;
    }
    //</editor-fold>



}

package com.app.recall.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.app.recall.R;


/**
 * Created by KenChan on 16/6/14.
 * 权限申请工具类
 */
public class PermissionUtils {

    //    public static final int CALENDAR = 356;
    //    public static final int CAMERA = 834;
    //    public static final int CONTACTS = 301;
    //    public static final int LOCATION = 185;
    //    public static final int MICROPHONE = 832;
    //    public static final int PHONE = 849;
    //    public static final int SENSORS = 23;
    //    public static final int SMS = 735;
    //    public static final int STORAGE = 559;
    //
    //    @IntDef({CALENDAR, CAMERA, CONTACTS, LOCATION, MICROPHONE, PHONE, SENSORS, SMS, STORAGE})
    //    @Retention(RetentionPolicy.SOURCE)
    //    public @interface PermissionKeys {
    //
    //    }

    public static boolean requestPermission(Activity activity, int requestCode, String...
            permissions) {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
            return true;
        }

        for (String permission : permissions) {
            int checkState = ContextCompat.checkSelfPermission(activity, permission);
            if (checkState != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, permissions, requestCode);
                return false;
            }
        }
        return true;
    }

    public static void showPermissionDialog(final Context context, String permission) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context,
                                                                    android.support.design.R
                                                                            .style
                                                                            .Base_Theme_AppCompat_Light_Dialog);

        boolean isTitleEmpty = TextUtils.isEmpty(getPermissionTitle(context, permission));
        builder.setTitle(
                isTitleEmpty ? context.getString(R.string.hint) : getPermissionTitle(context,
                                                                                     permission))
               .setMessage(
                getPermissionHint(context, permission)).setPositiveButton(R.string.positive,
                                                                          new DialogInterface
                                                                                  .OnClickListener() {
                                                                              @Override
                                                                              public void onClick
                                                                                      (DialogInterface dialog, int which) {
                                                                                  Intent intent =
                                                                                          new Intent(
                                                                                          Settings.ACTION_SETTINGS).addFlags(
                                                                                          Intent.FLAG_ACTIVITY_NEW_TASK);
                                                                                  context.startActivity(
                                                                                          intent);
                                                                              }
                                                                          }).setNegativeButton(
                R.string.negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        builder.create().dismiss();
                    }
                }).create().show();
    }

    private static String getPermissionHint(Context context, String permission) {
        switch (permission) {
            case Manifest.permission.READ_CALENDAR:
            case Manifest.permission.WRITE_CALENDAR:
                return context.getString(R.string.setting_calendar);
            case Manifest.permission.CAMERA:
                return context.getString(R.string.setting_camera);
            case Manifest.permission.WRITE_CONTACTS:
            case Manifest.permission.READ_CONTACTS:
            case Manifest.permission.GET_ACCOUNTS:
                return context.getString(R.string.setting_contacts);
            case Manifest.permission.ACCESS_FINE_LOCATION:
            case Manifest.permission.ACCESS_COARSE_LOCATION:
                break;
            case Manifest.permission.RECORD_AUDIO:
                return context.getString(R.string.setting_microphone);
            case Manifest.permission.READ_PHONE_STATE:
            case Manifest.permission.CALL_PHONE:
            case Manifest.permission.READ_CALL_LOG:
            case Manifest.permission.WRITE_CALL_LOG:
            case Manifest.permission.ADD_VOICEMAIL:
            case Manifest.permission.USE_SIP:
            case Manifest.permission.PROCESS_OUTGOING_CALLS:
                return context.getString(R.string.setting_phone);
            case Manifest.permission.BODY_SENSORS:
                return context.getString(R.string.setting_sensors);
            case Manifest.permission.SEND_SMS:
            case Manifest.permission.RECEIVE_SMS:
            case Manifest.permission.READ_SMS:
            case Manifest.permission.RECEIVE_WAP_PUSH:
            case Manifest.permission.RECEIVE_MMS:
                return context.getString(R.string.setting_sms);
            case Manifest.permission.READ_EXTERNAL_STORAGE:
            case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                return context.getString(R.string.setting_storage);

        }
        return null;
    }

    private static String getPermissionTitle(Context context, String permission) {
        switch (permission) {
            case Manifest.permission.READ_CALENDAR:
            case Manifest.permission.WRITE_CALENDAR:
                return context.getString(R.string.dialog_disable_calendar);
            case Manifest.permission.CAMERA:
                return context.getString(R.string.dialog_disable_camera);
            case Manifest.permission.WRITE_CONTACTS:
            case Manifest.permission.READ_CONTACTS:
            case Manifest.permission.GET_ACCOUNTS:
                return context.getString(R.string.dialog_disable_contacts);
            case Manifest.permission.ACCESS_FINE_LOCATION:
            case Manifest.permission.ACCESS_COARSE_LOCATION:
                break;
            case Manifest.permission.RECORD_AUDIO:
                return context.getString(R.string.dialog_disable_microphone);
            case Manifest.permission.READ_PHONE_STATE:
            case Manifest.permission.CALL_PHONE:
            case Manifest.permission.READ_CALL_LOG:
            case Manifest.permission.WRITE_CALL_LOG:
            case Manifest.permission.ADD_VOICEMAIL:
            case Manifest.permission.USE_SIP:
            case Manifest.permission.PROCESS_OUTGOING_CALLS:
                return context.getString(R.string.dialog_disable_phone);
            case Manifest.permission.BODY_SENSORS:
                return context.getString(R.string.dialog_disable_sensors);
            case Manifest.permission.SEND_SMS:
            case Manifest.permission.RECEIVE_SMS:
            case Manifest.permission.READ_SMS:
            case Manifest.permission.RECEIVE_WAP_PUSH:
            case Manifest.permission.RECEIVE_MMS:
                return context.getString(R.string.dialog_disable_sms);
            case Manifest.permission.READ_EXTERNAL_STORAGE:
            case Manifest.permission.WRITE_EXTERNAL_STORAGE:
                return context.getString(R.string.dialog_disable_storage);

        }
        return null;
    }
}

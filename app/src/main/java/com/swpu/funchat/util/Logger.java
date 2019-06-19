package com.swpu.funchat.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.orhanobut.logger.AndroidLogAdapter;

/**
 * Class description:
 *
 * @author zp
 * @version 1.0
 * @see Logger
 * @since 2019/6/17
 */
public class Logger {

    public static void init() {
        com.orhanobut.logger.Logger.addLogAdapter(new AndroidLogAdapter());
    }


    public static void d(@NonNull String message, @Nullable Object... args) {
        com.orhanobut.logger.Logger.d(message, args);
    }

    public static void d(@Nullable Object object) {
        com.orhanobut.logger.Logger.d(object);
    }

    public static void e(@NonNull String message, @Nullable Object... args) {
        com.orhanobut.logger.Logger.e(null, message, args);
    }

    public static void e(@Nullable Throwable throwable, @NonNull String message, @Nullable Object... args) {
        com.orhanobut.logger.Logger.e(throwable, message, args);
    }

    public static void i(@NonNull String message, @Nullable Object... args) {
        com.orhanobut.logger.Logger.i(message, args);
    }

    public static void v(@NonNull String message, @Nullable Object... args) {
        com.orhanobut.logger.Logger.v(message, args);
    }

    public static void w(@NonNull String message, @Nullable Object... args) {
        com.orhanobut.logger.Logger.w(message, args);
    }

    public static void wtf(@NonNull String message, @Nullable Object... args) {
        com.orhanobut.logger.Logger.wtf(message, args);
    }

    public static void json(@Nullable String json) {
        com.orhanobut.logger.Logger.json(json);
    }

    public static void xml(@Nullable String xml) {
        com.orhanobut.logger.Logger.xml(xml);
    }
}

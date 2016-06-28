package me.ilich.mymeteringdevices.data.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Locale;

public abstract class Dto implements Serializable {

    public static final int NOT_SET = -1;
    public static final SimpleDateFormat DB_SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.getDefault());

}

package me.ilich.mymeteringdevices.tools;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AssetsReader {

    public static void byLine(Context context, String fn, OnReadListener listener) {
        InputStream in = null;
        try {
            in = context.getAssets().open(fn);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                listener.onLine(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public interface OnReadListener {
        void onLine(String s);
    }

}

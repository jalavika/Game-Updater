package org.heater.api.serialized;

import lombok.SneakyThrows;
import org.heater.api.utils.OsCheck;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by romain on 07/05/2015.
 */
public class SerializeUtils {
    @SuppressWarnings("unchecked")
    public static Map<OsCheck.OSType, SerializedFile> getFiles(InputStream stream) {
        ObjectInputStream object = null;

        try {
            object = new ObjectInputStream(stream);
            return (Map<OsCheck.OSType, SerializedFile>) object.readObject();
        } catch (Exception e) {
            return new HashMap<>();
        } finally {
            try {
                if(object != null) object.close();
            } catch (IOException e) {}
        }
    }

    public static Map<OsCheck.OSType, SerializedFile> getFiles(URL url) {
        try {
            return getFiles(url.openStream());
        } catch(Exception e) {
            return new HashMap<>();
        }
    }

    public static Map<OsCheck.OSType, SerializedFile> getFiles(String localPath) {
        try {
            return getFiles(new FileInputStream(new File(localPath)));
        } catch(Exception e) {
            return new HashMap<>();
        }
    }

    @SneakyThrows
    public static void writeFiles(Map<OsCheck.OSType, SerializedFile> files, String path) {
        java.io.File f = new java.io.File(path+"files.dat");
        if (f.exists())
            f.delete();

        FileOutputStream out = new FileOutputStream(path+"files.dat");
        ObjectOutputStream object = new ObjectOutputStream(out);

        object.writeObject(files);
        object.flush();
        object.close();
        out.close();
    }
}

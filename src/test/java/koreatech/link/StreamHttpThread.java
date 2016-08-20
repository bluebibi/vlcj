package koreatech.link;

import koreatech.link.service.OrchidService;
import koreatech.link.service.VlcService;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.headless.HeadlessMediaPlayer;

/**
 * Created by Kyo on 2016. 8. 16..
 */
public class StreamHttpThread extends Thread {
    private String[] args;
    private String mediaFolder;
    private String fileSeparator;
    private OrchidService orchidService = new OrchidService();
    private VlcService vlcService = new VlcService();

    public StreamHttpThread(String[] args, String mediaFolder, String fileSeparator) {
        this.args = args;
        this.mediaFolder = mediaFolder;
        this.fileSeparator = fileSeparator;
    }

    public void run() {
        try {
            String media = mediaFolder + fileSeparator + args[0];
            String options = vlcService.formatHttpStream(args[1], Integer.parseInt(args[2]));

            System.out.println("Streaming '" + media + "' to '" + options + "'");

            MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory(args);
            HeadlessMediaPlayer mediaPlayer = mediaPlayerFactory.newHeadlessMediaPlayer();
            mediaPlayer.playMedia(media, options);

            System.out.println("***************************");
            System.out.println("ID: " + orchidService.getOrchidContentName(args[0]));

        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}

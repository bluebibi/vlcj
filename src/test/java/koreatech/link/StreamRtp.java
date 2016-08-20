/*
 * This file is part of VLCJ.
 *
 * VLCJ is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * VLCJ is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with VLCJ.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright 2009-2016 Caprica Software Limited.
 */

package koreatech.link;

import koreatech.link.service.OrchidService;
import koreatech.link.service.VlcService;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.headless.HeadlessMediaPlayer;
import uk.co.caprica.vlcj.test.VlcjTest;

import javax.xml.bind.DatatypeConverter;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * An example of how to stream a media file using RTP.
 * <p>
 * The client specifies an MRL of <code>rtp://@230.0.0.1:5555</code>
 */
public class StreamRtp extends VlcjTest {
    public static String fileSeprator = System.getProperty("file.separator");
    public static String mediaFolder = fileSeprator + "usr" + fileSeprator + "local" + fileSeprator + "share";

    static OrchidService orchidService = new OrchidService();
    static VlcService vlcService = new VlcService();

    public static void main(String[] args) throws Exception {
        if(args.length != 3) {
            System.out.println("Specify a single MRL to stream");
            System.exit(1);
        }

        String media = mediaFolder + fileSeprator + args[0];
        String options = vlcService.formatRtpStream(args[1], Integer.parseInt(args[2]));

        System.out.println("Streaming '" + media + "' to '" + options + "'");

        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory(args);
        HeadlessMediaPlayer mediaPlayer = mediaPlayerFactory.newHeadlessMediaPlayer();

        mediaPlayer.playMedia(media,
            options,
            ":no-sout-rtp-sap",
            ":no-sout-standard-sap",
            ":sout-all",
            ":sout-keep"
        );

        System.out.println("***************************");
        System.out.println("ID: " + orchidService.getOrchidContentName(args[0]));

        // Don't exit
        Thread.currentThread().join();
    }
}

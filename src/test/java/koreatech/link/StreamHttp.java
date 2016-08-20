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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.headless.HeadlessMediaPlayer;
import uk.co.caprica.vlcj.test.VlcjTest;

/**
 * An example of how to stream a media file over HTTP.
 * <p>
 * The client specifies an MRL of <code>http://127.0.0.1:5555</code>
 */
public class StreamHttp extends VlcjTest {
    public static String fileSeparator = System.getProperty("file.separator");
    public static String mediaFolder = fileSeparator + "usr" + fileSeparator + "local" + fileSeparator + "share";

    public static void main(String[] args) throws Exception {
        if(args.length != 3) {
            System.out.println("Specify a single MRL to stream");
            System.exit(1);
        }

        StreamHttpThread streamHttpThread = new StreamHttpThread(args, mediaFolder, fileSeparator);

        // 조건!!!!!!!!
        streamHttpThread.start();

        // Don't exit
        Thread.currentThread().join();
    }
}

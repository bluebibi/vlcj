package koreatech.link.service;

import javax.xml.bind.DatatypeConverter;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 * Created by Kyo on 2016. 8. 16..
 */
public class OrchidService {
    final static byte[] contextIdForContentName
            = DatatypeConverter.parseHexBinary("292D05A61D8C335FA3411EBB5BAABE77");

    public String getOrchidContentName(String ContentName) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String name = ContentName;
        byte[] nameBytes = name.getBytes("UTF-8");

        ByteBuffer bb = ByteBuffer.allocate(contextIdForContentName.length + nameBytes.length);
        bb.put(contextIdForContentName);
        bb.put(nameBytes);
        byte[] concatenatedNameBytes = bb.array();  // concatenate

        md.update(concatenatedNameBytes);
        byte[] digest = md.digest(); // sha256
        digest = Arrays.copyOf(digest, 12); // Truncate_96 (note: 12 * 8 = 96  24개의 16진수)

        return DatatypeConverter.printHexBinary(digest);
    }
}

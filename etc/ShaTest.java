import javax.xml.bind.DatatypeConverter;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.BitSet;

public class ShaTest {
    final static byte[] contextIdForDomainName = DatatypeConverter.parseHexBinary("7C8C1B9AA7FA1356C9A2DA00FC128B0B"); // (note: 16 bytes = 16 * 8 = 128 bits)
    final static byte[] contextIdForIPv4Addr = DatatypeConverter.parseHexBinary("1AF52BA93BA24026CAF34D783DC12A09"); // (note: 16 bytes = 16 * 8 = 128 bits)
    final static byte[] contextIdForContentName = DatatypeConverter.parseHexBinary("292D05A61D8C335FA3411EBB5BAABE77"); // (note: 16 bytes = 16 * 8 = 128 bits)

    public static void main(String[] args) throws Exception{
        //Print Out Context IDs
        System.out.println(contextIdForIPv4Addr.length);
        System.out.println(BitSet.valueOf(contextIdForIPv4Addr));

        System.out.println();

        System.out.println(contextIdForContentName.length);
        System.out.println(BitSet.valueOf(contextIdForContentName));


        //SHA-256 and Truncate_96

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        String name = "/jeju/hologram/1/3";
        byte[] nameBytes = name.getBytes("UTF-8");

        ByteBuffer bb = ByteBuffer.allocate(contextIdForContentName.length + nameBytes.length);
        bb.put(contextIdForContentName);
        bb.put(nameBytes);
        byte[] concatenatedNameBytes = bb.array();

        md.update(concatenatedNameBytes);
        byte[] digest = md.digest();
        digest = Arrays.copyOf(digest, 12); // Truncate_96 (note: 12 * 8 = 96)

        System.out.println();

        System.out.println(digest.length);
        System.out.println(BitSet.valueOf(digest));
        System.out.println(DatatypeConverter.printHexBinary(digest));
    }
}

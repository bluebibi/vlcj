package koreatech.link.service;

/**
 * Created by Kyo on 2016. 8. 16..
 */
public class VlcService {
    public String formatRtpStream(String targetAddress, int targetPort) {
        StringBuilder sb = new StringBuilder(60);
        sb.append(":sout=#rtp{dst=");
        sb.append(targetAddress);
        sb.append(",port=");
        sb.append(targetPort);
        sb.append(",mux=ts}");
        return sb.toString();
    }

    public String formatHttpStream(String targetAddress, int targetPort) {
        StringBuilder sb = new StringBuilder(60);
        sb.append(":sout=#duplicate{dst=std{access=http,mux=ts,");
        sb.append("dst=");
        sb.append(targetAddress);
        sb.append(':');
        sb.append(targetPort);
        sb.append("}}");
        return sb.toString();
    }
}

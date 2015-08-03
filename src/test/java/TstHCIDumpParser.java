import org.jboss.rhiot.beacon.common.Beacon;
import org.jboss.rhiot.beacon.scannerjni.HCIDumpParser;
import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Scott Stark (sstark@redhat.com) (C) 2014 Red Hat Inc.
 */
public class TstHCIDumpParser {
   private static final String PI_HOST = "";

   @Test
   public void testQuantifier() {
      String line = "> 04 dog dog dog dog dog dog";
      Pattern p = Pattern.compile(">\\s04((\\s[dog]{3}){3})(.*)");
      Matcher m = p.matcher(line);
      assert m.matches();

      for(int n = 0; n <= m.groupCount(); n ++)
         System.out.printf("group(%d): %s\n", n, m.group(n));
   }
   @Test
   public void testHexREGroups() {
      String line = "> 04 3E 2A 02 01 03 01 17 8F A5 38 12 0F 1E 02 01 06 1A FF 4C ";
      Pattern p = Pattern.compile(">\\s04((?:\\s\\p{XDigit}{2}){6})(.*)");
      Matcher m = p.matcher(line);
      assert m.matches();
      for(int n = 0; n <= m.groupCount(); n ++)
         System.out.printf("group(%d): %s\n", n, m.group(n));
   }

   /**
    * Test matching the first line of a beacon in the hcidump output
    */
   @Test
   public void testRE() {
      String line = "> 04 3E 2A 02 01 03 01 92 EF 0D 06 40 30 1E 02 01 06 1A FF 4C ";
      Pattern p = Pattern.compile(">\\s04.*((?:\\s\\p{XDigit}{2}){6})((?:\\s\\p{XDigit}{2}){4}) 1A FF 4C");
      Matcher m = p.matcher(line.trim());
      Assert.assertTrue("There are matches", m.matches());
      for(int n = 0; n <= m.groupCount(); n ++)
         System.out.printf("group(%d): %s\n", n, m.group(n));
      System.out.printf("BDADDR: %s\n", m.group(1));
   }

   /**
    * Test client to read 5000 lines from the hcidump stream from the HCIDumpParser running in stream mode.
    * @throws Exception
    */
   @Test
   public void testHcidumpStreamRead5000() throws Exception {
      Socket client = new Socket(InetAddress.getLocalHost(), 12345);
      //Socket client = new Socket(InetAddress.getByName("192.168.1.95"), 12345);
      InputStream is = client.getInputStream();
      BufferedReader br = new BufferedReader(new InputStreamReader(is));
      for(int n = 0; n < 5000; n ++) {
         String line = br.readLine();
         System.out.printf("%d, %s\n", n, line);
      }
      client.close();
   }
}

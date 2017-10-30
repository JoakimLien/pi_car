package TwoWaySerialComm3;

import java.io.IOException;
import java.io.InputStream;
import gnu.io.SerialPort;

/**
 *
 * @author anndi
 */
  public class SerialReader implements Runnable {

    InputStream in;

    public SerialReader(InputStream in) {
      this.in = in;
    }

    public void run() {  // original
      byte[] buffer = new byte[1024];
      int len = -1;
      String msg;
      String msgComplete = "";  // appending msg from buffer
      try {
        while ((len = this.in.read(buffer)) > -1) {
          msg = new String(buffer, 0, len);
          msgComplete += msg;
          if ((!msgComplete.isEmpty())) {
            //System.out.print(msg);
            if (msgComplete.endsWith("\n")) {
              System.out.println("Complete text string from Arduino:");
              System.out.print(msgComplete);
              // split into substrings, each containing one integer number
              String[] subString = msgComplete.split("/");
              int[] intValues = new int[subString.length - 1]; // to avoid \r\n
              Integer[] integerValues = new Integer[subString.length - 1]; // wrapper
              System.out.println("Decoded integer values from Arduino:");
              for (int i = 0; i < (subString.length - 1); i++) {
                try {
                  intValues[i] = Integer.parseInt(subString[i]); // get the integer number
                }
                catch (NumberFormatException e) {
                  System.out.println("Parse integer exception);");
                }
                System.out.println(subString[i]);
              }
              System.out.println();
              msgComplete = "";  // reset, ready for next message
            }
          }
        }
      }
      catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
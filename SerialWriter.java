package TwoWaySerialComm3;

import java.io.IOException;
import java.io.OutputStream;
import gnu.io.SerialPort;

/**
 *
 * @author anndi
 */
  public class SerialWriter implements Runnable {
    OutputStream out;
    String msg;

    public SerialWriter(OutputStream out) {
      this.out = out;
      
    }
    
    public void setCommand(String command)
    {
        this.msg = command;
    }

    public void run() {

      while (true) {
       if( msg != null){
       char[] buffer = msg.toCharArray();
        //send text message
       for (int i = 0; i < buffer.length; i++) {
        try {
         this.out.write(buffer[i]);
         }
         catch (IOException e) {
           e.printStackTrace();
          }
        }
       msg = null;
       }
        try {
          Thread.sleep(5000);
        }
        catch (InterruptedException e) {
        }

      }

    }

  }
 
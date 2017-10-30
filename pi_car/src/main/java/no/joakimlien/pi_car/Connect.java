package no.joakimlien.pi_car;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author anndi
 */
public class Connect {

  private static final int BAUD_RATE = 115200;
  private static final int TIME_OUT = 2000;
  private static final String PORT_NAME = "COM3";
  
  public Connect(String direction) {
    try {
      // (new TwoWaySerialComm()).connect(PORT_NAME);
      connect(PORT_NAME,direction);
    }
    catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  void connect(String portName,String direction) throws Exception {
    CommPortIdentifier portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
    if (portIdentifier.isCurrentlyOwned()) {
      System.out.println("Error: Port is currently in use");
    }
    else {
      CommPort commPort = portIdentifier.open(this.getClass().getName(), TIME_OUT);

      if (commPort instanceof SerialPort) {
        SerialPort serialPort = (SerialPort) commPort;
        serialPort.setSerialPortParams(
                BAUD_RATE,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE);

        InputStream in = serialPort.getInputStream();
        OutputStream out = serialPort.getOutputStream();

        SerialReader sr = new SerialReader(in);
        Thread reader = new Thread(sr);
        reader.start();

        SerialWriter wr = new SerialWriter(out);
        Thread writer = new Thread(wr);
        writer.start();
       
        wr.setCommand(direction);


        
      }
      else {
        System.out.println("Error: Only serial ports are handled.");
      }
    }
  }
}
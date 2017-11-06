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
public class Connect implements Runnable{

  private static final int BAUD_RATE = 115200;
  private static final int TIME_OUT = 2000;
  private static final String PORT_NAME = "COM3";
  private static boolean isConnected = false;
  private SerialWriter wr;
  private SerialReader sr;
  
  
  
  public Connect() {
    try {
      connectToPort(PORT_NAME);
      System.out.println("trying to connect");
    }
    catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  public void run(){
      
      while(true){
          if(isConnected){
              
          
          }
      }
      
  }
  
  public void sendCommand(String command){
      
      wr.setCommand(command);
  }
  
  
  
  void connectToPort(String portName) throws Exception {
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

        sr = new SerialReader(in);
        Thread reader = new Thread(sr);
        reader.start();

        wr = new SerialWriter(out);
        Thread writer = new Thread(wr);
        writer.start();
       
        //wr.setCommand(direction);

    isConnected = true;
        
      }
      else {
        System.out.println("Error: Only serial ports are handled.");
      }
    }
  }
  
}

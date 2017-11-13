import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
/**
 *
 * @author joakimlien
 */
public class distanceSensor {
    
    public void start(){
        final GpioController gpio = GpioFactory.getInstance();
        final GpioPinDigitalInput pin01 = gpio.provisionDigitalInputPin(RaspiPin.GPIO_01,PinPullResistance.PULL_DOWN);
        final GpioPinDigitalOutput pin04 = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_04);
        
        int counter = 0;
        
        while(true){
            boolean isHigh = pin01.isHigh();
            if(isHigh){
                counter++;
                System.out.println("Approaching..." + counter);
                if(counter > 2){
                    break;
                }
            }
            pin04.setState(isHigh);
            delay(500);
        }
        gpio.shutdown();
        System.exit(0);
    }
    
    private static void delay(int ms){
        try{
            Thread.sleep(ms);
        }catch(InterruptedException ex){
            System.out.println(ex.toString());
        }
    }
    
    
}

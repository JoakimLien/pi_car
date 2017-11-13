
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
public class GP2Y0A41SM0F {
    
    public static void main(String[] args) {
        System.out.println("DMSDemo02 start...");
 
        // 建立GPIO控制物件
        GpioController gpio = GpioFactory.getInstance();
 
        // 建立GPIO_04輸入針腳物件
        final GpioPinDigitalInput serialDataOutput = 
                gpio.provisionDigitalInputPin(RaspiPin.GPIO_04);
 
        // 建立控制GPIO_05、GPIO_01、GPIO_06輸出物件
        final GpioPinDigitalOutput serialDataInput = 
                gpio.provisionDigitalOutputPin(RaspiPin.GPIO_05);
        final GpioPinDigitalOutput serialClock = 
                gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01);
        final GpioPinDigitalOutput chipSelect = 
                gpio.provisionDigitalOutputPin(RaspiPin.GPIO_06);
 
        // 建立MCP3008物件
        final MCP3008 mcp3008 = new MCP3008(
                serialClock, serialDataOutput, serialDataInput, chipSelect);
 
        int counter = 0;
 
        while (true) {
            // 讀取連接到MCP3008 0號通道的紅外線測距模組
            float adcValue = mcp3008.readVoltage(
                    MCP3008.MCP3008Channels.CH_00.getChannel());
 
            // 轉換為距離（公分）
            double distance = 12.3F * Math.pow(adcValue, -1);
 
            if (distance < 5) {
                counter++;
 
                System.out.println("Approaching... " + counter);
 
                if (counter > 2) {
                   break; 
                }
            }
 
            System.out.println("Distance: " + 
                    String.format("%2.2f", distance));
 
            delay(1000);
        }
 
        gpio.shutdown();
        System.exit(0);
    }
 
    /**
     * 暫停指定的時間（毫秒、1000分之一秒）
     * 
     * @param ms
     */   
    public static void delay(int ms) {
        try {
            Thread.sleep(ms);
        }
        catch (InterruptedException e) {
            System.out.println("================= " + e);
        }
    }    
 
}
class MCP3008 {
 
    private static final boolean DEBUG = true;
 
    /**
     * MCP3008的八個輸入埠
     */
    public enum MCP3008Channels {
 
        /**
         * MCP3008的八個輸入埠
         */
        CH_00(0);
 
        private int channel;
 
        private MCP3008Channels(int channel) {
            this.channel = channel;
        }
 
        public int getChannel() {
            return channel;
        }
 
    }
 
    // Serial data out
    private GpioPinDigitalInput serialDataOutput = null;
 
    // Serial data in、Serial clock、Chip select
    private GpioPinDigitalOutput serialDataInput = null;
    private GpioPinDigitalOutput serialClock = null;
    private GpioPinDigitalOutput chipSelect = null;
 
    public MCP3008(GpioPinDigitalOutput serialClock, 
            GpioPinDigitalInput serialDataOutput,
            GpioPinDigitalOutput serialDataInput,
            GpioPinDigitalOutput chipSelect) {
        this.serialClock = serialClock;
        this.serialDataOutput = serialDataOutput;
        this.serialDataInput = serialDataInput;
        this.chipSelect = chipSelect;
    }
 
    /**
     * 讀取指定輸入埠的資料
     * 
     * @param channel 輸入埠
     * @return 讀取的資料
     */
    public int read(int channel) {
        chipSelect.high();
        serialClock.low();
        chipSelect.low();
 
        int adccommand = channel;
 
        // 0x18 => 00011000
        adccommand |= 0x18; 
 
        adccommand <<= 3;
 
        // 傳送讀取的輸入埠給MCP3008
        for (int i = 0; i < 5; i++) { 
            // 0x80 => 0&10000000
            if ((adccommand & 0x80) != 0x0) {
                serialDataInput.high();
            } 
            else {
                serialDataInput.low();
            }
 
            adccommand <<= 1;
 
            tickPin(serialClock);
        }
 
        int adcOut = 0;
 
        // 讀取指定輸入埠的資料
        for (int i = 0; i < 12; i++) {
            tickPin(serialClock);
            adcOut <<= 1;
 
            if (serialDataOutput.isHigh()) {
                adcOut |= 0x1;
            }
        }
 
        chipSelect.high();
 
        // 移除第一個位元
        adcOut >>= 1; 
 
        return adcOut;
    }
 
    /**
     * 讀取指定輸入埠的資料
     * 
     * @param channel 輸入埠
     * @return 讀取的資料（電壓）
     */   
    public float readVoltage(int channel) {
        float result = -1;
        int value = read(channel);
 
        result = value * 3.3F / 1023;
 
        return result;
    }
 
    private void tickPin(GpioPinDigitalOutput pin) {
        pin.high();
        pin.low();
    }
 
}

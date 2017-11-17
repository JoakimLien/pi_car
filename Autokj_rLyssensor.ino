#include <SparkFunTSL2561.h>
#include <Wire.h>
#include "ArduinoMotorShieldR3.h"
#include <SoftwareSerial.h>

ArduinoMotorShieldR3 md;


SFE_TSL2561 light;

boolean gain;
unsigned int ms;

void setup()
{
  Serial.begin(9600);
  Serial.println("Start searching for light");

  light.begin();

  gain = 1;

  unsigned char time = 2;
  light.setTiming(gain, time, ms);

  light.setPowerUp();

}

void loop()
{
  delay(ms);
  unsigned int data0, data1;

  if (light.getData(data0, data1))
  {

    Serial.print("data0: ");
    Serial.print(data0);
    Serial.print(" data1: ");
    Serial.print(data1);

    double lux;
    boolean good;

    good = light.getLux(gain, ms, data0, data1, lux);
    Serial.print(" lux: ");
    Serial.print(lux);
    if (lux < 9) {
      forwards();
    } else {
      stop();
      turnRight();
    }
    if (good) Serial.println(" (good)"); else Serial.println(" (BAD)");
  }

}


void turnRight()
{
  md.setM1Speed(200);
  md.setM2Speed(400);
}

void backwards()
{
  md.setM1Speed(-400);
  md.setM2Speed(-400);
}

// Move forwards full speed
void forwards()
{
  md.setM1Speed(400);
  md.setM2Speed(400);
}

//stop the car
void stop()
{
  md.setM1Speed(0);
  md.setM2Speed(0);
}



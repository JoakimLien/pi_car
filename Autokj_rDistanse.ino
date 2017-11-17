#include "ArduinoMotorShieldR3.h"
#include <SoftwareSerial.h>
//M1 = pmwA
//M2 = pwmB

ArduinoMotorShieldR3 md;


void setup()
{
  Serial.begin(9600);
  Serial.setTimeout(20);
  Serial.println("Arduino Motor Shield R3 connected");
  md.init();

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
// move backwards full speed
void backwards()
{
  md.setM1Speed(-400);
  md.setM2Speed(-400);
}

// Turn right
void turnRight()
{
  md.setM1Speed(200);
  md.setM2Speed(400);
}

// Turn left
void turnLeft()
{
  md.setM1Speed(400);
  md.setM2Speed(200);
}


void loop() { // run over and over
  if (Serial.available()) {
    String command = Serial.readString();
    {

      Serial.println(command);
      if (command.equalsIgnoreCase("forwards"))
      {
        forwards();
      }
      else
      {
        backwards();
      }
      else if (command.equalsIgnoreCase("stop"))
      {
        stop();
      }
      else if (command.equalsIgnoreCase("turnl"))
      {
        turnLeft();
      }
    }
  }
}


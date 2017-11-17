
#include "ArduinoMotorShieldR3.h"
#include <SoftwareSerial.h>

ArduinoMotorShieldR3 md;



#define sensor A0 

void setup() {
  Serial.begin(9600);
  Serial.setTimeout(20);
  Serial.println("Arduino Motor Shield R3 connected");
  md.init();
}

void loop() {
  
  float volts = analogRead(sensor)*0.0048828125; 
  int distance = 13*pow(volts, -1);
  
  if (distance < 80){
    stop();
    Serial.println(distance);
    Serial.println("Stopping");
  }else {
  forwards();
  Serial.println("Driving");
  Serial.println(distance);
}
}

// Move forwards full speed
void forwards()
{
  md.setM1Speed(0);
  md.setM2Speed(0);
}

void turnRight()
{

  md.setM1Speed(100);
  md.setM2Speed(400);
  Serial.println(md.getM2CurrentMilliamps());
  Serial.println(md.getM1CurrentMilliamps());
  
}

//stop the car
void stop()
{
  md.setM1Speed(0);
  md.setM2Speed(0);
}

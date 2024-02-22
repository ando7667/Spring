package ru.gb;

import org.slf4j.event.Level;
import org.springframework.stereotype.Component;
import ru.gb.aspect.Loggable;

@Component
public class Louiggi implements Brother {

  @Loggable(level = Level.WARN)
  public void method1(String arg1, int arg2) {
    long l = 0;
    for (int i=0;i<100000;i++) {
      for (int j = 0; j < 10000;j++){
        l = j + l*i;
      }
    }
  }

  @Loggable(level = Level.WARN)
  public String method2() {
    long l = 0L;
    for (int i=0;i<10000;i++) {
      for (int j = 0; j < 10000;j++){
        l = j + l*i;
      }
    }
    return "value=" + l;
  }

  public String method3() {
    throw new RuntimeException("runtimeexceptionmsg");
  }

}

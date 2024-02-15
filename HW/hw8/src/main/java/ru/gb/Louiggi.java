package ru.gb;

import org.slf4j.event.Level;
import org.springframework.stereotype.Component;
import ru.gb.aspect.Loggable;

@Component
public class Louiggi implements Brother {

  @Loggable(level = Level.WARN)
  public void method1(String arg1, int arg2) {
    long l = 12;
    for (int i=0;i<1000000;i++) {
      l = l + l*i;
    }
  }

  @Loggable(level = Level.WARN)
  public String method2() {
    return "value";
  }

  public String method3() {
    throw new RuntimeException("runtimeexceptionmsg");
  }

}

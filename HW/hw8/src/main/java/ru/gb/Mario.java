package ru.gb;

import org.slf4j.event.Level;
import org.springframework.stereotype.Component;
import ru.gb.aspect.Loggable;

@Loggable(level = Level.INFO)
@Component
public class Mario implements Brother {

  public void method1(String arg1, int arg2) {
    long i =  34 ^ 224;
  }

  public String method2() {
    long i = 11234123L * 41231232;

    return "value=" + i;
  }

  public String method3() {
    throw new RuntimeException("runtimeexceptionmsg");
  }

}

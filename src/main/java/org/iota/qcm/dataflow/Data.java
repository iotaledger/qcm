package org.iota.qcm.dataflow;

import java.util.ArrayList;
import java.util.List;

public class Data {
  byte[] value;
  private List<ThrowingRunnable> runnables = new ArrayList<>();

  public Data(int size, ThrowingRunnable onDataArrived) {
    value = new byte[size];
    if(onDataArrived != null) {
      runnables.add(onDataArrived);
    }
  }

  public void update() {
    for (ThrowingRunnable runnable : runnables) {
      runnable.run();
    }
  }

  public void onUpdate(ThrowingRunnable runnable) {
    runnables.add(runnable);
  }

}

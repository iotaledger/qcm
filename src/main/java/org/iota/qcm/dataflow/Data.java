package org.iota.qcm.dataflow;

import java.util.ArrayList;
import java.util.List;

public class Data {
  byte[] value;
  private List<ThrowingRunnable> runnables = new ArrayList<>();

  public Data(int size) {
    value = new byte[size];
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

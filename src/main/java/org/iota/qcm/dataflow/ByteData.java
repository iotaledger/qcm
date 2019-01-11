package org.iota.qcm.dataflow;

import org.iota.qcm.util.Data;
import org.iota.qcm.util.ThrowingRunnable;

import java.util.ArrayList;
import java.util.List;

public class ByteData implements Data {
  byte[] value;
  private List<ThrowingRunnable> runnables = new ArrayList<>();

  public ByteData(int size, ThrowingRunnable onDataArrived) {
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

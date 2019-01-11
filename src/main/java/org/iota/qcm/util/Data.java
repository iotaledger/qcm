package org.iota.qcm.util;

public interface Data {
  void update();
  void onUpdate(ThrowingRunnable runnable);
  int size();
  void set(int i, byte v);
}

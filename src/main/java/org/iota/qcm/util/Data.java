package org.iota.qcm.util;

public interface Data {
  void update();
  void onUpdate(ThrowingRunnable runnable);
}

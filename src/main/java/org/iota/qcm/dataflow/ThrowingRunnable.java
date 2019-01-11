package org.iota.qcm.dataflow;

public interface ThrowingRunnable extends Runnable {

  @Override
  default void run() {
    try {
      runThrows();
    } catch (final Exception e) {
      throw new RuntimeException(e);
    }
  }

  void runThrows() throws Exception;
}

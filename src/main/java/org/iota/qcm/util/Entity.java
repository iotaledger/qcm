package org.iota.qcm.util;

public interface Entity {
  void invoke(short[] data);
  void decay();
}

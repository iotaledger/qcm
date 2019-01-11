package org.iota.qcm.dataflow;

public interface Transition {
  int size();
  boolean accept(Flow[] sources, Data value);
}

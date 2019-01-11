package org.iota.qcm.dataflow;

public class Merger implements Transition {
  int mySize;

  public Merger(int size) {
    mySize = size;
  }


  @Override
  public int size() {
    return mySize;
  }

  @Override
  public boolean accept(Flow[] sources, ByteData data) throws CollidingDataException {
    boolean rv = false;
    byte b;
    for(int i = 0; i < data.value.length; i++) {
      b = 0;
      for(Flow source: sources) {
        if (source == null) {
          continue;
        }
        if(data.value[i] != 0 && source.data.value[i] != 0 && source.data.value[i] != data.value[i]) {
          throw new CollidingDataException();
        } else if (source.data.value[i] != 0) {
          b = source.data.value[i];
        }
      }
      if(data.value[i] != b) {
        rv = true;
        data.value[i] = b;
      }
    }
    return rv;
  }
}

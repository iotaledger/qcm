package org.iota.qcm.dataflow;

public class Lut implements Transition {
  byte[][][] table = new byte[4][4][4];

  public void addCell(int i, int j, int k, byte v) {
    table[i][j][k] = v;
  }

  @Override
  public int size() {
    return 1;
  }

  @Override
  public boolean accept(Flow[] sources, Data data) {
    boolean rv = false;
    byte b;
    if(sources[0] != null && sources[1] != null && sources[2] != null) {
      for (int i = 0; i < data.value.length; i++) {
        b = table
            [sources[0].data.value[i]]
            [sources[1].data.value[i]]
            [sources[2].data.value[i]];
        if(b != data.value[i]) {
          rv = true;
          data.value[i] = b;
        }
      }
      return rv;
    }
    return false;
  }
}

package org.iota.qcm.dataflow;

import org.iota.qcm.Branch;
import org.iota.qcm.util.Data;
import org.iota.qcm.util.Entity;
import org.iota.qcm.util.TritMath;

public class CpuEntity implements Entity {
  private Branch[] branches;
  private Data myData;

  public CpuEntity() {}

  public void invoke(short[] data) {
    int t;
    for(int i = 0, j = 0; i < myData.size(); j++) {
      t = TritMath.shortTrit(data[j]);
      for(int k = 0; k < 18 && i < myData.size(); k+=2, i++) {
        myData.set(i, (byte) ((t >> k) & 3));
      }
    }
  }

  @Override
  public void decay() {
    branches = null;
    myData = null;
    System.gc();
  }
}

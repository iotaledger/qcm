package org.iota.qcm.dataflow;

public class CopyingTransition implements Transition{
  private int mySize;

  public CopyingTransition(int size) {
    mySize = size;
  }

  @Override
  public int size() {
    return mySize;
  }

  @Override
  public boolean accept(Flow[] sources, ByteData value) {
    int index = 0;
    for(Flow source: sources) {
      System.arraycopy(source.data.value, 0, value.value, index, source.data.value.length);
      index += source.data.value.length;
    }
    return true;
  }
}

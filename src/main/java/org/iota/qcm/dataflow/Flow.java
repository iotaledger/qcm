package org.iota.qcm.dataflow;

import org.iota.qcm.Branch;

import java.util.ArrayList;
import java.util.List;

public class Flow implements Branch {
  Transition transition;
  List<ThrowingRunnable> runnables;
  Flow[] sources;
  Data data;
  
  public Flow(Transition myTransition, Flow[] mySources, ThrowingRunnable onFirstDataArrived) {
    transition = myTransition;
    sources = mySources;
    data = new Data(myTransition.size(), onFirstDataArrived);
    runnables = new ArrayList<>();

    for(Flow source: mySources) {
      source.data.onUpdate(this::invoke);
    }
  }

  public Flow(Data input, int start, int size, ThrowingRunnable onFirstDataArrived) {
    transition = null;
    sources = null;
    data = new Data(size, onFirstDataArrived);

    input.onUpdate(() -> {
      System.arraycopy(input.value, start, data.value, 0, size);
      data.update();
    });
  }

  public void invoke() throws Exception {
    if(transition.accept(sources, data)) {
      data.update();
    }
  }
}

package org.iota.qcm.dataflow;

import java.util.ArrayList;
import java.util.List;

public class Flow {
  Transition transition;
  List<ThrowingRunnable> runnables;
  Flow[] sources;
  Data data;
  
  public Flow(Transition myTransition, Flow[] mySources) {
    transition = myTransition;
    sources = mySources;
    data = new Data(myTransition.size());
    runnables = new ArrayList<>();

    for(Flow source: mySources) {
      source.data.onUpdate(this::flow);
    }
  }

  public Flow(Data input, int start, int size) {
    transition = null;
    sources = null;
    data = new Data(size);

    input.onUpdate(() -> {
      System.arraycopy(input.value, start, data.value, 0, size);
      data.update();
    });
  }

  public void flow() throws Exception {
    if(transition.accept(sources, data)) {
      data.update();
    }
  }
}

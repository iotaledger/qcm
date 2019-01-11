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
      source.onUpdate(this::update);
    }
  }

  public void update() {
    if(transition.accept(sources, data)) {
      for (ThrowingRunnable runnable : runnables) {
        runnable.run();
      }
    }
  }

  public void onUpdate(ThrowingRunnable runnable) {
    runnables.add(runnable);
  }
}

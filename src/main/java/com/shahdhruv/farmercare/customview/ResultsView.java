package com.shahdhruv.farmercare.customview;

import java.util.List;
import com.shahdhruv.farmercare.tflite.Classifier.Recognition;

public interface ResultsView {
  public void setResults(final List<Recognition> results);
}

package com.frank.fftalk.util;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceUtil {
  public static   ExecutorService executorService= Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
}

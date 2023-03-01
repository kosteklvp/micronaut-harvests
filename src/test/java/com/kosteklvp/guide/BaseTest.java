package com.kosteklvp.guide;

import java.io.File;
import java.util.Collections;
import java.util.Map;

import org.junit.jupiter.api.io.TempDir;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.test.support.TestPropertyProvider;

abstract public class BaseTest implements TestPropertyProvider {

  @TempDir
  static File tempDir;

  @Override
  @NonNull
  public Map<String, String> getProperties() {
    return Collections.singletonMap("microstream.storage.main.storage-directory", tempDir.getAbsolutePath());
  }
}
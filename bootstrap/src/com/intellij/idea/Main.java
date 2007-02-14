package com.intellij.idea;

import com.intellij.ide.plugins.PluginManager;
import com.intellij.openapi.util.Comparing;
import org.jetbrains.annotations.NonNls;

import java.awt.*;

public class Main {
  private static boolean isHeadless;

  private Main() {
  }

  public static void main(final String[] args) {
    isHeadless = isHeadless(args);
    if (!isHeadless && GraphicsEnvironment.isHeadless()) {
      throw new HeadlessException("Unable to detect graphics environment");
    }
    PluginManager.main(args, Main.class.getName() + "Impl", "start");
  }

  public static boolean isHeadless(final String[] args) {
    @NonNls final String inspectAppCode = "inspect";
    @NonNls final String diffAppCode = "diff";
    @NonNls final String antAppCode = "ant";
    @NonNls final String duplocateCode = "duplocate";
    return args.length > 0 && (Comparing.strEqual(args[0], inspectAppCode) ||
                               Comparing.strEqual(args[0], diffAppCode) ||
                               Comparing.strEqual(args[0], antAppCode) ||
                               Comparing.strEqual(args[0], duplocateCode));
  }

  public static boolean isHeadless() {
    return isHeadless;
  }
}

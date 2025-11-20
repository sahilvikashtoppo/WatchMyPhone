package com.watchmyphone.util;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class UsageStatsHelper_Factory implements Factory<UsageStatsHelper> {
  private final Provider<Context> ctxProvider;

  public UsageStatsHelper_Factory(Provider<Context> ctxProvider) {
    this.ctxProvider = ctxProvider;
  }

  @Override
  public UsageStatsHelper get() {
    return newInstance(ctxProvider.get());
  }

  public static UsageStatsHelper_Factory create(Provider<Context> ctxProvider) {
    return new UsageStatsHelper_Factory(ctxProvider);
  }

  public static UsageStatsHelper newInstance(Context ctx) {
    return new UsageStatsHelper(ctx);
  }
}

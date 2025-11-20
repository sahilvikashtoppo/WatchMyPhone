package com.watchmyphone.di;

import android.content.Context;
import com.watchmyphone.util.UsageStatsHelper;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class AppModule_ProvideUsageStatsHelperFactory implements Factory<UsageStatsHelper> {
  private final Provider<Context> ctxProvider;

  public AppModule_ProvideUsageStatsHelperFactory(Provider<Context> ctxProvider) {
    this.ctxProvider = ctxProvider;
  }

  @Override
  public UsageStatsHelper get() {
    return provideUsageStatsHelper(ctxProvider.get());
  }

  public static AppModule_ProvideUsageStatsHelperFactory create(Provider<Context> ctxProvider) {
    return new AppModule_ProvideUsageStatsHelperFactory(ctxProvider);
  }

  public static UsageStatsHelper provideUsageStatsHelper(Context ctx) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideUsageStatsHelper(ctx));
  }
}

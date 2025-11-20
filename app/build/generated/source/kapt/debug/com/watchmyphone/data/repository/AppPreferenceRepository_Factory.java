package com.watchmyphone.data.repository;

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
public final class AppPreferenceRepository_Factory implements Factory<AppPreferenceRepository> {
  private final Provider<Context> contextProvider;

  public AppPreferenceRepository_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public AppPreferenceRepository get() {
    return newInstance(contextProvider.get());
  }

  public static AppPreferenceRepository_Factory create(Provider<Context> contextProvider) {
    return new AppPreferenceRepository_Factory(contextProvider);
  }

  public static AppPreferenceRepository newInstance(Context context) {
    return new AppPreferenceRepository(context);
  }
}

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
public final class Camera2Helper_Factory implements Factory<Camera2Helper> {
  private final Provider<Context> contextProvider;

  public Camera2Helper_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public Camera2Helper get() {
    return newInstance(contextProvider.get());
  }

  public static Camera2Helper_Factory create(Provider<Context> contextProvider) {
    return new Camera2Helper_Factory(contextProvider);
  }

  public static Camera2Helper newInstance(Context context) {
    return new Camera2Helper(context);
  }
}

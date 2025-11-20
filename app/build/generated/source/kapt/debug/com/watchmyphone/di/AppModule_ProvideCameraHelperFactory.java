package com.watchmyphone.di;

import android.content.Context;
import com.watchmyphone.util.Camera2Helper;
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
public final class AppModule_ProvideCameraHelperFactory implements Factory<Camera2Helper> {
  private final Provider<Context> ctxProvider;

  public AppModule_ProvideCameraHelperFactory(Provider<Context> ctxProvider) {
    this.ctxProvider = ctxProvider;
  }

  @Override
  public Camera2Helper get() {
    return provideCameraHelper(ctxProvider.get());
  }

  public static AppModule_ProvideCameraHelperFactory create(Provider<Context> ctxProvider) {
    return new AppModule_ProvideCameraHelperFactory(ctxProvider);
  }

  public static Camera2Helper provideCameraHelper(Context ctx) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideCameraHelper(ctx));
  }
}

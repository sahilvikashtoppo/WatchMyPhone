package com.watchmyphone.di;

import com.watchmyphone.data.local.AppDatabase;
import com.watchmyphone.data.local.IntruderDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class AppModule_ProvideIntruderDaoFactory implements Factory<IntruderDao> {
  private final Provider<AppDatabase> dbProvider;

  public AppModule_ProvideIntruderDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public IntruderDao get() {
    return provideIntruderDao(dbProvider.get());
  }

  public static AppModule_ProvideIntruderDaoFactory create(Provider<AppDatabase> dbProvider) {
    return new AppModule_ProvideIntruderDaoFactory(dbProvider);
  }

  public static IntruderDao provideIntruderDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideIntruderDao(db));
  }
}

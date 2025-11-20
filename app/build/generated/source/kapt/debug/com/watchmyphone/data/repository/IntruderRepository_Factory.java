package com.watchmyphone.data.repository;

import com.watchmyphone.data.local.IntruderDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class IntruderRepository_Factory implements Factory<IntruderRepository> {
  private final Provider<IntruderDao> daoProvider;

  public IntruderRepository_Factory(Provider<IntruderDao> daoProvider) {
    this.daoProvider = daoProvider;
  }

  @Override
  public IntruderRepository get() {
    return newInstance(daoProvider.get());
  }

  public static IntruderRepository_Factory create(Provider<IntruderDao> daoProvider) {
    return new IntruderRepository_Factory(daoProvider);
  }

  public static IntruderRepository newInstance(IntruderDao dao) {
    return new IntruderRepository(dao);
  }
}

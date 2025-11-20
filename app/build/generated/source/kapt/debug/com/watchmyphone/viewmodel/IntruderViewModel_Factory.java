package com.watchmyphone.viewmodel;

import com.watchmyphone.data.repository.AppPreferenceRepository;
import com.watchmyphone.data.repository.IntruderRepository;
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
public final class IntruderViewModel_Factory implements Factory<IntruderViewModel> {
  private final Provider<IntruderRepository> repoProvider;

  private final Provider<AppPreferenceRepository> appPreferenceRepoProvider;

  public IntruderViewModel_Factory(Provider<IntruderRepository> repoProvider,
      Provider<AppPreferenceRepository> appPreferenceRepoProvider) {
    this.repoProvider = repoProvider;
    this.appPreferenceRepoProvider = appPreferenceRepoProvider;
  }

  @Override
  public IntruderViewModel get() {
    return newInstance(repoProvider.get(), appPreferenceRepoProvider.get());
  }

  public static IntruderViewModel_Factory create(Provider<IntruderRepository> repoProvider,
      Provider<AppPreferenceRepository> appPreferenceRepoProvider) {
    return new IntruderViewModel_Factory(repoProvider, appPreferenceRepoProvider);
  }

  public static IntruderViewModel newInstance(IntruderRepository repo,
      AppPreferenceRepository appPreferenceRepo) {
    return new IntruderViewModel(repo, appPreferenceRepo);
  }
}

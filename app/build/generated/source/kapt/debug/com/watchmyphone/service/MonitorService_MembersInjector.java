package com.watchmyphone.service;

import com.watchmyphone.data.repository.IntruderRepository;
import com.watchmyphone.util.Camera2Helper;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class MonitorService_MembersInjector implements MembersInjector<MonitorService> {
  private final Provider<Camera2Helper> cameraHelperProvider;

  private final Provider<IntruderRepository> repoProvider;

  public MonitorService_MembersInjector(Provider<Camera2Helper> cameraHelperProvider,
      Provider<IntruderRepository> repoProvider) {
    this.cameraHelperProvider = cameraHelperProvider;
    this.repoProvider = repoProvider;
  }

  public static MembersInjector<MonitorService> create(Provider<Camera2Helper> cameraHelperProvider,
      Provider<IntruderRepository> repoProvider) {
    return new MonitorService_MembersInjector(cameraHelperProvider, repoProvider);
  }

  @Override
  public void injectMembers(MonitorService instance) {
    injectCameraHelper(instance, cameraHelperProvider.get());
    injectRepo(instance, repoProvider.get());
  }

  @InjectedFieldSignature("com.watchmyphone.service.MonitorService.cameraHelper")
  public static void injectCameraHelper(MonitorService instance, Camera2Helper cameraHelper) {
    instance.cameraHelper = cameraHelper;
  }

  @InjectedFieldSignature("com.watchmyphone.service.MonitorService.repo")
  public static void injectRepo(MonitorService instance, IntruderRepository repo) {
    instance.repo = repo;
  }
}

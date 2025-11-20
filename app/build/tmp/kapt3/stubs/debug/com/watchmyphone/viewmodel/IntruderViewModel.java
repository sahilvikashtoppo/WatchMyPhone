package com.watchmyphone.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\rR\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000f\u00a8\u0006\u0018"}, d2 = {"Lcom/watchmyphone/viewmodel/IntruderViewModel;", "Landroidx/lifecycle/ViewModel;", "repo", "Lcom/watchmyphone/data/repository/IntruderRepository;", "appPreferenceRepo", "Lcom/watchmyphone/data/repository/AppPreferenceRepository;", "(Lcom/watchmyphone/data/repository/IntruderRepository;Lcom/watchmyphone/data/repository/AppPreferenceRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/watchmyphone/data/local/IntruderEntity;", "serviceEnabled", "Lkotlinx/coroutines/flow/StateFlow;", "", "getServiceEnabled", "()Lkotlinx/coroutines/flow/StateFlow;", "uiState", "getUiState", "delete", "Lkotlinx/coroutines/Job;", "id", "", "toggleService", "isEnabled", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class IntruderViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.watchmyphone.data.repository.IntruderRepository repo = null;
    @org.jetbrains.annotations.NotNull()
    private final com.watchmyphone.data.repository.AppPreferenceRepository appPreferenceRepo = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.watchmyphone.data.local.IntruderEntity>> _uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.watchmyphone.data.local.IntruderEntity>> uiState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> serviceEnabled = null;
    
    @javax.inject.Inject()
    public IntruderViewModel(@org.jetbrains.annotations.NotNull()
    com.watchmyphone.data.repository.IntruderRepository repo, @org.jetbrains.annotations.NotNull()
    com.watchmyphone.data.repository.AppPreferenceRepository appPreferenceRepo) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.watchmyphone.data.local.IntruderEntity>> getUiState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> getServiceEnabled() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job delete(long id) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job toggleService(boolean isEnabled) {
        return null;
    }
}
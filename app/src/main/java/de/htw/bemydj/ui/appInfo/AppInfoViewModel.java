package de.htw.bemydj.ui.appInfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AppInfoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AppInfoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is AppInfo fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
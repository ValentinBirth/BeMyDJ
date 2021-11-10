package de.htw.bemydj.ui.networkInfo;


import androidx.lifecycle.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class NetworkInfoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NetworkInfoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Network Info fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
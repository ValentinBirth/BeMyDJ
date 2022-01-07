package de.htw.bemydj.ui.appInstruction;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AppInstructionViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AppInstructionViewModel() {
        mText = new MutableLiveData<>();
        //TODO add instructions
        String instruction = "Instruction";
        mText.setValue(instruction);
    }

    public LiveData<String> getText() {
        return mText;
    }
}
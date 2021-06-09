package com.totalpe.mytask;

import com.google.android.material.textfield.TextInputLayout;

public class Utils {
    public static boolean isTextInputLayoutEmpty(TextInputLayout textInputLayout, String errorMsg) {

        if (textInputLayout.getEditText().getText().toString().trim().isEmpty()) {
            textInputLayout.setError(errorMsg);
            return false;
        } else {
            textInputLayout.setError(null);
            return true;
        }
    }
}

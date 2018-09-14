package com.huynd.view

import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext

class RNAutoCompleteTextViewPackage: ReactPackage {
  override fun createNativeModules(reactContext: ReactApplicationContext): MutableList<NativeModule> {
    return mutableListOf()
  }

  override fun createViewManagers(reactContext: ReactApplicationContext): MutableList<AutoCompleteTextViewManager> {
    return mutableListOf(
        AutoCompleteTextViewManager()
    )
  }
}

package com.huynd.view.listeners

import android.widget.AutoCompleteTextView
import com.facebook.react.modules.core.DeviceEventManagerModule
import com.facebook.react.uimanager.ThemedReactContext
import com.huynd.view.Constants.Companion.EVENT_ON_DISMISS

class OnDismissListener(private val reactContext: ThemedReactContext) : AutoCompleteTextView.OnDismissListener {
  override fun onDismiss() {
    reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java).emit(
        EVENT_ON_DISMISS, null
    )
  }
}

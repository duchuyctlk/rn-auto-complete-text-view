package com.huynd.view.listeners

import android.view.View
import android.widget.AdapterView
import com.facebook.react.bridge.Arguments
import com.facebook.react.modules.core.DeviceEventManagerModule
import com.facebook.react.uimanager.ThemedReactContext
import com.huynd.view.Constants.Companion.EVENT_ON_ITEM_CLICK
import com.huynd.view.Constants.Companion.EVENT_ON_ITEM_CLICK_KEY_CLICKED_ITEM

class OnItemClickListener(private val reactContext: ThemedReactContext) : AdapterView.OnItemClickListener {
  override fun onItemClick(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
    val event = Arguments.createMap()
    event.putString(EVENT_ON_ITEM_CLICK_KEY_CLICKED_ITEM, parent?.getItemAtPosition(position).toString())
    reactContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java).emit(
        EVENT_ON_ITEM_CLICK,
        event
    )
  }
}

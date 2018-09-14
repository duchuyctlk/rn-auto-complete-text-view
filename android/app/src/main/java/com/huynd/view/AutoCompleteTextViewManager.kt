package com.huynd.view

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp

class AutoCompleteTextViewManager: SimpleViewManager<AutoCompleteTextView>() {

  private lateinit var mContext: Context

  companion object {
    const val REACT_CLASS = "NativeAutoCompleteTextView"

    const val REACT_PROP_DATA_SOURCE = "dataSource"
    const val REACT_PROP_TEXT = "text"
    const val REACT_PROP_COMPLETION_HINT = "completionHint"
    const val REACT_PROP_COMPLETION_THRESHOLD = "completionThreshold"
    const val REACT_PROP_SHOW_DROP_DOWN = "showDropDown"

    const val PARAM_TEXT = "text"
    const val PARAM_FILTER = "filter"
  }

  override fun getName(): String {
    return REACT_CLASS
  }

  override fun createViewInstance(reactContext: ThemedReactContext): AutoCompleteTextView {
    mContext = reactContext
    return AutoCompleteTextView(reactContext)
  }

  @ReactProp(name = REACT_PROP_DATA_SOURCE)
  fun setDataSource(view: AutoCompleteTextView, dataSource: ReadableArray) {
    val list = ArrayList<String>()
    dataSource.toArrayList().forEach {
      list.add(it.toString())
    }
    val adapter = object: ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_dropdown_item, list) {}
    view.setAdapter(adapter)
  }

  @ReactProp(name = REACT_PROP_TEXT)
  fun setText(view: AutoCompleteTextView, text: ReadableMap) {
    val stringText = text.getString(PARAM_TEXT)
    val filter = text.getBoolean(PARAM_FILTER)
    view.setText(stringText, filter)
  }

  @ReactProp(name = REACT_PROP_COMPLETION_HINT)
  fun setCompletionHint(view: AutoCompleteTextView, completionHint: String) {
    view.completionHint = completionHint
  }

  @ReactProp(name = REACT_PROP_COMPLETION_THRESHOLD)
  fun setCompletionThreshold(view: AutoCompleteTextView, completionThreshold: Int) {
    view.threshold = completionThreshold
  }

  @ReactProp(name = REACT_PROP_SHOW_DROP_DOWN)
  fun showDropDown(view: AutoCompleteTextView, show: Boolean) {
    if (show) {
      view.showDropDown()
    }
  }
}

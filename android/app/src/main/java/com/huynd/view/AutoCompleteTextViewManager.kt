package com.huynd.view

import android.content.Context
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.facebook.react.bridge.ReadableArray
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.common.MapBuilder
import com.facebook.react.uimanager.SimpleViewManager
import com.facebook.react.uimanager.ThemedReactContext
import com.facebook.react.uimanager.annotations.ReactProp
import com.huynd.view.Constants.Companion.EVENT_ON_DISMISS
import com.huynd.view.Constants.Companion.EVENT_ON_ITEM_CLICK
import com.huynd.view.Constants.Companion.EVENT_REGISTRATION_NAME
import com.huynd.view.listeners.OnDismissListener
import com.huynd.view.listeners.OnItemClickListener

class AutoCompleteTextViewManager : SimpleViewManager<AutoCompleteTextView>() {

  private lateinit var mContext: Context

  companion object {
    private const val REACT_CLASS = "NativeAutoCompleteTextView"

    private const val REACT_PROP_DATA_SOURCE = "dataSource"
    private const val REACT_PROP_TEXT = "text"
    private const val REACT_PROP_COMPLETION_HINT = "completionHint"
    private const val REACT_PROP_COMPLETION_THRESHOLD = "completionThreshold"
    private const val REACT_PROP_SHOW_DROP_DOWN = "showDropDown"
    private const val REACT_PROP_DROP_DOWN_HEIGHT = "dropDownHeight"
    private const val REACT_PROP_DROP_DOWN_WIDTH = "dropDownWidth"
    private const val REACT_PROP_DROP_DOWN_HORIZONTAL_OFFSET = "dropDownHorizontalOffset"
    private const val REACT_PROP_DROP_DOWN_VERTICAL_OFFSET = "dropDownVerticalOffset"

    private const val PARAM_TEXT = "text"
    private const val PARAM_FILTER = "filter"

    private const val MATCH_PARENT = "match_parent"
    private const val WRAP_CONTENT = "wrap_content"
  }

  override fun getName(): String {
    return REACT_CLASS
  }

  override fun createViewInstance(reactContext: ThemedReactContext): AutoCompleteTextView {
    mContext = reactContext
    return AutoCompleteTextView(reactContext).apply {
      onItemClickListener = OnItemClickListener(reactContext)
      setOnDismissListener(OnDismissListener(reactContext))
    }
  }

  override fun getExportedCustomDirectEventTypeConstants(): MutableMap<String, Any> {
    return MapBuilder.builder<String, Any>()
        .put(EVENT_ON_ITEM_CLICK, MapBuilder.of(EVENT_REGISTRATION_NAME, EVENT_ON_ITEM_CLICK))
        .put(EVENT_ON_DISMISS, MapBuilder.of(EVENT_REGISTRATION_NAME, EVENT_ON_DISMISS))
        .build()
  }

  @ReactProp(name = REACT_PROP_DATA_SOURCE)
  fun setDataSource(view: AutoCompleteTextView, dataSource: ReadableArray) {
    val list = ArrayList<String>()
    dataSource.toArrayList().forEach {
      list.add(it.toString())
    }
    val adapter = object : ArrayAdapter<String>(mContext, android.R.layout.simple_spinner_dropdown_item, list) {}
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
    with(view) {
      if (show) {
        showDropDown()
      } else {
        dismissDropDown()
      }
    }
  }

  @ReactProp(name = REACT_PROP_DROP_DOWN_HEIGHT)
  fun setDropDownHeight(view: AutoCompleteTextView, value: String) {
    view.dropDownHeight = getLayoutSizeValue(value)
  }

  @ReactProp(name = REACT_PROP_DROP_DOWN_WIDTH)
  fun setDropDownWidth(view: AutoCompleteTextView, value: String) {
    view.dropDownWidth = getLayoutSizeValue(value)
  }

  @ReactProp(name = REACT_PROP_DROP_DOWN_HORIZONTAL_OFFSET)
  fun setDropDownHorizontalOffset(view: AutoCompleteTextView, offsetInDp: Int) {
    view.dropDownHorizontalOffset = dpToPixels(offsetInDp)
  }

  @ReactProp(name = REACT_PROP_DROP_DOWN_VERTICAL_OFFSET)
  fun setDropDownVerticalOffset(view: AutoCompleteTextView, offsetInDp: Int) {
    view.dropDownVerticalOffset = dpToPixels(offsetInDp)
  }

  private fun getLayoutSizeValue(value: String): Int {
    return if (value == MATCH_PARENT) ViewGroup.LayoutParams.MATCH_PARENT else ViewGroup.LayoutParams.WRAP_CONTENT
  }

  private fun dpToPixels(dpValue: Int): Int {
    val pxValue = dpValue * mContext.resources.displayMetrics.density
    return pxValue.toInt()
  }
}

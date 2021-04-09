# coconut-ui
**Version 1.1.2-alpha-v7**

 - support wrapper method for bottomSheetDialog
 - support dropdown bottom slide panel

**Usage:**

[build.gradle]
<pre>
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
</pre>

<pre>
dependencies {
	        implementation 'com.github.acan12:coconut-ui:1.1.2-alpha-v3'
	}
</pre>

# Available Features:
**1. Show Slide Panel from bottom**
<pre>
// custom layout for content slide panel -> R.layout.content_slide
UIBottomSheetSlidePanel.showSlidePanel(R.layout.content_slide, this,   object : UIBottomSheetSlidePanel.SlideCallbackListener() {  
            override fun onCall(dialog: BottomSheetDialog, view: View) {  
                view.paymentButton.setOnClickListener {  
		  			dialog.dismiss();  
			}  
		}  
 })
</pre>

**2. Show custom dropdown from bottom**
```kotlin
    // build and show dropdown slide up
    // parentLayout -> Layout Activity or Fragment on Element Root ID
     var dropDown = UiDropdownOnBottom.instance.build(
                listOf<DropDownItemModel>(
                    DropDownItemModel("first"),
                    DropDownItemModel("second")
                ),
                parentLayout,
                this,
                object : UiDropdownOnBottom.OnItemListener() {
                    override fun onClick(view: View, pos: Int, item: DropDownItemModel) {
                        Toast.makeText(
                            this@DemoDropDownActivity,
                            "name: ${(view as TextView).text} | ${item.value}",
                            Toast.LENGTH_SHORT
                        ).show()
                        onBackPressed()
                    }

                    override fun outsideItemClick() {
                        onBackPressed()
                    }

                }
            )?.show()
    
    ...
    // dismiss
    override fun onBackPressed() {
        if (!dropDown!!.dismiss()) {
            super.onBackPressed()
        }
    }
```

**3. Custom layout and change color of coconut dropdown** 

[`coconut_slide_panel.xml`]  use for background layout
```xml
    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@color/coconut_color_background_transparent"
        android:orientation="vertical">
    
        <View
            android:id="@+id/slideBackgroundCoconut"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_above="@+id/scrollbar" />
    
        <ScrollView
            android:id="@+id/scrollbar"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_alignParentBottom="true"
            android:fillViewport="true">
    
            <LinearLayout
                android:id="@+id/slideContentCoconut"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:background="@color/color_white"
                android:orientation="vertical" />
        </ScrollView>
    </RelativeLayout>   
```
*must have element ID `slideBackgroundCoconut`, and `slideContentCoconut`*

[`color.xml`]  
```xml
    <!-- Dropdown Component  -->
    <color name="coconut_color_item_selected">#f3f3f3</color>
    <color name="coconut_text_color">#000000</color>
    <color name="coconut_color_background_transparent">#CC000000</color>
```


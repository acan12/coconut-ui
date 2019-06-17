# coconut-ui
**version 1.1.1:**

 - integrate wrapper method for bottomSheetDialog 

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
	        implementation 'com.github.acan12:coconut-ui:1.1.1'
	}
</pre>

[Kotlin]
<pre>
// [Activity]
// custom layout for content slide panel -> R.layout.content_slide
UIBottomSheetSlidePanel.showSlidePanel(R.layout.content_slide, this,   object : UIBottomSheetSlidePanel.SlideCallbackListener() {  
            override fun onCall(dialog: BottomSheetDialog, view: View) {  
                view.paymentButton.setOnClickListener {  
		  			dialog.dismiss();  
			}  
		}  
 })
</pre>



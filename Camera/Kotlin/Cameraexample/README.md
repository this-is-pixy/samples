&#x202b;
# استفاده از دوربین بدون دسترسی
&#x202b;
.برای استفاده از دوربین و ثبت تصاویر بدون دسترسی لازم است مطابق کد عمل کنید
&#x202b;
با توجه به [قوانین کافه‌بازار](https://developers.cafebazaar.ir/fa/app-publish-guidelines/) برنامه‌هایی که از دوربین دستگاه استفاده می‌کنند نباید از دسترسی زیر در برنامه استفاده کرده باشند
```kotlin
<uses-permission android:name="android.permission.CAMERA"/>
```
&#x202b;
##### .جهت پیاده‌سازی این مورد لازم است طبق مراحل زیر عمل کنید

&#x202b;
.ابتدا با استفاده از قطع کد زیر درخواست بازگشایی دوربین دستگاه را ارسال می‌کنیم
```Kotlin
val cameraIntent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
startActivityForResult(cameraIntent, CAMERA_REQUEST)
```
&#x202b;
.پس‌از بازگشایی دوربین و ثبت تصویر، محتوا به برنامه برگشت داده می‌شود
.جهت دریافت تصویر مانند مثال زیر عمل می‌کنیم
```Kotlin
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
super.onActivityResult(requestCode, resultCode, data)
    if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
        var imageUri =  data?.data
        // do something with image
    }
}
```

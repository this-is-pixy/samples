<div dir="rtl">

#### برقراری تماس با "Intent"

جهت برقراری تماس بدون استفاده از دسترسی "Call Phone" به صورت زیر عمل می‌کنیم.

<div dir="ltr">
  
```Java
Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:09xxxxxxxxx"))
startActivity(intent)
```
</div> 
  
در صورتی که اکش `Intent.ACTION_DIAL` را با `Intent.ACTION_CALL` جایگزین کنیم خطای `java.lang.SecurityException` دریافت خواهیم کرد.
این بدان معناست که استفاده از اکشن `Intent.ACTION_CALL` نیازمند اضافه کردن دسترسی `Call Phone` در فایل منیفست برنامه است.

</div>

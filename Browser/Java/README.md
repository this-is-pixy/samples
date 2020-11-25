<div dir="rtl"><b>دریافت وضعیت پرداخت در مرورگر خارج از برنامه</b></div>
<br>
<br>
<div dir="rtl">باتوجه یه این‌که درگاه پرداخت می‌بایست در مرورگری خارج از برنامه بازگشایی و نمایش داده شود
لازم است از طریقی برنامه وضعیت پرداخت را جهت ارائه خدمات مناسب بررسی کند.
</div>
<div dir="rtl">فرض کنید پس‌از پرداخت بدون در نظر گرفتن وضعیت آن (موفق، ناموفق) مرورگر به آدرسی مانند آدرس زیر توسط سرور Redirect می‌شود.</div>

```php
header('Location: https://myapplication.ir?q=valuee&s=success');
```
<div dir="rtl">
جهت بازگشایی برنامه توسط مرورگر پس‌از Redirect شدن به آدرس فوق می‌بایست در ابتدا با استفاده از IntentFilter ها برنامه‌ی خود را به عنوان میزبان معرفی کنیم.
</div> 

<div dir="rtl">فرض کنید وضعیت پرداخت باید به اکتیویتی با نام PaymentActivity برگرداند شود. برای این‌کار تگ activity مربوط به اکتیویتی پرداخت درفایل Manifest به صورت زیر خواهد بود.</div>

```xml
<activity android:name=".PaymentActivity">  
	<intent-filter> 
		<action android:name="android.intent.action.VIEW"/>  
		<category android:name="android.intent.category.BROWSABLE"/>  
		<category android:name="android.intent.category.DEFAULT"/>  
		<data  
			android:host="myapplication.ir" 
			android:scheme="https">  
		</data>  
	</intent-filter>
</activity>
```
<div dir="rtl"> با استفاده از تگ زیر PaymentActivity قادر خواهد بود تا توسط مرورگر فراخوانی شود.</div>

```xml
<category android:name="android.intent.category.BROWSABLE"/>
```
<div dir="rtl">
با استفاده ازتگ زیر Intent های ارسالی از مرورگر را فیلتر خواهیم کرد.
</div>

```xml
<data  
	android:host="myapplication.ir" 
	android:scheme="https">  
</data>
```
<div dir="rtl">
باتوجه به نحوه‌ی پیاده‌سازی برنامه هم‌اکنون قادر خواهد بود آدرس‌های باز‌شده درمرورگر با فرمت زیر را میزبانی کند.
</div>

```html
https://myapplication.ir?q=valuee&s=success
```
<div dir="rtl">
پس‌از اجرای PaymentActivity توسط مرورگر، متود زیر صدا زده خواهد شد.
</div>

```Java
@Override  
protected void onNewIntent(Intent intent) {  
	super.onNewIntent(intent);  
	if(intent.getAction() != null && intent.getAction().equals(Intent.ACTION_VIEW)) {  
		Uri uri = intent.getData();  
		try {  
			String q = uri.getQueryParameter("q");  
			String s = uri.getQueryParameter("s");  
			//do something with data  
  
		}catch (NullPointerException exception) { }  
	}  
}
```

<div dir="rtl">
پیشنهاد می‌کنیم وضعیت پرداخت را از طریق query-string ها دریافت نکنید. بهتر است پس‌از بازگشت از مرورگر برنامه وضعیت پرداخت را ازمنبعی معتبر مانند سرور سوال کند.
</div>

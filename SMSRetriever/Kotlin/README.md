<div dir="rtl"><b>استفاده از "Google SMS Retriever API" جهت درج خودکار پیامک فعال‌سازی</b></div>

<div dir="rtl">
برای تأیید خودکار شماره تلفن‌ها، باید هر دو بخش کلاینت و سرور تأیید شماره همراه را پیاده سازی کنید. این سند نحوه پیاده‌سازی بخش مشتری در برنامه "Android" را توصیف می کند.
</div>
<br>
<div dir="rtl">
ابتدا "Play Services auth component" را در فایل "Gradle" خود اضافه کنید.
</div>

```php
implementation 'com.google.android.gms:play-services-auth:19.0.0'  
implementation 'com.google.android.gms:play-services-auth-api-phone:17.5.0'
```
<div dir="rtl"> 
<p>
شما می‌توانید شماره همراه کاربر را به هر صورت که برای برنامه‌ی شما مناسب است بدست آورید.
غالباً پیاده‌سازی باکسی با امکان انتخاب شماره همراه ذخیره‌شده در دستگاه تجربه کاربری زیباتری را به کاربر ارائه می‌دهد.
</p>
<p>
روش زیر به پیاده‌سازی این فرایند به شما کمک خواهد کرد. البته لازم به ذکر است حذف این مرحله خللی در عملکرد برنامه‌ی شما جهت خواندن خودکار پیامک‌ها ایجاد نخواهد کرد.
</p>
</div>

```java
private fun requestHint() {  
	val hintRequest = HintRequest.Builder().apply { 
		setPhoneNumberIdentifierSupported(true)  
	}  
  
	val credentialIntent =	
		Credentials.getClient(this).getHintPickerIntent(hintRequest.build())  
	startIntentSenderForResult(credentialIntent.intentSender, RESOLVE_HINT, null, 0, 0, 0)  
}
```
<div dir="rtl">
<p> شماره همراه انتخاب شده توسط کاربر به روش "onActivityResult" بر خواهد گشت.<p/>
 </div>

```java
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {  
    super.onActivityResult(requestCode, resultCode, data)  
    if (requestCode == RESOLVE_HINT)  
        if (resultCode == Activity.RESULT_OK) {  
            val credential = data?.getParcelableExtra<Credential>(Credential.EXTRA_KEY)  
  
            // Selected phone number  
            val phone = credential?.id;  
  }  
}
```
<div dir="rtl">
<p> جهت شروع فرایند تایید خودکار شماره همراه کاربر از روش زیر استفاده می کنیم.</p>
</div>

```java
fun smsRetriever() {  
	val smsRetriever = SmsRetriever.getClient(context)  
	val task = smsRetriever.startSmsRetriever()  
  
	task.addOnSuccessListener {  
		// Successfully started retriever, expect broadcast intent  
		// ...  
	}  
  
	task.addOnFailureListener {  
		// Failed to start retriever, inspect Exception for more details 
		// ...  
	}  
}
```
<div dir="rtl">
<p>فرایند فوق تنها برای ۵ دقیقه جهت دریافت پیامک حاوی کد فعال‌سازی و شناسه‌ی منحصر به فرد برنامه‌ی شما فعال خواهد بود.</p>
</div>

<div dir="rtl">
<p>زمانی که پیامک فعال‌سازی در دستگاه کاربر دریافت  شد، سرویس‌های گوگل یک "Intent" با اکشن "SmsRetriever.SMS_RETRIEVED_ACTION" ارسال خواهند کرد که حاوی متن پیام است.</p>
<p> جهت دریافت آن لازم است از یک "BroadcastReceiver" استفاده کنید.</p>
</div>

```Java
class MBroadcastRetriever : BroadcastReceiver() {  
    override fun onReceive(context: Context?, intent: Intent?) {  
        if(intent != null) {  
            if (SmsRetriever.SMS_RETRIEVED_ACTION == intent.action) {  
                val extras = intent.extras  
                val status: Status? = extras!![SmsRetriever.EXTRA_STATUS] as Status  
                when (status?.statusCode) {  
                    CommonStatusCodes.SUCCESS -> {  
                        // Get SMS message contents  
                        val smsMessage = extras[SmsRetriever.EXTRA_SMS_MESSAGE] as String?  
                    }  
                    CommonStatusCodes.TIMEOUT -> {  
	                    // Waiting for SMS timed out (5 minutes)  
						// Handle the error ...  
					}  
                }  
            }  
        }  
    }  
}
```
<div dir="rtl">
همان‌طور که مشخص است، متن پیامک در متغیر "smsMessage" ذخیره شده است.
شما می‌توانید از این متغیر جهت درج خودکار کد فعال‌سازی در کادر مربوط استفاده کنید.
</div>
<br>
<div dir="rtl">
	<p><b>نکات مربوط به قالب صحیح پیامک ارسالی</b></p>
</div>
<div dir="rtl">
	<ul>
		<li>
			<span> طول پیامک ارسالی نباید بیشتر از ۱۴۰ بایت باشد</span>
		</li>
		<li>
			<span>متن پیامک حاوی یک کد فعال‌سازی یک‌بار مصرف است  </span>
		</li>
		<li>
			<span>متن پیامک شامل یک رشته‌ی منحصر به فرد ۱۱ کارکتری است که برنامه‌ی شما را مشخص می‌کند </span>
		</li>
	<ul>
</div>

```xml
Your ExampleApp code is: 5489A2

FA+9qCX9VSu
```
<br>
<div dir="rtl">
	<p><b>محاسبه رشته هش برنامه شما</b></p>
</div>
<div dir="rtl">
	<span>
	جهت محاسبه‌ی رشته‌ی یکتا مربوط به برنامه از <a href="">اسکریپت</a> توسعه‌داده شده توسط گوگل استفاده کنید.
	ما بخش‌هایی از این اسکریپت را برای استفاده‌ی آسان‌تر تغییر دادیم.
	</span>
</div>

<div dir="rtl">
	<p>
	پس‌از دریافت فایل اسکریپت، کد زیر را در محیط "command prompt" اجرا کنید.
	</p>
</div>

```xml
sms_retriever_hash_v9.sh --package "com.your.packagename" --keystore /path/to/your.keystore
```
<div dir="rtl">
	<p>فراموش نکنید نام بسته‌ی برنامه‌ی و مسیر فایل "keystore" را متناسب با برنامه‌ی خود تغییر دهید.</p>
</div>
<div dir="rtl">
	<p>خروجی اسکریپت یک فایل "sms_retriever_hash.txt" با محتویات زیر است.</p>
</div>

```xml
SMS Retriever hash code:  [value: (example IOhh7MhVDB5)]
First 8 bytes encoded by base64: [value]
SHA-256 output in hex: [value]
certificate in hex: [value]
```
<div dir="rtl">
	<p>مقدار "SMS Retriever hash code" چکیده‌ی برنامه‌ی شماست که در متن پیامک ارسال می‌شود.</p>
</div>

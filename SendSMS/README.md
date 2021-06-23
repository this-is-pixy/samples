<div dir="rtl">

### ارسال پیام کوتاه
جهت ارسال پیام کوتاه از طریق "Intent" می‌توان به روش زیر عمل کرد.

<div dir="ltr">

```Java
Uri uri = Uri.parse("smsto:09xxxxxxxxx");   
Intent intent = new Intent(Intent.ACTION_SENDTO, uri);   
intent.putExtra("sms_body", "The SMS text");   
startActivity(intent);  
```
</div>

پس‌از اجرای کد بالا، "message app" دستگاه با اطلاعات ارسالی از طریق "Intent" نمایش داده می‌شود و کاربر با اختیار خود می‌تواند پیام را ارسال و یا آن‌را حذف نماید.

استفاده از "Intent" در ارسال پیام کوتاه نیازی به اضافه کردن دسترسی "Send SMS" ندارد.

</div>

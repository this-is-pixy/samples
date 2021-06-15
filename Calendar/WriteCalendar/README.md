<div dir="rtl">

### افزودن رویداد
جهت افزودن رویداد در تقویم از طریق `Intent` می‌توان به روش زیر عمل کرد.

<div dir="ltr">

```Java
 Intent intent = new Intent(Intent.ACTION_INSERT)
        .setData(Events.CONTENT_URI)
        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
        .putExtra(Events.TITLE, "title")
        .putExtra(Events.DESCRIPTION, "descripotion")
        .putExtra(Events.EVENT_LOCATION, "location")
        .putExtra(Events.AVAILABILITY, Events.AVAILABILITY_BUSY)
        .putExtra(Intent.EXTRA_EMAIL, "john@gmail.com");
startActivity(intent);
```
</div>

پس‌از درج اطلاعات مورد نظر خود می‌توانید از `Intent` و تقویم پیشفرض دستگاه جهت افزودن رویداد استفاده کنید.

این نحوه‌ی پیاده‌سازی نیازی به استفاده از دسترسی خاصی ندارد.


</div>
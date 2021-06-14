<div dir="rtl">

 ### افزودن مخاطب
جهت افزودن مخاطب از طریق `Intent` از کد زیر استفاده کنید.

<div dir="ltr">

```Java

Intent intent = new Intent(Intent.ACTION_INSERT);

intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
intent.putExtra(ContactsContract.Intents.Insert.NAME, "09xxxxxxxxx");
intent.putExtra(ContactsContract.Intents.Insert.PHONE, "John");
intent.putExtra(ContactsContract.Intents.Insert.EMAIL, "John@mail.com");

startActivity(intent);
```
</div>

درصورت تمایل به بسته شدن خودکار صفحه‌ی مخاطبین مقدار زیر را همراه با Intent ارسال کنید.

<div dir="ltr">

```Java
intent.putExtra("finishActivityOnSaveCompleted", true)
```
</div>

----
 ### ویرایش و حذف مخاطب
برای انجام عملیات حذف و ویرایش یک مخاطب، با استفاده از کد زیر صفحه‌ی جزئیات مخاطب ذخیره شده در دفترچه تلفن دستگاه را نمایش می‌دهیم،
کاربر به انتخاب خود می‌تواند مخاطب نمایش داده‌شده را حذف یا ویرایش کند.

<div dir="ltr">

```Java

Intent intent = new Intent(Intent.ACTION_VIEW);
Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_URI, String.valueOf(contactID));
intent.setData(uri);
startActivity(intent);

```
</div>

`contactID` شناسه‌ی مخاطب مورد نظر می‌باشد که می‌توان [به این صورت](https://github.com/this-is-pixy/samples/tree/master/Contact/ReadContact) آن‌را دریافت کرد.

</div>

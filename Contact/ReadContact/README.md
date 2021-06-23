<div dir="rtl">

#### بازیابی مخاطبین ذخیره شده

جهت دریافت اطلاعات تنها یک مخاطب و انتخاب آن توسط کاربر ابتدا از "Intent" برای نمایش لیست مخاطبین ذخیر شده استفاده می‌کنیم.


<div dir="ltr">

```Java
Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);  
startActivityForResult(intent, {reqCode}); 
```
</div>

"reqCode" هر مقدار دلخواه با تایپ "int" است که جهت ایجاد وجه تمایز بین نتیجه‌های برگشتی استفاده می‌شود.

پس‌از انتخاب مخاطب توسط کاربر، نتیجه به روش "onActivityResult" برمی‌گردد

<div dir="ltr">

```Java
@Override 
public void onActivityResult(int reqCode, int resultCode, Intent data){
	super.onActivityResult(reqCode, resultCode, data);
}
```
</div>

به منظور خواندن شماره همراه و نام مخاطب انتخاب شده توسط کاربر به صورت زیر عمل کنید.

<div dir="ltr">

```Java
Uri contactUri = data.getData(); 
String[] projection = {
	ContactsContract.CommonDataKinds.Phone.NUMBER, 
	ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
    	ContactsContract.CommonDataKinds.Phone._ID
    }
Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
cursor.moveToNext();

Long id = cursor.getLong(cursor.getColumnIndex(projection[2]));
String name = cursor.getString(cursor.getColumnIndex(projection[1]));
String phoneNumber = cursor.getString(cursor.getColumnIndex(projection[0]));

cursor.close();
```

</div>


</div>

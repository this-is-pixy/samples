<div dir="rtl">

#### ÈÇÒíÇÈí ãÎÇØÈíä ĞÎíÑå ÔÏå

ÌåÊ ÏÑíÇİÊ ÇØáÇÚÇÊ ÊäåÇ í˜ ãÎÇØÈ æ ÇäÊÎÇÈ Âä ÊæÓØ ˜ÇÑÈÑ ÇÈÊÏÇ ÇÒ `Intent` ÈÑÇí äãÇíÔ áíÓÊ ãÎÇØÈíä ĞÎíÑ ÔÏå ÇÓÊİÇÏå ãí˜äíã.


<div dir="ltr">

```Java
Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);  
startActivityForResult(intent, {reqCode}); 
```
</div>

`reqCode` åÑ ãŞÏÇÑ ÏáÎæÇå ÈÇ ÊÇí `int` ÇÓÊ ˜å ÌåÊ ÇíÌÇÏ æÌå ÊãÇíÒ Èíä äÊíÌååÇí ÈÑÔÊí ÇÓÊİÇÏå ãíÔæÏ.

ÓÇÒ ÇäÊÎÇÈ ãÎÇØÈ ÊæÓØ ˜ÇÑÈÑ¡ äÊíÌå Èå ÊÇÈÚ `onActivityResult` ÈÑãíÑÏÏ

<div dir="ltr">

```Java
@Override 
public void onActivityResult(int reqCode, int resultCode, Intent data){
	super.onActivityResult(reqCode, resultCode, data);
}
```
</div>

Èå ãäÙæÑ ÎæÇäÏä ÔãÇÑå åãÑÇå æ äÇã ãÎÇØÈ ÇäÊÎÇÈ ÔÏå ÊæÓØ ˜ÇÑÈÑ Èå ÕæÑÊ ÒíÑ Úãá ˜äíÏ.

<div dir="ltr">

```Java
Uri contactUri = data.getData(); 
String[] projection = {
    ContactsContract.CommonDataKinds.Phone.NUMBER, 
	ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
    ContactsContract.CommonDataKinds.Phone.CONTACT_ID
    }
Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
cursor.moveToNext();

phone.id = cursor.getLong(cursor.getColumnIndex(projection[2]));
phone.name = cursor.getString(cursor.getColumnIndex(projection[1]));
phone.phoneNumber = cursor.getString(cursor.getColumnIndex(projection[0]));

```

</div>


</div>
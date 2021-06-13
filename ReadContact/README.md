<div dir="rtl">

#### ������� ������� ����� ���

��� ������ ������� ���� � ����� � ������ �� ���� ����� ����� �� `Intent` ���� ����� ���� ������� ���� ��� ������� �흘���.


<div dir="ltr">

```Java
Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);  
startActivityForResult(intent, {reqCode}); 
```
</div>

`reqCode` �� ����� ������ �� ��� `int` ��� �� ��� ����� ��� ����� ��� �������� �ѐ��� ������� �����.

�ӝ�� ������ ����� ���� ����ѡ ����� �� ���� `onActivityResult` ���흐���

<div dir="ltr">

```Java
@Override 
public void onActivityResult(int reqCode, int resultCode, Intent data){
	super.onActivityResult(reqCode, resultCode, data);
}
```
</div>

�� ����� ������ ����� ����� � ��� ����� ������ ��� ���� ����� �� ���� ��� ��� ����.

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
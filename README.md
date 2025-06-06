# My-house# سیستم مدیریت دستگاه‌های هوشمند

این پروژه یک سیستم ساده برای مدیریت دستگاه‌های هوشمند مانند چراغ‌ها و ترموستات‌ها به زبان جاوا است. با استفاده از این سیستم می‌توانید دستگاه‌ها را اضافه، ویرایش، حذف کرده و برای آن‌ها قوانین زمان‌بندی شده تعریف کنید.

## امکانات

- افزودن دستگاه‌های هوشمند (چراغ و ترموستات)
- ویرایش ویژگی‌های دستگاه‌ها (روشن/خاموش، دما، روشنایی)
- حذف دستگاه‌ها
- افزودن قوانین زمان‌بندی شده برای تغییر وضعیت دستگاه‌ها
- بررسی و اعمال قوانین بر اساس زمان مشخص

## ساختار پروژه

- `Device`: کلاس نماینده‌ی یک دستگاه هوشمند.
- `Rule`: کلاس نماینده‌ی یک قانون زمان‌بندی شده.
- `Database`: کلاس اصلی برای مدیریت دستگاه‌ها و قوانین.

کلاس database



متدها:

addDevice(String type, String name, String protocol)

افزودن یک دستگاه جدید به سیستم. بررسی می‌کند که نام تکراری نباشد و نوع و پروتکل معتبر باشند.


setDevice(String name, String property, String value)

تغییر ویژگی‌های یک دستگاه مانند روشن/خاموش بودن، دما (برای ترموستات) یا روشنایی (برای چراغ).


removeDevice(String name)

حذف یک دستگاه بر اساس نام آن. قوانین مرتبط با این دستگاه هم حذف می‌شوند.


listDevices()
لیستی از همه دستگاه‌های اضافه شده را برمی‌گرداند.



addRule(String deviceName, String time, String action)

اضافه کردن یک قانون (Rule) برای روشن یا خاموش شدن یک دستگاه در یک زمان خاص.


listRules()

لیستی از تمام قوانین تعریف‌شده را برمی‌گرداند.


checkRules(String time)

در یک زمان خاص بررسی می‌کند که آیا قانونی وجود دارد یا نه، و در صورت وجود، آن را اعمال می‌کند.


isValidTime(String time)

بررسی می‌کند که زمان ورودی معتبر باشد (فرمت صحیح و مقادیر قابل قبول برای ساعت و دقیقه).


applyAction(String deviceName, String action)

برای یک دستگاه خاص، عملیات روشن یا خاموش کردن را انجام می‌دهد.


کلاس Device

کلاس نماینده‌ی دستگاه هوشمند (چراغ یا ترموستات).


متدها:

getType()
بازگرداندن نوع دستگاه.



getName()

بازگرداندن نام دستگاه.


getProtocol()

بازگرداندن نوع پروتکل دستگاه (WiFi یا Bluetooth).



setStatus(boolean status)

روشن یا خاموش کردن دستگاه.


setBrightness(int brightness)

تنظیم روشنایی (ویژه چراغ‌ها).


setTemperature(int temperature)

تنظیم دما (ویژه ترموستات‌ها).


toString()

نمایش اطلاعات کلی دستگاه به صورت یک رشته.


کلاس Rule

کلاس نماینده‌ی یک قانون زمان‌بندی‌شده برای کنترل وضعیت دستگاه.


متدها:

getDeviceName()

بازگرداندن نام دستگاه مربوط به قانون.


getTime()

بازگرداندن زمان اجرای قانون.


getAction()

بازگرداندن نوع عمل (روشن یا خاموش).


toString()

نمایش اطلاعات قانون به صورت یک رشته متنی.





Функционал - возмодность просмотреть курс валют относительно выбранной валюты. Имеется возможность отметить валюту как избранную с сохранением в БД

<img width="200" alt="image" src="https://user-images.githubusercontent.com/74677991/201527797-7d215237-d133-4e2e-9d54-138d6b88376d.png"><img width="200" alt="image" src="https://user-images.githubusercontent.com/74677991/201527809-f8de956d-9c91-491f-b5f1-925a8d333792.png">
<img width="200" alt="image" src="https://user-images.githubusercontent.com/74677991/201527837-71c03326-cf11-40f8-bb7e-d3ae45b7c9c2.png"><img width="200" alt="image" src="https://user-images.githubusercontent.com/74677991/201527859-c0bc692f-d738-4e58-a5c0-36fb6eac3ba8.png">


Стэк: Kotlin, Retrofit, StateFlow, Room, MVVM
API: https://www.vatcomply.com/documentation

P.S.
В списке валют есть русский рубль, однако при его выборе выпадает ошибка. Я специально его оставил для показа обработки ошибок

P.S.S. Мапинг json с валютами с сервера выглядит непрезентабельно из-за того, что сервер отдает не лист с валютами, а объект, внутри которого 32 объекта. 

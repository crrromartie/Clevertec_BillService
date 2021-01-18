# Clevertec_BillService

Для запуска приложения необходимо импортировать базу данных MySQL (папка - database) в корне. 
В файле database.properties(resources/properties/database.properties) необходимо указать 
пароль и имя пользователя базы данных MySQL. Все пути к файлам настраиваются в константах класса FilePath. 
Запуск таски по скачиванию шаблона: gradle downloadPlugin 
Путь скачивания файла переопределяется в build.gradle (source.targetFile = 'path')

# 🧩 User API

Простой REST API на Spring Boot для управления пользователями.  
Позволяет создавать, редактировать, просматривать и удалять пользователей, включая загрузку фотографии.

## ⚙️ Технологии

- Java 24
- Spring Boot 3.5.4
- PostgreSQL
- Hibernate (JPA)
- Spring Security (Basic Auth)
- Lombok

## 🔐 Авторизация

API защищён базовой авторизацией (для базовой защиты тестового проекта этого достаточно).  
**Логин:** `admin`  
**Пароль:** `admin`

## 📦 CRUD по пользователям

Путь: `/api/users`

| Метод | Описание             | Тип запроса       |
|-------|----------------------|-------------------|
| GET   | Получить всех        | `application/json` |
| GET   | Получить по ID       | `application/json` |
| POST  | Создать пользователя | `multipart/form-data` |
| PUT   | Обновить пользователя| `multipart/form-data` |
| DELETE| Удалить по ID        | —                 |

## 📷 Работа с фото

- Фото можно передать как `MultipartFile` (формат `jpg/jpeg/png`);
- Путь к сохранённому файлу сохраняется в поле `photoPath`.

## 🧪 Тесты

- В проекте присутствуют unit-тесты для `UserService` и `UserController`;
- Покрыты основные сценарии: создание, чтение, валидация, ошибки.

## 🚀 Запуск

1. Настроить PostgreSQL, создать БД (например `user_api`) через команду:
```bash
createdb -h localhost --username=postgres --password user-api;
```
2. Указать настройки подключения в `application.properties`;
3. Запустить через `./gradlew bootRun` или через IDE.

## 📫 Пример запроса (Postman)

```http
POST /api/users
Authorization: Basic admin:admin
Content-Type: multipart/form-data
Body:
  user: {JSON-строка с данными}
  photo: файл изображения
```
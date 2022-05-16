# Площадка для организаторов мероприятий

## Heroku Demo
https://tfs-eventus.herokuapp.com/docs/index.html
https://tfs-eventus.herokuapp.com/docs/docs.yaml

## MVP (предварительно)

- Сообщество (организации или группы по интересам)
- Мероприятие (порождаются сообществами как конкретный элемент в разрезе календарной шкалы)
- Пользователь (подписываются на сообщества, участвуют в мероприятиях, администрируют сообщества)
- Отзыв (порождаются пользователями на мероприятия)
- Уведомление (рассылка уведомлений о новом мероприятии в сообществе пользователям подписчикам)
- Тег (поиск сообществ)

## Технологический стек

- Scala
- ZIO 2.X
- Tapir via ZIO HTTP
- Quill 
- Postgres
- Flyway

## Roadmap

✓ Каркас приложения на основе простой сущности (событие)
· Определение MVP
· Реализация MVP
· Рассылки уведомлений (??? - мыло и/или телега) (??? - сервис внутри приложения или отдельный микросервис)
· Поиск

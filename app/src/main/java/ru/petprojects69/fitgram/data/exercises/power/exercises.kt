package ru.petprojects69.fitgram.data.exercises.power

import ru.petprojects69.fitgram.R
import ru.petprojects69.fitgram.domain.entity.exercises.ExerciseEntity
import ru.petprojects69.fitgram.domain.entity.exercises.PowerExerciseEntity

val benchPress = PowerExerciseEntity(
    exercise = ExerciseEntity(
        name = "Жим штанги лежа",
        description = "Является базовым упражнением и лучшим для набора мышечной массы и силы. " +
                "В данном упражнении участвую следующие группы мышц: " +
                "мышцы груди, трицепс, передние дельтовидные.",
        poster = R.drawable.ex_bench_press,
        location = "Спортзал",
        muscleGroup = "Грудь"
    )
)

val squat = PowerExerciseEntity(
    exercise = ExerciseEntity(
        name = "Присед",
        description = "Приседания — одно из самых лучших и ходовых базовых упражнений в " +
                "бодибилдинге, кроссфите, пауэрлифтинге и у рядовых ЗОЖников. " +
                "Невозможно представить тренировочную программу именно без классических приседаний " +
                "со штангой.",
        poster = R.drawable.ex_squat,
        location = "Спортзал",
        muscleGroup = "Ноги"
    )
)

val deadLift = PowerExerciseEntity(
    exercise = ExerciseEntity(
        name = "Становая тяга",
        description = "Становая тяга является незаменимым упражнением для ударного наращивания " +
                "массы, силы и мощи мышц спины и ног. Она замечательно укрепляет все мышцы, " +
                "которые держат позвоночник: именно от их силы зависит, какой вес Вы сможете " +
                "взять в других упражнениях, без риска заработать травму.",
        poster = R.drawable.ex_deadlift,
        location = "Спортзал",
        muscleGroup = "Ноги, спина"
    )
)

val lunges = PowerExerciseEntity(
    exercise = ExerciseEntity(
        name = "Выпады",
        description = "Выпады – это упражнение, которое поможет развить ягодицы и придать им " +
                "красивую форму. Имейте в виду, что, не смотря на то, что в " +
                "выпадах также работают и квадрицепсы с бицепсами бедра, нарастить большую " +
                "мышечную массу с их помощью Вы не сможете.",
        poster = R.drawable.ex_lunges,
        location = "Спортзал",
        muscleGroup = "Ноги"
    )
)

val militaryPress = PowerExerciseEntity(
    exercise = ExerciseEntity(
        name = "Жим штанги стоя",
        description = "Это упражнение поможет расширить плечи и придать им более объёмную и " +
                "выразительную форму. Помимо этого, жим стоя поможет развить взрывную силу " +
                "всех мышц торса.",
        poster = R.drawable.ex_military_press,
        location = "Спортзал",
        muscleGroup = "Плечи"
    )
)

val bicepsCurl = PowerExerciseEntity(
    exercise = ExerciseEntity(
        name = "Подъем штанги на бицепс",
        description = "Общеизвестный факт - это лучшее упражнение для объёма и силы бицепса.",
        poster = R.drawable.ex_biceps_curl,
        location = "Спортзал",
        muscleGroup = "Руки"
    )
)

val tricepsExtension = PowerExerciseEntity(
    exercise = ExerciseEntity(
        name = "Разгибания рук в блочном тренажере",
        description = "Разгибания рук в блочном тренажере отлично нагружают трицепс",
        poster = R.drawable.ex_triceps_extension,
        location = "Спортзал",
        muscleGroup = "Руки"
    )
)

val barbellPull = PowerExerciseEntity(
    exercise = ExerciseEntity(
        name = "ТЯга штанги в наклоне",
        description = "Данное упражнение нагружает мышцу средней части спины и " +
                "является чуть ли не лучшим упражнением для «добавления толщины» " +
                "верхней части широчайших мышц, нижним и средним частям трапеций, " +
                "а также ромбовидной мышце.",
        poster = R.drawable.ex_barbell_pull,
        location = "Спортзал",
        muscleGroup = "Спина"
    )
)

val pushUps = PowerExerciseEntity(
    exercise = ExerciseEntity(
        name = "Отжимания",
        description = "Отжимания – это то упражнение, которое должно быть в программе, " +
                "как новичка, так и профессионала. Именно они могут помочь добиться " +
                "накачанного торса, крепкого пресса и мускулистых рук.",
        poster = R.drawable.ex_pushup,
        location = "Спотзал, дом, улица",
        muscleGroup = "Грудь"
    )
)

val pullUps = PowerExerciseEntity(
    exercise = ExerciseEntity(
        name = "Подтягивания",
        description = "Подтягивания являются эффективнейшим упражнением для развития широчайших" +
                " мышц. Лучшего упражнения для их «прокачки» не найти. Это упражнение должно " +
                "стать неотъемлемой частью Вашей тренировки спины.",
        poster = R.drawable.ex_pullup,
        location = "Спотзал, дом, улица",
        muscleGroup = "Спина, руки"
    )
)
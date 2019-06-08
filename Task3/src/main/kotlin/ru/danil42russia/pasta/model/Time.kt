package ru.danil42russia.pasta.model

enum class Time(val abbreviation: String) {
    TEN_MINUTE("10M"),
    ONE_HOUR("1H"),
    THREE_HOUR("3H"),
    ONE_DAY("1D"),
    ONE_WEEK("1W"),
    ONE_MONTH("1M"),
    NEVER("N");
}
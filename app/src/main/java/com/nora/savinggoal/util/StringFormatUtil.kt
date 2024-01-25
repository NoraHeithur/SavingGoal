package com.nora.savinggoal.util

object StringFormatUtil {
    fun toThousandsFormat(number: Int): String {
        return String.format("%,d", number)
    }

    fun twoDecimalPos(number: Double): Float {
        return String.format("%.2f", number).toFloat()
    }
}
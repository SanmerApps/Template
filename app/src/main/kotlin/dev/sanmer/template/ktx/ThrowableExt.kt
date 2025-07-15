package dev.sanmer.template.ktx

val Throwable.messageOrName: String
    inline get(): String = message ?: javaClass.name
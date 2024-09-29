package net.dixq.readingflashcard

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
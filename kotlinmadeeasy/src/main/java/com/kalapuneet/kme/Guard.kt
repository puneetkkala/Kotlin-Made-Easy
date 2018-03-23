package com.kalapuneet.kme

class Guard {
    private lateinit var e: Throwable

    operator fun invoke(body: () -> Guard): Guard {
        return try {
            body()
        } catch (e: Throwable) {
            this.e = e
            e.printStackTrace()
            failure()
        } finally {
            always()
        }
    }

    fun failure(body: (e: Throwable) -> Unit = {}): Guard {
        body(e)
        return this
    }

    fun always(body: () -> Unit = {}) {
        body()
    }
}

fun guard(init: Guard.() -> Unit): Guard {
    val guard = Guard()
    guard.init()
    return guard
}
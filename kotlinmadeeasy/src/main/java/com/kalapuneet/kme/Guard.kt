package com.kalapuneet.kme

import android.util.Log

class Guard {
    private lateinit var fail: (e: Throwable) -> Unit
    private lateinit var alw: () -> Unit

    operator fun invoke(guard: () -> Unit) {
        try {
            guard.invoke()
        } catch (e: Throwable) {
            e.printStackTrace()
            if (::fail.isInitialized) {
                fail.invoke(e)
            } else {
                Log.e("Guard", "Fail is not initialized")
                return
            }
        } finally {
            if (::alw.isInitialized) {
                alw.invoke()
            } else {
                Log.e("Guard", "Alw is not initialized")
            }
        }
    }

    fun failure(body: (e: Throwable) -> Unit): Guard {
        this.fail = body
        return this
    }

    fun always(body: () -> Unit): Guard {
        this.alw = body
        return this
    }
}

fun guard(init: Guard.() -> Unit): Guard {
    val guard = Guard()
    guard.init()
    return guard
}
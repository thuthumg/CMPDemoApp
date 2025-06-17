package com.ttm.cmpdemoapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
package com.necatisozer.common.exception

class ServerError(val code: Int, override val message: String? = null, val body: Any? = null) : RuntimeException()
package com.nullnull.portfolio.testutil

fun setId(entity: Any, id: Long) {
    val field = findField(entity.javaClass, "id")
        ?: throw NoSuchFieldException("Field 'id' not found in ${entity.javaClass.name} hierarchy")

    field.isAccessible = true
    field.set(entity, id)
}

private fun findField(clazz: Class<*>, name: String): java.lang.reflect.Field? {
    var current: Class<*>? = clazz
    while (current != null && current != Any::class.java) {
        try {
            return current.getDeclaredField(name)
        } catch (_: NoSuchFieldException) {
            current = current.superclass
        }
    }
    return null
}
package br.duayres.app.infra.utils

import kotlin.reflect.KMutableProperty
import kotlin.reflect.KProperty
import kotlin.reflect.full.isSupertypeOf
import kotlin.reflect.full.memberProperties

object CopyObjectUtils {
  fun <T : Any, R : Any> T.copyPropsFrom(fromObject: R, vararg props: KProperty<*>) {
    val mutableProps = this::class.memberProperties.filterIsInstance<KMutableProperty<*>>()
    val sourceProps = if (props.isEmpty()) fromObject::class.memberProperties else props.toList()

    mutableProps.forEach { targetProp ->
      sourceProps.find {
        it.name == targetProp.name && targetProp.returnType.isSupertypeOf(it.returnType)
      }?.let { matchingProp ->
        targetProp.setter.call(this, matchingProp.getter.call(fromObject))
      }
    }
  }
}
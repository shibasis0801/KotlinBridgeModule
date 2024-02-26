package dev.shibasis.kotlin.bridge

import kotlin.experimental.ExperimentalObjCName
import kotlin.native.ObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName("KotlinBridgeModule", exact = true)
object KotlinBridgeModule {
    fun syncBlockingFunction() = "syncBlockingFunction"
    fun normalAsyncFunction(callback: (Any) -> Unit) {
        callback("normalAsyncFunction")
    }
    fun promiseFunction(resolve: (Any) -> Unit, reject: (Any) -> Unit) {
        resolve("promiseFunction")
        // reject("promiseFunctionError")
    }
}
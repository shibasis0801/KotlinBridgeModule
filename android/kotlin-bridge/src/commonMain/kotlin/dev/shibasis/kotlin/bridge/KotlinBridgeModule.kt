package dev.shibasis.kotlin.bridge

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
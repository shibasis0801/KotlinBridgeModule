package dev.shibasis.kotlin.bridge

import dev.shibasis.kotlin.react.RCTBridgeModuleProtocol
import dev.shibasis.kotlin.react.RCTBridgeModuleProtocolMeta
import dev.shibasis.kotlin.react.RCTMethodInfo
import dev.shibasis.kotlin.react.RCTModuleMethod
import dev.shibasis.kotlin.react.RCTPromiseRejectBlock
import dev.shibasis.kotlin.react.RCTPromiseResolveBlock
import dev.shibasis.kotlin.react.RCTResponseSenderBlock
import kotlinx.cinterop.Arena
import kotlinx.cinterop.alloc
import kotlinx.cinterop.cstr
import kotlinx.cinterop.ptr
import platform.darwin.NSObject
import kotlin.experimental.ExperimentalObjCName
@OptIn(ExperimentalObjCName::class)
@ObjCName("DarwinBridgeModule", exact = true)
class DarwinBridgeModule: NSObject(), RCTBridgeModuleProtocol {
    companion object: NSObject(), RCTBridgeModuleProtocolMeta {
        override fun moduleName() = "DarwinBridgeModule"
    }
    private val arena = Arena()
    fun close() {
        arena.clear()
    }

    init {

    }

    @OptIn(ExperimentalObjCName::class)
    @ObjCName("syncBlockingFunction")
    fun syncBlockingFunction() = "syncBlockingFunction"

    @OptIn(ExperimentalObjCName::class)
    @ObjCName("normalAsyncFunction")
    fun normalAsyncFunction(callback: RCTResponseSenderBlock) {
        if (callback != null)
            callback(listOf("normalAsyncFunction"))
    }

    @OptIn(ExperimentalObjCName::class)
    @ObjCName("promiseFunction")
    fun promiseFunction(resolve: RCTPromiseResolveBlock, reject: RCTPromiseRejectBlock) {
        resolve?.invoke("promiseFunction")
        // reject("promiseFunctionError")
    }

    override fun methodsToExport(): List<*> {
        val syncMethodInfo = arena.alloc<RCTMethodInfo>()
        syncMethodInfo.isSync = true
        syncMethodInfo.objcName = "syncBlockingFunction".cstr.getPointer(arena)
        val syncMethod = RCTModuleMethod(syncMethodInfo.ptr, this.`class`())

        val asyncMethodInfo = arena.alloc<RCTMethodInfo>()
        asyncMethodInfo.isSync = false
        asyncMethodInfo.objcName = "normalAsyncFunction:(RCTResponseSenderBlock)callback".cstr.getPointer(arena)
        val asyncMethod = RCTModuleMethod(asyncMethodInfo.ptr, this.`class`())

        val promiseMethodInfo = arena.alloc<RCTMethodInfo>()
        promiseMethodInfo.isSync = false
        promiseMethodInfo.objcName = "promiseFunction:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject".cstr.getPointer(arena)
        val promiseMethod = RCTModuleMethod(promiseMethodInfo.ptr, this.`class`())

        return listOf(
            syncMethod,
            asyncMethod,
            promiseMethod
        )
    }
}

fun getModule(): RCTBridgeModuleProtocol = DarwinBridgeModule() as RCTBridgeModuleProtocol
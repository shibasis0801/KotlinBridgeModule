package dev.shibasis.kotlin.bridge

import dev.shibasis.kotlin.react.BridgeModuleProtocol
import dev.shibasis.kotlin.react.BridgeModuleProtocolMeta
import dev.shibasis.kotlin.react.RCTBridgeMethodProtocol
import dev.shibasis.kotlin.react.RCTBridgeModuleProtocol
import dev.shibasis.kotlin.react.RCTBridgeModuleProtocolMeta
import dev.shibasis.kotlin.react.RCTMethodInfo
import dev.shibasis.kotlin.react.RCTModuleMethod
import dev.shibasis.kotlin.react.RCTPromiseRejectBlock
import dev.shibasis.kotlin.react.RCTPromiseResolveBlock
import dev.shibasis.kotlin.react.RCTResponseSenderBlock
import kotlinx.cinterop.Arena
import kotlinx.cinterop.ExportObjCClass
import kotlinx.cinterop.alloc
import kotlinx.cinterop.cstr
import kotlinx.cinterop.ptr
import platform.darwin.NSObject
import kotlin.experimental.ExperimentalObjCName


@ExportObjCClass
@OptIn(ExperimentalObjCName::class)
@ObjCName("DarwinBridgeModule", exact = true)
class DarwinBridgeModule: NSObject(), BridgeModuleProtocol {
    companion object: NSObject(), BridgeModuleProtocolMeta  {
        override fun moduleName() = "DarwinBridgeModule"
    }
    private val arena = Arena()
    fun close() {
        arena.clear()
    }

    override fun syncBlockingFunction() = "Kotlin: syncBlockingFunction"

    override fun normalAsyncFunction(callback: RCTResponseSenderBlock) {
        if (callback != null)
            callback(listOf("Kotlin: normalAsyncFunction"))
    }

    override fun promiseFunction(resolve: RCTPromiseResolveBlock, reject: RCTPromiseRejectBlock) {
        resolve?.invoke("Kotlin: promiseFunction")
        // reject("promiseFunctionError")
    }

    override fun methodsToExport(): List<RCTBridgeMethodProtocol> {
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

fun getModule(): RCTBridgeModuleProtocol = DarwinBridgeModule()


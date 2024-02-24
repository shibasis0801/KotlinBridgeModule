#import "NormalBridgeModule.h"
#import <React/RCTBridge.h>
#import <React/RCTBridge+Private.h>
#import <React/RCTBridgeMethod.h>
#import <React/RCTModuleMethod.h>


@implementation NormalBridgeModule
void RCTRegisterModule(Class);
+(NSString *)moduleName
{
  return @"NormalBridgeModule";
}
+(void)load
{
  RCTRegisterModule(self);
}
- (NSString *)syncBlockingFunction {
  return @"NormalBridgeModule";
}

- (void)normalAsyncFunction:(RCTResponseSenderBlock)callback
{
  callback(@[ @"Result from normalAsyncFunction" ]);
}

- (void)promiseFunction:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject
{
  resolve(@"Result from promiseFunction");
}

- (NSArray<id<RCTBridgeMethod>> *)methodsToExport {
  const char *syncBlockingSelector = "syncBlockingFunction";
  RCTMethodInfo *syncMethodInfo = new RCTMethodInfo{.objcName = syncBlockingSelector, .isSync = YES};
  id syncMethod = [[RCTModuleMethod alloc] initWithExportedMethod:syncMethodInfo moduleClass:[self class]];
  
  const char *normalAsyncFunctionSelector = "normalAsyncFunction:(RCTResponseSenderBlock)callback";
  RCTMethodInfo *callbackMethodInfo = new RCTMethodInfo{.objcName = normalAsyncFunctionSelector, .isSync = NO};
  id callbackMethod = [[RCTModuleMethod alloc] initWithExportedMethod:callbackMethodInfo moduleClass:[self class]];
  
  const char *promiseFunctionSelector = "promiseFunction:(RCTPromiseResolveBlock)resolve reject:(RCTPromiseRejectBlock)reject";
  RCTMethodInfo *promiseMethodInfo = new RCTMethodInfo{.objcName = promiseFunctionSelector, .isSync = NO};
  id promiseMethod = [[RCTModuleMethod alloc] initWithExportedMethod:promiseMethodInfo moduleClass:[self class]];

  
  return @[
    syncMethod,
    callbackMethod,
    promiseMethod
  ];
}

@end

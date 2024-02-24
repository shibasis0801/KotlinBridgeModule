#import "NormalBridgeModule.h"

@implementation NormalBridgeModule

RCT_EXPORT_MODULE();

RCT_EXPORT_BLOCKING_SYNCHRONOUS_METHOD(syncBlockingFunction)
{
  return @"Result from syncBlockingFunction";
}

RCT_EXPORT_METHOD(normalAsyncFunction:(RCTResponseSenderBlock)callback)
{
  // Perform some asynchronous operation
  dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
    // Simulate a delay
    [NSThread sleepForTimeInterval:2.0];
    
    // Callback with some result
    callback(@[@"Result from normalAsyncFunction"]);
  });
}

RCT_EXPORT_METHOD(promiseFunction:(RCTPromiseResolveBlock)resolve rejecter:(RCTPromiseRejectBlock)reject)
{
  dispatch_async(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0), ^{
    // Simulate a delay
    [NSThread sleepForTimeInterval:2.0];
    
    resolve(@"Result from promiseFunction");
    
    // In case of an error, you can reject the promise
    // reject(@"error_code", @"Error message", error);
  });
}

@end

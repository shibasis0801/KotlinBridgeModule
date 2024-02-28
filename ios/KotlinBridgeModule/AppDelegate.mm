#import "AppDelegate.h"
#import <React/RCTBundleURLProvider.h>
#import "NormalBridgeModule.h"
#import <kotlin_bridge/kotlin_bridge.h>

#import <objc/runtime.h>

void listMethodsForClass(Class cls) {
    unsigned int methodCount = 0;
 
    Method *methods = class_copyMethodList(cls, &methodCount);

    for (unsigned int i = 0; i < methodCount; i++) {
        Method method = methods[i];
        SEL selector = method_getName(method);
        const char *name = sel_getName(selector);
        NSLog(@"%s:Method: %s",  class_getName(cls), name);
    }

    free(methods);
}

typedef RCTModuleMethod *(^RCTModuleCreatorBlock)(RCTMethodInfo *methodInfo);

@implementation AppDelegate

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
  self.moduleName = @"KotlinBridgeModule";
  // You can add your custom initial props in the dictionary below.
  // They will be passed down to the ViewController used by React Native.
  self.initialProps = @{};

//  NSString *result = [[KotlinBridgeModule shared] syncBlockingFunction ];
//  NSLog(@"Result: %@", result);

  id normalModule = [ NormalBridgeModule new ];
  
//  listMethodsForClass(object_getClass(nativeModule));
  listMethodsForClass(object_getClass(normalModule));

  return [super application:application didFinishLaunchingWithOptions:launchOptions];
}

- (NSArray<id<RCTBridgeModule>> *)extraModulesForBridge:(RCTBridge *)bridge
{
  return @[[Kotlin_bridgeDarwinBridgeModuleKt getModule]];

}

- (NSURL *)sourceURLForBridge:(RCTBridge *)bridge
{
  return [self getBundleURL];
}

- (NSURL *)getBundleURL
{
#if DEBUG
  return [[RCTBundleURLProvider sharedSettings] jsBundleURLForBundleRoot:@"index"];
#else
  return [[NSBundle mainBundle] URLForResource:@"main" withExtension:@"jsbundle"];
#endif
}

@end
